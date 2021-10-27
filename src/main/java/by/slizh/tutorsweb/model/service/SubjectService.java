package by.slizh.tutorsweb.model.service;

import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.Subject;

import java.util.List;

/**
 * The interface SubjectService.
 */
public interface SubjectService {

    /**
     * Find all subjects.
     *
     * @return the list of all subjects
     * @throws ServiceException in case of any dao errors
     */
    List<Subject> findAllSubjects() throws ServiceException;

    /**
     * Delete subject by id.
     *
     * @param id the subject id
     * @return true if subject deleted successfully
     * @throws ServiceException in case of any dao errors
     */
    boolean deleteSubjectById(int id) throws ServiceException;

    /**
     * Add subject.
     *
     * @param subject the subject entity
     * @return true if subject added successfully
     * @throws ServiceException in case of any dao errors
     */
    boolean addSubject(Subject subject) throws ServiceException;

    /**
     * Find subjects by tutor id.
     *
     * @param tutorId the tutor id
     * @return the list of subjects associated with tutor
     * @throws ServiceException in case of any dao errors
     */
    List<Subject> findSubjectsByTutorId(int tutorId) throws ServiceException;

    /**
     * Edit tutor subjects.
     *
     * @param tutorId       the tutor id
     * @param oldSubjectIds the old subject ids list
     * @param newSubjectIds the new subject ids list
     * @throws ServiceException in case of any dao errors
     */
    void editTutorSubjects(int tutorId, List<Integer> oldSubjectIds, List<Integer> newSubjectIds) throws ServiceException;
}
