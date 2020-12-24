package com.shin.controller;

import com.shin.service.UserManageService;
import com.shin.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userManage")
public class UserManageController {

    @Resource
    UserManageService userManageService;

    /**
     * 获取用户信息
     * @return Result 用户名、密码、各ai功能调用次数
     */
    @RequestMapping("/getUserInfo")
    public Result getUserInfo(){
        List<Map<String, Object>> userInfo = userManageService.getUserInfo();
        return Result.success("success",userInfo);
    }
}
