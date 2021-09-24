package by.slizh.tutorsweb.model.dao;

import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.model.entity.Subject;

public abstract class SubjectDao extends AbstractDao<Integer, Subject> {

    public abstract boolean createTutorSubject(int tutorId, int subjectId) throws DaoException;
}
