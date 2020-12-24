package com.shin.service.Impl;

import com.shin.dao.UserManageMapper;
import com.shin.service.UserManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserManageServiceImpl implements UserManageService {

    @Resource
    UserManageMapper userManageMapper;

    /**
     * 获取用户信息
     * @return map 用户名、密码、各ai功能调用次数
     */
    @Override
    public List<Map<String, Object>> getUserInfo() {
        return userManageMapper.getUserInfo();
    }
}
