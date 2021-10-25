package by.slizh.tutorsweb.model.dao;

import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.model.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class EntityTransaction {

    private Connection connection;

    public void initTransaction(AbstractDao... daos) throws DaoException {
        if (connection == null) {
            connection = ConnectionPool.getInstance().getConnection();
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException("Failed to disable auto-commit for transaction", e);
        }
        for (AbstractDao dao : daos) {
            dao.setConnection(connection);
        }
    }

    public void init(AbstractDao dao) {
        if (connection == null) {
            connection = ConnectionPool.getInstance().getConnection();
        }
        dao.setConnection(connection);
    }

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

    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException("Failed to commit for transaction", e);
        }
    }

    public void rollback() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException("Failed to rollback for transaction", e);
        }
    }
}
