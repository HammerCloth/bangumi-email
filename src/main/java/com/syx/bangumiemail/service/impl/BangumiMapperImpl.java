package com.syx.bangumiemail.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syx.bangumiemail.mapper.BangumiMapper;
import com.syx.bangumiemail.model.Bangumi;
import com.syx.bangumiemail.service.BangumiService;
import org.springframework.stereotype.Service;

/**
 * @ClassName BangumiMapperImpl
 * @Description 实现类
 * @Author SiYiXiong
 * @Date 2022/4/9 23:25
 * @Version v0.1
 **/
@Service
public class BangumiMapperImpl extends ServiceImpl<BangumiMapper, Bangumi> implements BangumiService {
}
