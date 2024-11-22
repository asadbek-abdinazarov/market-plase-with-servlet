package javachi.biz.marketplaseservlet.service;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import javachi.biz.marketplaseservlet.config.EmailConfig;

public class EmailService {
    public static void sendEmail(String toEmail, String subject, String messageBody) {
        try {
            Session session = EmailConfig.getEmailSession();

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("javachibiz@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(messageBody);

            Transport.send(message);
            System.out.println("Email successfully sent to " + toEmail);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
