package com.shin.service;

public interface InvokingCountService {
    /**
     * 初始化用户的ai调用次数
     * @param username 用户名
     */
    void initCount(String username);

    /**
     * ai调用次数+1
     * @param username 用户名
     * @param column 字段名
     */
    void countInc(String username,String column);
}
