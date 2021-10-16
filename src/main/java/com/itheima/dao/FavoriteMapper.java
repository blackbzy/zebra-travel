package com.itheima.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.pojo.Category;
import com.itheima.pojo.Favorite;
import com.itheima.pojo.Route;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FavoriteMapper extends BaseMapper<Favorite> {
}
