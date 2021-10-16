package by.slizh.tutorsweb._main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();


    public static void main(String[] args) {

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
