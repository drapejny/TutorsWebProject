package by.slizh.tutorsweb.model.service.impl;

import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.dao.EntityTransaction;
import by.slizh.tutorsweb.model.dao.SubjectDao;
import by.slizh.tutorsweb.model.dao.TutorDao;
import by.slizh.tutorsweb.model.dao.impl.SubjectDaoImpl;
import by.slizh.tutorsweb.model.dao.impl.TutorDaoImpl;
import by.slizh.tutorsweb.model.entity.Tutor;
import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.model.service.TutorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.slizh.tutorsweb.controller.command.RequestParameter.*;

public class TutorServiceImpl implements TutorService {

    private static final Logger logger = LogManager.getLogger();

    private static TutorServiceImpl instance = new TutorServiceImpl();

    private TutorServiceImpl() {
    }

    public static TutorServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<Tutor> findTutorByEmail(String email) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        TutorDao tutorDao = new TutorDaoImpl();
        try {
            transaction.init(tutorDao);
            return tutorDao.findTutorByEmail(email);
        } catch (DaoException e) {
            logger.error("Failed to make transaction in findTutorByEmail method", e);
            throw new ServiceException("Failed to make transaction in findTutorByEmail method", e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Failed to end transaction in findTutorByEmail method", e);
            }
        }

    }

    @Override
    public Optional<Tutor> findTutorById(int id) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        TutorDao tutorDao = new TutorDaoImpl();
        try {
            transaction.init(tutorDao);
            return tutorDao.findById(id);
        } catch (DaoException e) {
            logger.error("Failed to make transaction in findTutorById method", e);
            throw new ServiceException("Failed to make transaction in findTutorById method", e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Failed to end transaction in findTutorById method", e);
            }
        }

    }

    @Override
    public void createTutor(User user, Map<String, String[]> tutorMap) throws ServiceException {
        Tutor tutor = new Tutor.TutorBuilder()
                .setUserId(user.getUserId())
                .setPhone(tutorMap.get(PHONE)[0])
                .setCity(tutorMap.get(CITY)[0])
                .setEducation(tutorMap.get(EDUCATION)[0])
                .setInfo(tutorMap.get(INFORMATION)[0])
                .setPricePerHour(Integer.parseInt(tutorMap.get(PRICE)[0]))
                .setIsActive(true)
                .createTutor();
        EntityTransaction transaction = new EntityTransaction();
        TutorDao tutorDao = new TutorDaoImpl();
        SubjectDao subjectDao = new SubjectDaoImpl();
        try {
            transaction.initTransaction(tutorDao, subjectDao);
            tutorDao.create(tutor);
            for (String subjectId : tutorMap.get(SUBJECT)) {
                subjectDao.createTutorSubject(tutor.getTutorId(), Integer.parseInt(subjectId));
            }
            transaction.commit();
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException ex) {
                logger.error("Failed to rollback transaction in createTutor method", e);
            }
            logger.error("Failed to make transaction in createTutor method", e);
            throw new ServiceException("Failed to make transaction in createTutor method", e);
        } finally {
            try {
                transaction.endTransaction();
            } catch (DaoException e) {
                logger.error("Failed to end transaction in createTutor method", e);
            }
        }
    }

    @Override
    public void updateTutor(Tutor tutor) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        TutorDao tutorDao = new TutorDaoImpl();
        try {
            transaction.init(tutorDao);
            tutorDao.update(tutor);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Failed to end transaction in updateTutor method", e);
            }
        }
    }

    @Override
    public boolean deleteTutorByEmail(String email) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        TutorDao tutorDao = new TutorDaoImpl();
        try {
            transaction.init(tutorDao);
            Optional<Tutor> tutor = tutorDao.findTutorByEmail(email);
            if (tutor.isPresent()) {
                return tutorDao.deleteById(tutor.get().getTutorId());
            }
            return false;
        } catch (DaoException e) {
            logger.error("Failed to make transaction in deleteTutorByEmail method", e);
            throw new ServiceException("Failed to make transaction in deleteTutorByEmail method", e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Failed to end transaction in deleteTutorByEmail method", e);
            }

        }
    }

    @Override
    public List<Tutor> searchTutors(int subjectId, String city, int minPrice, int maxPrice, int offset, int numberOfRecords, String sort) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        TutorDao tutorDao = new TutorDaoImpl();
        try {
            transaction.init(tutorDao);
            List<Tutor> tutors = tutorDao.searchTutors(subjectId, city, minPrice, maxPrice, offset, numberOfRecords, sort);
            return tutors;
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Failed to end transaction in searchTutors method", e);
            }
        }
    }

    @Override
    public int countSearchedRecords(int subjectId, String city, int minPrice, int maxPrice) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        TutorDao tutorDao = new TutorDaoImpl();
        try {
            transaction.init(tutorDao);
            int count = tutorDao.countSearchedTutors(subjectId, city, minPrice, maxPrice);
            return count;
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Failed to end transaction in countSearchedTutors method", e);
            }
        }
    }

    @Override
    public List<String> findAllCities() throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        TutorDao tutorDao = new TutorDaoImpl();
        try {
            transaction.init(tutorDao);
            List<String> cities = tutorDao.findAllCities();
            return cities;
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Can't end transaction in findAllCities method", e);
            }
        }
    }

    @Override
    public List<Tutor> findApplications(int offset, int numberOfRecords) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        TutorDao tutorDao = new TutorDaoImpl();
        try {
            transaction.init(tutorDao);
            List<Tutor> tutors = tutorDao.findApplications(offset, numberOfRecords);
            return tutors;
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Can't end transaction in findApplications method", e);
            }
        }
    }

    @Override
    public Map<User, Tutor> findTutorsByUsers(List<User> users) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        TutorDao tutorDao = new TutorDaoImpl();
        Map<User, Tutor> tutorMap = new HashMap<>();
        try {
            transaction.init(tutorDao);
            for (User user : users) {
                Optional<Tutor> tutor = tutorDao.findTutorByEmail(user.getEmail());
                if (tutor.isPresent()) {
                    tutorMap.put(user, tutor.get());
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Failed to end transaction in findTutorsByUsers method", e);
            }
        }
        return tutorMap;
    }

    @Override
    public int countApplications() throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        TutorDao tutorDao = new TutorDaoImpl();
        try {
            transaction.init(tutorDao);
            return tutorDao.countApplications();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Can't end transaction in countApplications method", e);
            }
        }
    }

    @Override
    public boolean deleteTutorById(int tutorId) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        TutorDao tutorDao = new TutorDaoImpl();
        try {
            transaction.init(tutorDao);
            return tutorDao.deleteById(tutorId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Can't end transaction in deleteTutorById method", e);
            }
        }
    }
}
