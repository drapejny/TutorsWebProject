package by.slizh.tutorsweb._main;

import by.slizh.tutorsweb.entity.Feedback;
import by.slizh.tutorsweb.entity.Subject;
import by.slizh.tutorsweb.entity.Tutor;
import by.slizh.tutorsweb.entity.User;
import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.dao.*;
import by.slizh.tutorsweb.model.dao.impl.FeedbackDaoImpl;
import by.slizh.tutorsweb.model.dao.impl.SubjectDaoImpl;
import by.slizh.tutorsweb.model.dao.impl.TutorDaoImpl;
import by.slizh.tutorsweb.model.dao.impl.UserDaoImpl;
import by.slizh.tutorsweb.service.UserService;
import by.slizh.tutorsweb.service.impl.UserServiceImpl;
import by.slizh.tutorsweb.util.PasswordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws ClassNotFoundException, DaoException, ServiceException {
        User user = new User.UserBuilder()
                .setFirstName("Anton")
                .setLastName("Slizh")
                .setEmail("slizh@yandex.by")
                .setPhone("+375297777777")
                .setCity("Минск")
                .setRole(User.Role.USER)
                .setStatus(User.Status.ACTIVE)
                .createUser();
        User admin = new User.UserBuilder()
                .setFirstName("Tom")
                .setLastName("Parker")
                .setEmail("parker@yandex.by")
                .setPhone("+375298888888")
                .setCity("Гродно")
                .setRole(User.Role.ADMIN)
                .setStatus(User.Status.ACTIVE)
                .createUser();

        Tutor tutor = new Tutor.TutorBuilder()
                .setFirstName("Bob")
                .setLastName("Dylan")
                .setEmail("dylan@yandex.by")
                .setPhone("+375294444444")
                .setCity("Минск")
                .setRole(User.Role.TUTOR)
                .setStatus(User.Status.ACTIVE)
                .setEducation("BSUIR")
                .setInfo("Some info")
                .setPricePerHour(BigDecimal.valueOf(25))
                .createTutor();
        Feedback feedback = new Feedback.FeedbackBuilder()
                .setText("Some text")
                .setDate(LocalDate.now())
                .setRating(5)
                .setUserId(1)
                .setTutorId(1)
                .createFeedback();
        System.out.println(LocalDateTime.now());
        UserDao userDao = new UserDaoImpl();
        TutorDao tutorDao = new TutorDaoImpl();
        FeedbackDao feedbackDao = new FeedbackDaoImpl();
        EntityTransaction transaction = new EntityTransaction();

        try {
            transaction.initTransaction(userDao, tutorDao, feedbackDao);
//            userDao.create(user, "123");
//            userDao.create(admin, "123");
//            userDao.create(tutor, "123");
//            tutorDao.create(tutor);
            feedback.setFeedbackId(4);
            feedback.setText("Some text");
            feedbackDao.update(feedback);


        } finally {
            transaction.endTransaction();
        }
    }
}
