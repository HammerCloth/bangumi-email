package com.syx.bangumiemail.service;

import com.syx.bangumiemail.model.Bangumi;
import com.syx.bangumiemail.model.EmailData;

import java.util.List;

/**
 * @ClassName EmailData
 * @Description TODO
 * @Author SiYiXiong
 * @Date 2022/4/14 14:55
 * @Version v0.1
 **/
public interface EmailDataService {
    /**
     * 获取没有结束到番剧列表
     */
    List<EmailData> getAllNoEnd();
}
