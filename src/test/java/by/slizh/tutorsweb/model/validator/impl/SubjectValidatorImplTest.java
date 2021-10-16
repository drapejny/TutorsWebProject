package by.slizh.tutorsweb.model.validator.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubjectValidatorImplTest {

    @Test
    public void validateCorrectSubjectNameTest(){
        String subjectName  = "Белорусский язык";
        boolean result = SubjectValidatorImpl.getInstance().validateSubjectName(subjectName);
        assertTrue(result);
    }

    @Test
    public void validateIncorrectSubjectNameTest(){
        String subjectName  = "Белорусский язык???";
        boolean result = SubjectValidatorImpl.getInstance().validateSubjectName(subjectName);
        assertFalse(result);
    }

}