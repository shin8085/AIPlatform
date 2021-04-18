package com.shin.controller;

import com.shin.pojo.UserInfo;
import com.shin.service.UserInfoService;
import com.shin.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Resource
    UserInfoService userInfoService;

    /**
     * 获取用户信息
     * @param username 用户名
     * @return 用户信息
     */
    @RequestMapping("/getUserInfo")
    public Result getUserInfo(String username){
        UserInfo userInfo = userInfoService.getUserInfo(username);
        return Result.success("success",userInfo);
    }

    /**
     * 更新用户信息
     * @param userInfo 用户信息
     * @return 更新结果
     */
    @RequestMapping("/updateUserInfo")
    public Result updateUserInfo(@RequestBody UserInfo userInfo){
        System.out.println(userInfo);
        int result = userInfoService.updateUserInfo(userInfo);
        if(result==0){
            return Result.error("更新失败!");
        }else{
            return Result.success("更新成功!");
        }
    }

    /**
     * 获取用户ai接口调用此时
     * @param username 用户名
     * @return 各ai接口调用次数
     */
    @RequestMapping("/getUserInvokingCount")
    public Result getUserInvokingCount(String username){
        List<Map<Object, Object>> invokingCount = userInfoService.getUserInvokingCountByUsername(username);
        return Result.success("success",invokingCount);
    }
}
