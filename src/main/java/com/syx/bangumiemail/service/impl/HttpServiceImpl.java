package com.syx.bangumiemail.service.impl;

import com.syx.bangumiemail.service.HttpService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName HttpServiceImpl
 * @Description TODO
 * @Author SiYiXiong
 * @Date 2022/4/11 23:29
 * @Version v0.1
 **/
@Service
public class HttpServiceImpl implements HttpService {
    @Value("${bangumi-data.url}")
    private String url;
    @Override
    public String getResourcesJson() {
        //使用RestTemplate来使用rest资源
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);
        //拼接http请求，进行http请求，并获得响应体
        String data = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
        return data;
    }
}
