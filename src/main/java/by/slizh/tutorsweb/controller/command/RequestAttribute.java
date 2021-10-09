package by.slizh.tutorsweb.controller.command;

public final class RequestAttribute {

    public static String REGISTRATION_USER_DATA = "userMap";

    public static String TUTOR = "tutor";

    public static String SUBJECTS = "subjects";

    public static String TUTORS = "tutors";

    public static String CITIES = "cities";

    public static String FEEDBACKS = "feedbacks";
    public static String USERS = "users";
    public static String TUTOR_SUBJECTS = "tutorSubjects";
    public static String APPLICATIONS = "applications";
    public static String APPLICATION = "application";

    public static final String SEARCHED_PAGE_NUMBER = "searchedPageNumber";
    public static final String SEARCHED_PAGES_COUNT = "searchedPagesCount";

    public static final String PAGE_COUNT = "pageCount";
    public static final String PAGE_NUM = "pageNumber";


    public static String ERROR_USER_NON_ACTIVATED = "errorNonActivatedMessage";
    public static String ERROR_USER_BLOCKED = "errorBlockedMessage";
    public static String ERROR_WRONG_PASSWORD_OR_EMAIL = "errorLogInMessage";
    public static final String ERROR_WRONG_DATA = "errorWrongDataMessage";
    public static final String ERROR_EMAIL_EXISTS = "errorEmailExistsMessage";
    public static final String SUCCESSFUL_EDIT_DATA = "successEditMessage";
    public static final String ERROR_WRONG_PASSWORD = "errorWrongPasswordMessage";
    public static final String SUCCESSFUL_EDIT_PASSWORD = "successEditPassword";

    public static final int APPLICATIONS_ON_PAGE_NUMBER = 2;
    public static final int TUTORS_ON_SEARCH_PAGE_NUMBER = 2;
    public static final int DEFAULT_OFFSET = 0;
    public static final int FIRST_PAGE_NUMBER = 1;


    private RequestAttribute() {
    }
}
