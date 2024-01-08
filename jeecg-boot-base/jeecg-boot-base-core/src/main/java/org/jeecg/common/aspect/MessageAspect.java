//package org.jeecg.common.aspect;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Enumeration;
//
//@Aspect
//@Component
//@Slf4j
//public class MessageAspect {
//    @Pointcut("execution(public * org.jeecg.modules..*.*Controller.queryPageList(..))")
//    public void excudeService() {
//    }
//
//    @Before("excudeService()")
//    public Object doBfore(JoinPoint joinPoint) throws Throwable {
//
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        //从获取RequestAttributes中获取HttpServletRequest的信息
//        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
//        log.info("==> 请求者的IP："+request.getRemoteAddr());
//        //如果要获取Session信息的话，可以这样写：
//        //HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
//        Enumeration<String> enumeration = request.getParameterNames();
//        // 从前端的Header中获取查询的分页大小 TODO
//        Integer pageNo = 1;
//        Integer pageSize = 10;
//
//
//        return null;
//    }
//}
