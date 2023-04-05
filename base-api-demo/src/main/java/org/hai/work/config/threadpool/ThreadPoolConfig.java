package org.hai.work.config.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author mr.ahai
 */
@Configuration
public class ThreadPoolConfig {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {

        // 队列
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(100);
        // 线程工厂
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        // 拒绝策略
        ThreadPoolExecutor.AbortPolicy abortPolicy = new ThreadPoolExecutor.AbortPolicy();
        logger.info("构建线程池初始化完成");
        return new ThreadPoolExecutor(100, 100, 10, TimeUnit.SECONDS, workQueue, threadFactory, abortPolicy);
    }
}
