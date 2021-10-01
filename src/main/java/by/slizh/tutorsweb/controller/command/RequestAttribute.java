package by.slizh.tutorsweb.controller.command;

public final class RequestAttribute {

    public static String REGISTRATION_USER_DATA = "userMap";

    public static String TUTOR = "tutor";

    public static String SUBJECTS = "subjects";

    public static String TUTORS = "tutors";

    public static final String SEARCHED_PAGE_NUMBER = "searchedPageNumber";
    public static final String SEARCHED_PAGES_COUNT = "searchedPagesCount";


    public static String ERROR_USER_NON_ACTIVATED = "errorNonActivatedMessage";
    public static String ERROR_USER_BLOCKED = "errorBlockedMessage";
    public static String ERROR_WRONG_PASSWORD_OR_EMAIL = "errorLogInMessage";
    public static final String ERROR_WRONG_DATA = "errorWrongDataMessage";
    public static final String ERROR_EMAIL_EXISTS = "errorEmailExistsMessage";
    public static final String SUCCESSFUL_EDIT_DATA = "successEditMessage";
    public static final String ERROR_WRONG_PASSWORD = "errorWrongPassword";
    public static final String SUCCESSFUL_EDIT_PASSWORD = "successEditPassword";


    private RequestAttribute() {
    }
}
