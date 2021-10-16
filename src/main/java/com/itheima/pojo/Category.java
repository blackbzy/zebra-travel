package com.itheima.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * 分类实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@TableName("tab_category")
public class Category implements Serializable {
    @TableId(type = IdType.AUTO)
    private int cid;//分类id
    private String cname;//分类名称

}
