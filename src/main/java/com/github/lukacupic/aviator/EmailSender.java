package com.github.lukacupic.aviator;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
    private final String to;
    private final String from;
    private final CredentialsConfig config;
    private Properties properties;

    public EmailSender(String to, String from, CredentialsConfig config) {
        this.to = to;
        this.from = from;
        this.config = config;

        init();
    }

    private void init() {
        this.properties = System.getProperties();

        // Setup mail server
        this.properties.put("mail.smtp.host", "smtp.gmail.com");
        this.properties.put("mail.smtp.port", "465");
        this.properties.put("mail.smtp.ssl.enable", "true");
        this.properties.put("mail.smtp.auth", "true");
    }

    public void send(String subject, String text) throws MessagingException {
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        config.getCredential(Credential.GMAIL_USERNAME),
                        config.getCredential(Credential.GMAIL_PASSWORD)
                );
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

        message.setSubject(subject);
        message.setText(text);

        Transport.send(message);
    }
}