package by.slizh.tutorsweb.model.service;

import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.Tutor;
import by.slizh.tutorsweb.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TutorService {

    Optional<Tutor> findTutorByEmail(String email) throws ServiceException;

    void createTutor(User user, Map<String,String[]> tutorMap) throws ServiceException;

    boolean deleteTutorByEmail(String email) throws ServiceException;
}
