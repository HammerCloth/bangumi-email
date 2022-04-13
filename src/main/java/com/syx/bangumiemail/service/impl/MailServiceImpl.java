package com.syx.bangumiemail.service.impl;

import com.syx.bangumiemail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @ClassName MailServiceImpl
 * @Description TODO
 * @Author SiYiXiong
 * @Date 2022/4/13 23:02
 * @Version v0.1
 **/
@Service
public class MailServiceImpl implements MailService {
    @Value("${spring.mail.username}")
    private String sendName;
    @Autowired
    private JavaMailSender sender;
    @Autowired
    private TemplateEngine templateEngine;
    @Override
    public void sendSimpleMessage(String subject, String msg, String[] emails) {
        try{
            SimpleMailMessage mail = new SimpleMailMessage();//建立mail对象
            mail.setSubject(subject);
            mail.setText(msg);
            mail.setTo(emails);
            mail.setFrom(sendName);
            sender.send(mail);
            System.out.println("邮件发送成功");
        }catch (Exception ex){
            System.out.println("邮件发送失败");
        }

    }

    @Override
    public void sentHTML(String subject, String msg, String[] emails) {
        try {
            MimeMessage mimeMessage = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(sendName);
            helper.setTo(emails);
            helper.setSubject(subject);
            //初始化模版
            Context context = new Context();
            context.setVariable("msg",msg);
            String emailTemplate = templateEngine.process("emailTemplate", context);
            helper.setText(emailTemplate,true);
            sender.send(mimeMessage);
            System.out.println("发送成功");
        } catch (MessagingException e) {
            System.out.println("发送失败");
        }
    }
}
