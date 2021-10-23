package by.slizh.tutorsweb.model.dao;

import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * The type User dao.
 */
public abstract class UserDao extends AbstractDao<User> {

    /**
     * Create record in user table
     *
     * @param user     the user entity
     * @param password the password
     * @return true if creating successfully
     * @throws DaoException in case of any SQL errors
     */
    public abstract boolean create(User user, String password) throws DaoException;

    /**
     * Update record in user table
     *
     * @param user     the user entity
     * @param password the password
     * @return true if updating successfully
     * @throws DaoException in case of any SQL errors
     */
    public abstract boolean updateUserPassword(User user, String password) throws DaoException;

    /**
     * Find user by email.
     *
     * @param email the email
     * @return the optional {@link Optional} with user entity or empty optional
     * @throws DaoException in case of any SQL errors
     */
    public abstract Optional<User> findUserByEmail(String email) throws DaoException;

    /**
     * Find user password.
     *
     * @param user the user entity
     * @return the user password hash
     * @throws DaoException in case of any SQL errors
     */
    public abstract String findUserPassword(User user) throws DaoException;

    /**
     * Search users.
     *
     * @param searchLine the search line
     * @param offset     the offset
     * @param rowCount   th row count
     * @return the list of searched users
     * @throws DaoException in case of any SQL errors
     */
    public abstract List<User> searchUsers(String searchLine, int offset, int rowCount) throws DaoException;

    /**
     * Count searched users.
     *
     * @param searchLine the search line
     * @return the number of searched users
     * @throws DaoException in case of any SQL errors
     */
    public abstract int countSearchUsers(String searchLine) throws DaoException;

    /**
     * Find all admins.
     *
     * @return the list of admins
     * @throws DaoException in case of any SQL errors
     */
    public abstract List<User> findAllAdmins() throws DaoException;

}
