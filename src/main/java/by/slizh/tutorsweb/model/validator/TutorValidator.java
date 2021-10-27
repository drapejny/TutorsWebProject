package by.slizh.tutorsweb.model.validator;

import java.util.Map;

/**
 * The interface TutorValidator.
 */
public interface TutorValidator {

    /**
     * Validate map containing tutor parameters.
     *
     * @param tutorMap the tutor map
     * @return true if all parameters matches corresponding patterns
     */
    boolean validateTutorMap(Map<String, String[]> tutorMap);

    /**
     * Validate phone.
     *
     * @param phone the phone
     * @return true if phone matches corresponding pattern
     */
    boolean validatePhone(String phone);

    /**
     * Validate education.
     *
     * @param education the education
     * @return true if education matches corresponding pattern
     */
    boolean validateEducation(String education);

    /**
     * Validate tutor information.
     *
     * @param info the tutor information
     * @return true if information matches corresponding pattern
     */
    boolean validateInfo(String info);

    /**
     * Validate price.
     *
     * @param price the price
     * @return true if price matches corresponding pattern
     */
    boolean validatePrice(String price);

    /**
     * Validate city.
     *
     * @param city the city
     * @return true if city matches corresponding pattern
     */
    boolean validateCity(String city);
}
