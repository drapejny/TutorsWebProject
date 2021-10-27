package by.slizh.tutorsweb.model.service;

import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.Tutor;
import by.slizh.tutorsweb.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface TutorService.
 */
public interface TutorService {

    /**
     * Find tutor by email.
     *
     * @param email the user email
     * @return the optional {@link Optional} with tutor entity or empty optional
     * @throws ServiceException in case of any dao errors
     */
    Optional<Tutor> findTutorByEmail(String email) throws ServiceException;

    /**
     * Find tutor by id.
     *
     * @param id the tutor id
     * @return the optional {@link Optional} with tutor entity or empty optional
     * @throws ServiceException in case of any dao errors
     */
    Optional<Tutor> findTutorById(int id) throws ServiceException;

    /**
     * Create tutor.
     *
     * @param user     the user entity
     * @param tutorMap the map with tutor parameters
     * @throws ServiceException in case of any dao errors
     */
    void createTutor(User user, Map<String, String[]> tutorMap) throws ServiceException;

    /**
     * Update tutor.
     *
     * @param tutor the tutor entity
     * @throws ServiceException in case of any dao errors
     */
    void updateTutor(Tutor tutor) throws ServiceException;

    /**
     * Search tutors.
     *
     * @param subjectId       the subject id
     * @param city            the city
     * @param minPrice        the min price
     * @param maxPrice        the max price
     * @param offset          the offset
     * @param numberOfRecords the number of records
     * @param sort            the sort
     * @return the list of searched tutors
     * @throws ServiceException  in case of any dao errors
     */
    List<Tutor> searchTutors(int subjectId, String city, int minPrice, int maxPrice, int offset, int numberOfRecords, String sort) throws ServiceException;

    /**
     * Count searched records.
     *
     * @param subjectId the subject id
     * @param city      the city
     * @param minPrice  the min price
     * @param maxPrice  the max price
     * @return the number of searched records
     * @throws ServiceException  in case of any dao errors
     */
    int countSearchedRecords(int subjectId, String city, int minPrice, int maxPrice) throws ServiceException;

    /**
     * Find all cities.
     *
     * @return the list of all cities
     * @throws ServiceException in case of any dao errors
     */
    List<String> findAllCities() throws ServiceException;

    /**
     * Find applications.
     *
     * @param offset          the offset
     * @param numberOfRecords the number of records
     * @return the list of tutor applications
     * @throws ServiceException in case of any dao errors
     */
    List<Tutor> findApplications(int offset, int numberOfRecords) throws ServiceException;

    /**
     * Find tutors by users.
     *
     * @param users list of users entities
     * @return the map, where keys are users entities and values are tutor entities related to users
     * @throws ServiceException in case of any dao errors
     */
    Map<User,Tutor> findTutorsByUsers(List<User> users) throws ServiceException;

    /**
     * Count applications.
     *
     * @return the number of applications
     * @throws ServiceException in case of any dao errors
     */
    int countApplications() throws ServiceException;

    /**
     * Delete tutor by id.
     *
     * @param tutorId the tutor id
     * @return true if tutor deleted successfully
     * @throws ServiceException in case of any dao errors
     */
    boolean deleteTutorById(int tutorId) throws ServiceException;
}
