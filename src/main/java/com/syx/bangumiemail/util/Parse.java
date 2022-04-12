package com.syx.bangumiemail.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.syx.bangumiemail.model.Bangumi;
import com.syx.bangumiemail.model.Site;
import com.syx.bangumiemail.model.SiteMeta;
import com.syx.bangumiemail.model.TransTitle;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName Parse
 * @Description 转化工具类
 * @Author SiYiXiong
 * @Date 2022/4/11 23:44
 * @Version v0.1
 **/
@Component
public class Parse {

    /**
     * 将json解析分为SiteMeta和Iterms
     *
     * @param data 从网址中获取的json
     * @return 保存不同的json的map
     */
    public HashMap<String, String> separateMetaAndIterms(String data) {
        HashMap<String, String> map = JSON.parseObject(data, new TypeReference<HashMap<String, String>>() {
        });
        return map;
    }

    /**
     * 将SiteMeta解析为对应的对象集合
     *
     * @param data SiteMeta的json
     * @return SiteMeta的对象集合
     */
    public List<SiteMeta> parseSiteMeta(String data) {
        HashMap<String, HashMap<String, String>> map = JSON.parseObject(data,
                new TypeReference<HashMap<String, HashMap<String, String>>>() {
                });
        List<SiteMeta> list = new ArrayList<>();
        for (String i : map.keySet()) {
            String name = i;
            String title = map.get(i).get("title");
            String urlTemplate = map.get(i).get("urlTemplate");
            String regions = map.get(i).get("regions");
            list.add(new SiteMeta(name, title, urlTemplate, regions));
        }
        return list;
    }

    /**
     * 将items条目分开，作为json集合
     *
     * @param data items条目
     * @return String集合
     */
    public List<String> parseItems(String data) {
        List<String> strings = JSON.parseArray(data, String.class);//获得了每一个对象的json
        return strings;
    }

    /**
     * 获取条目的翻译名的对象
     *
     * @param data 每一个条目对应的JSON
     * @return 条目翻译名对象的集合，但是没有填入bangumiID
     */
    public List<TransTitle> parseItermToTransTitle(String data) {
        HashMap<String, String> map = JSON.parseObject(data,
                new TypeReference<HashMap<String, String>>() {
                });
        String titleTranslate = map.get("titleTranslate");//存储在另外一个表中
        HashMap<String, String> titleMap = JSON.parseObject(titleTranslate,
                new TypeReference<HashMap<String, String>>() {
                });
        List<TransTitle> list = new ArrayList<>();
        for (String i : titleMap.keySet()) {
            List<String> strings = JSON.parseArray(titleMap.get(i), String.class);
            for (String s : strings) {
                TransTitle title = new TransTitle();
                title.setTrans_title(s);
                title.setTrans_type(i);
                list.add(title);
            }
        }
        return list;
    }

    /**
     * 将条目JSON转化为bangumi对象
     *
     * @param data 条目JSON
     * @return bangumi对象
     */
    public Bangumi parseItemToBangumi(String data) {
        HashMap<String, String> map = JSON.parseObject(data,
                new TypeReference<HashMap<String, String>>() {
                });
        String title = map.get("title");
        String type = map.get("type");
        String lang = map.get("lang");
        String officialSite = map.get("officialSite");
        String begin = map.get("begin");
        String broadcast;
        if (map.containsKey("broadcast")) {
            broadcast = map.get("broadcast");
        } else {
            broadcast = "R/" + map.get("begin") + "/P7D";
        }
        String end = map.get("end");
        String comment = map.get("comment");//备注，暂时不存
        Bangumi bangumi = new Bangumi();
        bangumi.setTitle(title);
        bangumi.setType(type);
        bangumi.setLang(lang);
        bangumi.setOfficialSite(officialSite);
        bangumi.setBegin(begin);
        bangumi.setBroadcast(broadcast);
        bangumi.setEnd(end);
        bangumi.setEndFlag(end.equals("") ? 0 : 1);
        bangumi.setComment(comment);
        return bangumi;
    }

    /**
     * 将每一个条目的数据转化为site对象的集合
     *
     * @param data 条目数据
     * @return site对象的集合
     */
    public List<Site> parseItemToSite(String data) {
        HashMap<String, String> map = JSON.parseObject(data,
                new TypeReference<HashMap<String, String>>() {
                });
        String sites = map.get("sites");//存在另外一个表中
        List<String> strings = JSON.parseArray(sites, String.class);
        List<Site> list = new ArrayList<>();
        for (String i : strings) {
            HashMap<String, String> siteMap = JSON.parseObject(i, new TypeReference<HashMap<String, String>>() {
            });
            Site site = new Site();
            site.setSite(siteMap.get("site"));
            site.setSiteId(siteMap.get("id"));
            if (siteMap.containsKey("url")) {
                site.setUrl(siteMap.get("url"));
            }
            if (siteMap.containsKey("comment")) {
                site.setComment(siteMap.get("comment"));
            }
            if (siteMap.containsKey("comment")) {
                site.setRegions(siteMap.get("regions"));
            }
            list.add(site);
        }
        return list;
    }
}
