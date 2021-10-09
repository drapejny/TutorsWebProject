package by.slizh.tutorsweb.model.dao;

import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.exception.DaoException;

import java.util.List;
import java.util.Optional;

public abstract class UserDao extends AbstractDao<User> {

    public abstract boolean create(User user, String password) throws DaoException;

    public abstract boolean updateUserPassword(User user, String password) throws DaoException;

    public abstract Optional<User> findUserByEmail(String email) throws DaoException;

    public abstract String findUserPassword(User user) throws DaoException;

    public abstract List<User> searchUsers(String searchLine) throws DaoException;

}
