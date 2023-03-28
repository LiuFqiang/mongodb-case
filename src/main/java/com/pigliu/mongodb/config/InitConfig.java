package com.pigliu.mongodb.config;

import com.pigliu.mongodb.service.MonitorQueueWorkerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 初始化bean
 * @author liufuqiang
 */
@Configuration
public class InitConfig {

    @Bean(value = "workerQueur", destroyMethod = "shutdown")
    public MonitorQueueWorkerService workerService() {
        return new MonitorQueueWorkerService();
    }
}
