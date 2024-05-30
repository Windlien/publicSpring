package com.example.publicspring;

import cn.hutool.extra.mail.Mail;
import cn.hutool.extra.mail.MailAccount;
import com.example.publicspring.windLien.util.StringUtil;
import com.sun.mail.util.MailSSLSocketFactory;

import java.security.GeneralSecurityException;
import java.util.regex.Pattern;

public class TestMail {
    public static final Pattern REGEX_MAIL_ADDRESS = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");

    public void testMail() throws Exception {
        MailAccount mailAccount = getMailAccount();
        Mail mail = Mail.create(mailAccount);
        String toEmail = "";
        if (StringUtil.isBlank(toEmail)) {
            System.out.println("[]用户没有配置邮箱");
        } else if (StringUtil.isY("CHECK_USER_EMAIL_ADDRESS_FORMAT") && !REGEX_MAIL_ADDRESS.matcher(toEmail).matches()) {
            System.out.println("用户邮箱格式不正确");
        } else {
            mail.setHtml(true);
            mail.to(toEmail);
            mail.setCcs("抄送");
            mail.setTitle("");
            mail.setContent("");
            mail.send();
        }
    }

    private MailAccount getMailAccount() {
        MailAccount mailAccount = new MailAccount();
        mailAccount.setSslEnable(StringUtil.isY("EMAIL_SSL_FLAG"));
        mailAccount.setHost("smtp.mail.server");
        mailAccount.setPort(25);
        mailAccount.setAuth(true);
        mailAccount.setUser(("EMAIL_USERNAME"));
        mailAccount.setPass(("EMAIL_PASSWORD"));
        mailAccount.setFrom(("EMAIL_FROM"));
        mailAccount.setDebug(false);
        mailAccount.setConnectionTimeout(Long.parseLong("9000"));
        mailAccount.setTimeout(Long.parseLong(("40000")));
        if (mailAccount.isSslEnable()) {
            // 手动跳过SSL验证
            MailSSLSocketFactory sf = null;
            try {
                sf = new MailSSLSocketFactory();
            } catch (GeneralSecurityException e) {
                throw new RuntimeException(e);
            }
            sf.setTrustAllHosts(true);
            mailAccount.setCustomProperty("mail.smtp.ssl.socketFactory", sf);
        }
        return mailAccount;
    }
}
