package edu.mum.cs.waa.project.bajargebeyaproject;

import com.sun.mail.util.MailSSLSocketFactory;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.*;
import edu.mum.cs.waa.project.bajargebeyaproject.service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.security.GeneralSecurityException;
import java.time.LocalDate;

@SpringBootApplication
public class BajargebeyaprojectApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BajargebeyaprojectApplication.class, args);
//        sendMailCheck(context);
    }

    private static void sendMailCheck(ApplicationContext context) {
        MailService mailService = context.getBean(MailService.class);
        try {
            Email email = new Email();
            email.setSubject("Application starts");
            email.setMessage("App has started");
            email.addReceiver("sujiv.shrestha@mum.edu");

            mailService.sendMail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    public JavaMailSender getJavaMailSender() throws GeneralSecurityException {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        return mailSender;
    }
}
