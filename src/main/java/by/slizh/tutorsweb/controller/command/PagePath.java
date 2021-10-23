package by.slizh.tutorsweb.controller.command;

/**
 * Describes all pages paths.
 */
public final class PagePath {

    public static final String ERROR_PAGE = "/jsp/error/error.jsp";
    public static final String LOGIN_PAGE = "/jsp/login.jsp";
    public static final String MAIN_PAGE = "/jsp/main.jsp";
    public static final String REGISTRATION_PAGE = "/jsp/registration.jsp";
    public static final String EDIT_PROFILE_PAGE = "/jsp/edit_profile.jsp";
    public static final String ADD_APPLICATION_PAGE = "/jsp/tutor/add_application.jsp";
    public static final String APPLICATION_PAGE = "/jsp/application.jsp";
    public static final String ALL_APPLICATIONS_PAGE = "/jsp/admin/all_applications.jsp";
    public static final String ALL_SUBJECTS_PAGE = "/jsp/admin/all_subjects.jsp";
    public static final String SEARCH_PAGE = "jsp/search.jsp";
    public static final String TUTOR_PROFILE_PAGE = "/jsp/tutor/tutor_profile.jsp";
    public static final String EDIT_TUTOR_PROFILE_PAGE = "/jsp/tutor/edit_tutor_profile.jsp";
    public static final String SEARCH_USERS_PAGE = "/jsp/admin/search_users.jsp";
    public static final String ALL_ADMINS_PAGE = "/jsp/admin/all_admins.jsp";
    public static final String CONFIRMATION_PAGE = "/jsp/confirmation.jsp";
    public static final String ABOUT_US_PAGE = "jsp/about_us.jsp";
    public static final String GO_TO_LOGIN_PAGE = "/controller?command=login_page";
    public static final String GO_TO_TUTOR_PROFILE_PAGE = "/controller?command=tutor_profile_page";
    public static final String GO_TO_EDIT_TUTOR_PROFILE_PAGE = "/controller?command=edit_tutor_profile_page";
    public static final String GO_TO_EDIT_PROFILE_PAGE = "/controller?command=edit_profile_page";
    public static final String GO_TO_APPLICATION_PAGE = "/controller?command=become_tutor";
    public static final String GO_TO_ALL_APPLICATIONS_PAGE = "/controller?command=all_applications_page";
    public static final String GO_TO_ALL_SUBJECTS_PAGE = "/controller?command=all_subjects_page";
    public static final String GO_TO_SEARCH_USERS_PAGE = "/controller?command=search_users_page";
    public static final String GO_TO_ALL_ADMINS_PAGE = "/controller?command=all_admins_page";
    public static final String GO_TO_MAIN_PAGE = "/controller?command=main_page";
    public static final String GO_TO_CONFIRMATION_PAGE = "/controller?command=confirmation_page";

    private PagePath() {
    }
}
