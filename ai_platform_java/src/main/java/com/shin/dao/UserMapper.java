package com.shin.dao;

import com.shin.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    /**
     * 增加用户
     * @param user 用户
     */
    void addUser(User user);

    /**
     * 通过username获取User
     * @param username 用户名
     * @return User
     */
    User queryUserByName(String username);
}
