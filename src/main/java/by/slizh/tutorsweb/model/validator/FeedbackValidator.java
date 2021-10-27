package by.slizh.tutorsweb.model.validator;

/**
 * The interface FeedbackValidator.
 */
public interface FeedbackValidator {

    /**
     * Validate feedback text.
     *
     * @param text the feedback text
     * @return true if feedback text matches corresponding pattern
     */
    boolean validateFeedbackText(String text);
}
