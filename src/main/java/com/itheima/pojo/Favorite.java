package com.itheima.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * 收藏实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@TableName("tab_favorite")
public class Favorite implements Serializable {
    private Integer rid;//旅游路线ID
    private Integer uid;//用户ID
	private String date;//收藏时间
    private Route route;//旅游线路对象
    private User user;//所属用户
}
