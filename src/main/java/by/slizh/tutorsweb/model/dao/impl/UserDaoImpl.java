package by.slizh.tutorsweb.model.dao.impl;

import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.model.dao.ColumnName;
import by.slizh.tutorsweb.model.dao.UserDao;
import by.slizh.tutorsweb.model.util.Base64Coder;
import by.slizh.tutorsweb.model.util.security.PasswordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.slizh.tutorsweb.model.dao.ColumnName.*;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.*;

public class UserDaoImpl extends UserDao {

    private static final Logger logger = LogManager.getLogger();

    private static final String SQL_FIND_ALL_USERS = """
            SELECT user_id, first_name, last_name, email, photo, role_name, status_name
            FROM users
            JOIN role ON users.role_id = role.role_id
            JOIN status ON users.status_id = status.status_id;
            """;
    private static final String SQL_FIND_USER_BY_ID = """
            SELECT user_id, first_name, last_name, email, photo, role_name, status_name
            FROM users
            JOIN role ON users.role_id = role.role_id
            JOIN status ON users.status_id = status.status_id
            WHERE user_id = ?;
            """;
    private static final String SQL_FIND_USER_BY_EMAIL = """
            SELECT user_id, first_name, last_name, email, photo, role_name, status_name
            FROM users
            JOIN role ON users.role_id = role.role_id
            JOIN status ON users.status_id = status.status_id
            WHERE email = ?;
            """;
    private static final String SQL_DELETE_USER_BY_ID = """
            DELETE FROM users WHERE user_id = ?;
            """;
    private static final String SQL_CREATE_USER = """
            INSERT INTO users (first_name, last_name, email, password, role_id, status_id)
            VALUES (?, ?, ?, ?, ?, ?);
            """;
    private static final String SQL_UPDATE_USER = """
            UPDATE users
            SET first_name = ?, last_name = ?, photo = ?, role_id = ?, status_id = ?
            WHERE user_id = ?;
            """;
    private static final String SQL_UPDATE_USER_PASSWORD = """
            UPDATE users
            SET password = ?
            WHERE user_id = ?;
            """;
    private static final String SQL_FIND_USER_PASSWORD = """
            SELECT password
            FROM users
            WHERE user_id = ?;
            """;
    private static final String SQL_SEARCH_USERS = """
            SELECT user_id, first_name, last_name, email, photo, role_name, status_name
            FROM users
            JOIN role ON role.role_id = users.role_id
            JOIN status ON status.status_id = users.status_id
            WHERE last_name LIKE ? OR email LIKE ?
            ORDER BY last_name, first_name
            LIMIT ?, ?;
            """;
    private static final String SQL_COUNT_SEARCH_USERS = """
            SELECT COUNT(*) AS count
            FROM users
            WHERE last_name LIKE ? OR email LIKE ?;
            """;
    private static final String SQL_FIND_ALL_ADMINS = """
            SELECT user_id, first_name, last_name, email, photo, role_name, status_name
            FROM users
            JOIN role ON users.role_id = role.role_id
            JOIN status ON users.status_id = status.status_id
            WHERE role_name = 'admin' AND email != 'admin@admin.com'
            ORDER BY first_name, last_name;
            """;

    @Override
    public List<User> findAll() throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_USERS)) {
            List<User> users = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = buildUser(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            logger.error("Failed to find all users", e);
            throw new DaoException("Failed to find all users", e);
        }
    }

    @Override
    public Optional<User> findById(int id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = buildUser(resultSet);
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("Failed to find user by id", e);
            throw new DaoException("Failed to find user by id", e);
        }
    }

    @Override
    public boolean deleteById(int id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER_BY_ID)) {
            statement.setInt(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("Failed to delete user by id", e);
            throw new DaoException("Failed to delete user by id", e);
        }
    }

    @Override
    public boolean create(User user) throws DaoException {
        throw new UnsupportedOperationException("This method unavailable in UserDao, use method create(User user, String password)");
    }

    @Override
    public boolean create(User user, String password) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_USER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, PasswordEncoder.encodePassword(password));
            statement.setInt(5, user.getRole().getId());
            statement.setInt(6, user.getStatus().getId());
            boolean result = statement.executeUpdate() == 1;
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setUserId(resultSet.getInt(1));
            }
            return result;
        } catch (SQLException e) {
            logger.error("Failed to create user", e);
            throw new DaoException("Failed to create user", e);
        }
    }

    @Override
    public User update(User user) throws DaoException {
        User oldUser = findById(user.getUserId()).
                orElseThrow(() -> new DaoException("Failed to update user, userId not found"));
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setBlob(3, user.getPhoto() == null ? null : Base64Coder.decode(user.getPhoto()));
            statement.setInt(4, user.getRole().getId());
            statement.setInt(5, user.getStatus().getId());
            statement.setInt(6, user.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to update user", e);
            throw new DaoException("Failed to update user", e);
        }
        return oldUser;
    }

    @Override
    public boolean updateUserPassword(User user, String password) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_PASSWORD)) {
            statement.setString(1, PasswordEncoder.encodePassword(password));
            statement.setInt(2, user.getUserId());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("Failed to update user password", e);
            throw new DaoException("Failed to update user password", e);
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = buildUser(resultSet);
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("Failed to find user by email", e);
            throw new DaoException("Failed to find user by email", e);
        }
    }

    @Override
    public String findUserPassword(User user) throws DaoException {
        String passwordHash;
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_PASSWORD)) {
            statement.setInt(1, user.getUserId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                passwordHash = resultSet.getString(ColumnName.PASSWORD);
            } else {
                logger.error("Can't find user with this id");
                throw new DaoException("Can't find user with this id");
            }
            return passwordHash;
        } catch (SQLException e) {
            logger.error("Failed to find user password", e);
            throw new DaoException("Failed to find user password", e);
        }
    }

    @Override
    public List<User> searchUsers(String searchLine, int offset, int rowCount) throws DaoException {
        searchLine = "%" + searchLine + "%";
        try (PreparedStatement statement = connection.prepareStatement(SQL_SEARCH_USERS)) {
            statement.setString(1, searchLine);
            statement.setString(2, searchLine);
            statement.setInt(3, offset);
            statement.setInt(4, rowCount);
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }
            return users;
        } catch (SQLException e) {
            logger.error("Failed to search users", e);
            throw new DaoException("Failed to search users", e);
        }
    }

    @Override
    public int countSearchUsers(String searchLine) throws DaoException {
        searchLine = "%" + searchLine + "%";
        try (PreparedStatement statement = connection.prepareStatement(SQL_COUNT_SEARCH_USERS)) {
            statement.setString(1, searchLine);
            statement.setString(2, searchLine);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("Failed to count searched users", e);
            throw new DaoException("Failed to count searched users", e);
        }
        return 0;
    }

    @Override
    public List<User> findAllAdmins() throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_ADMINS)) {
            ResultSet resultSet = statement.executeQuery();
            List<User> admins = new ArrayList<>();
            while (resultSet.next()) {
                User admin = buildUser(resultSet);
                admins.add(admin);
            }
            return admins;
        } catch (SQLException e) {
            logger.error("Failed to find all admins", e);
            throw new DaoException();
        }
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return new User.UserBuilder()
                .setUserId(resultSet.getInt(USER_ID))
                .setFirstName(resultSet.getString(FIRST_NAME))
                .setLastName(resultSet.getString(LAST_NAME))
                .setEmail(resultSet.getString(EMAIL))
                .setPhoto(resultSet.getBlob(PHOTO) == null ? null : Base64Coder.encode(resultSet.getBlob(PHOTO).getBinaryStream()))
                .setRole(User.Role.valueOf(resultSet.getString(ROLE_NAME).toUpperCase(Locale.ROOT)))
                .setStatus(User.Status.valueOf(resultSet.getString(STATUS_NAME).toUpperCase(Locale.ROOT)))
                .createUser();
    }
}
