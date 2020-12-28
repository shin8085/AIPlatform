package com.shin.controller;

import com.shin.service.UserManageService;
import com.shin.utils.Result;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * 修改用户信息
     * @param request 用户名、密码、各ai功能调用次数
     * @return Result
     */
    @RequestMapping("/editUserInfo")
    public Result editUserInfo(@RequestBody Map<String,String> request){
        try{
            userManageService.updateUserInfo(request);
            return Result.success("更新成功");
        }catch (Exception e){
            System.out.println("更新失败");
            return Result.error("更新失败");
        }
    }

    /**
     * 删除用户信息
     * @param request username用户名
     * @return Result
     */
    @RequestMapping("/deleteUserInfo")
    public Result deleteUserInfo(@RequestBody Map<String,String> request){
        if(userManageService.deleteUserInfo(request.get("username"))==0){
            System.out.println("删除用户信息失败");
            return Result.error("删除失败");
        }
        return Result.success("删除成功");
    }
}
