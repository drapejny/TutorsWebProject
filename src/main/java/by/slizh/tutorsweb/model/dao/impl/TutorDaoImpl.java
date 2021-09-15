package by.slizh.tutorsweb.model.dao.impl;

import by.slizh.tutorsweb.model.entity.Tutor;
import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.model.dao.ColumnName;
import by.slizh.tutorsweb.model.dao.TutorDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
    private static final String SQL_DELETE_TUTOR_BY_ID = """
            DELETE FROM tutors WHERE tutor_id = ?;
            """;
    private static final String SQL_CREATE_TUTOR = """
            INSERT INTO tutors(user_id, phone, education, info, price_per_hour, is_active)
            VALUES (?, ?, ?, ?, ?, ?);
            """;
    private static final String SQL_UPDATE_TUTOR = """
            UPDATE tutors
            SET phone = ?, education = ?, info = ?, price_per_hour = ?, is_active = ?
            WHERE tutor_id = ?;
            """;

    @Override
    public List<Tutor> findAll() throws DaoException {
        List<Tutor> tutors = new LinkedList<Tutor>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_TUTORS)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Tutor tutor = buildTutor(resultSet);
                    tutors.add(tutor);
                }
                return tutors;
            }
        } catch (SQLException e) {
            logger.error("Failed to find all tutors", e);
            throw new DaoException("Failed to find all tutors", e);
        }
    }

    @Override
    public Optional<Tutor> findById(Integer id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_TUTOR_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Tutor tutor = buildTutor(resultSet);
                    return Optional.of(tutor);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            logger.error("Failed to find tutor by id", e);
            throw new DaoException("Failed to find tutor by id", e);
        }
    }

    @Override
    public boolean deleteById(Integer id) throws DaoException {
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
            statement.setString(3, tutor.getEducation());
            statement.setString(4, tutor.getInfo());
            statement.setBigDecimal(5, tutor.getPricePerHour());
            statement.setByte(6, (byte) (tutor.isActive() ? 1 : 0));
            boolean result = statement.executeUpdate() == 1;
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    tutor.setTutorId(resultSet.getInt(1));
                }
                return result;
            }
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
            statement.setString(2, tutor.getEducation());
            statement.setString(3, tutor.getInfo());
            statement.setBigDecimal(4, tutor.getPricePerHour());
            statement.setByte(5, (byte) (tutor.isActive() ? 1 : 0));
            statement.setInt(6, tutor.getTutorId());

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to update tutor", e);
            throw new DaoException("Failed to update tutor", e);
        }
        return oldTutor;
    }

    private Tutor buildTutor(ResultSet resultSet) throws SQLException {
        Tutor tutor = new Tutor.TutorBuilder()
                .setTutorId(resultSet.getInt(ColumnName.TUTOR_ID))
                .setPhone(ColumnName.PHONE)
                .setEducation(resultSet.getString(ColumnName.EDUCATION))
                .setInfo(resultSet.getString(ColumnName.INFO))
                .setPricePerHour(resultSet.getBigDecimal(ColumnName.PRICE_PER_HOUR))
                .setActive(resultSet.getByte(ColumnName.IS_ACTIVE) == 1 ? true : false)
                .setUserId(resultSet.getInt(ColumnName.USER_ID))
                .setFirstName(resultSet.getString(ColumnName.FIRST_NAME))
                .setLastName(resultSet.getString(ColumnName.LAST_NAME))
                .setEmail(resultSet.getString(ColumnName.EMAIL))
                .setCity(resultSet.getString(ColumnName.CITY))
                .setPhoto(resultSet.getBinaryStream(ColumnName.PHOTO))
                .setRole(User.Role.valueOf(resultSet.getString(ColumnName.ROLE_NAME).toUpperCase()))
                .setStatus(User.Status.valueOf(resultSet.getString(ColumnName.STATUS_NAME).toUpperCase()))
                .createTutor();
        return tutor;
    }

}
