package com.syx.bangumiemail.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Trans_title
 * @Description pojo
 * @Author SiYiXiong
 * @Date 2022/4/9 23:45
 * @Version v0.1
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("trans_title")
public class TransTitle {
    @TableId(type = IdType.AUTO)
    private int id;//自增主键
    private int bangumiId;//番剧id
    private String trans_type;//翻译类型
    private String trans_title;//翻译名称
}
