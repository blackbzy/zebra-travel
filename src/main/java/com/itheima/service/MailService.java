package com.itheima.service;
import javax.mail.MessagingException;

/**
 * @Description 发送邮箱服务接口
 * @Created by itheima
 */
public interface MailService {
    /**
     * 定义发送邮件接口
     * @param to 邮件接收方地址
     * @param subject  邮件主题
     * @param text  邮件正文
     */
    public void sendMsg(String to,String subject,String text) throws MessagingException;
}