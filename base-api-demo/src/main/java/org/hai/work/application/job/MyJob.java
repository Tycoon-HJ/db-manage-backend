package org.hai.work.application.job;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 定时任务
 *
 * @author mr.ahai
 */
@Component
public class MyJob {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    @XxlJob("writeSomeMsg")
    public void writeMsg() {
        threadPoolExecutor.execute(() -> {
            logger.info("执行成功了！！！");
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

    }

}
