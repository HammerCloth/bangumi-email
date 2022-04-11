package com.syx.bangumiemail;

import com.syx.bangumiemail.mapper.BangumiMapper;
import com.syx.bangumiemail.model.Bangumi;
import com.syx.bangumiemail.model.Site;
import com.syx.bangumiemail.model.SiteMeta;
import com.syx.bangumiemail.service.BangumiService;
import com.syx.bangumiemail.service.HttpService;
import com.syx.bangumiemail.service.SiteMetaService;
import com.syx.bangumiemail.util.Parse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
class BangumiEmailApplicationTests {

    @Autowired
    private BangumiMapper bangumiMapper;
    @Autowired
    private BangumiService bangumiService;
    @Autowired
    private HttpService hs;
    @Autowired
    private Parse p;
    @Autowired
    private SiteMetaService siteMetaService;
    @Test
    void contextLoads() {
        Bangumi bangumi = new Bangumi();
        bangumi.setTitle("Re:ゼロから始める異世界生活");
        bangumi.setType("tv");
        bangumi.setLang("ja");
        bangumi.setOfficialSite("http://re-zero-anime.jp/");
        bangumi.setBegin("2016-04-03T16:35:00.000Z");
        bangumi.setEnd("");
        bangumi.setEndFlag(1);
        //bangumiMapper.insert(bangumi);
        bangumiService.save(bangumi);
        List<Bangumi> bangumis = bangumiMapper.selectList(null);
        bangumis.stream().forEach(System.out::println);
    }

    @Test
    void testHttp(){
        String resourcesJson = hs.getResourcesJson();
        HashMap<String, String> map = p.separateMetaAndIterms(resourcesJson);
        List<SiteMeta> siteMeta = p.parseSiteMeta(map.get("siteMeta"));
        for(SiteMeta i:siteMeta){
            System.out.println(i);
        }
        siteMetaService.saveOrUpdateBatch(siteMeta);
    }




}
