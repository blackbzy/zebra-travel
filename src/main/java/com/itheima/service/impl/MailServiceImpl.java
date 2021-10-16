package com.itheima.service.impl;

import com.itheima.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @Description 邮件服务实现
 * @Created by itheima
 */
@Service("mailService")
public class MailServiceImpl implements MailService {

    /**
     * 注入发送发的邮件地址
     */
    @Value("${spring.mail.username}")
    private String from;
    /**
     * 注入发送邮件对象
     */
    @Autowired
    private JavaMailSender mailSender;

    /**
     * @param to 邮件接收方地址
     * @param subject  邮件主题
     * @param text  邮件正文
     * @throws MessagingException
     */
    @Override
    public void sendMsg(String to, String subject, String text) throws MessagingException {
        //构建邮件消息对象
        MimeMessage message = mailSender.createMimeMessage();
        //构建邮件消息包装类，方便参数设置
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        //设置mail发送方地址
        messageHelper.setFrom(from);
        //设置mail接收方地址
        messageHelper.setTo(to);
        //设置mail主题
        messageHelper.setSubject(subject);
        /**
         * 参数1：邮件正文
         * 参数2：true表示html格式
         */
        messageHelper.setText(text,true);
        mailSender.send(message);
    }
}