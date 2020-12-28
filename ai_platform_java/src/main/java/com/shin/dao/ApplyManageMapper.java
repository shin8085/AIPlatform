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
}
