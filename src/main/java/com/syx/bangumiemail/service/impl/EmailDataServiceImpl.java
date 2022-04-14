package com.syx.bangumiemail.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.syx.bangumiemail.model.Bangumi;
import com.syx.bangumiemail.model.EmailData;
import com.syx.bangumiemail.model.Site;
import com.syx.bangumiemail.model.TransTitle;
import com.syx.bangumiemail.service.BangumiService;
import com.syx.bangumiemail.service.EmailDataService;
import com.syx.bangumiemail.service.SiteService;
import com.syx.bangumiemail.service.TransTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName EmailDataServiceImpl
 * @Description TODO
 * @Author SiYiXiong
 * @Date 2022/4/14 14:57
 * @Version v0.1
 **/
@Service
public class EmailDataServiceImpl implements EmailDataService {
    @Autowired
    private BangumiService bangumiService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private TransTitleService transTitleService;
    @Override
    public List<EmailData> getAllNoEnd() {
        QueryWrapper<Bangumi> wrapper = new QueryWrapper<>();
        wrapper.eq("end_flag",0);
        List<Bangumi> list = bangumiService.list(wrapper);
        List<EmailData> result = new ArrayList<>();
        for (Bangumi bangumi : list) {
            EmailData data = new EmailData();
            data.setBangumi(bangumi);

            QueryWrapper<Site> siteQueryWrapper = new QueryWrapper<>();
            siteQueryWrapper.eq("bangumi_id",bangumi.getId());
            List<Site> sites = siteService.list(siteQueryWrapper);
            data.setSites(sites);

            QueryWrapper<TransTitle> ttQueryWrapper = new QueryWrapper<>();
            ttQueryWrapper.eq("bangumi_id",bangumi.getId());
            List<TransTitle> transTitles = transTitleService.list(ttQueryWrapper);
            data.setTransTitles(transTitles);

            result.add(data);
        }
        return result;
    }
}
