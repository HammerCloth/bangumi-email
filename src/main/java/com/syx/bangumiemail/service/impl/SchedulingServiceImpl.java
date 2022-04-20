package com.syx.bangumiemail.service.impl;

import com.syx.bangumiemail.service.BaseService;
import com.syx.bangumiemail.service.MailService;
import com.syx.bangumiemail.service.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @ClassName SchedulingServiceImpl
 * @Description TODO
 * @Author SiYiXiong
 * @Date 2022/4/20 21:00
 * @Version v0.1
 **/
@Service
public class SchedulingServiceImpl implements SchedulingService {
    @Autowired
    private BaseService baseService;
    @Autowired
    private MailService mailService;
    @Override
    @Scheduled(cron="0 0 0 * * 1")//每周周一的0点开始洗数据库
    public void reBaseWeekly() {
        baseService.clearDB();
        baseService.creatDB();
    }

    @Override
    @Scheduled(cron = "0 0 9 * * ?")//每天早上的9点发布
    public void sendEmailDaily() {
        mailService.sentHTML("每日新番剧","1.0版本",new String[]{"18552541076@163.com"});
    }


}
