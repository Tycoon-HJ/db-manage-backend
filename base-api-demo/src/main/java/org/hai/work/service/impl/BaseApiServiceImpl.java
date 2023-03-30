package org.hai.work.service.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.hai.work.service.BaseApiService;
/**
 * 基础方法
 * @author mr.ahai
 */
@DubboService
public class BaseApiServiceImpl implements BaseApiService {
    @Override
    public String testMsg() {
        return "hello, dubbo";
    }
}
