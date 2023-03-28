package com.pigliu.mongodb.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import java.util.HashMap;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 监控线程任务队列
 * @author liufuqiang
 */
@Slf4j
public class MonitorQueueWorkerService implements InitializingBean {

    private boolean isStarted = false;

    private volatile boolean isShutdown = true;

    private ThreadPoolExecutor threadPoolExecutor;

    private int threadNum = 5;

    @Override
    public void afterPropertiesSet() throws Exception {
        threadPoolExecutor =
                new ThreadPoolExecutor(threadNum, threadNum, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10));

        for (int i = 0; i < threadNum; i++) {
            int num = i;
            threadPoolExecutor.execute(() -> {
                String threadName = String.format("worker-%d", num);
                Thread.currentThread().setName(threadName);
                while (!isShutdown) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info(String.format("thread is %s, shutdown:%s", threadName, isShutdown));
                }
            });
        }

        log.info("worker is inited");
    }

    public void shutdown() {
        this.isShutdown = true;
        log.info("worker is shutdown");
    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long j = 0;
        for (long i = 0; i < 1000000000000000000L; i++) {
            j += i;
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(j);
    }
}
