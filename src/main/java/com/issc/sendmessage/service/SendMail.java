package com.issc.sendmessage.service;

import com.issc.sendmessage.Utils.PropertiesHelper;
import com.issc.sendmessage.bean.DataObject;


import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * 封装数据并发送
 * @author yyud
 */

public class SendMail {
    private List<String> key ;
    private List<String> value;
    private static SendMail sendMail = null;
    public static void main(String[] args) throws Exception {
        SendMail.creakMail();
       // TimingUtil.timer();
    }

    public static SendMail getSendMailObject(){
        return sendMail;
    }


    private SendMail(){
        key = new ArrayList<>();
        value = new ArrayList<>();
        PropertiesHelper.decryptionReadePropertiesFile("configfile.properties","AES加密",key,value);
    }


    /**
     * 发送邮件邮箱配置
     * @throws Exception
     */
    public static void creakMail() throws Exception {
        sendMail = new SendMail();
        DataObject dataObject = GetMessage.resultMessage();
        //注：建议使用主流邮箱，我曾遇到用sohu发出邮件丢失的情况，
        //不仅仅是在程序这里，包括在sohu邮箱客户端测试也发生了邮件丢失
        Properties prop = new Properties();
//        prop.setProperty("mail.host", "smtp.qq.com");
//        prop.setProperty("mail.transport.protocol", "smtp");
//        prop.setProperty("mail.smtp.auth", "true");
        //prop.setProperty(PropertiesHelper.readePropertiesFile("SetProperty1"), PropertiesHelper.readePropertiesFile("SetProperty2"));
        //prop.setProperty(PropertiesHelper.readePropertiesFile("SetProperty3"), PropertiesHelper.readePropertiesFile("SetProperty4"));
        //prop.setProperty(PropertiesHelper.readePropertiesFile("SetProperty5"), PropertiesHelper.readePropertiesFile("SetProperty6"));
        prop.setProperty(sendMail.getValue().get(10), sendMail.getValue().get(6));
        prop.setProperty(sendMail.getValue().get(3), sendMail.getValue().get(5));
        prop.setProperty(sendMail.getValue().get(0), sendMail.getValue().get(9));

        //使用STARTTLS,对于其它协议（如pop3，imap），只需要将smtp改为相应协议即可（pop3要改为pop）
        // 若要使用SSL，只需要设置mail.smtp.ssl.enable为true
        //prop.put(PropertiesHelper.readePropertiesFile("SetProperty7"), PropertiesHelper.readePropertiesFile("SetProperty6"));
        prop.put(sendMail.getValue().get(2), sendMail.getValue().get(9));

        //使用JavaMail发送邮件的5个步骤
        //1、创建session
        Session session = Session.getInstance(prop);
        //开启Session的debug模式，便于看到发送Email的运行状态
        session.setDebug(true);
        //2、通过session得到transport对象
        Transport ts = session.getTransport();
        //3、发件邮箱的帐号和密码。
        //ts.connect("990715561@qq.com", "lyqwleprxyembdjj");
        //ts.connect(PropertiesHelper.readePropertiesFile("UserName"), PropertiesHelper.readePropertiesFile("PassWord"));
        ts.connect(sendMail.getValue().get(4), sendMail.getValue().get(7));
        //4、创建邮件（Java用Message对象封装（代表）邮件），其中，最后一个参数含义：
        //（收件人<-->RecipientType.TO，抄送<-->RecipientType.CC，密送<-->RecipientType.BBC）
        Message message = createSimpleMail(session, "nice dream", dataObject.getResult().get(0).getContent(), Message.RecipientType.TO);
        //5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }

    /**
     * 发送邮件配置
     * @param session
     * @param title
     * @param content
     * @param type
     * @return
     * @throws Exception
     */

    public static MimeMessage createSimpleMail(Session session, String title, String content, Message.RecipientType type)
            throws Exception {
        //创建MIME邮件对象
        MimeMessage message = new MimeMessage(session);

        //指定发件人昵称
        String nick = MimeUtility.encodeText("晚安好梦！", "utf-8", "B");//避免乱码

        //指明邮件的发件人
        //message.setFrom(new InternetAddress(nick + "<"+PropertiesHelper.readePropertiesFile("UserName")+">"));
        message.setFrom(new InternetAddress(nick + "<"+sendMail.getValue().get(4) + ">"));
        //指明邮件的收件人
        // message.addRecipient(type, new InternetAddress(PropertiesHelper.readePropertiesFile("SendTo")));
           message.addRecipient(type, new InternetAddress(sendMail.getValue().get(13)));
        //邮件主题
        message.setSubject(title);

        //邮件的文本内容，加入一些符号让结尾美美哒(^.^)(^.^)
        message.setContent(content.substring(0, content.length() - 1) + "==>>(^.^)(^.^)晚安!", "text/html;charset=UTF-8");

        //返回创建好的邮件对象
        return message;
    }

    public List<String> getValue() {
        return value;
    }

    public List<String> getKey() {
        return key;
    }
}