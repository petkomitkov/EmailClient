package com.company;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.management.RuntimeErrorException;

/**
 * Created by Petko on 4/1/2016.
 */
public class EmailHelper {

    public static void sendEmail() {

        Scanner input = new Scanner(System.in);
        String username, password;
        System.out.println("Please enter your username: ");
        username = input.nextLine();
        System.out.println("Please enter your password: ");
        password = input.nextLine();

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(
                properties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                }
        );

        try {
            Message message = new MimeMessage(session) {
            };
            message.setFrom(new InternetAddress(username));

            System.out.println("Enter the recepient address: ");
            String recipient = input.nextLine();

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient));

            System.out.println("Please enter the subject: ");
            String subject = input.nextLine();

            message.setSubject(subject);

            System.out.println("Please enter the content: ");
            String content = input.nextLine();

            message.setContent("<h:body style=background-color:white;font-family:verdana;color:#66ccff;>"
            + content + "<br/><br/>" +
            "</body>", "text/html; charset=utf-8");

            Transport.send(message);
            SaveEmail(new Email(content, recipient, subject));

            System.out.println("The message was sent successfully.");
        }
        catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void showLastEmail() {
        Email email;

        try{

            FileInputStream fileInput = new FileInputStream("email.ser");
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            email = (Email)objectInput.readObject();
            objectInput.close();

            System.out.println("Recipient : " + email.getRecipient());
            System.out.println("Subject: " + email.getSubject());
            System.out.println("Content : " + email.getContent());

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void SaveEmail(Email email) {
        try {
            FileOutputStream fileOutput = new FileOutputStream("email.ser");
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(email);
            objectOutput.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
