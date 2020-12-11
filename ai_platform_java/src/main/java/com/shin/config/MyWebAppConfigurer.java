package com.shin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {

    /**上传地址*/
    @Value("${image.upload.tmp-path}")
    private String filePath;

    /**显示相对地址*/
    @Value("${image.upload.tmp-path.relative}")
    private String fileRelativePath;
    /**
     * 配置资源路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //临时图片路径
        registry.addResourceHandler(fileRelativePath+"**").addResourceLocations("file:/" + filePath);
        //项目内部图片路径
        registry.addResourceHandler("E:\\学习\\毕业设计\\AIPlatform\\ai_platform_java\\src\\main\\resources\\static\\images").addResourceLocations("file:/"+"/images/");
    }
}
