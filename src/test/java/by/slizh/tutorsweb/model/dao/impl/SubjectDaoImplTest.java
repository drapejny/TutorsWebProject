package by.slizh.tutorsweb.model.dao.impl;

import by.slizh.tutorsweb.exception.DaoException;
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

public class SubjectDaoImplTest {

    @Mock
    SubjectDaoImpl subjectDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllSubjectsTest() throws DaoException {
        when(subjectDao.findAll()).thenReturn(List.of(new Subject()));
        List<Subject> expected = List.of(new Subject());
        List<Subject> actual = subjectDao.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void findAllSubjectsEmptyTest() throws DaoException {
        when(subjectDao.findAll()).thenReturn(Collections.emptyList());
        List<Subject> expected = Collections.emptyList();
        List<Subject> actual = subjectDao.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void findSubjectByExistingIdTest() throws DaoException {
        when(subjectDao.findById(anyInt())).thenReturn(Optional.of(new Subject()));
        Optional<Subject> expected = Optional.of(new Subject());
        Optional<Subject> actual = subjectDao.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    public void findSubjectByNotExistingIdTest() throws DaoException {
        when(subjectDao.findById(anyInt())).thenReturn(Optional.empty());
        Optional<Subject> expected = Optional.empty();
        Optional<Subject> actual = subjectDao.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    public void deleteByExistingIdTest() throws DaoException {
        when(subjectDao.deleteById(anyInt())).thenReturn(true);
        boolean actual = subjectDao.deleteById(1);
        assertTrue(actual);
    }

    @Test
    public void deleteByNotExistingIdTest() throws DaoException {
        when(subjectDao.deleteById(anyInt())).thenReturn(false);
        boolean actual = subjectDao.deleteById(1);
        assertFalse(actual);
    }

    @Test
    public void createSubjectTest() throws DaoException {
        when(subjectDao.create(new Subject())).thenReturn(true);
        boolean actual = subjectDao.create(new Subject());
        assertTrue(actual);
    }

    @Test
    public void updateSubjectTest() throws DaoException {
        when(subjectDao.update(new Subject())).thenReturn(new Subject());
        Subject expected = new Subject();
        Subject actual = new Subject();
        assertEquals(expected, actual);
    }

    @Test
    public void createTutorSubjectTest() throws DaoException {
        when(subjectDao.createTutorSubject(anyInt(), anyInt())).thenReturn(true);
        boolean actual = subjectDao.createTutorSubject(1, 2);
        assertTrue(actual);
    }

    @Test
    public void deleteExistingTutorSubjectTest() throws DaoException {
        when(subjectDao.deleteTutorSubject(anyInt(), anyInt())).thenReturn(1);
        int expected = 1;
        int actual = subjectDao.deleteTutorSubject(1, 2);
        assertEquals(expected, actual);
    }

    @Test
    public void deleteNotExistingTutorSubjectTest() throws DaoException {
        when(subjectDao.deleteTutorSubject(anyInt(), anyInt())).thenReturn(0);
        int expected = 0;
        int actual = subjectDao.deleteTutorSubject(1, 2);
        assertEquals(expected, actual);
    }

    @Test
    public void findSubjectsByTutorIdTest() throws DaoException {
        when(subjectDao.findSubjectsByTutorId(anyInt())).thenReturn(List.of(new Subject()));
        List<Subject> expected = List.of(new Subject());
        List<Subject> actual = subjectDao.findSubjectsByTutorId(1);
        assertEquals(expected, actual);
    }

    @Test
    public void findSubjectByExistingNameTest() throws DaoException {
        when(subjectDao.findSubjectByName(anyString())).thenReturn(Optional.of(new Subject()));
        Optional<Subject> expected = Optional.of(new Subject());
        Optional<Subject> actual = subjectDao.findSubjectByName("name");
        assertEquals(expected, actual);
    }

    @Test
    public void findSubjectByNotExistingNameTest() throws DaoException {
        when(subjectDao.findSubjectByName(anyString())).thenReturn(Optional.empty());
        Optional<Subject> expected = Optional.empty();
        Optional<Subject> actual = subjectDao.findSubjectByName("name");
        assertEquals(expected, actual);
    }
    @Test
    public  void deleteSubjectsByTutorIdTest() throws DaoException{
        when(subjectDao.deleteSubjectsByTutorId(anyInt())).thenReturn(10);
        int expected = 10;
        int actual = subjectDao.deleteSubjectsByTutorId(1);
        assertEquals(expected, actual);
    }
}