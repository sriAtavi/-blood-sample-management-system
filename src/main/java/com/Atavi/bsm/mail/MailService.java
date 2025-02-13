package com.Atavi.bsm.mail;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Component
@AllArgsConstructor
public class MailService
{
    //Through Spring, we cannot send Normal Mobile message and Watsapp messages
    //Third party services = > twilio supports mail, Mobile messages, watsapp pages, Bulk messages also supported -> Popular n most
    // AWS SeS (Simple Email Service) - cost effective => limited to Only Mail, no other Mobile type messages

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public  void sendMail(String to, String subject, String text) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true); // MimeMessageHelper helps to populate the message in MimeMessage
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text ,true);
        mailSender.send(mimeMessage);
    }

    public String generateContent(String templateName, Map<String, Object> variables)
    {
        Context context = new Context();
        context.setVariables(variables); // Converting Thymeleaf template to String
        // Process the overall data and converts it into String HTML Document
        // IN the Context, placeholders
        return templateEngine.process(templateName, context); // returns the String format of Templates

    }
    //Simple Messaging & MIME = mIME OBJECT & HELPER OBJECT

}
