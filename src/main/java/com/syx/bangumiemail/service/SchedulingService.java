package com.syx.bangumiemail.service;

/**
 * @ClassName SchedulingService
 * @Description TODO
 * @Author SiYiXiong
 * @Date 2022/4/20 20:59
 * @Version v0.1
 **/
public interface SchedulingService {
    void reBaseWeekly();

    void sendEmailDaily();
}
