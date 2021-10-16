package com.itheima.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@TableName("tab_route")
public class Route implements Serializable {
    @TableId(type = IdType.AUTO)
    private int rid;//线路id，必输
    private String rname;//线路名称，必输
    private double price;//价格，必输
    @TableField("routeIntroduce")
    private String routeIntroduce;//线路介绍
    private String rflag;   //是否上架，必输，0代表没有上架，1代表是上架
    private String rdate;   //上架时间
    @TableField("isThemeTour")
    private String isThemeTour;//是否主题旅游，必输，0代表不是，1代表是
    private int count;//收藏数量
    private int cid;//所属分类，必输
    private String rimage;//缩略图
    private int sid;//所属商家
    private String sourceId;//抓取数据的来源id
    @TableField(exist = false)
    private Category category;//所属分类
    @TableField(exist = false)
    private Seller seller;//所属商家
    @TableField(exist = false)
    private List<RouteImg> routeImgList;//商品详情图片列表




}
