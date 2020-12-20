package com.shin.controller;

import com.shin.service.ApplyService;
import com.shin.utils.ImageTran;
import com.shin.utils.Result;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/apply")
public class ApplyController {

    @Resource
    ApplyService applyService;

    @RequestMapping("/test")
    public Result test(@RequestBody Map<String,String> map) {
        String base64 = map.get("base64Data");
        RestTemplate restTemplate=new RestTemplate();
        Map<String,String> request=new HashMap<>();
        request.put("baseimg",base64);
        request.put("people_id","123");
        Map<String, Object> resultMap =restTemplate.postForObject("http://lggsoft.vicp.net:8000"+"/face/recognize", request, Map.class);
        System.out.println(resultMap);
        if(resultMap!=null){
            List<List<Object>> boxes=(List<List<Object>>)resultMap.get("boxes");

            if(boxes.isEmpty()){
                return Result.error("未检测到人脸");
            }
            BufferedImage image = ImageTran.base64ToBufferedImage(base64);
            Graphics2D graphics = (Graphics2D) image.getGraphics();
            for(int i=0;i<boxes.size();i++){
                int x1=(int)boxes.get(i).get(0);
                int y1=(int)boxes.get(i).get(1);
                int x2=(int)boxes.get(i).get(2);
                int y2=(int)boxes.get(i).get(3);
                graphics.setStroke(new BasicStroke(3.0f));
                graphics.setColor(Color.red);
                graphics.drawRect(x1,y1,Math.abs(x1-x2),Math.abs(y1-y2));
            }

            return Result.success(ImageTran.BufferedImageToBase64(image));
        }
        return Result.error("未检测到人脸");
    }
    
    /**
     * 人脸识别
     * @param request 请求数据
     * @return Result
     */
    @RequestMapping("/face_recognize")
    public Result faceDetection(@RequestBody Map<String,String> request) {
        return applyService.faceDetection(request.get("base64Data"));
    }

    /**
     * 年龄检测
     * @param request 请求数据
     * @return Result
     */
    @RequestMapping("/age_estimation")
    public Result ageEstimation(@RequestBody Map<String,String> request) {
        return applyService.ageEstimation(request.get("base64Data"));
    }

    /**
     * 目标检测
     * @param request 请求数据
     * @return Result
     */
    @RequestMapping("/object_detection")
    public Result objectDetection(@RequestBody Map<String,String> request){
        return applyService.objectDetection(request.get("base64Data"));
    }

    /**
     * 烟雾检测
     * @param request 请求数据
     * @return Result
     */
    @RequestMapping("/smoke_detection")
    public Result smokeDetection(@RequestBody Map<String,String> request){
        return applyService.smokeDetection(request.get("base64Data"));
    }

    /**
     * 驾驶员状态检测
     * @param request 请求数据
     * @return Result
     */
    @RequestMapping("/distracted_driver_detection")
    public Result distractedDriverDetection(@RequestBody Map<String,String> request){
        return applyService.distractedDriverDetection(request.get("base64Data"));
    }

    /**
     * 口罩检测
     * @param request 请求数据
     * @return Result
     */
    @RequestMapping("/mask_detection")
    public Result maskDetection(@RequestBody Map<String,String> request) {
        return applyService.maskDetection(request.get("base64Data"));
    }

    /**
     * 性别检测
     * @param request 请求数据
     * @return result
     */
    @RequestMapping("/gender_detection")
    public Result genderDetection(@RequestBody Map<String,String> request) {
        return applyService.genderDetection(request.get("base64Data"));
    }
}
