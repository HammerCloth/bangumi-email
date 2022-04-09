package com.syx.bangumiemail;

import com.syx.bangumiemail.mapper.BangumiMapper;
import com.syx.bangumiemail.model.Bangumi;
import com.syx.bangumiemail.service.BangumiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BangumiEmailApplicationTests {

    @Autowired
    private BangumiMapper bangumiMapper;
    @Autowired
    private BangumiService bangumiService;
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

}
