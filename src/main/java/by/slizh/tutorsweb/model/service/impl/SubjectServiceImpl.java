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
}
