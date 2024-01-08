package org.jeecg.modules.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jeecg.common.exception.DictException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author mr.ahai
 * 自定义字典统一且切面处理
 */
@Aspect
@Component
@Slf4j
public class IdictAspect {


    /**
     * 仅对org.jeecg.modules.fieldmanage.controller.TbFieldManagerController 下的所有方法进行拦截处理
     * 全局生效时：请修改该且面
     */
    @Pointcut("execution(public * org.jeecg.modules.fieldmanage.controller.TbFieldManagerController.*(..))")
    public void idict() {
    }

    /**
     * 对字典值进行校验
     *
     * @param pjp 固定写法，用来获取字典传入的值
     * @return 返回该数据的值
     */
    @Around("idict()")
    public Object dictionaryConversion(ProceedingJoinPoint pjp) throws Throwable {
        // pjp.proceed方法在before方法执行后执行
        Object result = pjp.proceed();
        log.info("在后执行");
        return result;
    }

    /**
     * 执行注解之前前
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("idict()")
    public void before(JoinPoint joinPoint) throws Exception {
        // 入参数的DTO
        Object arg = joinPoint.getArgs()[0];
        Class<?> clazz = arg.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            // 如果字段上存在Idict字典
            if (field.isAnnotationPresent(Idict.class)) {
                // 获取字段的值
                field.setAccessible(true);
                // 如果传来的是空，可能是查询，直接跳过验证，必填校验通过NotNull 注解来校验
                if (Objects.isNull(field.get(arg))) {
                    break;
                }
                // 如果是字典的话，查看一下映射的字典是否正确
                String dictValue = field.get(arg).toString();
                // 获取字典上方标记的数据字典类
                Class<?> code = field.getAnnotation(Idict.class).code();
                Method getCode = code.getMethod("getValue");
                Object[] enumConstants = code.getEnumConstants();
                List<String> yValueList = new ArrayList<>();
                for (Object dict : enumConstants) {
                    String yValue = getCode.invoke(dict).toString();
                    yValueList.add(yValue);
                }
                if (!yValueList.contains(dictValue)) {
                    throw new DictException("报错了报错了！！！！！");
                }
            }
        }
    }
}
