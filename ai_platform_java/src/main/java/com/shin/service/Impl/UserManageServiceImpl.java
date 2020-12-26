package com.shin.service.Impl;

import com.alibaba.fastjson.JSON;
import com.shin.dao.UserManageMapper;
import com.shin.pojo.InvokingCount;
import com.shin.pojo.User;
import com.shin.service.UserManageService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserManageServiceImpl implements UserManageService {

    @Resource
    UserManageMapper userManageMapper;

    /**
     * 获取用户信息
     * @return map 用户名、密码、各ai功能调用次数
     */
    @Override
    public List<Map<String, Object>> getUserInfo() {
        return userManageMapper.getUserInfo();
    }

    /**
     * 修改用户信息
     * @param request 用户名、密码、各ai功能调用次数
     * @return int
     */
    @Override
    @Transactional
    public void updateUserInfo(Map<String, String> request) {
        String oldUsername=request.get("oldUsername");
        User user=JSON.parseObject(JSON.toJSONString(request), User.class);
        InvokingCount invokingCount = JSON.parseObject(JSON.toJSONString(request), InvokingCount.class);
        int updateUser = userManageMapper.updateUser(oldUsername, user);
        if(updateUser==0){
            throw new RuntimeException("更新失败");
        }
        int updateInvokingCount = userManageMapper.updateInvokingCount(invokingCount);
        if(updateInvokingCount==0){
            throw new RuntimeException("更新失败");
        }
    }

    /**
     * 根据用户名删除用户信息
     * @param username username
     */
    @Override
    public int deleteUserInfo(String username){
        return userManageMapper.deleteUser(username);
    }
}
