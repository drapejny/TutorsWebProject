package by.slizh.tutorsweb.model.service.impl;

import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.dao.EntityTransaction;
import by.slizh.tutorsweb.model.dao.FeedbackDao;
import by.slizh.tutorsweb.model.dao.UserDao;
import by.slizh.tutorsweb.model.dao.impl.FeedbackDaoImpl;
import by.slizh.tutorsweb.model.dao.impl.UserDaoImpl;
import by.slizh.tutorsweb.model.entity.Feedback;
import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.model.service.FeedbackService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class FeedbackServiceImpl implements FeedbackService {

    private static final Logger logger = LogManager.getLogger();

    private static FeedbackServiceImpl instance = new FeedbackServiceImpl();

    private FeedbackServiceImpl() {
    }

    public static FeedbackServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<Feedback> findFeedbacksByTutor(int tutorId) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        FeedbackDao feedbackDao = new FeedbackDaoImpl();
        try {
            transaction.init(feedbackDao);
            List<Feedback> feedbacks = feedbackDao.findByTutorId(tutorId);
            return feedbacks;
        } catch (DaoException e) {
            logger.error("Failed to make transaction in findFeedbacksByTutorId method", e);
            throw new ServiceException("Failed to make transaction in findFeedbacksByTutorId method", e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Failed to end transaction in findFeedbacksByTutorId method", e);
            }
        }
    }

    @Override
    public boolean addFeedback(Feedback feedback) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        FeedbackDao feedbackDao = new FeedbackDaoImpl();
        try {
            transaction.init(feedbackDao);
            return feedbackDao.create(feedback);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Failed to end transaction in addFeedback method", e);
            }
        }
    }

    @Override
    public Feedback updateFeedback(Feedback feedback) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        FeedbackDao feedbackDao = new FeedbackDaoImpl();
        try {
            transaction.init(feedbackDao);
            return feedbackDao.update(feedback);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Failed to end transaction in updateFeedback method", e);
            }
        }
    }

    @Override
    public Optional<Feedback> findFeedbackById(int id) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        FeedbackDao feedbackDao = new FeedbackDaoImpl();
        try {
            transaction.init(feedbackDao);
            return feedbackDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Failed to end transaction in findFeedbackById method", e);
            }
        }
    }
}
