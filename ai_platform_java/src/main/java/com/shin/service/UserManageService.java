package com.shin.service;

import java.util.List;
import java.util.Map;

public interface UserManageService {

    /**
     * 获取用户信息
     * @return map 用户名、密码、各ai功能调用次数
     */
    List<Map<String,Object>> getUserInfo();
}
