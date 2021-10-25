package by.slizh.tutorsweb.model.service.impl;

import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.dao.EntityTransaction;
import by.slizh.tutorsweb.model.dao.SubjectDao;
import by.slizh.tutorsweb.model.dao.impl.SubjectDaoImpl;
import by.slizh.tutorsweb.model.entity.Subject;
import by.slizh.tutorsweb.model.service.SubjectService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubjectServiceImpl implements SubjectService {

    private static final Logger logger = LogManager.getLogger();

    private static SubjectServiceImpl instance = new SubjectServiceImpl();

    private SubjectServiceImpl() {
    }

    public static SubjectServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<Subject> findAllSubjects() throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        SubjectDao subjectDao = new SubjectDaoImpl();
        try {
            transaction.init(subjectDao);
            return subjectDao.findAll();
        } catch (DaoException e) {
            logger.error("Failed to make transaction in findAllSubjects method", e);
            throw new ServiceException("Failed to make transaction in findAllSubjects method", e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                throw new ServiceException("Failed to end transaction in findAllSubjects method", e);
            }
        }
    }

    @Override
    public boolean deleteSubjectById(int id) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        SubjectDao subjectDao = new SubjectDaoImpl();
        try {
            transaction.init(subjectDao);
            return subjectDao.deleteById(id);
        } catch (DaoException e) {
            logger.error("Failed to make transaction in deleteSubjectById method", e);
            throw new ServiceException("Failed to make transaction in deleteSubjectById method", e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Failed to end transaction in deleteSubjectById method", e);
            }
        }
    }

    @Override
    public boolean addSubject(Subject subject) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        SubjectDao subjectDao = new SubjectDaoImpl();
        try {
            transaction.init(subjectDao);
            Optional<Subject> optionalSubject = subjectDao.findSubjectByName(subject.getSubjectName());
            if (optionalSubject.isPresent()) {
                return false;
            }
            return subjectDao.create(subject);
        } catch (DaoException e) {
            logger.error("Failed make transaction in addSubject method", e);
            throw new ServiceException("Failed make transaction in addSubject method", e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Failed to end transaction in addSubject method", e);
            }
        }
    }

    @Override
    public List<Subject> findSubjectsByTutorId(int tutorId) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        SubjectDao subjectDao = new SubjectDaoImpl();
        try {
            transaction.init(subjectDao);
            return subjectDao.findSubjectsByTutorId(tutorId);
        } catch (DaoException e) {
            logger.error("Failed to make transaction in findSubjectsByTutorId method", e);
            throw new ServiceException("Failed to make transaction in findSubjectsByTutorId method", e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Failed to end transaction in findSubjectsByTutorId method", e);
            }
        }
    }

    @Override
    public void editTutorSubjects(int tutorId, List<Integer> oldSubjectIds, List<Integer> newSubjectIds) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        SubjectDao subjectDao = new SubjectDaoImpl();
        List<Integer> deletedSubjects = new ArrayList<>(oldSubjectIds);
        deletedSubjects.removeAll(newSubjectIds);
        List<Integer> addedSubjects = new ArrayList<>(newSubjectIds);
        addedSubjects.removeAll(oldSubjectIds);
        try {
            transaction.initTransaction(subjectDao);
            for (Integer id : deletedSubjects) {
                subjectDao.deleteTutorSubject(tutorId, id);
            }
            for (Integer id : addedSubjects) {
                subjectDao.createTutorSubject(tutorId, id);
            }
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException ex) {
                logger.error("Failed to rollback transaction in editTutorSubjects method", e);
            }
            logger.error("Failed to make transaction in editTutorSubjects method", e);
            throw new ServiceException("Failed to make transaction in editTutorSubjects method", e);
        } finally {
            try {
                transaction.endTransaction();
            } catch (DaoException e) {
                logger.error("Failed to end transaction in editTutorSubjects method", e);
            }
        }

    }
}
