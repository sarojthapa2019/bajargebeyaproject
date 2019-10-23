package edu.mum.cs.waa.project.bajargebeyaproject.service.serviceImpl;

import edu.mum.cs.waa.project.bajargebeyaproject.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender sender;

    public void sendMail2(){

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo("sujiv.sth@gmail.com");
            helper.setText("How are you?");
            helper.setSubject("Hi");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        sender.send(message);
    }
    public void sendMail() throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("sujiv.sth@gmail.com", "suzeve69");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("sujiv.sth@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("sujiv.shrestha@mum.edu"));
        msg.setSubject("Tutorials point email");
        msg.setContent("Tutorials point email", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Tutorials point email", "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        MimeBodyPart attachPart = new MimeBodyPart();

        attachPart.attachFile("/data.sql");
        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);

        System.out.println("Mail sent");
    }
}
