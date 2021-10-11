package by.slizh.tutorsweb.model.dao;

import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.model.entity.Feedback;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public abstract class FeedbackDao extends AbstractDao<Feedback> {

    public abstract List<Feedback> findByTutorId(int tutorId) throws DaoException;

    public abstract Optional<Feedback> findByTutorIdAndUserId(int tutorId,int userId) throws DaoException;
}
