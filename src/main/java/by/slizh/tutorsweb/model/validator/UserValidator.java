package by.slizh.tutorsweb.model.validator;

/**
 * The interface UserValidator.
 */
public interface UserValidator {

    /**
     * Validate first name.
     *
     * @param firstName the user first name
     * @return true if first name matches corresponding pattern
     */
    boolean validateFirstName(String firstName);

    /**
     * Validate last name.
     *
     * @param lastName the user last name
     * @return true if last name matches corresponding pattern
     */
    boolean validateLastName(String lastName);

    /**
     * Validate user email.
     *
     * @param email the email
     * @return true if email matches corresponding pattern
     */
    boolean validateEmail(String email);

    /**
     * Validate password.
     *
     * @param password the password
     * @return true if password matches corresponding pattern
     */
    boolean validatePassword(String password);
}
