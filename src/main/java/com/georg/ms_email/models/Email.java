package com.georg.ms_email.models;

import com.georg.ms_email.enums.Status;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Document(collation = "emails")
public class Email {

    @Id
    private UUID id;

    private String subject;
    private String body;
    private String sender;
    private String receiver;
    private LocalDate sentDate;
    private Status statusEmail;

    public Email(){

    }

    public Email(UUID id, String subject, String body, String sender, String receiver, LocalDate sentDate, Status statusEmail) {
        this.id = id;
        this.subject = subject;
        this.body = body;
        this.sender = sender;
        this.receiver = receiver;
        this.sentDate = sentDate;
        this.statusEmail = statusEmail;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDateTime sentDate) {
        this.sentDate = sentDate;
    }

    public Status getStatusEmail() {
        return statusEmail;
    }

    public void setStatusEmail(Status statusEmail) {
        this.statusEmail = statusEmail;
    }
}
