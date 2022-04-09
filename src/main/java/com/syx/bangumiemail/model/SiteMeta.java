package com.syx.bangumiemail.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName SiteMeta
 * @Description siteMeta pojo
 * @Author SiYiXiong
 * @Date 2022/4/9 23:42
 * @Version v0.1
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("site_meta")
public class SiteMeta {
    @TableId
    private String name;//站点字段名
    private String title;//站点名称
    private String urlTemplate;//站点url模版
    private String regions;//地区
}
