package by.slizh.tutorsweb.model.service;

import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.Subject;
import by.slizh.tutorsweb.model.entity.Tutor;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    List<Subject> findAllSubjects() throws ServiceException;

    boolean deleteSubjectById(int id) throws ServiceException;

    boolean addSubject(Subject subject) throws ServiceException;

    List<Subject> findSubjectsByTutorId(int tutorId) throws ServiceException;

    void editTutorSubjects(int tutorId, List<Integer> oldSubjectIds, List<Integer> newSubjectIds) throws ServiceException;
}
