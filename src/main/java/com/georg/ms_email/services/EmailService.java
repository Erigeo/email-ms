package com.georg.ms_email.services;


import com.georg.ms_email.enums.Status;
import com.georg.ms_email.models.Email;
import com.georg.ms_email.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    JavaMailSender emailSender;

    public void sendEmail(Email email ) {
        email.setSentDate(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getSender());
            message.setSubject(email.getSubject());
            message.setSentDate(email.getSentDate());
            message.setText(email.getBody());
            message.setTo(email.getReceiver());
            emailSender.send(message);

            email.setStatusEmail(Status.SENT);
        } catch (Exception e){
            email.setStatusEmail(Status.ERROR);
        }
        finally {
            emailRepository.save(email);
        }
    }

    public Optional<Email> findByUUID(String uuid) {

    }

    public Page<Email> findByReceiver(String receiver) {

    }

    public Page<Email> findAll(Pageable pageable) {

    }


}
