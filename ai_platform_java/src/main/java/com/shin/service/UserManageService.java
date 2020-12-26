package com.shin.service;

import java.util.List;
import java.util.Map;

public interface UserManageService {

    /**
     * 获取用户信息
     * @return map 用户名、密码、各ai功能调用次数
     */
    List<Map<String,Object>> getUserInfo();

    /**
     * 修改用户信息
     * @param request 用户名、密码、各ai功能调用次数
     * @return
     */
    void updateUserInfo(Map<String,String> request);

    /**
     * 根据用户名删除用户信息
     * @param username username
     */
    int deleteUserInfo(String username);
}
