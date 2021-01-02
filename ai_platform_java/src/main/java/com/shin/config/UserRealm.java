package com.shin.config;

import com.shin.dao.UserMapper;
import com.shin.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;


public class UserRealm extends AuthorizingRealm {


    @Resource
    UserMapper userMapper;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("-----------授权-----------");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        info.addStringPermission(user.getIdentity());
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("-----------用户认证-----------");
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        User user = userMapper.queryUserByName(token.getUsername());
        if(user==null){
            return null; //UnknownAccountException
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
