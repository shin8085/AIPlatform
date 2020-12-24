package com.shin.service;

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

    /**
     * 修改密码
     * @param username 用户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return boolean 是否修改成功
     */
    boolean changePassword(String username,String oldPassword,String newPassword);
}
