package com.syx.bangumiemail.service;

/**
 * @ClassName HttpService
 * @Description 获取网络相关的服务
 * @Author SiYiXiong
 * @Date 2022/4/11 23:26
 * @Version v0.1
 **/
public interface HttpService {
    /**
     * 从固定url获取json格式的数据
     * @return
     */
    public String getResourcesJson();
}
