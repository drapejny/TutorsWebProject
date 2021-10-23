package by.slizh.tutorsweb.model.dao;

import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.model.entity.Feedback;

import java.util.List;
import java.util.Optional;

/**
 * The type Feedback dao.
 */
public abstract class FeedbackDao extends AbstractDao<Feedback> {

    /**
     * Find feedbacks by tutor id.
     *
     * @param tutorId the tutor id
     * @return the list of feedbacks associated with the tutor
     * @throws DaoException in case of any SQL errors
     */
    public abstract List<Feedback> findByTutorId(int tutorId) throws DaoException;

    /**
     * Find feedback by tutor id and user id.
     *
     * @param tutorId the tutor id
     * @param userId  the user id
     * @return the optional {@link Optional} with feedback entity or empty optional
     * @throws DaoException in case of any SQL errors
     */
    public abstract Optional<Feedback> findByTutorIdAndUserId(int tutorId,int userId) throws DaoException;
}
