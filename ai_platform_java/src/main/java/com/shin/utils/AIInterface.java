package com.shin.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 接口访问工具类
 */
public class AIInterface {

    /**
     * 通过base64编码访问百度api
     * @param base64Data base64编码
     * @return Map
     */
    static public Map<String,Object> requestBaiduApi(String base64Data,String field){
        RestTemplate restTemplate=new RestTemplate();
        LinkedMultiValueMap<String, String> request=new LinkedMultiValueMap<String,String>();
        request.add("type","face");
        request.add("image",base64Data);
        request.add("image_type","BASE64");
        request.add("max_face_num","10");
        request.add("face_field","age,gender,mask");
        String s = restTemplate.postForObject("https://ai.baidu.com/aidemo", request, String.class);
        System.out.println(s);
        return null;
    }

    /**
     * 通过base64编码
     * @param path 接口路径
     * @param base64Data base64编码
     * @return Map
     */
    static public Map<String,Object> request(String path,String base64Data){
        RestTemplate restTemplate=new RestTemplate();
        Map<String,String> request=new HashMap<>();
        request.put("baseimg",base64Data);
        request.put("people_id","123");
        return restTemplate.postForObject("http://lggsoft.vicp.net:8000"+path, request, Map.class);
    }

    /**
     * 通过图片文件
     * @param path 接口路径
     * @param file 图片文件
     * @return Map
     * @throws IOException io异常
     */
    static public Map<String,Object> request(String path,MultipartFile file) throws IOException {
        BASE64Encoder base64Encoder=new BASE64Encoder();
        String base64 = base64Encoder.encode(file.getBytes());
        RestTemplate restTemplate=new RestTemplate();
        Map<String,String> request=new HashMap<>();
        request.put("baseimg",base64);
        request.put("people_id","123");
        File file1=new File("E:/Temp/test.txt");
        Writer writer=new FileWriter(file1);
        writer.write(base64);
        writer.close();
        return restTemplate.postForObject("http://lggsoft.vicp.net:8000"+path, request, Map.class);
    }
}
