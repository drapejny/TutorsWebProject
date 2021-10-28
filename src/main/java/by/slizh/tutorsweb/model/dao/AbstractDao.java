package by.slizh.tutorsweb.model.dao;

import by.slizh.tutorsweb.model.entity.AbstractEntity;
import by.slizh.tutorsweb.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 * The type AbstractDao.
 *
 * @param <T> the entity type
 */
public abstract class AbstractDao< T extends AbstractEntity> {

    /**
     * The Connection.
     */
    protected Connection connection;

    /**
     * Find all entities.
     *
     * @return the list of all entities
     * @throws DaoException in case of any SQL errors
     */
    public abstract List<T> findAll() throws DaoException;

    /**
     * Find entity by id.
     *
     * @param id the entity id
     * @return the optional with entity or empty optional
     * @throws DaoException in case of any SQL errors
     */
    public abstract Optional<T> findById(int id) throws DaoException;

    /**
     * Delete entity by id
     *
     * @param id the entity id
     * @return true if deleting successfully
     * @throws DaoException in case of any SQL errors
     */
    public abstract boolean deleteById(int id) throws DaoException;

    /**
     * Create record in database.
     *
     * @param t entity for creating
     * @return true if creating successfully
     * @throws DaoException in case of any SQL errors
     */
    public abstract boolean create(T t) throws DaoException;

    /**
     * Update record in database
     *
     * @param t entity for updating
     * @return old entity object
     * @throws DaoException in case of any SQL errors
     */
    public abstract T update(T t) throws DaoException;

    /**
     * Sets connection.
     *
     * @param connection the database connection
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
