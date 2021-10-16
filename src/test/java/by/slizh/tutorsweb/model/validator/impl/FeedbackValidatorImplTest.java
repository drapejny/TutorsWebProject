package by.slizh.tutorsweb.model.validator.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackValidatorImplTest {

    @Test
    public void validateCorrectFeedbackTextTest(){
        String text = "Чётко, внатуре чётко";
        boolean result = FeedbackValidatorImpl.getInstance().validateFeedbackText(text);
        assertTrue(result);
    }

    @Test
    public void validateIncorrectFeedbackTextTest(){
        String text = "";
        boolean result = FeedbackValidatorImpl.getInstance().validateFeedbackText(text);
        assertFalse(result);
    }

}