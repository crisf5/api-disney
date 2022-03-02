package com.disney.api.service.impl;

import com.disney.api.service.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private Environment env;

    @Value("${disney.api.email.sender}")
    private String emailSender;

    @Value("${disney.api.email.enabled}")
    private boolean enabled;

    private static final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Override
    public void SendWelcomeEmailTo(String to) {
        if(!enabled){return;}
        String apiKey = env.getProperty("EMAIL_API_KEY");

        Email fromEmail = new Email(emailSender);
        Email toEmail = new Email(to);
        Content content = new Content(
                "text/plain",
                "Welcome to the Api Disney World!"
        );

        String subject = "Alkemy Disney";

        Mail mail = new Mail(fromEmail, subject, toEmail, content);
        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            log.info("{}",response.getStatusCode());
            log.info("{}",response.getBody());
            log.info("{}",response.getHeaders());

        } catch (IOException ex) {
            log.info("Error trying to send email");
        }

    }
}
