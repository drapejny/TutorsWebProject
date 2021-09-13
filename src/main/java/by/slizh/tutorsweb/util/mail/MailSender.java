package by.slizh.tutorsweb.util.mail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MailSender {

    private static final Logger logger = LogManager.getLogger();

    private static final String PROPERTIES_PATH = "mail/mail.properties";
    private static final String MESSAGE_SUBJECT = "Verification";
    private static final String MESSAGE_FIRST_PART = "To complete the registration, click the link <a href='http://localhost:8080/TutorsWebProject_war_exploded/controller?command=verification&user_id=";
    private static final String MESSAGE_SECOND_PART = "'>verification</a>";
    private static final String MESSAGE_TYPE = "text/html";

    private Properties properties = new Properties();
    private MimeMessage message;

    public MailSender() {
        try (InputStream inputStream = MailSender.class.getClassLoader().getResourceAsStream(PROPERTIES_PATH)) {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("Error loading mail properties", e);
        }
    }

    public void send(int userId, String userEmail) {
        try {
            initMessage(userId, userEmail);
            Transport.send(message);
        } catch (MessagingException e) {
            logger.error("Error sending message", e);
        }


    }

    private void initMessage(int userId, String userMail) {
        Session mailSession = SessionFactory.getInstance().createSession(properties);
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);
        try {
            message.setSubject(MESSAGE_SUBJECT);
            message.setContent(MESSAGE_FIRST_PART + userId + MESSAGE_SECOND_PART, MESSAGE_TYPE);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(userMail));
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }
}
