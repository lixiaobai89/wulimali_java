package com.example.lxb_website.login.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger logger = LogManager.getLogger(ApplicationStartupListener.class);

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        logger.info("应用已启动");
        // 当应用完全启动并准备好提供服务时触发
        System.out.println("Spring Boot 应用已启动！");
        // 这里可以添加额外的监控逻辑
    }
}
