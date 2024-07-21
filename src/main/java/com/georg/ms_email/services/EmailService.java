package com.georg.ms_email.services;


import com.georg.ms_email.enums.Status;
import com.georg.ms_email.models.Email;
import com.georg.ms_email.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

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

    public Optional<Email> findByUUID(UUID uuid) {
        return emailRepository.findById(uuid);
    }

   // public Page<Email> findByReceiver(String receiver, int page, int size) {
     //   Pageable pageable = PageRequest.of(page, size);
      //  return emailRepository.findByReceiver(receiver, (java.awt.print.Pageable) pageable);
    //}

    public Page<Email> findByReceiver(String receiver, Pageable pageable) {
        return emailRepository.findByReceiver(receiver, (java.awt.print.Pageable) pageable);
    }

    public Page<Email> findAll(Pageable pageable) {
        return emailRepository.findAll(pageable);
    }


}
