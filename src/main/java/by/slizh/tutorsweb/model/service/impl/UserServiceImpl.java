package by.slizh.tutorsweb.model.service.impl;

import by.slizh.tutorsweb.model.dao.TutorDao;
import by.slizh.tutorsweb.model.dao.impl.TutorDaoImpl;
import by.slizh.tutorsweb.model.entity.Feedback;
import by.slizh.tutorsweb.model.entity.Tutor;
import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.dao.EntityTransaction;
import by.slizh.tutorsweb.model.dao.UserDao;
import by.slizh.tutorsweb.model.dao.impl.UserDaoImpl;
import by.slizh.tutorsweb.model.service.UserService;
import by.slizh.tutorsweb.model.util.Base64Coder;
import by.slizh.tutorsweb.model.util.mail.MailSender;
import by.slizh.tutorsweb.model.util.security.LinkIdEncoder;
import by.slizh.tutorsweb.model.util.security.PasswordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger();

    private static UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<User> authenticate(String email, String password) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        UserDao userDao = new UserDaoImpl();
        Optional<User> result = Optional.empty();
        String passwordHash;
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
            logger.error("Failed to make transaction in authenticate method", e);
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
    public void registrate(String firstName, String lastName, String email, String password) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        UserDao userDao = new UserDaoImpl();
        User user = new User.UserBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setRole(User.Role.USER)
                .setStatus(User.Status.NON_ACTIVATED)
                .createUser();
        try {
            transaction.init(userDao);
            userDao.create(user, password);
        } catch (DaoException e) {
            logger.error("Failed to make transaaction in checkEmail method", e);
            throw new ServiceException("Failed to make transaction in checkEmail method", e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Can't end transaction in checkEmail method", e);
            }
        }
        MailSender mailSender = new MailSender();
        mailSender.send(LinkIdEncoder.encodeId(user.getUserId()), user.getEmail());
    }

    @Override
    public boolean isEmailExist(String email) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        UserDao userDao = new UserDaoImpl();
        try {
            transaction.init(userDao);
            Optional<User> optionalUser = userDao.findUserByEmail(email);
            if (optionalUser.isPresent()) {
                return true;
            }
        } catch (DaoException e) {
            logger.error("Failed to make transaction in isEmailExist method", e);
            throw new ServiceException("Failed to make transaction in isEmailExist method", e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Can't end transaction in isEmailExist method", e);
            }
        }
        return false;
    }

    @Override
    public boolean checkPassword(User user, String password) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        UserDao userDao = new UserDaoImpl();
        boolean result;
        try {
            transaction.init(userDao);
            String userPasswordHash = userDao.findUserPassword(user);
            result = PasswordEncoder.checkPassword(password, userPasswordHash);
        } catch (DaoException e) {
            logger.error("Failed to make transaction in checkPassword method", e);
            throw new ServiceException("Failed to make transaction in checkPassword method", e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Can't end transaction in checkPassword method", e);
            }
        }
        return result;
    }

    @Override
    public boolean verify(String userId) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        UserDao userDao = new UserDaoImpl();
        User user;
        try {
            transaction.init(userDao);
            int decodedId = LinkIdEncoder.decodeId(Integer.parseInt(userId));
            Optional<User> optionalUser = userDao.findById(decodedId);
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
                if (user.getStatus() == User.Status.NON_ACTIVATED) {
                    user.setStatus(User.Status.ACTIVATED);
                    userDao.update(user);
                    return true;
                }
            }
        } catch (DaoException e) {
            logger.error("Failed to make transaction in verify method", e);
            throw new ServiceException("Failed to make transaction in verify method", e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Can't end transaction in verify method", e);
            }
        }
        return false;
    }

    @Override
    public void updatePhoto(User user, InputStream inputStream) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        UserDao userDao = new UserDaoImpl();
        user.setPhoto(Base64Coder.encode(inputStream));
        try {
            transaction.init(userDao);
            userDao.update(user);
        } catch (DaoException e) {
            logger.error("Failed to make transaction in updatePhoto method", e);
            throw new ServiceException("Failed to make transaction in updatePhoto method", e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Can't end transaction in updatePhoto method", e);
            }
        }
    }

    @Override
    public void updateUser(User user) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        UserDao userDao = new UserDaoImpl();
        try {
            transaction.init(userDao);
            userDao.update(user);
        } catch (DaoException e) {
            logger.error("Failed to make transaction in updateUser method", e);
            throw new ServiceException("Failed to make transaction in updateUser method", e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Can't end transaction in updateUser method", e);
            }
        }
    }

    @Override
    public void updatePassword(User user, String password) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        UserDao userDao = new UserDaoImpl();
        try {
            transaction.init(userDao);
            userDao.updateUserPassword(user, password);
        } catch (DaoException e) {
            logger.error("Failed to make transaction in updatePassword method", e);
            throw new ServiceException("Failed to make transaction in updatePassword method", e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Can't end transaction in updatePassword method", e);
            }
        }
    }

    @Override
    public Map<Feedback, User> findUsersForFeedbacks(List<Feedback> feedbacks) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        UserDao userDao = new UserDaoImpl();
        try {
            transaction.init(userDao);
            Map<Feedback, User> resultMap = new HashMap<>();
            for (Feedback feedback : feedbacks) {
                Optional<User> user = userDao.findById(feedback.getUserId());
                if (user.isPresent()) {
                    if (user.get().getStatus() != User.Status.BLOCKED) {
                        resultMap.put(feedback, user.get());
                    }
                }
            }
            return resultMap;
        } catch (DaoException e) {
            logger.error("Failed to make transaction in findUsersForFeedbacks method", e);
            throw new ServiceException("Failed to make transaction in findUsersForFeedbacks method", e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Can't end transaction in findUsersForFeedbacks method", e);
            }
        }

    }

    @Override
    public void makeUserToTutor(int userId) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        UserDao userDao = new UserDaoImpl();
        TutorDao tutorDao = new TutorDaoImpl();
        try {
            transaction.initTransaction(userDao, tutorDao);
            Optional<User> user = userDao.findById(userId);
            if (user.isPresent()) {
                Optional<Tutor> tutor = tutorDao.findTutorByEmail(user.get().getEmail());
                if (tutor.isPresent()) {
                    user.get().setRole(User.Role.TUTOR);
                    userDao.update(user.get());
                }
            }
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException ex) {
                logger.error("Can't rollback transaction in makeUserToTutor method", e);
            }
            logger.error("Failed to make transaction in makeUserToTutor method", e);
            throw new ServiceException("Failed to make transaction in makeUserToTutor method", e);
        } finally {
            try {
                transaction.endTransaction();
            } catch (DaoException e) {
                logger.error("Can't end transaction in makeUserToTutor method", e);
            }
        }
    }

    @Override
    public List<User> searchUsers(String searchLine, int offset, int rowsCount) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        UserDao userDao = new UserDaoImpl();
        try {
            transaction.init(userDao);
            return userDao.searchUsers(searchLine, offset, rowsCount);
        } catch (DaoException e) {
            logger.error("Failed to make transaction n searchUsers method", e);
            throw new ServiceException("Failed to make transaction n searchUsers method", e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Can't end transaction in searchUsers method", e);
            }
        }
    }

    @Override
    public int countSearchedUsers(String searchLine) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        UserDao userDao = new UserDaoImpl();
        try {
            transaction.init(userDao);
            return userDao.countSearchUsers(searchLine);
        } catch (DaoException e) {
            logger.error("Failed to make transaction in countSearchedUsers method", e);
            throw new ServiceException("Failed to make transaction in countSearchedUsers method", e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Can't end transaction in countSearchedUsers method", e);
            }
        }
    }

    @Override
    public Optional<User> blockUser(int userId) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        UserDao userDao = new UserDaoImpl();
        try {
            transaction.init(userDao);
            Optional<User> user = userDao.findById(userId);
            if (user.isPresent() && user.get().getRole() != User.Role.ADMIN) {
                user.get().setStatus(User.Status.BLOCKED);
                userDao.update(user.get());
                return user;
            }

        } catch (DaoException e) {
            logger.error("Failed to make transaction in blockUser method", e);
            throw new ServiceException("Failed to make transaction in blockUser method", e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Can't end transaction in blockUser method", e);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> unblockUser(int userId) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        UserDao userDao = new UserDaoImpl();
        try {
            transaction.init(userDao);
            Optional<User> user = userDao.findById(userId);
            if (user.isPresent() && user.get().getRole() != User.Role.ADMIN) {
                user.get().setStatus(User.Status.ACTIVATED);
                userDao.update(user.get());
                return user;
            }
        } catch (DaoException e) {
            logger.error("Failed to make transaction in unblockUser method", e);
            throw new ServiceException("Failed to make transaction in unblockUser method", e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Can't end transaction in unblockUser method", e);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAllAdmins() throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        UserDao userDao = new UserDaoImpl();
        try {
            transaction.init(userDao);
            return userDao.findAllAdmins();
        } catch (DaoException e) {
            logger.error("Failed to make transaction in findAllAdmins method", e);
            throw new ServiceException("Failed to make transaction in findAllAdmins method", e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Can't end transaction in finaAllAdmins method", e);
            }
        }
    }

    @Override
    public boolean makeAdmin(int userId) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        UserDao userDao = new UserDaoImpl();
        try {
            transaction.init(userDao);
            Optional<User> user = userDao.findById(userId);
            if (user.isPresent()) {
                user.get().setRole(User.Role.ADMIN);
                userDao.update(user.get());
                return true;
            }
        } catch (DaoException e) {
            logger.error("Failed to make transaction in makeAdmin method", e);
            throw new ServiceException("Failed to make transaction in makeAdmin method", e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Can't end transaction in makeAdmin method", e);
            }
        }
        return false;
    }

    @Override
    public boolean deleteAdmin(int userId) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        UserDao userDao = new UserDaoImpl();
        try {
            transaction.init(userDao);
            Optional<User> admin = userDao.findById(userId);
            if (admin.isPresent()) {
                admin.get().setRole(User.Role.USER);
                userDao.update(admin.get());
                return true;
            }
        } catch (DaoException e) {
            logger.error("Failed to make transaction in deleteAdmin method", e);
            throw new ServiceException("Failed to make transaction in deleteAdmin method", e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Can't end transaction in deleteAdmin method", e);
            }
        }
        return false;
    }
}
