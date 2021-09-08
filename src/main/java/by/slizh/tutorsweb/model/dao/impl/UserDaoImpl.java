package by.slizh.tutorsweb.model.dao.impl;

import by.slizh.tutorsweb.entity.User;
import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.model.dao.ColumnName;
import by.slizh.tutorsweb.model.dao.UserDao;
import by.slizh.tutorsweb.util.PasswordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class UserDaoImpl extends UserDao {

    private static final Logger logger = LogManager.getLogger();

    private static final String SQL_FIND_ALL_USERS = """
            SELECT user_id, first_name, last_name, email, phone, city, photo, role_name, status_name
            FROM users
            JOIN role ON users.role_id = role.role_id
            JOIN status ON users.status_id = status.status_id;
            """;
    private static final String SQL_FIND_USER_BY_ID = """
            SELECT user_id, first_name, last_name, email, phone, city, photo, role_name, status_name
            FROM users
            JOIN role ON users.role_id = role.role_id
            JOIN status ON users.status_id = status.status_id
            WHERE user_id = ?;
            """;
    private static final String SQL_FIND_USER_BY_EMAIL = """
            SELECT user_id, first_name, last_name, email, phone, city, photo, role_name, status_name
            FROM users
            JOIN role ON users.role_id = role.role_id
            JOIN status ON users.status_id = status.status_id
            WHERE email = ?;
            """;
    private static final String SQL_DELETE_USER_BY_ID = """
            DELETE FROM users WHERE user_id = ?;
            """;
    private static final String SQL_CREATE_USER = """
            INSERT INTO users (first_name, last_name, email, phone, password, city, photo, role_id, status_id)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);
            """;
    private static final String SQL_UPDATE_USER = """
            UPDATE users
            SET first_name = ?, last_name = ?, phone = ?, city = ?, photo = ?, role_id = ?, status_id = ?
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

    @Override
    public List<User> findAll() throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_USERS)) {
            List<User> users = new LinkedList<User>();
            try (ResultSet resultSet = statement.executeQuery();) {
                while (resultSet.next()) {
                    User user = buildUser(resultSet);
                    users.add(user);
                }
                return users;
            }
        } catch (SQLException e) {
            logger.error("Failed to find all users", e);
            throw new DaoException("Failed to find all users", e);
        }
    }

    @Override
    public Optional<User> findById(Integer id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = buildUser(resultSet);
                    return Optional.of(user);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            logger.error("Failed to find user by id", e);
            throw new DaoException("Failed to find user by id", e);
        }
    }

    @Override
    public boolean deleteById(Integer id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER_BY_ID)) {
            statement.setInt(1, id);
            boolean result = statement.executeUpdate() == 1;
            return result;
        } catch (SQLException e) {
            logger.error("Failed to delete user by id", e);
            throw new DaoException("Failed to delete user by id", e);
        }
    }

    @Override
    public boolean create(User user) throws DaoException {
        throw new UnsupportedOperationException("This method unavailable in UserDao," +
                " use method create(User user, String password)");
    }

    @Override
    public boolean create(User user, String password) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_USER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhone());
            statement.setString(5, PasswordEncoder.encodePassword(password));
            statement.setString(6, user.getCity());
            statement.setBinaryStream(7, user.getPhoto());
            statement.setInt(8, user.getRole().getId());
            statement.setInt(9, user.getStatus().getId());
            boolean result = statement.executeUpdate() == 1;
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    user.setUserId(resultSet.getInt(1));
                }
                return result;
            }
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
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getCity());
            statement.setBinaryStream(5, user.getPhoto());
            statement.setInt(6, user.getRole().getId());
            statement.setInt(7, user.getStatus().getId());
            statement.setInt(8, user.getUserId());
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
            statement.setString(1, password);
            statement.setInt(2, user.getUserId());
            boolean result = statement.executeUpdate() == 1;
            return result;
        } catch (SQLException e) {
            logger.error("Failed to update user password", e);
            throw new DaoException("Failed to update user password", e);
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_EMAIL)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = buildUser(resultSet);
                    return Optional.of(user);
                } else {
                    return Optional.empty();
                }
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
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    passwordHash = resultSet.getString(ColumnName.PASSWORD);
                } else {
                    logger.error("Can't find user with this id");
                    throw new DaoException("Can't find user with this id");
                }
                return passwordHash;
            }
        } catch (SQLException e) {
            logger.error("Failed to find user password", e);
            throw new DaoException("Failed to find user password", e);
        }
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        User user = new User.UserBuilder()
                .setUserId(resultSet.getInt(ColumnName.USER_ID))
                .setFirstName(resultSet.getString(ColumnName.FIRST_NAME))
                .setLastName(resultSet.getString(ColumnName.LAST_NAME))
                .setEmail(resultSet.getString(ColumnName.EMAIL))
                .setPhone(resultSet.getString(ColumnName.PHONE))
                .setCity(resultSet.getString(ColumnName.CITY))
                .setPhoto(resultSet.getBinaryStream(ColumnName.PHOTO))
                .setRole(User.Role.valueOf(resultSet.getString(ColumnName.ROLE_NAME).toUpperCase(Locale.ROOT)))
                .setStatus(User.Status.valueOf(resultSet.getString(ColumnName.STATUS_NAME).toUpperCase(Locale.ROOT)))
                .createUser();
        return user;
    }
}
