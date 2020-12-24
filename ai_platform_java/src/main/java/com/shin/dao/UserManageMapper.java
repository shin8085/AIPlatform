package com.shin.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserManageMapper {

    /**
     * 获取用户信息
     * @return map 用户名、密码、各ai功能调用次数
     */
    List<Map<String,Object>> getUserInfo();
}
