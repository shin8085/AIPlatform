package com.shin.service;

import com.shin.pojo.Apply;

import java.util.List;

public interface ApplyManageService {

    /**
     * 获取ai应用信息
     * @return List
     */
    List<Apply> getApplyInfo();

    /**
     * 更新ai应用信息
     * @param apply ai应用实体类
     * @return int
     */
    int updateApplyInfo(Apply apply);
}
