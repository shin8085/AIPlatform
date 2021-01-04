package com.shin.dao;

import com.shin.pojo.Apply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ApplyManageMapper {

    /**
     * 获取所有apply
     * @return List
     */
    List<Apply> getAllApply();

    /**
     * 更新ai应用信息
     * @param apply ai应用实体类
     * @return int
     */
    int updateApply(Apply apply);

    /**
     * ai调用次数+1
     * @param apply_name ai应用名
     */
    int countInc(String apply_name);
}
