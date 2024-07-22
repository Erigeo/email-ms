package com.georg.ms_email.dto;


public class EmailDto {


    private String subject;
    private String body;
    private String receiver;



    public EmailDto(String subject, String body, String receiver) {
        this.subject = subject;
        this.body = body;

        this.receiver = receiver;
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



    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
