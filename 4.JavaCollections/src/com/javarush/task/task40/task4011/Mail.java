package com.javarush.task.task40.task4011;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class Mail {
    public static void main(String[] args) throws MessagingException {
        Properties properties = new Properties();
        Session session = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(session);

//устанавливаем тему письма
        message.setSubject("тестовое письмо!");

//добавляем текст письма
        message.setText("Asta la vista, baby!");

//указываем получателя
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("user@yandex.ru"));

//указываем дату отправления
        message.setSentDate(new Date());
    }
}
