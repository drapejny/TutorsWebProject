package by.slizh.tutorsweb.model.validator.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

 class UserValidatorImplTest {

    @Test
    public void validateFirstNameCorrectTest() {
        String firstName = "Saske";
        boolean result = UserValidatorImpl.getInstance().validateFirstName(firstName);
        assertTrue(result);
    }

    @Test
    public void validateFirstNameIncorrectTest(){
        String firstName = "!!!Saske!!!";
        boolean result = UserValidatorImpl.getInstance().validateFirstName(firstName);
        assertFalse(result);
    }

    @Test
    public void validateLastNameCorrectTest() {
        String lastName = "Saske";
        boolean result = UserValidatorImpl.getInstance().validateLastName(lastName);
        assertTrue(result);
    }

    @Test
    public void validateLastNameIncorrectTest(){
        String lastName = "!!!Saske!!!";
        boolean result = UserValidatorImpl.getInstance().validateLastName(lastName);
        assertFalse(result);
    }

    @Test
    public void validateEmailCorrectTest(){
        String email = "admin@admin.com";
        boolean result = UserValidatorImpl.getInstance().validateEmail(email);
        assertTrue(result);
    }

    @Test
    public void validateEmailIncorrectTest(){
        String email = "admin@";
        boolean result = UserValidatorImpl.getInstance().validateEmail(email);
        assertFalse(result);
    }

    @Test
    public void validatePasswordCorrectTest(){
        String password = "7please";
        boolean result = UserValidatorImpl.getInstance().validatePassword(password);
        assertTrue(result);
    }

    @Test
    public void validatePasswordIncorrectTest(){
        String password = "***pass";
        boolean result = UserValidatorImpl.getInstance().validatePassword(password);
        assertFalse(result);
    }
}