package com.itheima.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.*;
import com.itheima.pojo.*;
import com.itheima.service.RouteService;
import com.itheima.vo.PageBean;
import com.itheima.vo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RouteServiceimpl extends ServiceImpl<RouteMapper, Route> implements RouteService {
    @Autowired
    private RouteMapper routeMapper;

    @Override
    public ResultInfo findRouteList(Integer cid, int curPage, String rname) {
        PageHelper.startPage(curPage,8);
        List<Route> list=routeMapper.findRouteList(cid,rname);

        PageInfo<Route> pageInfo = new PageInfo<>(list);
        // 使用我们自己的分页工具类封装分页数据信息
        PageBean<Route> pageBean = new PageBean<>();
        pageBean.setData(list);
        pageBean.setFirstPage(pageInfo.getNavigateFirstPage());
        pageBean.setPrePage(pageInfo.getPrePage());
        pageBean.setCurPage(pageInfo.getPageNum());
        pageBean.setNextPage(pageInfo.getNextPage());
        pageBean.setTotalPage(pageInfo.getPages());
        pageBean.setCount(pageInfo.getTotal());
        pageBean.setPageSize(pageBean.getPageSize());
        return new ResultInfo(true,pageBean,null);
    }


    @Override
    public ResultInfo findRoutesFavoriteRank(int curPage, String rname, int startPrice, int endPrice) {
        PageHelper.startPage(curPage,8);
        List<Route> list= routeMapper.findRoutesFavoriteRank(rname,startPrice,endPrice);
        PageInfo<Route> pageInfo = new PageInfo<>(list);
        // 使用我们自己的分页工具类封装分页数据信息
        PageBean<Route> pageBean = new PageBean<>();
        pageBean.setData(list);
        pageBean.setFirstPage(pageInfo.getNavigateFirstPage());
        pageBean.setPrePage(pageInfo.getPrePage());
        pageBean.setCurPage(pageInfo.getPageNum());
        pageBean.setNextPage(pageInfo.getNextPage());
        pageBean.setTotalPage(pageInfo.getPages());
        pageBean.setCount(pageInfo.getTotal());
        pageBean.setPageSize(pageBean.getPageSize());
        return new ResultInfo(true,pageBean,null);
    }
}
