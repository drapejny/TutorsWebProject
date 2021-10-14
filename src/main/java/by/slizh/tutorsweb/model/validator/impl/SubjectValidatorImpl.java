package by.slizh.tutorsweb.model.validator.impl;

import by.slizh.tutorsweb.model.validator.SubjectValidator;

public class SubjectValidatorImpl implements SubjectValidator {

    private static SubjectValidatorImpl instance = new SubjectValidatorImpl();

    private static final String REGEXP_SUBJECT_NAME = "^[а-яА-Я ]{1,32}$";

    private SubjectValidatorImpl() {
    }

    public static SubjectValidatorImpl getInstance() {
        return instance;
    }

    @Override
    public boolean validateSubjectName(String subjectName) {
        return subjectName.matches(REGEXP_SUBJECT_NAME);
    }
}
