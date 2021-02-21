package com.afiefafian.demoemail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.mail.MessagingException;
import java.net.URL;

@SpringBootApplication
public class DemoEmailApplication {

    @Autowired
    private EmailSenderService emailSenderService;

    public static void main(String[] args) {
        SpringApplication.run(DemoEmailApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void triggerMail() throws MessagingException {
        emailSenderService.sendEmail(
                "test@test.com",
                "Test body Email",
                "Subject test"
        );

        URL url = this.getClass().getClassLoader().getResource("img.png");

        emailSenderService.sendEmailWithAttachment(
                "test@test.com",
                "Test body Email With Attachment",
                "Subject test with attachment",
                url.getPath()
        );
    }
}
