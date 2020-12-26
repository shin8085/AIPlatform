package com.shin.dao;

import com.shin.pojo.InvokingCount;
import com.shin.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserManageMapper {

    /**
     * 获取用户信息
     * @return map 用户名、密码、各ai功能调用次数
     */
    List<Map<String,Object>> getUserInfo();

    /**
     * 更新用户
     * @param oldUsername 原用户名
     * @param user 用户
     * @return int
     */
    int updateUser(String oldUsername, User user);

    /**
     * 更新ai调用次数
     * @param invokingCount ai次数实体类
     * @return int
     */
    int updateInvokingCount(InvokingCount invokingCount);

    /**
     * 根据用户名删除用户
     * @param username 用户名
     */
    int deleteUser(String username);

    /**
     * 根据用户名删除ai调用次数统计
     * @param username 用户名
     */
    int deleteInvokingCount(String username);
}
