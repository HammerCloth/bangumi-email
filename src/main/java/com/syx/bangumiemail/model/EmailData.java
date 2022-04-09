package com.syx.bangumiemail.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName emailData
 * @Description 用于将数据传输到HTML
 * @Author SiYiXiong
 * @Date 2022/4/14 14:49
 * @Version v0.1
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailData {
    private Bangumi bangumi;
    private List<Site> sites;
    private List<TransTitle> transTitles;
}
