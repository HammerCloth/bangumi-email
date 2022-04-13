package com.syx.bangumiemail.service;

/**
 * @ClassName MailService
 * @Description TODO
 * @Author SiYiXiong
 * @Date 2022/4/13 22:39
 * @Version v0.1
 **/
public interface MailService {

    /**
     * 发送简单的邮件消息给邮箱里面
     * @param msg 简单的邮件消息
     * @param subject 邮件主题
     * @param emails email集合
     */
    public void sendSimpleMessage(String subject,String msg,String[] emails);

    /**
     * 通过套用模版来发送邮件
     * @param msg 简单的邮件消息
     * @param subject 邮件主题
     * @param emails email集合
     */
    public void sentHTML(String subject,String msg,String[] emails);


}
