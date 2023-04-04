package org.hai.work.config;

import com.xxl.job.core.executor.XxlJobExecutor;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.hai.work.application.job.MyJob;
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

//    @Value("http://localhost:9080/xxl-job-admin")
//    private String adminAddresses;
//
//    @Value("")
//    private String accessToken;
//
//    @Value("god")
//    private String appname;
//
//    @Value("${xxl.job.executor.address}")
//    private String address;
//
//    @Value("${xxl.job.executor.ip}")
//    private String ip;
//
//    @Value("${xxl.job.executor.port}")
//    private int port;
//
//    @Value("${xxl.job.executor.logpath}")
//    private String logPath;
//
//    @Value("${xxl.job.executor.logretentiondays}")
//    private int logRetentionDays;


    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        logger.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses("http://localhost:9080/xxl-job-admin");
        xxlJobSpringExecutor.setAppname("godName");
        xxlJobSpringExecutor.setAddress("");
        xxlJobSpringExecutor.setIp("127.0.0.1");
        xxlJobSpringExecutor.setPort(4545);
        //xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath("xxl-job-base-api-demo.log");
        //xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);

        // Bean方法模式
        // 通过扫描@XxlJob方式注册

        // 注册Bean类模式
        XxlJobExecutor.registJobHandler("beanClassDemoJobHandler", new MyJob());

        return xxlJobSpringExecutor;
    }

}