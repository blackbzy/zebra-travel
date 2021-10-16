package com.itheima.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class ValidateController {
    @RequestMapping("/checkCode")
    public void getCheckCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.设置浏览器禁止缓存当前响应的数据
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("expires", "0");
        //2.创建图片对象
        //定义图片的宽和高
        int width=80;
        int height=30;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //获取画笔对象
        Graphics graphics = image.getGraphics();
        //设置画笔颜色并填充
        graphics.setColor(Color.gray);
        graphics.fillRect(0,0,width,height);
        //3.生成随机码(长度：5,由字母+数字随机组成)，并保存在当前的session下
        //返回随机的纯数字
        //RandomStringUtils.randomNumeric(5);
        String checkCode = RandomStringUtils.randomAlphanumeric(5);
        HttpSession session = request.getSession();
        session.setAttribute("CHECK_CODER",checkCode);
//        String id = session.getId();
//        Cookie cookie = new Cookie("JSESSIONID", id);
//        response.addCookie(cookie);
        //4.将随机码写入图片
        graphics.setColor(Color.yellow);
        graphics.setFont(new Font("黑体",Font.BOLD,24));
        graphics.drawString(checkCode,15,25);
        //5.将图片响应浏览器
        //参数1：指定写入的图片对象
        //参数2：图片的格式
        //参数3：指定以流的方式响应浏览器
        ImageIO.write(image,"PNG",response.getOutputStream());
    }
}