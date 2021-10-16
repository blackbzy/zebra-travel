package com.itheima.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 商家实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@TableName("tab_seller")
public class Seller implements Serializable {
    @TableId(type = IdType.AUTO)
    private int sid;//商家id
    private String sname;//商家名称
    private String consphone;//商家电话
    private String address;//商家地址
}
