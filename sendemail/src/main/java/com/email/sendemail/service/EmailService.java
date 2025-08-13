package com.email.sendemail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.email.sendemail.dto.EmailRequest;
import com.email.sendemail.exception.EmailSendException;

@Service
public class EmailService {
    @Value("${spring.mail.username}")
    String fromEmail;

    @Value("${email.destination}")
    String toEmail;

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMail(EmailRequest emailRequest) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(toEmail);
            message.setSubject("Contacto de: "+emailRequest.getName());
            message.setText(emailRequest.getBody());
            mailSender.send(message);
        } catch (Exception e) {
            throw new EmailSendException("Error enviando email: " + e.getMessage(), e);
        }
    }
}
