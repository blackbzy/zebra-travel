package com.itheima.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.pojo.*;
import com.itheima.service.CategoryService;
import com.itheima.service.RouteImgService;
import com.itheima.service.RouteService;
import com.itheima.service.SellerService;
import com.itheima.vo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/route")
public class RouteController {
    @Autowired
    private RouteService routeService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private RouteImgService routeImgService;


    @RequestMapping("/routeCareChoose")
    @ResponseBody
    public ResultInfo routeCareChoose(){
//        最新查询
        LambdaQueryWrapper<Route> wrapper1 = Wrappers.lambdaQuery();
        wrapper1.eq(Route::getRflag,1).orderByDesc(Route::getCount);
        Page<Route> page1 = new Page<>(1, 4);
        routeService.page(page1,wrapper1);
        List<Route> records1 = page1.getRecords();

//        受欢迎查询
        LambdaQueryWrapper<Route> wrapper2 = Wrappers.lambdaQuery();
        wrapper2.eq(Route::getRflag,1).orderByDesc(Route::getRdate);
        Page<Route> page2 = new Page<>(1, 4);
        routeService.page(page2,wrapper2);

    //主题查询
        LambdaQueryWrapper<Route> wrapper3 = Wrappers.lambdaQuery();
        wrapper3.eq(Route::getRflag,1).eq(Route::getIsThemeTour,1);
        Page<Route> page3 = new Page<>(1, 4);
        routeService.page(page3,wrapper3);
        Map<String, List<Route>> map = new HashMap<>();
        map.put("news",records1);
        map.put("popularity",page2.getRecords());
        map.put("themes",page3.getRecords());
        return new ResultInfo(true,map,"");
    }


    @RequestMapping("/findRouteList")
    @ResponseBody
    public ResultInfo findRouteList(Integer cid,
                                    @RequestParam(value = "curPage",required = false,defaultValue = "1") int curPage,String rname){

        return routeService.findRouteList(cid,curPage,rname);
    }


    @RequestMapping("/findRouteByRid")
    @ResponseBody
    public ResultInfo findRouteByRid(Integer rid){
//        先找route
        LambdaQueryWrapper<Route> wrapper1 = Wrappers.lambdaQuery();
        wrapper1.eq(Route::getRid,rid);
        Route route=routeService.getOne(wrapper1);

//        在找category
        LambdaQueryWrapper<Category> wrapper2 = Wrappers.lambdaQuery();
        wrapper2.eq(Category::getCid,route.getCid());
        Category category = categoryService.getOne(wrapper2);

//        再找卖家
        LambdaQueryWrapper<Seller> wrapper3 = Wrappers.lambdaQuery();
        wrapper3.eq(Seller::getSid,route.getSid());
        Seller seller = sellerService.getOne(wrapper3);

//      再找图片
        LambdaQueryWrapper<RouteImg> wrapper4 = Wrappers.lambdaQuery();
        wrapper4.eq(RouteImg::getRid,route.getRid());
        List<RouteImg> list = routeImgService.list(wrapper4);

        route.setCategory(category);
        route.setSeller(seller);
        route.setRouteImgList(list);
        return new ResultInfo(true,route,"");
    }


    @RequestMapping("/findRoutesFavoriteRank")
    @ResponseBody
    public ResultInfo findRoutesFavoriteRank(
            @RequestParam(value = "curPage",required = false,defaultValue = "1") Integer curPage,
            String rname,
            Integer startPrice,
            Integer endPrice
    ){
       return routeService.findRoutesFavoriteRank(curPage,rname,startPrice,endPrice);

    }
}
