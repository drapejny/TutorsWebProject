package by.slizh.tutorsweb.model.service.impl;

import by.slizh.tutorsweb.model.dao.ColumnName;
import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.dao.EntityTransaction;
import by.slizh.tutorsweb.model.dao.UserDao;
import by.slizh.tutorsweb.model.dao.impl.UserDaoImpl;
import by.slizh.tutorsweb.model.service.UserService;
import by.slizh.tutorsweb.model.validator.impl.UserValidatorImpl;
import by.slizh.tutorsweb.util.mail.MailSender;
import by.slizh.tutorsweb.util.security.PasswordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.slizh.tutorsweb.controller.command.RequestParameter.*;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger();

    private static UserServiceImpl instance;

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public Optional<User> authenticate(String email, String password) throws ServiceException {
        Optional<User> result = Optional.empty();
        String passwordHash;
        UserDao userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.init(userDao);
            Optional<User> optionalUser = userDao.findUserByEmail(email);
            if (optionalUser.isPresent()) {
                passwordHash = userDao.findUserPassword(optionalUser.get());
                if (PasswordEncoder.checkPassword(password, passwordHash)) {
                    result = optionalUser;
                }
            }
        } catch (DaoException e) {
            throw new ServiceException("Failed to make transaction in authenticate method", e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Can't end transaction in authenticate method", e);
            }
        }
        return result;
    }


    @Override
    public void registrate(Map<String, String> userMap) throws ServiceException {
        User user = new User.UserBuilder()
                .setFirstName(userMap.get(FIRST_NAME))
                .setLastName(userMap.get(LAST_NAME))
                .setEmail(userMap.get(EMAIL))
                .setCity(userMap.get(CITY))
                .setRole(User.Role.USER)
                .setStatus(User.Status.NON_ACTIVATED)
                .createUser();

        UserDao userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.init(userDao);
            userDao.create(user, userMap.get(PASSWORD));
        } catch (DaoException e) {
            throw new ServiceException("Failed to make transaction in checkEmail method", e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Can't end transaction in checkEmail method", e);
            }
        }
        MailSender mailSender = new MailSender();
        mailSender.send(user.getUserId(), user.getEmail());
    }

    @Override
    public boolean validateUserData(Map<String, String> userMap) {
        return UserValidatorImpl.getInstance().validateUserData(userMap);
    }

    @Override
    public boolean isEmailExist(String email) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.init(userDao);
            Optional<User> optionalUser = userDao.findUserByEmail(email);
            if (optionalUser.isPresent()) {
                return true;
            }
        } catch (DaoException e) {
            throw new ServiceException("Failed to make transaction in checkEmail method", e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Can't end transaction in checkEmail method", e);
            }
        }
        return false;
    }

    @Override
    public boolean verify(String userId) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        UserDao userDao = new UserDaoImpl();
        User user;
        try {
            transaction.init(userDao);
            Optional<User> optionalUser = userDao.findById(Integer.parseInt(userId));
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
                if (user.getStatus() == User.Status.NON_ACTIVATED) {
                    user.setStatus(User.Status.ACTIVATED);
                    userDao.update(user);
                    return true;
                }
            }
        } catch (DaoException e) {
            throw new ServiceException("Failed to make transaction verify method");
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Can't end transaction in verify method", e);
            }
        }
        return false;
    }
}
