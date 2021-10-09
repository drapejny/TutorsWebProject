package by.slizh.tutorsweb.controller.command;

public final class RequestParameter {

    //command
    public static final String COMMAND = "command";
    public static final String FILE = "file";

    //login
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

    //locale
    public static final String LOCALE = "locale";
    public static final String CURRENT_URL = "current_url";

    //registration
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String PASSWORD_REPEAT = "password_repeat";
    public static final String PHOTO = "photo";
    public static final String CITY = "city";

    //edit profile
    public static final String NEW_PASSWORD = "new_password";

    //verification
    public static final String USER_ID = "user_id";

    //application
    public static final String PHONE = "phone";
    public static final String EDUCATION = "education";
    public static final String INFORMATION = "info";
    public static final String PRICE = "price";
    public static final String SUBJECT = "subject";

    //subject
    public static final String SUBJECT_ID = "subject_id";
    public static final String SUBJECT_NAME = "subject_name";

    //search
    public static final String MIN_PRICE = "min_price";
    public static final String MAX_PRICE = "max_price";
    public static final String PAGE_NUMBER = "page_number";
    public static final String SORT = "sort";
    public static final String SEARCH_LINE = "search_line";

    //tutor profile
    public static final String TUTOR_ID = "tutor_id";
    public static final String TEXT = "text";
    public static final String RATING = "rating";
    public static final String FEEDBACK_ID = "feedback_id";
    public static final String IS_ACTIVE = "is_active";


    private RequestParameter() {
    }
}
