package com.shin.service;

import com.shin.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserInfoService {

    /**
     * 插入用户信息
     * @param userInfo 用户信息
     * @return int
     */
    int insertUserInfo(UserInfo userInfo);

    /**
     * 获取用户信息
     * @param username 用户名
     * @return 用户信息
     */
    UserInfo getUserInfo(String username);

    /**
     * 更细用户信息
     * @param userInfo 用户信息
     * @return int
     */
    int updateUserInfo(UserInfo userInfo);

    /**
     * 根据用户名返回用户ai调用信息
     * @param username 用户名
     * @return 调用次数
     */
    List<Map<Object,Object>> getUserInvokingCountByUsername(String username);

}
