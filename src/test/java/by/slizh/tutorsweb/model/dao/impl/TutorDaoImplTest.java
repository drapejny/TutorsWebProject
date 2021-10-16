package by.slizh.tutorsweb.model.dao.impl;

import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.model.entity.Tutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

public class TutorDaoImplTest {

    @Mock
    TutorDaoImpl tutorDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllTutorsTest() throws DaoException {
        when(tutorDao.findAll()).thenReturn(List.of(new Tutor()));
        List<Tutor> expected = List.of(new Tutor());
        List<Tutor> actual = tutorDao.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void findAllTutorsEmptyTest() throws DaoException {
        when(tutorDao.findAll()).thenReturn(Collections.emptyList());
        List<Tutor> expected = Collections.emptyList();
        List<Tutor> actual = tutorDao.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void findTutorByExistingEmailTest() throws DaoException {
        when(tutorDao.findTutorByEmail("email")).thenReturn(Optional.of(new Tutor()));
        Optional<Tutor> expected = Optional.of(new Tutor());
        Optional<Tutor> actual = tutorDao.findTutorByEmail("email");
        assertEquals(expected, actual);
    }

    @Test
    public void findTutorByNotExistingEmailTest() throws DaoException {
        when(tutorDao.findTutorByEmail("email")).thenReturn(Optional.empty());
        Optional<Tutor> expected = Optional.empty();
        Optional<Tutor> actual = tutorDao.findTutorByEmail("email");
        assertEquals(expected, actual);
    }

    @Test
    public void searchTutorsTest() throws DaoException {
        when(tutorDao.searchTutors(anyInt(), anyString(), anyInt(), anyInt(), anyInt(), anyInt(), anyString())).thenReturn(List.of(new Tutor()));
        List<Tutor> expected = List.of(new Tutor());
        List<Tutor> actual = tutorDao.searchTutors(1, "city", 1, 10, 1, 10, "sort");
        assertEquals(expected, actual);
    }

    @Test
    public void searchTutorsEmptyTest() throws DaoException {
        when(tutorDao.searchTutors(anyInt(), anyString(), anyInt(), anyInt(), anyInt(), anyInt(), anyString())).thenReturn(Collections.emptyList());
        List<Tutor> expected = Collections.emptyList();
        List<Tutor> actual = tutorDao.searchTutors(1, "city", 1, 10, 1, 10, "sort");
        assertEquals(expected, actual);
    }

    @Test
    public void countSearchedRecordsTest() throws DaoException {
        when(tutorDao.countSearchedRecords(anyInt(), anyString(), anyInt(), anyInt())).thenReturn(10);
        int expected = 10;
        int actual = tutorDao.countSearchedRecords(1, "city", 1, 2);
        assertEquals(expected, actual);
    }

    @Test
    public void findAllCitiesTest() throws DaoException {
        when(tutorDao.findAllCities()).thenReturn(List.of("city"));
        List<String> expected = List.of("city");
        List<String> actual = tutorDao.findAllCities();
        assertEquals(expected, actual);
    }

    @Test
    public void findAllCitiesEmptyTest() throws DaoException {
        when(tutorDao.findAllCities()).thenReturn(Collections.emptyList());
        List<String> expected = Collections.emptyList();
        List<String> actual = tutorDao.findAllCities();
        assertEquals(expected, actual);
    }

    @Test
    public void findApplicationsTest() throws DaoException {
        when(tutorDao.findApplications(anyInt(), anyInt())).thenReturn(List.of(new Tutor()));
        List<Tutor> expected = List.of(new Tutor());
        List<Tutor> actual = tutorDao.findApplications(1, 10);
        assertEquals(expected, actual);
    }

    @Test
    public void findApplicationsEmptyTest() throws DaoException {
        when(tutorDao.findApplications(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        List<Tutor> expected = Collections.emptyList();
        List<Tutor> actual = tutorDao.findApplications(1, 10);
        assertEquals(expected, actual);
    }

    @Test
    public void countApplicationsTest() throws DaoException {
        when(tutorDao.countApplications()).thenReturn(10);
        int expected = 10;
        int actual = tutorDao.countApplications();
        assertEquals(expected, actual);
    }

    @Test
    public void findTutorByExistingIdTest() throws DaoException {
        when(tutorDao.findById(anyInt())).thenReturn(Optional.of(new Tutor()));
        Optional<Tutor> expected = Optional.of(new Tutor());
        Optional<Tutor> actual = tutorDao.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    public void findTutorByNotExistingIdTest() throws DaoException {
        when(tutorDao.findById(anyInt())).thenReturn(Optional.empty());
        Optional<Tutor> expected = Optional.empty();
        Optional<Tutor> actual = tutorDao.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    public void deleteByExistingIdTest() throws DaoException {
        when(tutorDao.deleteById(anyInt())).thenReturn(true);
        boolean actual = tutorDao.deleteById(1);
        assertTrue(actual);
    }

    @Test
    public void deleteByNotExistingIdTest() throws DaoException {
        when(tutorDao.deleteById(1)).thenReturn(false);
        boolean actual = tutorDao.deleteById(1);
        assertFalse(actual);
    }

    @Test
    public void createTutorTest() throws DaoException {
        when(tutorDao.create(new Tutor())).thenReturn(true);
        boolean actual = tutorDao.create(new Tutor());
        assertTrue(actual);
    }

    @Test
    public void updateTutorTest() throws DaoException {
        when(tutorDao.update(new Tutor())).thenReturn(new Tutor());
        Tutor expected = new Tutor();
        Tutor actual = new Tutor();
        assertEquals(expected, actual);
    }
}