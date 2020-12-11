package com.shin.controller;

import com.shin.utils.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

@RestController
@RequestMapping("/apply")
public class ApplyController {

    /**临时图片真实地址*/
    @Value("${image.upload.tmp-path}")
    private String tempImagePath;

    /**临时图片显示相对地址*/
    @Value("${image.upload.tmp-path.relative}")
    private String tempImageRelativePath;

    /**
     * 年龄检测
     * @param file 图片
     * @return Result
     * @throws IOException
     */
    @RequestMapping("/age_estimation")
    public Result ageEstimation(MultipartFile file) throws IOException {
        BASE64Encoder base64Encoder=new BASE64Encoder();
        String base64=base64Encoder.encode(file.getBytes());
        RestTemplate restTemplate=new RestTemplate();
        Map<String,String> request=new HashMap<>();
        request.put("baseimg",base64);
        Map<String,String> resultMap = restTemplate.postForObject("http://lggsoft.vicp.net:8000/age/age_estimation", request, Map.class);
        System.out.println(resultMap);
        if(resultMap!=null){
            Object boxes = resultMap.get("boxes");
            Object result=resultMap.get("result");
            List<List<Object>> points=(List<List<Object>>)boxes;
            List<List<Object>> ages=(List<List<Object>>)result;
            if(points.isEmpty()){
                return Result.error("未检测到人脸");
            }
            BufferedImage image = ImageIO.read(file.getInputStream());
            Graphics graphics = image.getGraphics();
            for(int i=0;i<points.size();i++){
                int x1=(int)points.get(i).get(0);
                int y1=(int)points.get(i).get(1);
                double age=(double)ages.get(i).get(0);
                graphics.setFont(new Font("SimSun",Font.BOLD,25));
                graphics.drawString(String.valueOf((int)age)+"岁",x1,y1);
            }
            long time = new Date().getTime();
            File outputFile = new File(tempImagePath +time+".jpg");
            ImageIO.write(image,"jpg",outputFile);
            return Result.success(tempImageRelativePath +time+".jpg");
        }
        return Result.error("未检测到人脸");
    }
}
