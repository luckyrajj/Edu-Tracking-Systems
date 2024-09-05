package com.jsp.rest.ets.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MailSender {

    private JavaMailSender mailSender;

    @Async
    public void sendMail(MessageModel messageModel)throws MessagingException {
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
        helper.setTo(messageModel.getTo());
        helper.setSentDate(messageModel.getSendDate());
        helper.setSubject(messageModel.getSubject());
        helper.setText(messageModel.getText(),true);
        mailSender.send(mimeMessage);
    }
}
