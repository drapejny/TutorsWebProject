package by.slizh.tutorsweb.model.validator.impl;

import by.slizh.tutorsweb.model.service.impl.TutorServiceImpl;
import by.slizh.tutorsweb.model.validator.TutorValidator;

import java.util.Map;

import static by.slizh.tutorsweb.controller.command.RequestParameter.*;

public class TutorValidatorImpl implements TutorValidator {

    private static TutorValidatorImpl instance = new TutorValidatorImpl();

    private static final String REGEXP_PHONE = "^\\+375[0-9]{9}$";
    private static final String REGEXP_EDUCATION = "^.{1,300}$";
    private static final String REGEXP_INFO = "^.{1,500}$";
    private static final String REGEXP_PRICE = "^[0-9]{1,3}$";
    private static final String REGEXP_CITY = "[A-zА-яЁё`'.-]{1,32}$";

    private TutorValidatorImpl() {
    }

    public static TutorValidatorImpl getInstance() {
        return instance;
    }

    @Override
    public boolean validateTutorMap(Map<String, String[]> tutorMap) {
        if (validatePhone(tutorMap.get(PHONE)[0]) && validateCity(tutorMap.get(CITY)[0])
                && validateEducation(tutorMap.get(EDUCATION)[0])
                && validateInfo(tutorMap.get(INFORMATION)[0]) && validatePrice(tutorMap.get(PRICE)[0])
                && tutorMap.get(SUBJECT).length > 0 && tutorMap.get(SUBJECT).length <= 8) {
            return true;
        }
        return false;
    }

    @Override
    public boolean validatePhone(String phone) {
        return phone.matches(REGEXP_PHONE);
    }

    @Override
    public boolean validateEducation(String education) {
        return education.matches(REGEXP_EDUCATION);
    }

    @Override
    public boolean validateInfo(String info) {
        return info.matches(REGEXP_INFO);
    }

    @Override
    public boolean validatePrice(String price) {
        return price.matches(REGEXP_PRICE);
    }

    @Override
    public boolean validateCity(String city) {
        return city.matches(REGEXP_CITY);
    }
}
