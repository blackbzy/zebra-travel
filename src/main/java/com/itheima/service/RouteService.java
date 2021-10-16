package com.itheima.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.pojo.Favorite;
import com.itheima.pojo.Route;
import com.itheima.vo.ResultInfo;

public interface RouteService extends IService<Route> {

    ResultInfo findRouteList(Integer cid, int curPage, String rname);


    ResultInfo findRoutesFavoriteRank(int curPage, String rname, int startPrice, int endPrice);

}
