package com.shin.service.Impl;

import com.shin.dao.ApplyManageMapper;
import com.shin.dao.InvokingCountMapper;
import com.shin.service.InvokingCountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class InvokingCountServiceImpl implements InvokingCountService{

    @Resource
    InvokingCountMapper invokingCountMapper;

    @Resource
    ApplyManageMapper applyManageMapper;

    @Override
    public void initCount(String username) {
        invokingCountMapper.initCount(username);
    }

    @Override
    @Transactional
    public void countInc(String username, String column) {
        int r1=invokingCountMapper.countInc(username,column);
        if(r1==0){
            throw new RuntimeException("次数增加失败");
        }
        int r2=applyManageMapper.countInc(column);
        if(r2==0){
            throw new RuntimeException("次数增加失败");
        }
    }
}
