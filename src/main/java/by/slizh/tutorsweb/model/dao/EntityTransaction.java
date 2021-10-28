package by.slizh.tutorsweb.model.dao;

import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.model.connection.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The type EntityTransaction.
 */
public class EntityTransaction {

    private static final Logger logger = LogManager.getLogger();

    private Connection connection;

    /**
     * Initialize transaction.
     * Disable auto-commit mode on taken connection and
     * set current connection on all daos.
     *
     * @param daos the dao entities
     * @throws DaoException the dao exception in case of change
     * auto commit mode.
     */
    public void initTransaction(AbstractDao... daos) throws DaoException {
        if (connection == null) {
            connection = ConnectionPool.getInstance().getConnection();
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error("Failed to disable auto-commit for transaction", e);
            throw new DaoException("Failed to disable auto-commit for transaction", e);
        }
        for (AbstractDao dao : daos) {
            dao.setConnection(connection);
        }
    }

    /**
     * Set taken connection on single dao.
     * Don't disable auto-commit mode on connection.
     *
     * @param dao the dao
     */
    public void init(AbstractDao dao) {
        if (connection == null) {
            connection = ConnectionPool.getInstance().getConnection();
        }
        dao.setConnection(connection);
    }

    /**
     * End transaction. Set connection link to null and
     * release connection.
     * Activate auto-commit mode.
     *
     * @throws DaoException the dao exception
     */
    public void endTransaction() throws DaoException {
        if (connection == null) {
            throw new DaoException("Failed to end transaction, connection=null");
        }
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DaoException("Failed to enable auto-commit for transaction", e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException("Can't close connection", e);
        }
        connection = null;
    }

    /**
     * End single dao use. Set connection link to null
     * and release it.
     *
     * @throws DaoException the dao exception
     */
    public void end() throws DaoException {
        if (connection == null) {
            throw new DaoException("Failed to end, connection is null");
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException("Can't close connection", e);
        }
        connection = null;
    }

    /**
     * Commit transaction.
     *
     * @throws DaoException the dao exception
     */
    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException("Failed to commit for transaction", e);
        }
    }

    /**
     * Rollback transaction.
     *
     * @throws DaoException the dao exception
     */
    public void rollback() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException("Failed to rollback for transaction", e);
        }
    }
}
