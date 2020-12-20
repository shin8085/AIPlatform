package com.shin.aop;

import com.shin.pojo.User;
import com.shin.service.InvokingCountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//@Aspect
//@Component
public class ApplyAspect {

    @Resource
    InvokingCountService invokingCountService;

    /**
     * 定义切入点
     */
    @Pointcut("execution(public * com.shin.controller.ApplyController.*(..))")
    public void apply(){}

    /**
     * 调用切入点执行
     */
    @Before("apply()")
    public void doBeforeInvoking(JoinPoint point){
        //获取函数名
        String methodName=point.getSignature().getName();
        //驼峰命名转下划线
        String method_name=methodName.replaceAll("[A-Z]", "_$0").toLowerCase();

        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String username=user.getUsername();
        invokingCountService.countInc(username,method_name);
    }
}
