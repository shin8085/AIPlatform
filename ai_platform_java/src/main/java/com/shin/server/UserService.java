package com.shin.server;

import com.shin.pojo.User;

public interface UserService {
    void addUser(User user);
    boolean registerUser(User user);
}
