package com.totalshop;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //@洪伟 文件磁盘图片url 映射
        //配置server虚拟路径，handler为前台访问的目录，locations为files相对应的本地路径
        registry.addResourceHandler("/image/**").addResourceLocations("file:///C:\\Users\\70681\\Desktop\\Img\\");
        //服务器 文件磁盘图片url 映射
        /*registry.addResourceHandler("/image/**").addResourceLocations("file:///C:\Users\70681\Desktop\Img\");*/
        super.addResourceHandlers(registry);
    }
}