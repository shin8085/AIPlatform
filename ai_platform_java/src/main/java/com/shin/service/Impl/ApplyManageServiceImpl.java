package com.shin.service.Impl;

import com.shin.dao.ApplyManageMapper;
import com.shin.pojo.Apply;
import com.shin.service.ApplyManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ApplyManageServiceImpl implements ApplyManageService {

    @Resource
    ApplyManageMapper applyManageMapper;

    /**
     * 获取ai应用信息
     * @return List
     */
    @Override
    public List<Apply> getApplyInfo() {
        return applyManageMapper.getAllApply();
    }

    @Override
    public int updateApplyInfo(Apply apply) {
        return applyManageMapper.updateApply(apply);
    }
}
