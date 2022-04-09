package com.syx.bangumiemail;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.syx.bangumiemail.mapper.BangumiMapper;
import com.syx.bangumiemail.model.*;
import com.syx.bangumiemail.service.*;
import com.syx.bangumiemail.util.Parse;
import com.syx.bangumiemail.util.Time;
import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.servlet.tags.EditorAwareTag;

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
    @Autowired
    private BaseService baseService;
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
        System.out.println(resourcesJson);
        HashMap<String, String> map = p.separateMetaAndIterms(resourcesJson);
        List<SiteMeta> siteMeta = p.parseSiteMeta(map.get("siteMeta"));
        for(SiteMeta i:siteMeta){
            System.out.println(i);
        }
        siteMetaService.saveOrUpdateBatch(siteMeta);
    }

    @Test
    void testa(){
        String resourcesJson = hs.getResourcesJson();
        HashMap<String, String> map = p.separateMetaAndIterms(resourcesJson);
        String items = map.get("items");
        for (String s : JSON.parseArray(items, String.class)) {
            for (Site site : p.parseItemToSite(s)) {
                System.out.println(site.toString());
            }
        }

    }

    @Test
    void testb(){
        baseService.creatDB();
    }

    @Test
    void testc(){
        baseService.clearDB();
    }


    @Autowired
    private MailSender mailSender;
    @Test
    void testMail(){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("bangumiEmail@163.com");
        mail.setTo("18552541076@163.com");
        mail.setSubject("Test");
        mail.setText("testtest");
        mailSender.send(mail);

    }

    @Autowired
    private MailService mailService;
    @Test
    void testav(){
        //mailService.sendSimpleMessage("简单的邮件","jiandande youjian ",new String[]{"424193726@qq.com","18552541076@163.com"});
        mailService.sentHTML("简单的邮件","jiandande youjian ",new String[]{"424193726@qq.com"});
    }
    @Autowired
    private EmailDataService ems;
    @Test
    void  testsx(){
        List<EmailData> allNoEnd = ems.getAllNoEnd();
        System.out.println(allNoEnd.size());
        allNoEnd.forEach(System.out::println);
    }

    @Test
    void testTime(){
        String time = new String("R/2022-04-14T15:00:00.000Z/P0D");
        System.out.println(time.substring(29));
        System.out.println(time.substring(28,29));
        System.out.println(time.substring(0, 2));
        System.out.println(time.substring(2, 26));
        /*DateTimeFormatter fmt = ISODateTimeFormat.dateTime();
        MutableDateTime mutableDateTime = fmt.parseMutableDateTime(time);
        System.out.println(fmt.print(mutableDateTime));
        mutableDateTime.addDays(7);
        System.out.println(fmt.print(mutableDateTime));*/
    }

    @Test
    void testm(){
        MutableDateTime mutableDateTime = new MutableDateTime();
        DateTimeFormatter fmt = ISODateTimeFormat.dateTime();
        System.out.println(fmt.print(mutableDateTime));
    }

    @Autowired
    private Time time;
    @Autowired
    private TransTitleService transTitleService;
    @Test
    void testA(){
//        String time1 = new String("R/2022-04-14T15:00:00.000Z/P0D");
//        String time2 = new String("R/2022-04-14T15:00:00.000Z/P4D");
//        String time3 = new String("R/2022-04-14T15:00:00.000Z/P7D");
//        System.out.println(time.isBroadcastToday(time1));
//        System.out.println(time.isBroadcastToday(time2));
//        System.out.println(time.isBroadcastToday(time3));
        System.out.println(transTitleService.list(new QueryWrapper<TransTitle>().eq("id", 18462)));

    }


}
