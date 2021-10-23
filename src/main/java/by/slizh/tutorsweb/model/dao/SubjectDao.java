package by.slizh.tutorsweb.model.dao;

import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.model.entity.Subject;

import java.util.List;
import java.util.Optional;

/**
 * The type Subject dao.
 */
public abstract class SubjectDao extends AbstractDao<Subject> {

    /**
     * Create tutorSubject record in many-to-many table.
     *
     * @param tutorId   the tutor id
     * @param subjectId the subject id
     * @return true if creating successfully
     * @throws DaoException in case of any SQL errors
     */
    public abstract boolean createTutorSubject(int tutorId, int subjectId) throws DaoException;

    /**
     * Delete tutorSubject record in many-to-many table.
     *
     * @param tutorId   the tutor id
     * @param subjectId the subject id
     * @return the number of deleted records
     * @throws DaoException in case of any SQL errors
     */
    public abstract int deleteTutorSubject(int tutorId, int subjectId) throws DaoException;

    /**
     * Find subjects by tutor id.
     *
     * @param tutorId the tutor id
     * @return the list of subjects associated with the tutor
     * @throws DaoException in case of any SQL errors
     */
    public abstract List<Subject> findSubjectsByTutorId(int tutorId) throws DaoException;

    /**
     * Find subject by name.
     *
     * @param name the subject name
     * @return the optional {@link Optional} with subject entity or empty optional
     * @throws DaoException in case of any SQL errors
     */
    public abstract Optional<Subject> findSubjectByName(String name) throws DaoException;

    /**
     * Delete subjects by tutor id.
     *
     * @param tutorId the tutor id
     * @return the number of deleted records
     * @throws DaoException in case of any SQL errors
     */
    public abstract int deleteSubjectsByTutorId(int tutorId) throws DaoException;
}
