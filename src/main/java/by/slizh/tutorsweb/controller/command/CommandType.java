package by.slizh.tutorsweb.controller.command;

import by.slizh.tutorsweb.controller.command.impl.*;
import by.slizh.tutorsweb.controller.command.impl.admin.*;
import by.slizh.tutorsweb.controller.command.impl.go.*;

public enum CommandType {
    DEFAULT(new DefaultCommand()),
    LOGIN(new LoginCommand()),
    LOGIN_PAGE(new GoToLoginPage()),
    PROFILE_PAGE(new GoToProfilePage()),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    REGISTRATION_PAGE(new GoToRegistrationPage()),
    REGISTRATION(new RegistrationCommand()),
    MAIN_PAGE(new GoToMainPage()),
    LOGOUT(new LogOutCommand()),
    VERIFICATION(new VerificationCommand()),
    EDIT_PROFILE_PAGE(new GoToEditProfilePage()),
    EDIT_PROFILE(new EditProfileCommand()),
    EDIT_PASSWORD(new EditPasswordCommand()),
    ADD_APPLICATION(new AddApplicationCommand()),
    DELETE_APPLICATION(new DeleteApplication()),
    ALL_APPLICATIONS_PAGE(new GoToAllApplicationsPage()),
    ALL_SUBJECTS_PAGE(new GoToAllSubjectsPage()),
    ADD_SUBJECT(new AddSubjectCommand()),
    DELETE_SUBJECT(new DeleteSubjectCommand()),
    SEARCH(new SearchCommand()),
    SEARCH_PAGE(new GoToSearchPage()),
    BECOME_TUTOR(new BecomeTutorCommand()),
    TUTOR_PROFILE_PAGE(new GoToTutorProfilePage()),
    ADD_FEEDBACK(new AddFeedbackCommand()),
    EDIT_FEEDBACK(new EditFeedbackCommand()),
    DELETE_FEEDBACK(new DeleteFeedbackCommand()),
    EDIT_TUTOR_PROFILE_PAGE(new GoToEditTutorProfilePage()),
    EDIT_TUTOR_PROFILE(new EditTutorProfileCommand()),
    APPLICATION_PAGE(new GoToApplicationPage()),
    REJECT_APPLICATION(new RejectApplicationCommand()),
    ACCEPT_APPLICATION(new AcceptApplicationCommand()),
    SEARCH_USERS_PAGE(new GoToSearchUsersPage()),
    SEARCH_USERS(new SearchUsersCommand()),
    BLOCK_USER(new BlockUserCommand()),
    UNBLOCK_USER(new UnblockUserCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
