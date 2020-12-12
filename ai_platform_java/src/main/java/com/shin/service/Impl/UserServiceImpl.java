package com.shin.service.Impl;

import com.shin.dao.UserMapper;
import com.shin.pojo.User;
import com.shin.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    /**
     * 增加用户
     * @param user 用户
     */
    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    /**
     * 注册用户
     * @param user 用户
     * @return boolean 注册成功返回ture，失败返回false
     */
    @Override
    public boolean registerUser(User user) {
        User user_r = userMapper.queryUserByName(user.getUsername());
        if(user_r==null){ //用户名不存在
            userMapper.addUser(user);
            return true;
        }
        return false;
    }

}
