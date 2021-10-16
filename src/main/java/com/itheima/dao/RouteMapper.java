package com.itheima.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.pojo.Route;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface RouteMapper extends BaseMapper<Route> {
    @Select("<script>select * from tab_route\n" +
            "        <where>\n" +
            "            <if test=\"cid!=null and cid!=''\">\n" +
            "                and cid=#{cid}\n" +
            "            </if>\n" +
            "            <if test=\"rname!=null and rname!=''\">\n" +
            "                and rname like concat(\"%\",#{rname},\"%\")\n" +
            "            </if>\n" +
            "        </where></script>")
    List<Route> findRouteList(@Param("cid") Integer cid, @Param("rname")String rname);




    List<Route> findRoutesFavoriteRank(@Param("rname") String rname, @Param("startPrice")int startPrice,@Param("endPrice") int endPrice);

}
