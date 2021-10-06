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
            logger.error("Failed to find all subjects in findAllSubjects method", e);
            throw new ServiceException("Failed to find all subjects in findAllSubjects method", e);
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
            logger.error("Failed ot delete subject in deleteSubjectById method", e);
            throw new ServiceException("Failed ot delete subject in deleteSubjectById method", e);
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
            return subjectDao.create(subject);
        } catch (DaoException e) {
            logger.error("Failed ot add subject in addSubject method", e);
            throw new ServiceException("Failed ot add subject in addSubject method", e);
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
            throw new ServiceException(e);
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
        List<Integer> deletedSubjects = new ArrayList<>(oldSubjectIds);
        deletedSubjects.removeAll(newSubjectIds);
        List<Integer> addedSubjects = new ArrayList<>(newSubjectIds);
        addedSubjects.removeAll(oldSubjectIds);
        List<Integer> savedSubjects = new ArrayList<>(newSubjectIds);
        EntityTransaction transaction = new EntityTransaction();
        SubjectDao subjectDao = new SubjectDaoImpl();
        try {
            transaction.init(subjectDao);
            for (Integer id : deletedSubjects) {
                subjectDao.deleteTutorSubject(tutorId, id);
            }
            for (Integer id : addedSubjects) {
                subjectDao.createTutorSubject(tutorId, id);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            try{
                transaction.end();
            } catch (DaoException e) {
                logger.error("Failed to end transaction in editTutorSubjects method", e);
            }
        }

    }
}
