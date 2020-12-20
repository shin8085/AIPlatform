package com.shin.service;

import com.shin.utils.Result;

public interface ApplyService {

    /**
     * 人脸识别
     * @param base64Data 图片的base64编码
     * @return Result
     */
    Result faceDetection(String base64Data);

    /**
     * 年龄检测
     * @param base64Data 图片的base64编码
     * @return Result
     */
    Result ageEstimation(String base64Data);

    /**
     * 目标检测
     * @param base64Data 图片的base64编码
     * @return Result
     */
    Result objectDetection(String base64Data);

    /**
     * 烟雾检测
     * @param base64Data 图片的base64编码
     * @return Result
     */
    Result smokeDetection(String base64Data);

    /**
     * 驾驶员状态检测
     * @param base64Data 图片的base64编码
     * @return Result
     */
    Result distractedDriverDetection(String base64Data);

    /**
     * 口罩检测
     * @param base64Data 图片的base64编码
     * @return Result
     */
    Result maskDetection(String base64Data);

    /**
     * 性别检测
     * @param base64Data 图片的base64编码
     * @return result
     */
    Result genderDetection(String base64Data);
}
