package by.slizh.tutorsweb.model.validator;

/**
 * The interface SubjectValidator
 */
public interface SubjectValidator {

    /**
     * Validate subject name.
     *
     * @param subjectName the subject name
     * @return true if subject name matches corresponding pattern
     */
    boolean validateSubjectName(String subjectName);
}
