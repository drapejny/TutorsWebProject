package by.slizh.tutorsweb.model.dao.impl;

import by.slizh.tutorsweb.model.entity.Feedback;
import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.model.dao.ColumnName;
import by.slizh.tutorsweb.model.dao.FeedbackDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class FeedbackDaoImpl extends FeedbackDao {

    private static final Logger logger = LogManager.getLogger();

    private static final String SQL_FIND_ALL_FEEDBACKS = """
            SELECT feedback_id, text, date, rating, user_id, tutor_id
            FROM feedbacks;
            """;
    private static final String SQL_FIND_FEEDBACK_BY_ID = """
            SELECT feedback_id, text, date, rating, user_id, tutor_id
            FROM feedbacks
            WHERE feedback_id = ?;
            """;
    private static final String SQL_FIND_FEEDBACKS_BY_TUTOR_ID = """
            SELECT feedback_id, text, date, rating, user_id, tutor_id
            FROM feedbacks
            WHERE tutor_id = ?
            ORDER BY date DESC;
            """;
    private static final String SQL_DELETE_FEEDBACK_BY_ID = """
            DELETE FROM feedbacks WHERE feedback_id = ?;
            """;
    private static final String SQL_CREATE_FEEDBACK = """
             INSERT INTO feedbacks (feedback_id, text, date, rating, user_id, tutor_id)
            VALUES (?, ?, ?, ?, ?, ?);
            """;
    private static final String SQL_UPDATE_FEEDBACK = """
            UPDATE feedbacks
            SET text = ?, date = ?, rating = ?
            WHERE feedback_id = ?;
            """;


    @Override
    public List<Feedback> findAll() throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_FEEDBACKS)) {
            List<Feedback> feedbacks = new ArrayList<>();
            try (ResultSet resultSet = statement.executeQuery();) {
                while (resultSet.next()) {
                    Feedback feedback = buildFeedback(resultSet);
                    feedbacks.add(feedback);
                }
                return feedbacks;
            }
        } catch (SQLException e) {
            logger.error("Failed to find all feedbacks", e);
            throw new DaoException("Failed to find all feedbacks", e);
        }
    }

    @Override
    public Optional<Feedback> findById(int id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_FEEDBACK_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Feedback feedback = buildFeedback(resultSet);
                    return Optional.of(feedback);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            logger.error("Failed to find feedback by id", e);
            throw new DaoException("Failed to find feedback by id", e);
        }
    }

    @Override
    public boolean deleteById(int id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_FEEDBACK_BY_ID)) {
            statement.setInt(1, id);
            boolean result = statement.executeUpdate() == 1;
            return result;
        } catch (SQLException e) {
            logger.error("Failed to delete feedback by id", e);
            throw new DaoException("Failed to delete feedback by id", e);
        }
    }

    @Override
    public boolean create(Feedback feedback) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_FEEDBACK, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, feedback.getFeedbackId());
            statement.setString(2, feedback.getText());
            statement.setDate(3, Date.valueOf(feedback.getDate()));
            statement.setInt(4, feedback.getRating());
            statement.setInt(5, feedback.getUserId());
            statement.setInt(6, feedback.getTutorId());
            boolean result = statement.executeUpdate() == 1;
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    feedback.setFeedbackId(resultSet.getInt(1));
                }
                return result;
            }
        } catch (SQLException e) {
            logger.error("Failed to create feedback", e);
            throw new DaoException("Failed to create feedback", e);
        }
    }

    @Override
    public Feedback update(Feedback feedback) throws DaoException {
        Feedback oldFeedback = findById(feedback.getFeedbackId()).
                orElseThrow(() -> new DaoException("Failed to update feedback, feedbackId not found"));
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_FEEDBACK)) {
            statement.setString(1, feedback.getText());
            statement.setDate(2, Date.valueOf(feedback.getDate()));
            statement.setInt(3, feedback.getRating());
            statement.setInt(4, feedback.getFeedbackId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to update feedback", e);
            throw new DaoException("Failed to update feedback", e);
        }
        return oldFeedback;
    }

    @Override
    public List<Feedback> findByTutorId(int tutorId) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_FEEDBACKS_BY_TUTOR_ID)) {
            statement.setInt(1, tutorId);
            List<Feedback> feedbacks = new ArrayList<>();
            try (ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()){
                    Feedback feedback = buildFeedback(resultSet);
                    feedbacks.add(feedback);
                }
                return feedbacks;
            }
        } catch (SQLException e) {
            logger.error("Failed to find feedbacks by tutorId", e);
            throw new DaoException("Failed to find feedbacks by tutorId", e);
        }
    }

    private Feedback buildFeedback(ResultSet resultSet) throws SQLException {
        Feedback feedback = new Feedback.FeedbackBuilder()
                .setFeedbackId(resultSet.getInt(ColumnName.FEEDBACK_ID))
                .setText(resultSet.getString(ColumnName.TEXT))
                .setDate(resultSet.getDate(ColumnName.DATE).toLocalDate())
                .setRating(resultSet.getInt(ColumnName.RATING))
                .setUserId(resultSet.getInt(ColumnName.USER_ID))
                .setTutorId(resultSet.getInt(ColumnName.TUTOR_ID))
                .createFeedback();
        return feedback;
    }
}
