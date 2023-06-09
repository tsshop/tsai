package com.ts.util;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.ts.ai.domain.SmsConfig;
import com.ts.common.enums.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class SmsUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    public Boolean send(String phone, String code) throws  Exception {
        SmsConfig config = (SmsConfig) redisTemplate.opsForValue().get("sms");
        if (SystemConstant.SMSTypeEnum.ALI_DA_YU.getCode().equals(config.getType())){
            SendSmsResponse sendSmsResponse = sendAliDaYuSms(config, phone, code);
            return "OK".equals(sendSmsResponse.getCode());
        }else if (SystemConstant.SMSTypeEnum.YUN_JI.getCode().equals(config.getType())){
            JSONObject jsonObject = sendYunJiSms(config, phone, code);
            return "200".equals(jsonObject.getString("code"));
        }
        return false;
    }

    public SendSmsResponse sendAliDaYuSms(SmsConfig config, String phone, String code) throws ClientException {

        JSONObject jsonObject = JSONObject.parseObject(config.getConfig());

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", jsonObject.getString("accessKeyId"), jsonObject.getString("accessKeySecret"));
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(jsonObject.getString("signname"));
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(jsonObject.getString("templatecode"));
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\""+code+"\"}");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        System.out.println("sendSmsResponse:" + sendSmsResponse);

        return sendSmsResponse;
    }

    public JSONObject sendYunJiSms(SmsConfig config, String phone, String code) throws ClientException {

        JSONObject jsonObject = JSONObject.parseObject(config.getConfig());

        JSONObject jsonBody = new JSONObject();

        jsonBody.put("accessKey", jsonObject.getString("accessKey"));
        jsonBody.put("accessSecret", jsonObject.getString("accessSecret"));
        jsonBody.put("signCode", jsonObject.getString("signCode"));
        jsonBody.put("templateCode", jsonObject.getString("templateCode"));
        jsonBody.put("mobile", phone);

        String url = "https://api.juncyun.com/api/msgService/sendSms";

        // 变量参数用map存
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(code));
        // 变量参数map存入json对象
        jsonBody.put("params", params);
        jsonBody.put("msgType", 1);
        System.out.println(jsonBody);

        JSONObject result = doPost(url, jsonBody);

        System.out.println("result:" + result);

        return  result;
    }

    // 发送短信
    public static JSONObject doPost(String url, JSONObject json) {
        JSONObject response;
        try {
            String result = HttpUtil.post(url, json.toString());// 返回json格式
            response = JSONObject.parseObject(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}