package com.shin.controller;

import com.shin.pojo.User;
import com.shin.server.UserService;
import com.shin.utils.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AccountController {

    @Resource
    UserService userService;

    @RequestMapping("/register")
    public Result register(@RequestBody User user){
        boolean result = userService.registerUser(user);
        if(result){
            return Result.success("注册成功");
        }
        else{
            return Result.error("用户名已存在");
        }
    }
}
