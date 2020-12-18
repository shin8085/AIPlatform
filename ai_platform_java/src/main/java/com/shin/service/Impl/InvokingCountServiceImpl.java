package com.shin.service.Impl;

import com.shin.dao.InvokingCountMapper;
import com.shin.service.InvokingCountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class InvokingCountServiceImpl implements InvokingCountService{

    @Resource
    InvokingCountMapper invokingCountMapper;

    @Override
    public void initCount(String username) {
        invokingCountMapper.initCount(username);
    }

    @Override
    public void countInc(String username, String column) {
        invokingCountMapper.countInc(username,column);
    }
}
