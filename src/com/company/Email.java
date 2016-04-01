package com.company;

import java.io.Serializable;

/**
 * Created by Petko on 4/1/2016.
 */
public class Email implements Serializable{

    private String content;
    private String recipient;
    private String subject;

    public Email(String c, String r, String s) {
        content = c;
        recipient = r;
        subject = s;
    }

    public String getContent() { return content; }
    public String getRecipient() {return recipient; }
    public String getSubject() { return subject; }

    @Override
    public String toString() {
        return new StringBuffer("Recipient: ")
                .append(recipient)
                .append("Subject: ")
                .append(subject)
                .append("Content: ")
                .append(content).toString();
    }
}
