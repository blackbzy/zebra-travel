package com.itheima.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.CategoryMapper;
import com.itheima.dao.FavoriteMapper;
import com.itheima.dao.RouteMapper;
import com.itheima.pojo.Category;
import com.itheima.pojo.Favorite;
import com.itheima.pojo.Route;
import com.itheima.pojo.User;
import com.itheima.service.FavoriteService;
import com.itheima.vo.PageBean;
import com.itheima.vo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteServiceimpl extends ServiceImpl<FavoriteMapper, Favorite> implements FavoriteService {
}
