package by.slizh.tutorsweb.model.service;

import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.Feedback;
import by.slizh.tutorsweb.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface FeedbackService {
    List<Feedback> findFeedbacksByTutor(int tutorId) throws ServiceException;

    boolean addFeedback(Feedback feedback) throws ServiceException;

    Feedback updateFeedback(Feedback feedback) throws ServiceException;

    Optional<Feedback> findFeedbackById(int id) throws ServiceException;
}
