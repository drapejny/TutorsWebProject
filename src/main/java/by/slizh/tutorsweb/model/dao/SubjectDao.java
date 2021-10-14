package by.slizh.tutorsweb.model.dao;

import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.Subject;

import java.util.List;
import java.util.Optional;

public abstract class SubjectDao extends AbstractDao<Subject> {

    public abstract boolean createTutorSubject(int tutorId, int subjectId) throws DaoException;

    public abstract int deleteTutorSubject(int tutorId, int subjectId) throws DaoException;

    public abstract List<Subject> findSubjectsByTutorId(int tutorId) throws DaoException;

    public abstract Optional<Subject> findSubjectByName(String name) throws DaoException;

    public abstract int deleteSubjectsByTutorId(int tutorId) throws DaoException;
}
