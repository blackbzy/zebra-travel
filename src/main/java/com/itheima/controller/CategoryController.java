package com.itheima.controller;

import com.itheima.pojo.Category;
import com.itheima.service.CategoryService;
import com.itheima.vo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/findAllCategory")
    @ResponseBody
    public ResultInfo findAllCategory(){
        List<Category> list=categoryService.list();
        if (list==null ||list.size()==0){
            return new ResultInfo(false,"","");
        }
        return new ResultInfo(true,list,"");
    }
}
