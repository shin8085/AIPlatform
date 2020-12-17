package com.shin.controller;

import com.shin.service.ApplyService;
import com.shin.utils.Result;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;


@RestController
@RequestMapping("/apply")
public class ApplyController {

    @Resource
    ApplyService applyService;

    /**
     * 人脸识别
     * @param file 图片
     * @return Result
     * @throws IOException
     */
    @RequestMapping("face_recognize")
    public Result faceDetection(MultipartFile file) throws IOException {
        return applyService.faceDetection(file);
    }

    /**
     * 年龄检测
     * @param file 图片
     * @return Result
     * @throws IOException
     */
    @RequestMapping("/age_estimation")
    public Result ageEstimation(MultipartFile file) throws IOException {
        return applyService.ageEstimation(file);
    }

    /**
     * 目标检测
     * @param file 图片
     * @return Result
     */
    @RequestMapping("object_detection")
    public Result objectDetection(MultipartFile file) throws IOException {
        return applyService.objectDetection(file);
    }

    /**
     * 烟雾检测
     * @param file 图片
     * @return Result
     * @throws IOException
     */
    @RequestMapping("/smoke_detection")
    public Result smokeDetection(MultipartFile file) throws IOException {
        return applyService.smokeDetection(file);
    }

    /**
     * 驾驶员状态检测
     * @param file 图片
     * @return Result
     * @throws IOException
     */
    @RequestMapping("/distractedDriverDetection")
    public Result distractedDriverDetection(MultipartFile file) throws IOException {
        return applyService.distractedDriverDetection(file);
    }

    /**
     * 口罩检测
     * @param file 图片
     * @return Result
     * @throws IOException
     */
    @RequestMapping("/mask_detection")
    public Result maskDetection(MultipartFile file) throws IOException {
        return applyService.maskDetection(file);
    }

    /**
     * 性别检测
     * @param file 图片
     * @return result
     */
    @RequestMapping("/gender_detection")
    public Result genderDetection(MultipartFile file) throws IOException {
        return applyService.genderDetection(file);
    }
}
