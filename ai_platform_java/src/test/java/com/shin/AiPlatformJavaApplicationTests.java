package com.shin;

import com.shin.pojo.User;
import com.shin.server.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class AiPlatformJavaApplicationTests {

    @Resource
    UserService userService;
    @Test
    void contextLoads() {

    }

}
