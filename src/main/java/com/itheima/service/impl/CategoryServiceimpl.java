package com.itheima.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.dao.CategoryMapper;
import com.itheima.pojo.Category;
import com.itheima.service.CategoryService;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceimpl extends ServiceImpl<CategoryMapper,Category> implements CategoryService {

}

