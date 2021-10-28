package by.slizh.tutorsweb.model.util.mail;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 * The type SessionFactory.
 */
class SessionFactory {

    private static SessionFactory instance = new SessionFactory();
    private static final String MAIL_USER_NAME = "mail.user.name";
    private static final String MAIL_USER_PASSWORD = "mail.user.password";
    private SessionFactory() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static SessionFactory getInstance() {
        return instance;
    }

    /**
     * Create mail session.
     *
     * @param properties the properties
     * @return the session
     */
    public Session createSession(Properties properties) {
        String userName = properties.getProperty(MAIL_USER_NAME);
        String userPassword = properties.getProperty(MAIL_USER_PASSWORD);
        return Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, userPassword);
            }
        });

    }
}
