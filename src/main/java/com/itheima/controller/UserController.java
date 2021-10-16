package com.itheima.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.itheima.pojo.User;
import com.itheima.service.MailService;
import com.itheima.service.UserService;
import com.itheima.util.Md5Util;
import com.itheima.util.UuidUtil;
import com.itheima.vo.ResultInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;

    @RequestMapping("/register")
    @ResponseBody
    public ResultInfo register(User user, String check, HttpServletRequest request) {

        HttpSession session = request.getSession();
        String checkCoder = (String) session.getAttribute("CHECK_CODER");
        session.removeAttribute("CHECK_CODER");
        if (StringUtils.isEmpty(check)){
            return new ResultInfo(false,null,"验证码不能为空");
        }
        if (!check.equalsIgnoreCase(checkCoder)){
            return new ResultInfo(false,null,"验证码错误");
        }
        //2.判断用户名密码是否为空
        if (user.getPassword()==null ||user.getName()==null ){
            return new ResultInfo(false,null,"用户名密码不能为空");
        }
        session.invalidate();

        //3.对用户名查重
        String name = user.getName();
        LambdaQueryWrapper<User> wrapper1 = Wrappers.lambdaQuery();
        wrapper1.eq(User::getUsername,name);
        List<User> list = userService.list(wrapper1);
        if(list.size()!=0){
            return new ResultInfo(false,null,"该用户名已注册");
        }

        user.setPassword(Md5Util.encodeByMd5(user.getPassword()));
        user.setStatus("N");
        String code = UuidUtil.getUuid();
        user.setCode(code);

        //4.完成用户注册
        boolean save = userService.save(user);
        if(!save){
            return new ResultInfo(false,null,"注册失败");
        }
        //5.向用户邮箱发送邮件
        try {
            mailService.sendMsg("1131064363@qq.com","激活邮件","<a href='http://localhost:8080/user/active?code="+code+"'>黑马旅游账户激活-点我激活</a>");
        } catch (MessagingException e) {
            e.printStackTrace();
            return new ResultInfo(false,null,"邮件发送失败");
        }
//        MailUtil.sendMail("1131064363@qq.com", "<a href='http://localhost:8080/user/active?code="+code+"'>黑马旅游账户激活-点我激活</a>");
        return new ResultInfo(true,null,"注册成功请激活");

    }


    @RequestMapping("/active")
    public String active(String code){
        LambdaUpdateWrapper<User> wrapper1 = Wrappers.lambdaUpdate();
        wrapper1.eq(User::getCode,code).set(User::getStatus,"Y");
        userService.update(wrapper1);
        return "redirect:/login.html";
    }


    @RequestMapping("/login")
    @ResponseBody
    public  ResultInfo login(User user, String check){
        //        1.校验验证码
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        ServletRequestAttributes sa= (ServletRequestAttributes) requestAttributes;
        HttpSession session = sa.getRequest().getSession();
        String checkCoder = (String) session.getAttribute("CHECK_CODER");
        session.removeAttribute("CHECK_CODER");
        if (StringUtils.isEmpty(check)){
            return new ResultInfo(false,null,"验证码不能为空");
        }
        if (!check.equalsIgnoreCase(checkCoder)){
            return new ResultInfo(false,null,"验证码错误");
        }
        //2.判断用户名密码是否为空
        if (user.getPassword()==null ||user.getUsername()==null ){
            return new ResultInfo(false,null,"用户名密码不能为空");
        }
        user.setPassword(Md5Util.encodeByMd5(user.getPassword()));
//调整
        LambdaQueryWrapper<User> wrapper1 = Wrappers.lambdaQuery();
        wrapper1.eq(User::getUsername,user.getUsername()).eq(User::getPassword,user.getPassword());
        User one = userService.getOne(wrapper1);
        if(one==null){
            return new ResultInfo(false,null,"该用户不存在");
        }
        if("N".equals(one.getStatus())){
            return new ResultInfo(false,null,"请先激活");
        }
        session.setAttribute("user",one);
        return new ResultInfo(true,one,"");
    }



    @RequestMapping("/getLoginUserData")
    @ResponseBody
    public ResultInfo getLoginUserData(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if (user==null){
        return new ResultInfo(false,null,"");}
        return new ResultInfo(true,user,"");
    }


    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session, HttpServletRequest request){
        session.invalidate();
        String referer = request.getHeader("referer");
        return "redirect"+referer;
    }
}
