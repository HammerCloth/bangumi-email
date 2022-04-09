package com.syx.bangumiemail.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Site
 * @Description site pojo
 * @Author SiYiXiong
 * @Date 2022/4/9 23:34
 * @Version v0.1
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("site")
public class Site {
    @TableId(type = IdType.AUTO)
    private int id;//主键id
    private int bangumiId;//番剧id
    private String type;//站点类型
    private String site;//站点名称
    private String siteId;//填入的id
    private String url;//优先级最高的url
    private String comment;//备注
    private String regions;//地区
}
