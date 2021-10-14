package by.slizh.tutorsweb.model.validator;

public interface UserValidator {

    boolean validateFirstName(String firstName);

    boolean validateLastName(String lastName);

    boolean validateEmail(String email);

    boolean validatePassword(String password);



}
