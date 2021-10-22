package by.slizh.tutorsweb.model.validator.impl;

import by.slizh.tutorsweb.model.validator.FeedbackValidator;

public class FeedbackValidatorImpl implements FeedbackValidator {

    private static FeedbackValidatorImpl instance = new FeedbackValidatorImpl();

    private static final String REGEXP_FEEDBACK_TEXT = "^(.|\n){1,300}$";

    private FeedbackValidatorImpl() {
    }

    public static FeedbackValidatorImpl getInstance() {
        return instance;
    }

    @Override
    public boolean validateFeedbackText(String text) {
        return text.matches(REGEXP_FEEDBACK_TEXT);
    }
}
