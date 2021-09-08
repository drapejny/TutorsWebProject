package by.slizh.tutorsweb._main;

import by.slizh.tutorsweb.entity.Subject;
import by.slizh.tutorsweb.entity.Tutor;
import by.slizh.tutorsweb.entity.User;
import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.dao.EntityTransaction;
import by.slizh.tutorsweb.model.dao.SubjectDao;
import by.slizh.tutorsweb.model.dao.TutorDao;
import by.slizh.tutorsweb.model.dao.UserDao;
import by.slizh.tutorsweb.model.dao.impl.SubjectDaoImpl;
import by.slizh.tutorsweb.model.dao.impl.TutorDaoImpl;
import by.slizh.tutorsweb.model.dao.impl.UserDaoImpl;
import by.slizh.tutorsweb.service.UserService;
import by.slizh.tutorsweb.service.impl.UserServiceImpl;
import by.slizh.tutorsweb.util.PasswordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class Main {
    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) throws ClassNotFoundException, DaoException, ServiceException {
//        Tutor tutor = new Tutor.TutorBuilder()
//                .setUserId(5)
//                .setTutorId(2)
//                .setFirstName("Andreiqqw12123213312eii")
//                .setLastName("Slizh")
//                .setEmail("slizh1@yandex.by")
//                .setPhone("123")
//                .setCity("Grodno")
//                .setRole(User.Role.TUTOR)
//                .setStatus(User.Status.ACTIVE)
//                .setEducation("BSUIR")
//                .setInfo("info b")
//                .setPricePerHour(BigDecimal.valueOf(25))
//                .createTutor();
//        UserDao userDao = new UserDaoImpl();
//        EntityTransaction transaction = new EntityTransaction();
//        transaction.init(userDao);
//        userDao.update(tutor);
//        transaction.end();
        String hash = PasswordEncoder.encodePassword("123");
        System.out.println(PasswordEncoder.checkPassword("123",hash));
    }
}
