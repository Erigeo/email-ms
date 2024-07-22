package com.georg.ms_email.services;

import com.georg.ms_email.enums.Status;
import com.georg.ms_email.models.Email;
import com.georg.ms_email.repositories.EmailRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendEmail(Email email) {
        email.setSentDate(LocalDateTime.now());
        email.setId(UUID.randomUUID());
        email.setSender("capturedex@gmail.com");
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");


            helper.setFrom(email.getSender(), "CaptureDex");
            helper.setTo(email.getReceiver());
            helper.setSubject(email.getSubject());
            helper.setSentDate(java.util.Date.from(email.getSentDate().atZone(java.time.ZoneId.systemDefault()).toInstant()));


            Context context = new Context();
            context.setVariable("subject", email.getSubject());
            context.setVariable("body", email.getBody());


            String htmlContent = templateEngine.process("emailTemplate", context);
            helper.setText(htmlContent, true);

            emailSender.send(mimeMessage);

            email.setStatusEmail(Status.SENT);
        } catch (Exception e) {
            email.setStatusEmail(Status.ERROR);
            System.err.println("Error sending email: " + e.getMessage());
        } finally {
            emailRepository.save(email);
        }
    }

    public Optional<Email> findByUUID(UUID uuid) {
        return emailRepository.findById(uuid);
    }

    public Page<Email> findByReceiver(String receiver, Pageable pageable) {
        return emailRepository.findByReceiver(receiver, pageable);
    }

    public Page<Email> findAll(Pageable pageable) {
        return emailRepository.findAll(pageable);
    }
}
