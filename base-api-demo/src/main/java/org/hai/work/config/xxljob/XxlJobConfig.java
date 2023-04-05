package org.hai.work.config.xxljob;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mr.ahai
 */
@Configuration
public class XxlJobConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 方便测试，后续开发采取配置文件注入的方式
     * @return xxlSpring执行器
     */
    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        logger.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses("http://localhost:9080/xxl-job-admin");
        xxlJobSpringExecutor.setAppname("godName");
        xxlJobSpringExecutor.setAddress("");
        xxlJobSpringExecutor.setIp("127.0.0.1");
        xxlJobSpringExecutor.setPort(4545);
        xxlJobSpringExecutor.setAccessToken("default_token");
        xxlJobSpringExecutor.setLogPath("xxl-job-base-api-demo.log");
        //xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);
        return xxlJobSpringExecutor;
    }

}