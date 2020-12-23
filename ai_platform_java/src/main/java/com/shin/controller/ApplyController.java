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
    
    /**
     * 人脸识别
     * @param request 请求数据
     * @return Result
     */
    @RequestMapping("/face_recognize")
    public Result faceRecognize(@RequestBody Map<String,String> request) {
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
