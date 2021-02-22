package com.devsdg.digipos.GestionEmail.Service;


import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
public class MyAuthentication extends Authenticator {
    private final static String id = "contact@digiplus-cm.com";
    private final static String pw = "Contactcm123.";


    public boolean sendMail(String TO, String message, String subject) {


        Properties p = System.getProperties();
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.host", "mail.digiplus-cm.com");
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.port", "587");
        p.put("mail.username",id);
        p.put("mail.password",pw);

        try {
            Session session = Session.getDefaultInstance(p, null);
            Transport transport=session.getTransport();
            MimeMessage msg = new MimeMessage(session);

            msg.setSentDate(new Date());
            InternetAddress from = new InternetAddress();
            from = new InternetAddress("Digishop<" + id + ">");
            // sender
            msg.setFrom(from);
            // receiver
            InternetAddress to = new InternetAddress(TO);
            // title
            msg.setSubject(subject, "UTF-8");
            // context
            msg.setText(message, "UTF-8");
            // header
            msg.setHeader("content-Type", "text/html");
            msg.addRecipient(Message.RecipientType.TO, to);
            // send msg
            transport.connect(p.getProperty("mail.smtp.host"),p.getProperty("mail.username"),p.getProperty("mail.password"));
            transport.sendMessage(msg,msg.getRecipients(Message.RecipientType.TO));
            transport.close();

        } catch (AddressException addr_e) {
            addr_e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return true;
    }
}
