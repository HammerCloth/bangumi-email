package com.syx.bangumiemail.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.syx.bangumiemail.model.SiteMeta;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName Parse
 * @Description TODO
 * @Author SiYiXiong
 * @Date 2022/4/11 23:44
 * @Version v0.1
 **/
@Component
public class Parse {

    public HashMap<String, String> separateMetaAndIterms(String data){
        HashMap<String, String> map = JSON.parseObject(data, new TypeReference<HashMap<String, String>>() {});
        return map;
    }

    public List<SiteMeta> parseSiteMeta(String data){
        HashMap<String, HashMap<String, String>> map = JSON.parseObject(data, new TypeReference<HashMap<String, HashMap<String, String>>>() {});
        /**
         * private String name;//站点字段名
         *     private String title;//站点名称
         *     private String urlTemplate;//站点url模版
         *     private String regions;//地区
         */
        List<SiteMeta> list = new ArrayList<>();
        for(String i:map.keySet()){
            String name = i;
            String title = map.get(i).get("title");
            String urlTemplate = map.get(i).get("urlTemplate");
            String regions = map.get(i).get("regions");
            list.add(new SiteMeta(name,title,urlTemplate,regions));
        }
        return list;
    }

}
