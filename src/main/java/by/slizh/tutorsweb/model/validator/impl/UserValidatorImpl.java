package by.slizh.tutorsweb.model.validator.impl;

import by.slizh.tutorsweb.model.validator.UserValidator;

import java.util.Map;

import static by.slizh.tutorsweb.controller.command.RequestParameter.*;

public class UserValidatorImpl implements UserValidator {

    private static UserValidatorImpl instance = new UserValidatorImpl();

    private static final String REGEXP_FIRST_NAME = "^[A-zА-яЁё`'.-]{1,32}$";
    private static final String REGEXP_LAST_NAME = "^[A-zА-яЁё`'.-]{1,32}$";
    private static final String REGEXP_EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String REGEXP_PASSWORD = "^\\w{6,20}$";

    private UserValidatorImpl() {
    }

    public static UserValidatorImpl getInstance() {
        return instance;
    }

    @Override
    public boolean validateFirstName(String firstName) {
        return firstName.matches(REGEXP_FIRST_NAME);
    }

    @Override
    public boolean validateLastName(String lastName) {
        return lastName.matches(REGEXP_LAST_NAME);
    }

    @Override
    public boolean validateEmail(String email) {
        return email.matches(REGEXP_EMAIL);
    }

    @Override
    public boolean validatePassword(String password) {
        return password.matches(REGEXP_PASSWORD);
    }

}
