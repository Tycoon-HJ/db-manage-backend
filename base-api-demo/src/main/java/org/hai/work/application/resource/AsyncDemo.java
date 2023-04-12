package org.hai.work.application.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异步案例
 *
 * @author mr.ahai
 */
@Slf4j
@RestController
public class AsyncDemo {

    /**
     * 每次会开启一个新的线程：SimpleAsyncTaskExecutor
     * 不建议使用，因为无法对线程池进行控制
     */
    @GetMapping("/xx")
    @Async
    public void newThread() {
        log.info("11111" + Thread.currentThread().getName());
    }
}