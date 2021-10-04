package by.slizh.tutorsweb.model.dao;

import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.model.entity.Feedback;

import java.util.List;

public abstract class FeedbackDao extends AbstractDao<Feedback> {

    public abstract List<Feedback> findByTutorId(int tutorId) throws DaoException;
}
