package by.slizh.tutorsweb.model.dao.impl;

import by.slizh.tutorsweb.model.entity.Subject;
import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.model.dao.ColumnName;
import by.slizh.tutorsweb.model.dao.SubjectDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubjectDaoImpl extends SubjectDao {

    private static final Logger logger = LogManager.getLogger();

    private static final String SQL_FIND_ALL_SUBJECTS = """
            SELECT subject_id, subject_name
            FROM subjects
            ORDER BY subject_name;
            """;
    private static final String SQL_FIND_SUBJECT_BY_ID = """
            SELECT subject_id, subject_name
            FROM subjects
            WHERE subject_id = ?;
            """;
    private static final String SQL_FIND_SUBJECT_BY_NAME = """
            SELECT subject_id, subject_name
            FROM subjects
            WHERE subject_name = ?;
            """;
    private static final String SQL_DELETE_SUBJECT_BY_ID = """
            DELETE FROM subjects
            WHERE subject_id = ?;
            """;
    private static final String SQL_CREATE_SUBJECT = """
            INSERT INTO subjects (subject_name)
            VALUES (?);
            """;
    private static final String SQL_UPDATE_SUBJECT = """
            UPDATE subjects
            SET subject_name = ?
            WHERE subject_id = ?;
            """;
    private static final String SQL_CREATE_TUTOR_SUBJECT = """
            INSERT INTO tutors_has_subject(tutor_id, subject_id)
            VALUES(?, ?);
            """;
    private static final String SQL_FIND_SUBJECTS_BY_TUTOR_ID = """
            SELECT tutors_has_subject.subject_id, subject_name
            FROM tutors_has_subject
            JOIN subjects ON subjects.subject_id = tutors_has_subject.subject_id
            WHERE tutor_id = ?;
            """;
    private static final String SQL_DELETE_SUBJECTS_BY_TUTOR_ID = """
            DELETE FROM tutors_has_subject
            WHERE tutor_id = ?;
            """;
    private static final String SQL_DELETE_TUTOR_SUBJECT = """
            DELETE FROM tutors_has_subject
            WHERE tutor_id = ? AND subject_id = ?;
            """;

    @Override
    public List<Subject> findAll() throws DaoException {
        List<Subject> subjects = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_SUBJECTS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(resultSet.getInt(ColumnName.SUBJECT_ID));
                subject.setSubjectName(resultSet.getString(ColumnName.SUBJECT_NAME));
                subjects.add(subject);
            }
            return subjects;
        } catch (SQLException e) {
            logger.error("Failed to find all subjects", e);
            throw new DaoException("Failed to find all subjects", e);
        }
    }

    @Override
    public Optional<Subject> findById(int id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_SUBJECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(resultSet.getInt(ColumnName.SUBJECT_ID));
                subject.setSubjectName(resultSet.getString(ColumnName.SUBJECT_NAME));
                return Optional.of(subject);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("Failed to find subject by id", e);
            throw new DaoException("Failed to find subject by id", e);
        }
    }

    @Override
    public boolean deleteById(int id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_SUBJECT_BY_ID)) {
            statement.setInt(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("Failed to delete subject by if", e);
            throw new DaoException("Failed to delete subject by if", e);
        }
    }

    @Override
    public boolean create(Subject subject) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_SUBJECT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, subject.getSubjectName());
            boolean result = statement.executeUpdate() == 1;
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                subject.setSubjectId(resultSet.getInt(1));
            }
            return result;
        } catch (SQLException e) {
            logger.error("Failed to create subject", e);
            throw new DaoException("Failed to create subject", e);
        }
    }

    @Override
    public Subject update(Subject subject) throws DaoException {
        Subject oldSubject = findById(subject.getSubjectId())
                .orElseThrow(() -> new DaoException("Failed to update subject, subjectId not found"));
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_SUBJECT)) {
            statement.setString(1, subject.getSubjectName());
            statement.setInt(2, subject.getSubjectId());
            statement.executeUpdate();
            return oldSubject;
        } catch (SQLException e) {
            logger.error("Failed to update subject", e);
            throw new DaoException("Failed to update subject", e);
        }
    }

    @Override
    public boolean createTutorSubject(int tutorId, int subjectId) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_TUTOR_SUBJECT)) {
            statement.setInt(1, tutorId);
            statement.setInt(2, subjectId);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("Failed to create tutorSubject record", e);
            throw new DaoException("Failed to create tutorSubject record", e);
        }
    }

    @Override
    public int deleteTutorSubject(int tutorId, int subjectId) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_TUTOR_SUBJECT)) {
            statement.setInt(1, tutorId);
            statement.setInt(2, subjectId);
            return statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to delete tutorSubject record", e);
            throw new DaoException("Failed to delete tutorSubject record", e);
        }
    }

    @Override
    public List<Subject> findSubjectsByTutorId(int tutorId) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_SUBJECTS_BY_TUTOR_ID)) {
            statement.setInt(1, tutorId);
            ResultSet resultSet = statement.executeQuery();
            List<Subject> subjects = new ArrayList<>();
            while (resultSet.next()) {
                int subjectId = resultSet.getInt(ColumnName.SUBJECT_ID);
                String subjectName = resultSet.getString(ColumnName.SUBJECT_NAME);
                subjects.add(new Subject(subjectId, subjectName));
            }
            return subjects;
        } catch (SQLException e) {
            logger.error("Failed to find subjects by tutor id", e);
            throw new DaoException("Failed to find subjects by tutor id", e);
        }
    }

    @Override
    public Optional<Subject> findSubjectByName(String name) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_SUBJECT_BY_NAME)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(resultSet.getInt(ColumnName.SUBJECT_ID));
                subject.setSubjectName(resultSet.getString(ColumnName.SUBJECT_NAME));
                return Optional.of(subject);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("Failed to find subjects by name", e);
            throw new DaoException("Failed to find subjects by name", e);
        }
    }

    @Override
    public int deleteSubjectsByTutorId(int tutorId) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_SUBJECTS_BY_TUTOR_ID)) {
            statement.setInt(1, tutorId);
            return statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to delete subjects by tutor id", e);
            throw new DaoException("Failed to delete subjects by tutor id", e);
        }
    }
}
