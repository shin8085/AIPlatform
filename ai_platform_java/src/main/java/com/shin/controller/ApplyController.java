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
     * 年龄检测
     * @param file 图片
     * @return Result
     * @throws IOException
     */
    @RequestMapping("/age_estimation")
    public Result ageEstimation(MultipartFile file) throws IOException {
        return applyService.ageEstimation(file);
    }
}
