package by.slizh.tutorsweb.model.service;

import by.slizh.tutorsweb.model.entity.Feedback;
import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.exception.ServiceException;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface UserService.
 */
public interface UserService {

    /**
     * Authenticate user by email and password.
     *
     * @param email    the email
     * @param password the password
     * @return the optional {@link Optional} with user entity if such email exist
     * and password id correct, else return the empty optional
     * @throws ServiceException in case of any dao errors
     */
    Optional<User> authenticate(String email, String password) throws ServiceException;

    /**
     * Registrate new user.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param email     the email
     * @param password  the password
     * @throws ServiceException in case of any dao errors
     */
    void registrate(String firstName, String lastName, String email, String password) throws ServiceException;

    /**
     * Check is email already exist.
     *
     * @param email the email
     * @return true if email already exist
     * @throws ServiceException in case of any dao errors
     */
    boolean isEmailExist(String email) throws ServiceException;

    /**
     * Check password by comparing password hash and hash in database.
     *
     * @param user     the user
     * @param password the password
     * @return true if user password hash equals hash in database
     * @throws ServiceException in case of any dao errors
     */
    boolean checkPassword(User user, String password) throws ServiceException;

    /**
     * Verify user by updating user status.
     *
     * @param userId the user id
     * @return true if user status was not activated
     * @throws ServiceException in case of any dao errors
     */
    boolean verify(String userId) throws ServiceException;

    /**
     * Update photo.
     *
     * @param user        the user entity
     * @param inputStream the input stream with photo data
     * @throws ServiceException in case of any dao errors
     */
    void updatePhoto(User user, InputStream inputStream) throws ServiceException;

    /**
     * Update user.
     *
     * @param user the user entity
     * @throws ServiceException in case of any dao errors
     */
    void updateUser(User user) throws ServiceException;

    /**
     * Update password.
     *
     * @param user     the user
     * @param password the password
     * @throws ServiceException in case of any dao errors
     */
    void updatePassword(User user, String password) throws ServiceException;

    /**
     * Find users for feedbacks.
     *
     * @param feedbacks the feedbacks
     * @return the map where keys are feedbacks and values are corresponding users
     * @throws ServiceException in case of any dao errors
     */
    Map<Feedback, User> findUsersForFeedbacks(List<Feedback> feedbacks) throws ServiceException;

    /**
     * Change user to tutor.
     *
     * @param userId the user id
     * @throws ServiceException in case of any dao errors
     */
    void makeUserToTutor(int userId) throws ServiceException;

    /**
     * Search users by last name or email, according to search line.
     * Searching return ordered by last name and email list of users.
     *
     * @param searchLine the search line
     * @param offset     the offset
     * @param rowsCount  the rows count
     * @return the list of searched users
     * @throws ServiceException in case of any dao errors
     */
    List<User> searchUsers(String searchLine, int offset, int rowsCount) throws ServiceException;

    /**
     * Count searched users.
     *
     * @param searchLine the search line
     * @return the number of searched users
     * @throws ServiceException in case of any dao errors
     */
    int countSearchedUsers(String searchLine) throws ServiceException;

    /**
     * Block user by changing status.
     * Ignore operation if user is admin.
     * Return current user with new status.
     *
     * @param userId the user id
     * @return the optional {@link Optional} with updated user entity or empty optional
     * @throws ServiceException in case of any dao errors
     */
    Optional<User> blockUser(int userId) throws ServiceException;

    /**
     * Unblock user optional.
     * Ignore operation if user is admin.
     * Return current user with new status
     *
     * @param userId the user id
     * @return the optional {@link Optional} with updated user entity or empty optional
     * @throws ServiceException in case of any dao errors
     */
    Optional<User> unblockUser(int userId) throws ServiceException;

    /**
     * Find all admins.
     *
     * @return the list all admins
     * @throws ServiceException in case of any dao errors
     */
    List<User> findAllAdmins() throws ServiceException;

    /**
     * Update user to admin.
     *
     * @param userId the user id
     * @return true if user status changed successfully
     * @throws ServiceException in case of any dao errors
     */
    boolean makeAdmin(int userId) throws ServiceException;

    /**
     * Change admin status to user.
     *
     * @param userId the user id
     * @return true if admin status changed successfully
     * @throws ServiceException in case of any dao errors
     */
    boolean deleteAdmin(int userId) throws ServiceException;
}
