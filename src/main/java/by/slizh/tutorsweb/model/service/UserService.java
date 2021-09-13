package by.slizh.tutorsweb.model.service;

import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.exception.ServiceException;

import java.util.Map;
import java.util.Optional;

public interface UserService {

    Optional<User> authenticate(String email, String password) throws ServiceException;

    void registrate(Map<String, String> userMap) throws ServiceException;

    boolean validateUserData(Map<String, String> userMap);

    boolean isEmailExist(String email) throws ServiceException;

    boolean verify(String userId) throws ServiceException;

}
