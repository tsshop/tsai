package com.ts.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ts.common.core.redis.RedisCache;
import com.ts.mvc.domain.payBean.PrecreateBody;
import com.ts.mvc.service.ConfigService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * @author : tsai
 * @date : 2023/5/8
 */
@Component
public class PayUtil {


    @Autowired
    ConfigService configService;

    public String pay(BigDecimal amt, String orderNo,String notifyUrl,String openId) throws Exception{

        JSONObject pay = JSONUtil.parseObj(configService.getByKey("pay"));
        String appId = pay.getStr("appId");
        String appKey = pay.getStr("appKey");
        String mid = pay.getStr("mid");
        String tid = pay.getStr("tid");
        String orderPrefix = pay.getStr("orderPrefix");

        //1. 组建请求报文
        Date now = new Date();
        PrecreateBody reqBody = new PrecreateBody();

        String requestUrl = "https://api-mop.chinaums.com/v1/netpay/wx/unified-order";
        reqBody.setTradeType("JSAPI");
        reqBody.setInstMid("YUEDANDEFAULT");
        reqBody.setMerchantUserId(RandomUtil.randomNumbers(10));
        reqBody.setSubOpenId(openId);

        reqBody.setRequestTimestamp(DateUtil.format(now,"yyyy-MM-dd HH:mm:ss"));
        reqBody.setMerOrderId(orderPrefix+orderNo);
        reqBody.setMid(mid);
        reqBody.setTid(tid);
        reqBody.setTotalAmount(amt.multiply(new BigDecimal(100)).setScale(0,BigDecimal.ROUND_HALF_DOWN).toString());
        reqBody.setNotifyUrl(notifyUrl);

        //2. 获取认证报文，timestamp为当前日期，老旧日期无法请求成功
        String authorization = getAuthorization(appId,appKey,DateUtil.format(now,"yyyMMddHHmmss"), RandomUtil.randomString(16),reqBody.toString());
        System.out.println("authorization:\n"+authorization);

        //3. 发送http请求，并解析返回信息
        String response = request(requestUrl,authorization,reqBody.toString());
        System.out.println("request body:\n"+reqBody);
        System.out.println("requestUrl:\n"+requestUrl);
        System.out.println("response:\n"+response);
        JSONObject jsonObject = JSONUtil.parseObj(response);
        if (!"SUCCESS".equals(jsonObject.getStr("errCode"))){
            throw new RuntimeException("请刷新后重试");
        }

        if (ObjectUtil.isEmpty(jsonObject.get("jsPayRequest"))){
            throw new RuntimeException(jsonObject.get("errMsg").toString());
        }

        return jsonObject.get("jsPayRequest").toString();
    }

    /**
     * 获取签名头
     * @param appid
     * @param appkey
     * @param timestamp 格式:"yyyyMMddHHmmss"
     * @param nonce 随机字符串，
     * @param body 请求体
     * @return authorization 认证报文
     * @throws Exception
     */
    public static String getAuthorization(String appid, String appkey, String timestamp, String nonce, String body) throws Exception {
        byte[] data = body.getBytes("utf-8");
        InputStream is = new ByteArrayInputStream(data);
        String testSH = DigestUtils.sha256Hex(is);
        String s1 = appid+timestamp+nonce+testSH;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(appkey.getBytes("utf-8"),"HmacSHA256"));
        byte[] localSignature = mac.doFinal(s1.getBytes("utf-8"));
        String localSignatureStr = Base64.encodeBase64String(localSignature);
        return  "OPEN-BODY-SIG AppId="+"\""+appid+"\""+", Timestamp="+"\""+timestamp+"\""+", Nonce="+"\""+nonce+"\""+", Signature="+"\""+localSignatureStr+"\"";
    }


    /**
     * 发送http请求
     * @param url 请求url
     * @param authorization 认证报文
     * @param reqBody  请求体
     * @return response
     */
    public static String request(String url, String authorization, String reqBody){
        String response = "";
        PrintWriter out = null;
        BufferedReader in = null;
        try{
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            HttpURLConnection httpUrlConnection = (HttpURLConnection) conn;
            httpUrlConnection.setRequestProperty("Content-Type", "application/json");
            httpUrlConnection.setRequestProperty("authorization",authorization);
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setDoInput(true);
            out = new PrintWriter(httpUrlConnection.getOutputStream());
            out.write(reqBody);
            out.flush();
            httpUrlConnection.connect();
            in = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                response += line;
            }
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (out != null) { out.close();}
                if (in != null) {in.close();}
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return response;
    }

    public String makeSign(Map<String, String> params) {

        JSONObject pay = JSONUtil.parseObj(configService.getByKey("pay"));
        String secretKey = pay.getStr("secretKey");

        String preStr = buildSignString(params); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String text = preStr + secretKey;
        System.out.println("待签名字符串：" + text);
        return DigestUtils.sha256Hex(getContentBytes(text)).toUpperCase();
    }

    public static String buildSignString(Map<String, String> params) {

        if (params == null || params.size() == 0) {
            return "";
        }

        List<String> keys = new ArrayList<>(params.size());

        for (String key : params.keySet()) {
            if ("sign".equals(key))
                continue;
            if (StrUtil.isEmpty(params.get(key)))
                continue;
            keys.add(key);
        }

        Collections.sort(keys);

        StringBuilder buf = new StringBuilder();

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                buf.append(key + "=" + value);
            } else {
                buf.append(key + "=" + value + "&");
            }
        }

        return buf.toString();
    }

    public static byte[] getContentBytes(String content) {
        try {
            return content.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("签名过程中出现错误");
        }
    }

    public static Map<String, String> getRequestParams(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        Map<String, String> params2 = new HashMap<>();
        for (String key : params.keySet()) {
            String[] values = params.get(key);
            if (values.length > 0) {
                params2.put(key, request.getParameter(key));
            }
        }
        return params2;
    }
}
