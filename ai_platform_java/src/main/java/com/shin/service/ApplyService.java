package com.shin.service;

import com.shin.utils.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ApplyService {

    /**
     * 年龄检测
     * @param file 图片
     * @return Result
     * @throws IOException
     */
    Result ageEstimation(MultipartFile file) throws IOException;

    /**
     * 性别检测
     * @param file 图片
     * @return result
     */
    Result genderDetection(MultipartFile file) throws IOException;
}
