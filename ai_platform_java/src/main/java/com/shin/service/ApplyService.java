package com.shin.service;

import com.shin.utils.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ApplyService {

    /**
     * 人脸识别
     * @param file 图片
     * @return Result
     * @throws IOException
     */
    Result faceDetection(MultipartFile file) throws IOException;

    /**
     * 年龄检测
     * @param file 图片
     * @return Result
     * @throws IOException
     */
    Result ageEstimation(MultipartFile file) throws IOException;

    /**
     * 烟雾检测
     * @param file 图片
     * @return Result
     * @throws IOException
     */
    Result smokeDetection(MultipartFile file) throws IOException;

    /**
     * 性别检测
     * @param file 图片
     * @return result
     */
    Result genderDetection(MultipartFile file) throws IOException;
}
