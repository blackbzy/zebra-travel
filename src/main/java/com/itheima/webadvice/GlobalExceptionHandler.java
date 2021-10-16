package com.itheima.webadvice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 定义全局异常分类管理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //处理运行期异常
    @ExceptionHandler(RuntimeException.class)
    public void handlerRuntimeException(RuntimeException ex, HttpServletResponse response, HttpServletRequest request) throws IOException {
        //1.获取错误信息
        String errorMessage = ex.getMessage();
        //获取访问路径
        String curPath = request.getServletPath();
        //2.输出日志信息
        logger.error("用户访问路径：{}，错误信息：{}",curPath,errorMessage);
        //3.跳转到错误页面
        response.sendRedirect("/error.html");
    }
}