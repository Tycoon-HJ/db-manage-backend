package org.hai.work.application.job;

import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 * @author mr.ahai
 */
@Component
public class MyJob extends IJobHandler {


    @XxlJob("writeSomeMsg")
    public void writeMsg(){
        System.out.println("1111111");
    }

    @Override
    public void execute() throws Exception {

    }
}
