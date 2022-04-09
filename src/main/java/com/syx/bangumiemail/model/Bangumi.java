package com.syx.bangumiemail.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName bangumi
 * @Description bangumi pojo
 * @Author SiYiXiong
 * @Date 2022/4/9 22:20
 * @Version v0.1
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("bangumi")
public class Bangumi {
    @TableId(type = IdType.AUTO)
    private int id;//主键id
    private String title;//日文名称
    private String type;//番剧类别
    private String lang;//番剧语言
    private String officialSite;//官网
    private int endFlag;//番剧是否完结，0表示没有完结
    private String begin;//番剧开始时间
    private String end;//番剧结束时间
}
