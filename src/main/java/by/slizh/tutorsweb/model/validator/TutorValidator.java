package by.slizh.tutorsweb.model.validator;

import java.util.Map;

public interface TutorValidator {

    boolean validateTutorMap(Map<String, String[]> tutorMap);

    boolean validatePhone(String phone);

    boolean validateEducation(String education);

    boolean validateInfo(String info);

    boolean validatePrice(String price);
}
