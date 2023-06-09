package com.ts.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EcgFileUtil {
    public static List<Object> EcgFileByXml(String url) throws IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        List<Object> ecgList=new ArrayList<>();
        try{
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(url);
            NodeList component = document.getElementsByTagName("sequence");
            for(int i = 0; i < component.getLength(); i++){
                Element ele = (Element) component.item(i);
                NodeList childNodes= ele.getChildNodes();
                String code="";
                Map<String,Object> map=new HashMap<>();
                for(int j = 0; j < childNodes.getLength(); j++){
                    Node n = childNodes.item(j);
                    if(n.getNodeName().equals("code")){
                        code=n.getAttributes().item(0).getNodeValue();
                        map.put("code",code);
//                        System.out.println(code);
                    }
                    if(n.getNodeName().equals("digits")){
                        String t=n.getTextContent();
                        String[] d=t.split(" ");
                        map.put("data",d);
                    }
                }
                ecgList.add(map);
            }
        }catch (ParserConfigurationException | IOException | SAXException e){
            e.printStackTrace();
        }
        return ecgList;
    }
    public static List<Object> EcgFileByFilena(String url) throws IOException {
        byte[] bytes= createFileItem(url);
        List<Object> ecgList=new ArrayList<>();

        int length = bytes.length;
        if(length <16000){
            List<Short> list=new ArrayList<>();
            for(int i = 0; i< length; i+=2){
                byte byte0 =bytes[i];
                byte byte1 =bytes[i+1];
                short value= (short)(((byte0 & 0xff) << 8) | (byte1 & 0xff));
                list.add(value);
            }
            Map<String,Object> map=new HashMap<>();
            map.put("code","V1");
            map.put("data",list);
            ecgList.add(map);
        }
       else {
            List<Short> v1=new ArrayList<>();
            List<Short> v2=new ArrayList<>();
            List<Short> v3=new ArrayList<>();
            for(int i = 0; i< length; i+=6){
                byte byte0 = bytes[i];
                byte byte1 = bytes[i+1];
                byte byte2 = bytes[i+2];
                byte byte3 = bytes[i+3];
                byte byte4 = bytes[i+4];
                byte byte5 = bytes[i+5];
                short value1= (short)(((byte0 & 0xff) << 8) | (byte1 & 0xff));
                short value2= (short)(((byte2 & 0xff) << 8) | (byte3 & 0xff));
                short value3= (short)(((byte4 & 0xff) << 8) | (byte5 & 0xff));
                v1.add(value1);
                v2.add(value2);
                v3.add(value3);
            }
            Map<String,Object> map=new HashMap<>();
            map.put("code","V1");
            map.put("data",v1);
            ecgList.add(map);

            map=new HashMap<>();
            map.put("code","V2");
            map.put("data",v2);
            ecgList.add(map);

            map=new HashMap<>();
            map.put("code","V3");
            map.put("data",v3);
            ecgList.add(map);
        }
        return ecgList;
    }
    private static byte [] createFileItem(String url) throws IOException {
        InputStream is =null;
        byte [] resultBytes=null;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(50000);
            conn.setConnectTimeout(50000);
            //设置应用程序要从网络连接读取数据
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                is = conn.getInputStream();
                byte [] bytes = new byte[102400];
                int index = 0;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (-1 != (index = is.read(bytes,0,bytes.length))){
                    byteArrayOutputStream.write(bytes,0,index);
                }
                //is.read(bytes)
                resultBytes = byteArrayOutputStream.toByteArray();
                is.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("文件下载失败", e);
        }
        return resultBytes;
    }

    public static void main(String[] args) throws IOException {
        String urlStr="https://file.xiaole-sharp.com:8314/group1/M00/22/21/Cr_IdmN_HV6AHeTNAACvyFYy__4.filena";

        System.out.println(EcgFileByFilena(urlStr));
    }

}
