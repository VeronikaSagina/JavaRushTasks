package com.javarush.task.task40.task4011;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class YandexMailSender {

    public static void main(String[] args) {
        try {
            sendFromYandex("user@yandex.ru", "5457874", "user@gmail.com", "Привет ", "To be or not to be...");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public static void sendFromYandex(String from, String pass, String to, String subject, String body) throws AddressException, MessagingException {
        Properties props = System.getProperties();
        String host = "smtp.yandex.com";
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.quitwait", "false");
        props.put("mail.debug", "true");

        Session session = Session.getInstance(props);
        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);
        message.setSubject(subject, "UTF-8");
        message.setText(body, "UTF-8");

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress toAddress = new InternetAddress(to);
            message.addRecipient(Message.RecipientType.TO, toAddress);

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
        } catch (MessagingException me) {
        }
    }
}
