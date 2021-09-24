package by.slizh.tutorsweb.model.service;

import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> findAllSubjects() throws ServiceException;

    boolean deleteSubjectById(int id) throws ServiceException;

    boolean addSubject(Subject subject) throws ServiceException;
}
