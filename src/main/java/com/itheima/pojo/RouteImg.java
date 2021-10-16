package com.itheima.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@TableName("tab_route_img")
public class RouteImg implements Serializable {
    private int rgid;//商品图片id
    private int rid;//旅游商品id
    private String bigPic;//详情商品大图
    private String smallPic;//详情商品小图


}
