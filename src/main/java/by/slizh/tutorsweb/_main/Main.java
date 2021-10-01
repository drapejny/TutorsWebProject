package by.slizh.tutorsweb._main;

import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.dao.EntityTransaction;
import by.slizh.tutorsweb.model.dao.FeedbackDao;
import by.slizh.tutorsweb.model.dao.TutorDao;
import by.slizh.tutorsweb.model.dao.UserDao;
import by.slizh.tutorsweb.model.dao.impl.FeedbackDaoImpl;
import by.slizh.tutorsweb.model.dao.impl.TutorDaoImpl;
import by.slizh.tutorsweb.model.dao.impl.UserDaoImpl;
import by.slizh.tutorsweb.model.entity.Feedback;
import by.slizh.tutorsweb.model.entity.Tutor;
import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.util.mail.MailSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws ClassNotFoundException, DaoException, ServiceException {
        System.out.println(Integer.parseInt("0222"));

//        User user = new User.UserBuilder()
//                .setFirstName("admin")
//                .setLastName("admin")
//                .setEmail("admin@yandex.by")
//                .setCity("Минск")
//                .setRole(User.Role.ADMIN)
//                .setStatus(User.Status.ACTIVATED)
//                .createUser();
//        User admin = new User.UserBuilder()
//                .setFirstName("a")
//                .setLastName("a")
//                .setEmail("a@a.by")
//                .setCity("Минск")
//                .setRole(User.Role.USER)
//                .setStatus(User.Status.ACTIVATED)
//                .createUser();
//
//        Tutor tutor = new Tutor.TutorBuilder()
//                .setFirstName("b")
//                .setLastName("b")
//                .setEmail("b@b.by")
//                .setPhone("+375294444444")
//                .setCity("Минск")
//                .setRole(User.Role.TUTOR)
//                .setStatus(User.Status.ACTIVATED)
//                .setEducation("BSUIR")
//                .setInfo("Some info")
//                .setPricePerHour(10)
//                .setActive(true)
//                .createTutor();
//
//        UserDao userDao = new UserDaoImpl();
//        TutorDao tutorDao = new TutorDaoImpl();
//        EntityTransaction transaction = new EntityTransaction();
//        try {
//            transaction.initTransaction(userDao, tutorDao);
//            userDao.create(user, "123123");
//            userDao.create(admin, "123123");
//            userDao.create(tutor, "123123");
//            tutorDao.create(tutor);
//        } finally {
//            transaction.endTransaction();
//        }
    }

}
