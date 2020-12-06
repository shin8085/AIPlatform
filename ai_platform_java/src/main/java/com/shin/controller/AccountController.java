package com.shin.controller;

import com.shin.pojo.User;
import com.shin.server.UserService;
import com.shin.utils.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class AccountController {

    @Resource
    UserService userService;

    /**
     * 用户登录
     * @param user 用户
     * @return Result
     */
    @RequestMapping("/login")
    public Result login(@RequestBody User user){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        try {
            subject.login(token); //登录
            Session session= subject.getSession();
            return Result.success("登录成功",session);
        }catch (UnknownAccountException e){
            //用户名不存在
            return Result.error("用户不存在");
        }catch (IncorrectCredentialsException e){
            //密码错误
            return Result.error("密码错误");
        }
    }

    /**
     * 用户注册
     * @param user 用户
     * @return Result
     */
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

    /**
     * 检查session是否正确
     * @param sessionId 会话
     * @return Result
     */
    @RequestMapping("/checkLoginSession")
    public Result checkLoginSession(@RequestParam("loginSessionId") String sessionId){
        Subject subject = SecurityUtils.getSubject();
        if(sessionId.equals(subject.getSession().getId())){
            return Result.success();
        }
        return Result.error();
    }

    /**
     * 退出登录
     */
    @RequestMapping("/logout")
    public void logout(){
        Subject subject=SecurityUtils.getSubject();
        subject.logout();
    }
}
