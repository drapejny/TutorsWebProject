package by.slizh.tutorsweb.model.service;

import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.Feedback;

import java.util.List;
import java.util.Optional;

/**
 * The interface FeedbackService.
 */
public interface FeedbackService {

    /**
     * Find feedbacks by tutor.
     *
     * @param tutorId the tutor id
     * @return the list of tutor
     * @throws ServiceException in case of any dao errors
     */
    List<Feedback> findFeedbacksByTutor(int tutorId) throws ServiceException;

    /**
     * Add feedback.
     *
     * @param feedback the feedback entity
     * @return true if feedback added successfully
     * @throws ServiceException in case of any dao errors
     */
    boolean addFeedback(Feedback feedback) throws ServiceException;

    /**
     * Update feedback.
     *
     * @param feedback the feedback entity
     * @return the old feedback entity
     * @throws ServiceException in case of any dao errors
     */
    Feedback updateFeedback(Feedback feedback) throws ServiceException;

    /**
     * Find feedback by id.
     *
     * @param id the feedback id
     * @return the optional {@link Optional} with feedback entity or empty optional
     * @throws ServiceException in case of any dao errors
     */
    Optional<Feedback> findFeedbackById(int id) throws ServiceException;

    /**
     * Delete feedback by id.
     *
     * @param id the feedback id
     * @return true if feedback deleted successfully
     * @throws ServiceException in case of any dao errors
     */
    boolean deleteFeedbackById(int id) throws ServiceException;

}
