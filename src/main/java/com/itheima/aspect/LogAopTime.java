package com.itheima.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogAopTime {
    // 获取日志对象
    private static final Logger logger= LoggerFactory.getLogger(LogAopTime.class);

    // 定义切入点表达式
    @Pointcut("execution(* com.itheima.service.impl.*.find*(..))")
    public void pt(){}

    @Around("pt()")
    public Object logAdvice(ProceedingJoinPoint pjp){

        //获取方法的入参
        Object[] args = pjp.getArgs();
        //获取当前的方法名称
        Signature methodName = pjp.getSignature();
        //获取执行开始时间
        long startTime = System.currentTimeMillis();
        //指定调用的方法
        Object result = null;
        try {
            result = pjp.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        //获取执行结束时间
        long endTime = System.currentTimeMillis();
        //日志打印
        logger.info("执行方法：{}，入参：{}，执行时间：{}",methodName, Arrays.toString(args),endTime-startTime);
        return result;
    }
}
