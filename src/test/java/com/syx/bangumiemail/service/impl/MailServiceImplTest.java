package com.syx.bangumiemail.service.impl;

import com.syx.bangumiemail.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ClassName MailServiceImplTest
 * @Description TODO
 * @Author SiYiXiong
 * @Date 2022/4/13 23:18
 * @Version v0.1
 **/
class MailServiceImplTest {

    @Autowired
    private MailService mailService;
    @Test
    void testa(){
        mailService.sendSimpleMessage("简单的邮件","jiandande youjian ",new String[]{"424193726@qq.com","18552541076@163.com"});
    }

}