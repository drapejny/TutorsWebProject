package by.slizh.tutorsweb.model.service;

import by.slizh.tutorsweb.model.entity.Feedback;
import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.exception.ServiceException;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    Optional<User> authenticate(String email, String password) throws ServiceException;

    void registrate(Map<String, String> userMap) throws ServiceException;

    boolean validateUserData(Map<String, String> userMap);

    boolean isEmailExist(String email) throws ServiceException;

    boolean checkPassword(User user, String password) throws ServiceException;

    boolean verify(String userId) throws ServiceException;

    void updatePhoto(User user, InputStream inputStream) throws ServiceException;

    void updateUser(User user) throws ServiceException;

    void updatePassword(User user,String password) throws ServiceException;

    Map<Feedback, User> findUsersForFeedbacks(List<Feedback> feedbacks) throws ServiceException;

}
