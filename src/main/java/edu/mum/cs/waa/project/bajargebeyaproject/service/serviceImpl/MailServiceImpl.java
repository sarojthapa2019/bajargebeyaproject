package edu.mum.cs.waa.project.bajargebeyaproject.service.serviceImpl;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.Email;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.User;
import edu.mum.cs.waa.project.bajargebeyaproject.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

@Service
public class MailServiceImpl implements MailService {
    private final String MailSender = "bajargebeya@outlook.com";
    private final String MailPassword = "bajar123";

    @Autowired
    private JavaMailSender sender;

    public void sendMail(Email email) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.office365.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MailSender, MailPassword);
            }
        });
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(MailSender, false));

        msg.setRecipients(Message.RecipientType.TO, email.getReceivers());
        msg.setSubject(email.getSubject());
        msg.setContent(email.getMessage(), "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(email.getMessage(), "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        MimeBodyPart attachPart = new MimeBodyPart();

        if(email.getFilePath()!=null) {
            attachPart.attachFile(email.getFilePath());
            multipart.addBodyPart(attachPart);
        }
        msg.setContent(multipart);
        Transport.send(msg);

        System.out.println("Mail sent");
    }

    @Override
    public void sendMail(User receiver, String subject, String message) {
        Email email = new Email();
        email.addReceiver(receiver.getEmail());
        email.setMessage(message);
        email.setSubject(subject);
        try {
            sendMail(email);
            System.out.println("Mail sent to "+receiver.getFirstName()+" successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Mail sending failed!!!");
        }
    }
}
