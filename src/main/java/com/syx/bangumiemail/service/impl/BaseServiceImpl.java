package com.syx.bangumiemail.service.impl;

import com.syx.bangumiemail.mapper.BangumiMapper;
import com.syx.bangumiemail.mapper.SiteMapper;
import com.syx.bangumiemail.mapper.TransTitleMapper;
import com.syx.bangumiemail.model.Bangumi;
import com.syx.bangumiemail.model.Site;
import com.syx.bangumiemail.model.SiteMeta;
import com.syx.bangumiemail.model.TransTitle;
import com.syx.bangumiemail.service.*;
import com.syx.bangumiemail.util.Parse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName BaseServiceImpl
 * @Description TODO
 * @Author SiYiXiong
 * @Date 2022/4/12 21:56
 * @Version v0.1
 **/
@Service
public class BaseServiceImpl implements BaseService {
    @Autowired
    private Parse parse;
    @Autowired
    private HttpService httpService;
    @Autowired
    private SiteMetaService siteMetaService;
    @Autowired
    private BangumiService bangumiService;
    @Autowired
    private TransTitleService transTitleService;
    @Autowired
    private TransTitleMapper transTitleMapper;
    @Autowired
    private SiteService siteService;
    @Autowired
    private SiteMapper siteMapper;
    @Override

    public void creatDB() {
        String resourcesJson = httpService.getResourcesJson();
        HashMap<String, String> metaAndIterms = parse.separateMetaAndIterms(resourcesJson);
        List<SiteMeta> siteMeta = parse.parseSiteMeta(metaAndIterms.get("siteMeta"));
        siteMetaService.saveOrUpdateBatch(siteMeta);
        List<String> strings = parse.parseItems(metaAndIterms.get("items"));
        List<TransTitle> tlist = new ArrayList<>();
        List<Site> siteList = new ArrayList<>();
        for(String item:strings){
            Bangumi bangumi = parse.parseItemToBangumi(item);
            bangumiService.save(bangumi);
            int bangumiId = bangumi.getId();
            for (TransTitle transTitle : parse.parseItermToTransTitle(item)) {
                transTitle.setBangumiId(bangumiId);
                tlist.add(transTitle);
            }
            for (Site site : parse.parseItemToSite(item)) {
                site.setBangumiId(bangumiId);
                siteList.add(site);
            }
        }
        siteService.saveBatch(siteList);
        transTitleService.saveBatch(tlist);

    }

    @Override
    public void clearDB() {
        siteService.remove(null);
        siteMetaService.remove(null);
        bangumiService.remove(null);
        transTitleService.remove(null);
    }
}
