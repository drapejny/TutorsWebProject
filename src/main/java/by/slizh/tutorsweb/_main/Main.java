package by.slizh.tutorsweb._main;

import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.model.dao.EntityTransaction;
import by.slizh.tutorsweb.model.dao.FeedbackDao;
import by.slizh.tutorsweb.model.dao.SubjectDao;
import by.slizh.tutorsweb.model.dao.impl.FeedbackDaoImpl;
import by.slizh.tutorsweb.model.dao.impl.SubjectDaoImpl;
import by.slizh.tutorsweb.model.entity.Feedback;
import by.slizh.tutorsweb.model.entity.Subject;
import by.slizh.tutorsweb.model.entity.Tutor;
import by.slizh.tutorsweb.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws DaoException {
        new A();
//        User user = new User.UserBuilder()
//                .setFirstName("a")
//                .setLastName("a")
//                .setEmail("a@a.by")
//                .setRole(User.Role.TUTOR)
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
//                .setFirstName("a")
//                .setLastName("a")
//                .setEmail("a@a.by")
//                .setPhone("+375294444444")
//                .setCity("Минск")
//                .setRole(User.Role.TUTOR)
//                .setStatus(User.Status.ACTIVATED)
//                .setEducation("BSUIR")
//                .setInfo("Some info")
//                .setPricePerHour(10)
//                .setIsActive(true)
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
