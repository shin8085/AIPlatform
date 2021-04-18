package com.shin.dao;

import com.shin.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserInfoMapper {

    /**
     * 插入用户信息
     * @param userInfo 用户信息
     * @return int
     */
    int insertUserInfo(@Param("userInfo") UserInfo userInfo);

    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return 用户信息
     */
    UserInfo getUserInfoByUsername(String username);

    /**
     * 更细用户信息
     * @param userInfo 用户信息
     * @return int
     */
    int updateUserInfo(@Param("userInfo") UserInfo userInfo);

    /**
     * 根据用户名返回用户ai调用信息
     * @param username 用户名
     * @return 调用次数
     */
    List<Map<Object, Object>> getUserInvokingCountByUsername(String username);
}
