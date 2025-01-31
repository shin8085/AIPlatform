package com.shin.controller;

import com.shin.dao.InvokingCountMapper;
import com.shin.pojo.User;
import com.shin.pojo.UserInfo;
import com.shin.service.InvokingCountService;
import com.shin.service.UserInfoService;
import com.shin.service.UserService;
import com.shin.utils.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AccountController {

    @Resource
    UserService userService;

    @Resource
    InvokingCountService invokingCountService;

    @Resource
    UserInfoService userInfoService;

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
            subject.getSession().setTimeout(24*60*60*1000); //设置session有效时间为24小时
            Session session= subject.getSession();
            User login_user=(User)subject.getPrincipal();
            Map<String,Object> response=new HashMap<>();
            response.put("session",session);
            response.put("identity",login_user.getIdentity());
            return Result.success("登录成功",response);
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
        user.setIdentity("user");
        boolean result = userService.registerUser(user);
        if(result){
            UserInfo userInfo=new UserInfo();
            userInfo.setUsername(user.getUsername());
            userInfo.setJoin_date(new Date(new java.util.Date().getTime()));
            userInfoService.insertUserInfo(userInfo);
            invokingCountService.initCount(user.getUsername()); //初始化ai调用次数
            return Result.success("注册成功");
        }
        else{
            return Result.error("用户名已存在");
        }
    }

    /**
     * 修改密码
     * @param request 用户名username、旧密码oldPassword、新密码newPassword
     * @return Result
     */
    @RequestMapping("/changePassword")
    public Result changePassword(@RequestBody Map<String,String> request){
        boolean r = userService.changePassword(request.get("username"), request.get("oldPassword"), request.get("newPassword"));
        if(r){
            return Result.success("修改成功!");
        }else{
            return Result.error("旧密码错误");
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
