package by.slizh.tutorsweb.model.dao.impl;

import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.model.entity.Feedback;
import by.slizh.tutorsweb.model.entity.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

public class FeedbackDaoImplTest {

    @Mock
    FeedbackDaoImpl feedbackDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllFeedbacksTest() throws DaoException {
        when(feedbackDao.findAll()).thenReturn(List.of(new Feedback()));
        List<Feedback> expected = List.of(new Feedback());
        List<Feedback> actual = feedbackDao.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void findAllFeedbacksEmptyTest() throws DaoException {
        when(feedbackDao.findAll()).thenReturn(Collections.emptyList());
        List<Feedback> expected = Collections.emptyList();
        List<Feedback> actual = feedbackDao.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void findFeedbackByExistingIdTest() throws DaoException {
        when(feedbackDao.findById(anyInt())).thenReturn(Optional.of(new Feedback()));
        Optional<Feedback> expected = Optional.of(new Feedback());
        Optional<Feedback> actual = feedbackDao.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    public void findFeedbackByNotExistingIdTest() throws DaoException {
        when(feedbackDao.findById(anyInt())).thenReturn(Optional.empty());
        Optional<Feedback> expected = Optional.empty();
        Optional<Feedback> actual = feedbackDao.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    public void deleteFeedbackByExistingIdTest() throws DaoException {
        when(feedbackDao.deleteById(anyInt())).thenReturn(true);
        boolean actual = feedbackDao.deleteById(1);
        assertTrue(actual);
    }

    @Test
    public void deleteFeedbackByNotExistingIdTest() throws DaoException {
        when(feedbackDao.deleteById(anyInt())).thenReturn(false);
        boolean actual = feedbackDao.deleteById(1);
        assertFalse(actual);
    }

    @Test
    public void createFeedbackTest() throws DaoException {
        when(feedbackDao.create(new Feedback())).thenReturn(true);
        boolean actual = feedbackDao.create(new Feedback());
        assertTrue(actual);
    }

    @Test
    public void updateFeedbackTest() throws DaoException {
        when(feedbackDao.update(new Feedback())).thenReturn(new Feedback());
        Feedback expected = new Feedback();
        Feedback actual = new Feedback();
        assertEquals(expected, actual);
    }

    @Test
    public void findFeedbacksByTutorIdTest() throws DaoException {
        when(feedbackDao.findByTutorId(anyInt())).thenReturn(List.of(new Feedback()));
        List<Feedback> expected = List.of(new Feedback());
        List<Feedback> actual = feedbackDao.findByTutorId(1);
        assertEquals(expected, actual);
    }

    @Test
    public void findFeedbacksByTutorIdEmptyTest() throws DaoException {
        when(feedbackDao.findByTutorId(anyInt())).thenReturn(Collections.emptyList());
        List<Feedback> expected = Collections.emptyList();
        List<Feedback> actual = feedbackDao.findByTutorId(1);
        assertEquals(expected, actual);
    }

    @Test
    public void findByExistingTutorIdAndExistingUserIdTest() throws DaoException {
        when(feedbackDao.findByTutorIdAndUserId(anyInt(), anyInt())).thenReturn(Optional.of(new Feedback()));
        Optional<Feedback> expected = Optional.of(new Feedback());
        Optional<Feedback> actual = feedbackDao.findByTutorIdAndUserId(1, 2);
        assertEquals(expected, actual);
    }
    @Test
    public void findByNotExistingTutorIdAndNotExistingUserIdTest() throws DaoException {
        when(feedbackDao.findByTutorIdAndUserId(anyInt(), anyInt())).thenReturn(Optional.empty());
        Optional<Feedback> expected = Optional.empty();
        Optional<Feedback> actual = feedbackDao.findByTutorIdAndUserId(1, 2);
        assertEquals(expected, actual);
    }

}