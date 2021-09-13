package by.slizh.tutorsweb._main;

import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.util.mail.MailSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws ClassNotFoundException, DaoException, ServiceException {
        // System.out.println(LocalDateTime.now());
//        MailSender mailSender = new MailSender();
//        mailSender.send(1,"antoshka-51762@yandex.by");
//        User user = new User.UserBuilder()
//                .setFirstName("Anton")
//                .setLastName("Slizh")
//                .setEmail("slizh@yandex.by")
//                .setPhone("+375297777777")
//                .setCity("Минск")
//                .setRole(User.Role.USER)
//                .setStatus(User.Status.ACTIVATED)
//                .createUser();
//        User admin = new User.UserBuilder()
//                .setFirstName("Tom")
//                .setLastName("Parker")
//                .setEmail("parker@yandex.by")
//                .setPhone("+375298888888")
//                .setCity("Гродно")
//                .setRole(User.Role.ADMIN)
//                .setStatus(User.Status.ACTIVATED)
//                .createUser();
//
//        Tutor tutor = new Tutor.TutorBuilder()
//                .setFirstName("Bob")
//                .setLastName("Dylan")
//                .setEmail("dylan@yandex.by")
//                .setPhone("+375294444444")
//                .setCity("Минск")
//                .setRole(User.Role.TUTOR)
//                .setStatus(User.Status.ACTIVATED)
//                .setEducation("BSUIR")
//                .setInfo("Some info")
//                .setPricePerHour(BigDecimal.valueOf(25))
//                .setActive(true)
//                .createTutor();
//        Feedback feedback = new Feedback.FeedbackBuilder()
//                .setText("Some text")
//                .setDate(LocalDate.now())
//                .setRating(5)
//                .setUserId(1)
//                .setTutorId(1)
//                .createFeedback();
//        System.out.println(LocalDateTime.now());
//        UserDao userDao = new UserDaoImpl();
//        TutorDao tutorDao = new TutorDaoImpl();
//        FeedbackDao feedbackDao = new FeedbackDaoImpl();
//        EntityTransaction transaction = new EntityTransaction();
////
//        try {
//            transaction.initTransaction(userDao, tutorDao, feedbackDao);
////            userDao.create(user, "123");
////            userDao.create(admin, "123");
////            userDao.create(tutor, "123");
////            tutorDao.create(tutor);
//            tutor.setTutorId(2);
//            tutor.setActive(true);
//            tutorDao.update(tutor);
//
//
//        } finally {
//            transaction.endTransaction();
//        }
    }

}
