package com.shin.utils;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class AIInterface {

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
