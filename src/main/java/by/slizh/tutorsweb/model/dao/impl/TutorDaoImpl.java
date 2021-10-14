package by.slizh.tutorsweb.model.dao.impl;

import by.slizh.tutorsweb.model.entity.Tutor;
import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.model.dao.TutorDao;
import by.slizh.tutorsweb.util.Base64Coder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static by.slizh.tutorsweb.model.dao.ColumnName.*;

public class TutorDaoImpl extends TutorDao {

    private static final Logger logger = LogManager.getLogger();

    private static final String SQL_FIND_ALL_TUTORS = """
             SELECT tutor_id, phone, education, info, price_per_hour, is_active,
                                users.user_id, first_name, last_name, email, city, photo, role_name, status_name
             FROM tutors
             JOIN users ON tutors.user_id = users.user_id
             JOIN role ON users.role_id = role.role_id
             JOIN status ON users.status_id = status.status_id;
            """;
    private static final String SQL_FIND_TUTOR_BY_ID = """
            SELECT tutor_id, phone, education, info, price_per_hour, is_active,
                                users.user_id, first_name, last_name, email, city, photo, role_name, status_name
             FROM tutors
             JOIN users ON tutors.user_id = users.user_id
             JOIN role ON users.role_id = role.role_id
             JOIN status ON users.status_id = status.status_id
             WHERE tutor_id = ?;
            """;
    private static final String SQL_FIND_TUTOR_BY_EMAIL = """
            SELECT tutor_id, phone, education, info, price_per_hour, is_active,
                                users.user_id, first_name, last_name, email, city, photo, role_name, status_name
             FROM tutors
             JOIN users ON tutors.user_id = users.user_id
             JOIN role ON users.role_id = role.role_id
             JOIN status ON users.status_id = status.status_id
             WHERE email = ?;
            """;
    private static final String SQL_FIND_ALL_CITIES = """
            SELECT DISTINCT city, role_id FROM tutors
            JOIN users ON tutors.user_id = users.user_id
            WHERE role_id = 3;
            """;
    private static final String SQL_DELETE_TUTOR_BY_ID = """
            DELETE FROM tutors WHERE tutor_id = ?;
            """;
    private static final String SQL_CREATE_TUTOR = """
            INSERT INTO tutors(user_id, phone, city, education, info, price_per_hour, is_active)
            VALUES (?, ?, ?, ?, ?, ?, ?);
            """;
    private static final String SQL_UPDATE_TUTOR = """
            UPDATE tutors
            SET phone = ?, city = ?, education = ?, info = ?, price_per_hour = ?, is_active = ?
            WHERE tutor_id = ?;
            """;
    private static final String SQL_SEARCH_TUTORS_FIRST_PART = """
            SELECT tutor_id, phone, education, info, price_per_hour, is_active, users.user_id, first_name, last_name, email, city, photo, role_name, status_name
            FROM tutors
            JOIN users ON tutors.user_id = users.user_id
            JOIN role ON users.role_id = role.role_id
            JOIN status ON users.status_id = status.status_id
            WHERE tutors.tutor_id IN (SELECT tutor_id FROM tutors_has_subject WHERE subject_id = ?) AND
            city LIKE ? AND
            price_per_hour > ? AND tutors.price_per_hour < ? AND
            is_active = true AND
            role_name = 'tutor' AND
            status_name = 'activated' 
            """;
    private static final String SQL_SEARCH_TUTORS_LIMIT_PART = "LIMIT ?, ?;";
    private static final String SQL_COUNT_SEARCHED_RECORDS = """
            SELECT COUNT(*) AS count
            FROM tutors
             JOIN users ON tutors.user_id = users.user_id
             JOIN role ON users.role_id = role.role_id
             JOIN status ON users.status_id = status.status_id
             WHERE tutors.tutor_id IN (SELECT tutor_id FROM tutors_has_subject WHERE subject_id = ?) AND
             city LIKE ? AND
             price_per_hour > ? AND tutors.price_per_hour < ? AND
             is_active = true AND
             role_name = 'tutor' AND
             status_name = 'activated';
            """;
    private static final String SQL_FIND_APPLICATIONS = """
             SELECT tutor_id, phone, education, info, price_per_hour, is_active,
                                users.user_id, first_name, last_name, email, city, photo, role_name, status_name
             FROM tutors
             JOIN users ON tutors.user_id = users.user_id
             JOIN role ON users.role_id = role.role_id
             JOIN status ON users.status_id = status.status_id
             WHERE role_name = 'user'
             ORDER BY tutor_id
             LIMIT ?, ?;
            """;
    private static final String SQL_COUNT_APPLICATIONS = """
            SELECT COUNT(*) AS count
            FROM tutors
            JOIN users ON tutors.user_id = users.user_id
            WHERE role_id = 2;
            """;


    @Override
    public List<Tutor> findAll() throws DaoException {
        List<Tutor> tutors = new LinkedList<Tutor>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_TUTORS)) {
            ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Tutor tutor = buildTutor(resultSet);
                    tutors.add(tutor);
                }
                return tutors;

        } catch (SQLException e) {
            logger.error("Failed to find all tutors", e);
            throw new DaoException("Failed to find all tutors", e);
        }
    }


    @Override
    public Optional<Tutor> findTutorByEmail(String email) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TUTOR_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    Tutor tutor = buildTutor(resultSet);
                    return Optional.of(tutor);
                } else {
                    return Optional.empty();
                }

        } catch (SQLException e) {
            logger.error("Failed to find tutor by email", e);
            throw new DaoException("Failed to find tutor by email", e);
        }
    }

    @Override
    public List<Tutor> searchTutors(int subjectId, String city, int minPrice, int maxPrice, int offset, int numberOfRecords, String sort) throws DaoException {
        List<Tutor> tutors = new ArrayList<>();
        String query = buildSearchQuery(sort);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, subjectId);
            statement.setString(2, city);
            statement.setInt(3, minPrice - 1);
            statement.setInt(4, maxPrice + 1);
            statement.setInt(5, offset);
            statement.setInt(6, numberOfRecords);
            ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Tutor tutor = buildTutor(resultSet);
                    tutors.add(tutor);
                }
                return tutors;
        } catch (SQLException e) {
            logger.error("Failed to search tutors", e);
            throw new DaoException("Failed to search tutors", e);
        }
    }

    @Override
    public int countSearchedRecords(int subjectId, String city, int minPrice, int maxPrice) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_COUNT_SEARCHED_RECORDS)) {
            statement.setInt(1, subjectId);
            statement.setString(2, "%" + city + "%");
            statement.setInt(3, minPrice - 1);
            statement.setInt(4, maxPrice + 1);
            ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    return 0;
                }
        } catch (SQLException e) {
            logger.error("Failed to count searched tutors", e);
            throw new DaoException("Failed to count searched tutors", e);
        }
    }

    @Override
    public List<String> findAllCities() throws DaoException {
        List<String> cities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_CITIES)) {
            ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    cities.add(resultSet.getString(1));
                }
            return cities;
        } catch (SQLException e) {
            logger.error("Failed to find all cities", e);
            throw new DaoException("Failed to find all cities", e);
        }
    }

    @Override
    public List<Tutor> findApplications(int offset, int numberOfRecords) throws DaoException {
        List<Tutor> tutors = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_APPLICATIONS)) {
            statement.setInt(1, offset);
            statement.setInt(2, numberOfRecords);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tutors.add(buildTutor(resultSet));
            }
            return tutors;
        } catch (SQLException e) {
            logger.error("Failed to find applications", e);
            throw new DaoException("Failed to find applications", e);
        }
    }

    @Override
    public int countApplications() throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_COUNT_APPLICATIONS)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            logger.error("Failed to count applications", e);
            throw new DaoException("Failed to count applications", e);
        }
    }


    @Override
    public Optional<Tutor> findById(int id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TUTOR_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    Tutor tutor = buildTutor(resultSet);
                    return Optional.of(tutor);
                } else {
                    return Optional.empty();
                }
        } catch (SQLException e) {
            logger.error("Failed to find tutor by id", e);
            throw new DaoException("Failed to find tutor by id", e);
        }
    }

    @Override
    public boolean deleteById(int id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_TUTOR_BY_ID)) {
            statement.setInt(1, id);
            boolean result = statement.executeUpdate() == 1;
            return result;
        } catch (SQLException e) {
            logger.error("Failed to delete tutor by id", e);
            throw new DaoException("Failed to delete tutor by id", e);
        }
    }


    @Override
    public boolean create(Tutor tutor) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_TUTOR, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, tutor.getUserId());
            statement.setString(2, tutor.getPhone());
            statement.setString(3, tutor.getCity());
            statement.setString(4, tutor.getEducation());
            statement.setString(5, tutor.getInfo());
            statement.setInt(6, tutor.getPricePerHour());
            statement.setByte(7, (byte) (tutor.getIsActive() ? 1 : 0));
            boolean result = statement.executeUpdate() == 1;
            ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    tutor.setTutorId(resultSet.getInt(1));
                }
                return result;
        } catch (SQLException e) {
            logger.error("Failed to create tutor", e);
            throw new DaoException("Failed to create tutor", e);
        }
    }


    @Override
    public Tutor update(Tutor tutor) throws DaoException {
        Tutor oldTutor = findById(tutor.getTutorId())
                .orElseThrow(() ->
                        new DaoException("Failed to update tutor, tutorId not found")
                );
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_TUTOR)) {
            statement.setString(1, tutor.getPhone());
            statement.setString(2, tutor.getCity());
            statement.setString(3, tutor.getEducation());
            statement.setString(4, tutor.getInfo());
            statement.setInt(5, tutor.getPricePerHour());
            statement.setByte(6, (byte) (tutor.getIsActive() ? 1 : 0));
            statement.setInt(7, tutor.getTutorId());

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to update tutor", e);
            throw new DaoException("Failed to update tutor", e);
        }
        return oldTutor;
    }

    private Tutor buildTutor(ResultSet resultSet) throws SQLException {
        Tutor tutor = new Tutor.TutorBuilder()
                .setTutorId(resultSet.getInt(TUTOR_ID))
                .setPhone(resultSet.getString(PHONE))
                .setEducation(resultSet.getString(EDUCATION))
                .setInfo(resultSet.getString(INFO))
                .setPricePerHour(resultSet.getInt(PRICE_PER_HOUR))
                .setIsActive(resultSet.getByte(IS_ACTIVE) == 1 ? true : false)
                .setUserId(resultSet.getInt(USER_ID))
                .setFirstName(resultSet.getString(FIRST_NAME))
                .setLastName(resultSet.getString(LAST_NAME))
                .setEmail(resultSet.getString(EMAIL))
                .setCity(resultSet.getString(CITY))
                .setPhoto(resultSet.getBlob(PHOTO) == null ? null : Base64Coder.encode(resultSet.getBlob(PHOTO).getBinaryStream()))
                .setRole(User.Role.valueOf(resultSet.getString(ROLE_NAME).toUpperCase()))
                .setStatus(User.Status.valueOf(resultSet.getString(STATUS_NAME).toUpperCase()))
                .createTutor();
        return tutor;
    }


    private String buildSearchQuery(String sort) {
        StringBuilder stringBuilder = new StringBuilder(SQL_SEARCH_TUTORS_FIRST_PART);
        String orderBy;
        switch (sort) {
            case "price_asc":
                orderBy = "ORDER BY " + PRICE_PER_HOUR + " ASC ";
                break;
            case "price_desc":
                orderBy = "ORDER BY " + PRICE_PER_HOUR + " DESC ";
                break;
            default:
                orderBy = "ORDER BY " + TUTOR_ID + " ";
        }
        stringBuilder.append(orderBy);
        stringBuilder.append(SQL_SEARCH_TUTORS_LIMIT_PART);
        return stringBuilder.toString();

    }

}
