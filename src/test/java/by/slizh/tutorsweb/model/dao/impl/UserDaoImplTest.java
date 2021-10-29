package by.slizh.tutorsweb.model.dao.impl;

import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserDaoImplTest {

    @Mock
    private UserDaoImpl userDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllUsersTest() throws DaoException {
        when(userDao.findAll()).thenReturn(List.of(new User()));
        List<User> expected = List.of(new User());
        List<User> actual = userDao.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void findByIdExistingUserTest() throws DaoException {
        when(userDao.findById(anyInt())).thenReturn(Optional.of(new User()));
        Optional<User> expected = Optional.of(new User());
        Optional<User> actual = userDao.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    public void findByIdNotExistingUserTest() throws DaoException {
        when(userDao.findById(anyInt())).thenReturn(Optional.empty());
        Optional<User> expected = Optional.empty();
        Optional<User> actual = userDao.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    public void deleteByIdTrueTest() throws DaoException {
        when(userDao.deleteById(anyInt())).thenReturn(true);
        boolean actual = userDao.deleteById(1);
        assertTrue(actual);
    }

    @Test
    public void deleteByIdFalseTest() throws DaoException {
        when(userDao.deleteById(anyInt())).thenReturn(false);
        boolean actual = userDao.deleteById(1);
        assertFalse(actual);
    }

    @Test
    public void createUserTrueTest() throws DaoException {
        when(userDao.create(new User(), "password")).thenReturn(true);
        boolean actual = userDao.create(new User(), "password");
        assertTrue(actual);
    }

    @Test
    public void createUserFalseTest() throws DaoException {
        when(userDao.create(new User(), "password")).thenReturn(false);
        boolean actual = userDao.create(new User(), "password");
        assertFalse(actual);
    }

    @Test
    public void createUserUnsupportedOperationTest() throws DaoException {
        when(userDao.create(new User())).thenThrow(new UnsupportedOperationException());
        assertThrows(UnsupportedOperationException.class, () -> {
            userDao.create(new User());
        });
    }

    @Test
    public void updateUserTest() throws DaoException {
        when(userDao.update(new User())).thenReturn(new User());
        User expected = new User();
        User actual = userDao.update(new User());
        assertEquals(expected, actual);
    }

    @Test
    public void updateUserPasswordTrueTest() throws DaoException {
        when(userDao.updateUserPassword(new User(), "password")).thenReturn(true);
        boolean actual = userDao.updateUserPassword(new User(), "password");
        assertTrue(actual);
    }

    @Test
    public void updateUserPasswordFalseTest() throws DaoException {
        when(userDao.updateUserPassword(new User(), "password")).thenReturn(false);
        boolean actual = userDao.updateUserPassword(new User(), "password");
        assertFalse(actual);
    }

    @Test
    public void findUserByExistingEmailTest() throws DaoException {
        when(userDao.findUserByEmail("email")).thenReturn(Optional.of(new User()));
        Optional<User> expected = Optional.of(new User());
        Optional<User> actual = userDao.findUserByEmail("email");
        assertEquals(expected, actual);
    }

    @Test
    public void findUserByNotExistingEmailTest() throws DaoException {
        when(userDao.findUserByEmail("email")).thenReturn(Optional.empty());
        Optional<User> expected = Optional.empty();
        Optional<User> actual = userDao.findUserByEmail("email");
        assertEquals(expected, actual);
    }

    @Test
    public void findUserPasswordTest() throws DaoException {
        when(userDao.findUserPassword(new User())).thenReturn("passwordHash");
        String expected = "passwordHash";
        String actual = userDao.findUserPassword(new User());
        assertEquals(expected, actual);
    }

    @Test
    public void searchUsersTest() throws DaoException {
        when(userDao.searchUsers("searchLine",0,5)).thenReturn(List.of(new User()));
        List<User> expected = List.of(new User());
        List<User> actual = userDao.searchUsers("searchLine",0,5);
        assertEquals(expected,actual);
    }

    @Test
    public void searchUsersEmptyTest() throws DaoException{
        when(userDao.searchUsers("emptySearchLine",0,5)).thenReturn(Collections.emptyList());
        List<User> expected = Collections.emptyList();
        List<User> actual = userDao.searchUsers("emptySearchLine",0,5);
        assertEquals(expected,actual);
    }
    @Test
    public void findAllAdminsTest() throws DaoException{
        when(userDao.findAllAdmins()).thenReturn(List.of(new User()));
        List<User> expected = List.of(new User());
        List<User> actual = userDao.findAllAdmins();
        assertEquals(expected,actual);
    }
    @Test
    public void findAllAdminsEmptyTest() throws DaoException{
        when(userDao.findAllAdmins()).thenReturn(Collections.emptyList());
        List<User> expected = Collections.emptyList();
        List<User> actual = userDao.findAllAdmins();
        assertEquals(expected,actual);
    }



}





