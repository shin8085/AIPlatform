package com.shin.server;

import com.shin.pojo.User;

public interface UserService {

    /**
     * 增加用户
     * @param user 用户
     */
    void addUser(User user);

    /**
     * 注册用户
     * @param user 用户
     * @return boolean 注册成功返回ture，失败返回false
     */
    boolean registerUser(User user);
}
