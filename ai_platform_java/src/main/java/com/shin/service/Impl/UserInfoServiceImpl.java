package com.shin.service.Impl;

import com.shin.dao.UserInfoMapper;
import com.shin.pojo.UserInfo;
import com.shin.service.UserInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    UserInfoMapper userInfoMapper;

    /**
     * 插入用户信息
     * @param userInfo 用户信息
     * @return int
     */
    @Override
    public int insertUserInfo(UserInfo userInfo){
        return userInfoMapper.insertUserInfo(userInfo);
    }

    /**
     * 获取用户信息
     * @param username 用户名
     * @return 用户信息
     */
    @Override
    public UserInfo getUserInfo(String username) {
        return userInfoMapper.getUserInfoByUsername(username);
    }

    /**
     * 更细用户信息
     * @param userInfo 用户信息
     * @return int
     */
    @Override
    public int updateUserInfo(UserInfo userInfo) {
        return userInfoMapper.updateUserInfo(userInfo);
    }

    /**
     * 根据用户名返回用户ai调用信息
     * @param username 用户名
     * @return 调用次数
     */
    @Override
    public List<Map<Object, Object>> getUserInvokingCountByUsername(String username) {
        return userInfoMapper.getUserInvokingCountByUsername(username);
    }
}
