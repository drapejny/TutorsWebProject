package by.slizh.tutorsweb.model.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The multithreading safe ConnectionPool.
 */
public class ConnectionPool {

    private static final Logger logger = LogManager.getLogger();

    /**
     * Connection pool instance
     */
    private static ConnectionPool instance;

    private static final AtomicBoolean isCreated = new AtomicBoolean(false);
    private static final ReentrantLock locker = new ReentrantLock();
    private final BlockingQueue<ProxyConnection> freeConnections;
    private final BlockingQueue<ProxyConnection> givenAwayConnections;
    private static final int DEFAULT_POOL_SIZE = 32;

    private ConnectionPool() {
        freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        givenAwayConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            ProxyConnection proxyConnection;
            try {
                proxyConnection = ConnectionFactory.createConnection();
                freeConnections.put(proxyConnection);
            } catch (SQLException e) {
                logger.fatal("Can't create connection for connection pool", e);
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                logger.fatal("Something wrong with current thread", e);
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Gets instance of connection pool.
     * Safe for multithreading use.
     *
     * @return the instance of connection pool
     */
    public static ConnectionPool getInstance() {
        if (!isCreated.get()) {
            locker.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    isCreated.set(true);
                }
            } finally {
                locker.unlock();
            }
        }
        return instance;
    }

    /**
     * Gets free connection from queue.
     * Using blocking queue to store free connections.
     *
     * @return the connection
     */
    public ProxyConnection getConnection() {
        ProxyConnection proxyConnection = null;
        try {
            proxyConnection = freeConnections.take();
            givenAwayConnections.put(proxyConnection);
        } catch (InterruptedException e) {
            logger.error("Can't put connection to givenAwayConnections queue", e);
            Thread.currentThread().interrupt();
        }
        return proxyConnection;
    }

    /**
     * Release connection. Method move connection
     * from given away connections queue to free connections queue.
     *
     * @param connection the connection
     */
    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            givenAwayConnections.remove(connection);
            try {
                freeConnections.put((ProxyConnection) connection);
            } catch (InterruptedException e) {
                logger.error("Can't put connection to freeConnections queue", e);
                Thread.currentThread().interrupt();
            }
        } else {
            logger.fatal("Wrong connection is detected, expected ProxyConnection object");
            throw new RuntimeException();
        }
    }

    /**
     * Destroy pool.
     * Close all connections and deregister drivers.
     */
    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (SQLException e) {
                logger.error("Failed to close connection when destroying pool", e);
            } catch (InterruptedException e) {
                logger.error("Interrupted while waiting", e);
                Thread.currentThread().interrupt();
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("Exception when deregister driver ", e);
            }
        });
    }
}
