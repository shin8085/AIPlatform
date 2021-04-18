package com.shin;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;


@SpringBootTest
class AiPlatformJavaApplicationTests {

    @Test
    void contextLoads() {
        RestTemplate restTemplate=new RestTemplate();
        LinkedMultiValueMap<String, String> map= new LinkedMultiValueMap<String,String>();
        map.add("type","face");
        map.add("image","");
        map.add("image_type","BASE64");
        map.add("max_face_num","10");
        map.add("face_field","age,gender,mask");
        String s = restTemplate.postForObject("https://ai.baidu.com/aidemo", map, String.class);
        System.out.println(s);
    }

}
