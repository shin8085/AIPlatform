package com.shin.config;

import com.shin.dao.UserMapper;
import com.shin.pojo.User;
import com.shin.server.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;


public class UserRealm extends AuthorizingRealm {


    @Resource
    UserMapper userMapper;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证");
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        User user = userMapper.queryUserByName(token.getUsername());
        if(user==null){
            return null; //UnknownAccountException
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
