package by.slizh.tutorsweb.model.validator.impl;

import by.slizh.tutorsweb.model.validator.UserValidator;

import java.util.Map;

import static by.slizh.tutorsweb.controller.command.RequestParameter.*;

public class UserValidatorImpl implements UserValidator {

    private static UserValidatorImpl instance = new UserValidatorImpl();

    private static final String REGEXP_FIRST_NAME = "a+";
    private static final String REGEXP_LAST_NAME = "a+";
    private static final String REGEXP_EMAIL = ".+";
    private static final String REGEXP_PASSWORD = "a+";
    private static final String REGEXP_PHONE = "a+";
    private static final String REGEXP_CITY = "a+";

    private static final String EMPTY_LINE = "";

    private UserValidatorImpl() {
    }

    public static UserValidatorImpl getInstance() {
        return instance;
    }

    @Override
    public boolean validateUserData(Map<String, String> userMap) {
        boolean result = true;
        if (!validateFirstName(userMap.get(FIRST_NAME))) {
            userMap.put(FIRST_NAME, EMPTY_LINE);
            result = false;
        }
        if (!validateLastName(userMap.get(LAST_NAME))) {
            userMap.put(LAST_NAME, EMPTY_LINE);
            result = false;
        }
        if (!validateEmail(userMap.get(EMAIL))) {
            userMap.put(EMAIL, EMPTY_LINE);
            result = false;
        }
        if (!validatePassword(userMap.get(PASSWORD))) {
            userMap.put(PASSWORD, EMPTY_LINE);
            result = false;
        }
        if (!validatePhone(userMap.get(PHONE))) {
            userMap.put(PHONE, EMPTY_LINE);
            result = false;
        }
        if (!validateCity(userMap.get(CITY))) {
            userMap.put(CITY, EMPTY_LINE);
            result = false;
        }
        return result;
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

    @Override
    public boolean validatePhone(String phone) {
        return phone.matches(REGEXP_PHONE);
    }

    @Override
    public boolean validateCity(String city) {
        return city.matches(REGEXP_CITY);
    }
}
