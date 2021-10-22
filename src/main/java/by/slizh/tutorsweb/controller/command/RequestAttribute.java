package by.slizh.tutorsweb.controller.command;

public final class RequestAttribute {

    /**
     * Describes all request attributes.
     */

    public static String TUTOR = "tutor";
    public static String SUBJECTS = "subjects";
    public static String TUTORS = "tutors";
    public static String TUTOR_ID = "tutorId";
    public static String FEEDBACKS = "feedbacks";
    public static String USERS = "users";
    public static String APPLICATIONS = "applications";
    public static String APPLICATION = "application";
    public static final String PAGE_COUNT = "pageCount";
    public static final String PAGE_NUM = "pageNumber";
    public static final String SORT = "sort";
    public static final String CITY = "city";
    public static final String MIN_PRICE = "minPrice";
    public static final String MAX_PRICE = "maxPrice";
    public static final String SUBJECT_ID = "subjectId";
    public static final String TUTORS_MAP = "tutorsMap";
    public static final String EXCEPTION = "exception";
    public static final String ADMINS = "admins";
    public static String ERROR_USER_NON_ACTIVATED = "errorNonActivatedMessage";
    public static String ERROR_USER_BLOCKED = "errorBlockedMessage";
    public static String ERROR_WRONG_PASSWORD_OR_EMAIL = "errorLogInMessage";
    public static final String ERROR_WRONG_DATA = "errorWrongDataMessage";
    public static final String ERROR_EMAIL_EXISTS = "errorEmailExistsMessage";
    public static final String SUCCESSFUL_EDIT_DATA = "successEditMessage";
    public static final String ERROR_WRONG_PASSWORD = "errorWrongPasswordMessage";
    public static final String SUCCESSFUL_EDIT_PASSWORD = "successEditPassword";
    public static final String SUCCESSFUL_USER_BLOCK = "successBlockUserMessage";
    public static final String SUCCESSFUL_USER_UNBLOCK = "successUnblockUserMessage";
    public static final String SUCCESSFUL_ADD_SUBJECT = "successAddSubjectMessage";
    public static final String ERROR_ADD_SUBJECT = "errorAddSubjectMessage";
    public static final String SUCCESSFUL_DELETE_SUBJECT = "successDeleteSubjectMessage";
    public static final String ERROR_DELETE_SUBJECT = "errorDeleteSubjectMessage";
    public static final String SUCCESS_REGISTRATION_MESSAGE = "successRegistrationMessage";
    public static final int APPLICATIONS_ON_PAGE_NUMBER = 1;
    public static final int TUTORS_ON_SEARCH_PAGE_NUMBER = 2;
    public static final int DEFAULT_OFFSET = 0;


    private RequestAttribute() {
    }
}
