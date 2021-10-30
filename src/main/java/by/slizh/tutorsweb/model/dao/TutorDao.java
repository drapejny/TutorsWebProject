package by.slizh.tutorsweb.model.dao;

import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.model.entity.Tutor;

import java.util.List;
import java.util.Optional;

/**
 * The type Tutor dao.
 */
public abstract class TutorDao extends AbstractDao<Tutor> {

    /**
     * Find tutor by email.
     *
     * @param email the email
     * @return the optional {@link Optional} with tutor entity or empty optional
     * @throws DaoException in case of any SQL errors
     */
    public abstract Optional<Tutor> findTutorByEmail(String email) throws DaoException;

    /**
     * Search tutors.
     *
     * @param subjectId       the subject id
     * @param city            the city
     * @param minPrice        the min price
     * @param maxPrice        the max price
     * @param offset          the offset
     * @param numberOfRecords the number of records
     * @param sort            the type of sort
     * @return the list of searched tutors
     * @throws DaoException the dao exception
     */
    public abstract List<Tutor> searchTutors(int subjectId, String city, int minPrice, int maxPrice, int offset, int numberOfRecords, String sort) throws DaoException;

    /**
     * Count searched records.
     *
     * @param subjectId the subject id
     * @param city      the city
     * @param minPrice  the min price
     * @param maxPrice  the max price
     * @return the number of searched tutors
     * @throws DaoException the dao exception
     */
    public abstract int countSearchedTutors(int subjectId, String city, int minPrice, int maxPrice) throws DaoException;

    /**
     * Find all cities.
     *
     * @return the list of cities
     * @throws DaoException in case of any SQL errors
     */
    public abstract List<String> findAllCities() throws DaoException;

    /**
     * Find applications.
     *
     * @param offset          the offset
     * @param numberOfRecords the number of applications
     * @return the list of unconfirmed tutors
     * @throws DaoException in case of any SQL errors
     */
    public abstract List<Tutor> findApplications(int offset, int numberOfRecords) throws DaoException;

    /**
     * Count applications.
     *
     * @return the number of unconfirmed tutors
     * @throws DaoException in case of any SQL errors
     */
    public abstract int countApplications() throws DaoException;
}
