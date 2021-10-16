package com.itheima.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.pojo.Favorite;
import com.itheima.pojo.Route;
import com.itheima.pojo.User;
import com.itheima.service.FavoriteService;
import com.itheima.service.RouteService;
import com.itheima.vo.PageBean;
import com.itheima.vo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/favorite")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private RouteService routeService;


    @RequestMapping("/isFavoriteByRid")
    @ResponseBody
    public ResultInfo isFavoriteByRid(HttpSession session, int rid){
//        是否收藏
        User user = (User) session.getAttribute("user");
        if (user==null){
            return new ResultInfo(true,false,"");
        }
        Integer uid = user.getUid();
        LambdaQueryWrapper<Favorite> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Favorite::getRid,rid).eq(Favorite::getUid,uid);
        Favorite favorite=favoriteService.getOne(wrapper);
        if (favorite==null){
            return new ResultInfo(true,false,"");
        }
        return new ResultInfo(true,true,"");
    }


    @RequestMapping("/addFavorite")
    @ResponseBody
    public ResultInfo addFavorite(HttpSession session, int rid){
//        搜藏表添加记录
        User user = (User) session.getAttribute("user");
        if (user==null){
            return new ResultInfo(false,"","请先登录");
        }
        Integer uid = user.getUid();
        Favorite build = Favorite.builder().rid(rid).uid(uid).build();
        boolean save = favoriteService.save(build);

//        线路表收藏加一 还是老的简单
//        先查出该线路
        LambdaQueryWrapper<Route> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Route::getRid,rid);
        Route route=routeService.getOne(wrapper);

        LambdaUpdateWrapper<Route> wrapper1 = Wrappers.lambdaUpdate();
        wrapper1.set(Route::getCount,route.getCount()+1).eq(Route::getRid,rid);

        Route route2=routeService.getOne(wrapper);

        return new ResultInfo(true,route2,"");
    }




    @RequestMapping("/findFavoriteByPage")
    @ResponseBody
    public ResultInfo findFavoriteByPage(HttpSession session,
                                         @RequestParam(value = "curPage",required = false,defaultValue = "1") int curPage){
//        根据页数查找收藏
        User user = (User) session.getAttribute("user");
        if (user==null){
            return new ResultInfo(false,"","请先登录");
        }
        //单表查用户收藏的对象
        LambdaQueryWrapper<Favorite> wrapper = Wrappers.lambdaQuery();
        wrapper.select(Favorite::getRid).eq(Favorite::getUid,user.getUid());
        List<Object> list = favoriteService.listObjs(wrapper);
//        查询路线
        LambdaQueryWrapper<Route> wrapper2 = Wrappers.lambdaQuery();
//      会有类型转换问题
        wrapper2.in(Route::getRid,list);
        Page<Route> page = new Page<>(curPage, 4);
        routeService.page(page, wrapper2);
        List<Route> records = page.getRecords();
        // 使用我们自己的分页工具类封装分页数据信息
        List<Favorite> favoriteList = new ArrayList<>();
        for (Route route : records) {
            Favorite favorite = new Favorite();
            favorite.setRoute(route);
            favoriteList.add(favorite);
        }
        // 使用我们自己的分页工具类封装分页数据信息
        int current = (int) page.getCurrent();
        PageBean<Favorite> pageBean = new PageBean<>();
        pageBean.setData(favoriteList);
        pageBean.setFirstPage(current>4?current-4:1);
        pageBean.setPrePage(current-1);
        pageBean.setCurPage(current);
        pageBean.setNextPage(current+1);
        pageBean.setTotalPage((int) page.getPages());
        pageBean.setCount(page.getTotal());
        pageBean.setPageSize((int) page.getSize());
        return new ResultInfo(true,pageBean,null);
    }
}
