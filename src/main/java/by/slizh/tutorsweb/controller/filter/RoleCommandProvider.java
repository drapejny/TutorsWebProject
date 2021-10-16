package by.slizh.tutorsweb.controller.filter;

import by.slizh.tutorsweb.controller.command.CommandType;
import by.slizh.tutorsweb.model.entity.User;

import static by.slizh.tutorsweb.controller.command.CommandType.*;

import java.util.EnumSet;
import java.util.Set;

class RoleCommandProvider {

    private static RoleCommandProvider instance;

    private EnumSet<CommandType> guestCommands = EnumSet.of(
            DEFAULT,
            CONFIRMATION_PAGE,
            LOGIN_PAGE,
            MAIN_PAGE,
            REGISTRATION_PAGE,
            SEARCH_PAGE,
            TUTOR_PROFILE_PAGE,
            LOGIN,
            REGISTRATION,
            SEARCH,
            VERIFICATION,
            CHANGE_LOCALE
    );

    private EnumSet<CommandType> userCommands = EnumSet.of(
            DEFAULT,
            EDIT_PROFILE_PAGE,
            MAIN_PAGE,
            SEARCH_PAGE,
            TUTOR_PROFILE_PAGE,
            ADD_APPLICATION,
            ADD_FEEDBACK,
            BECOME_TUTOR,
            DELETE_FEEDBACK,
            EDIT_FEEDBACK,
            EDIT_PASSWORD,
            EDIT_PROFILE,
            LOGOUT,
            SEARCH,
            CHANGE_LOCALE
    );

    private EnumSet<CommandType> tutorCommands = EnumSet.of(
            DEFAULT,
            EDIT_TUTOR_PROFILE_PAGE,
            MAIN_PAGE,
            SEARCH_PAGE,
            TUTOR_PROFILE_PAGE,
            DELETE_FEEDBACK,
            EDIT_PASSWORD,
            EDIT_TUTOR_PROFILE,
            LOGOUT,
            SEARCH,
            CHANGE_LOCALE
    );

    private EnumSet<CommandType> adminCommands = EnumSet.of(
            DEFAULT,
            ACCEPT_APPLICATION,
            ADD_ADMIN,
            ADD_SUBJECT,
            BLOCK_USER,
            DELETE_ADMIN,
            DELETE_SUBJECT,
            REJECT_APPLICATION,
            SEARCH_USERS,
            UNBLOCK_USER,
            ALL_ADMINS_PAGE,
            ALL_APPLICATIONS_PAGE,
            ALL_SUBJECTS_PAGE,
            APPLICATION_PAGE,
            EDIT_PROFILE_PAGE,
            MAIN_PAGE,
            SEARCH_PAGE,
            SEARCH_USERS_PAGE,
            TUTOR_PROFILE_PAGE,
            ADD_FEEDBACK,
            DELETE_FEEDBACK,
            EDIT_FEEDBACK,
            EDIT_PASSWORD,
            EDIT_PROFILE,
            LOGOUT,
            SEARCH);

    private RoleCommandProvider() {
    }

    public static RoleCommandProvider getInstance() {
        if (instance == null) {
            instance = new RoleCommandProvider();
        }
        return instance;
    }

    public boolean checkCommand(User.Role role, CommandType commandType) {
        boolean checkFlag = false;
        switch (role) {
            case GUEST:
                checkFlag = guestCommands.contains(commandType);
                break;
            case USER:
                checkFlag = userCommands.contains(commandType);
                break;
            case TUTOR:
                checkFlag = tutorCommands.contains(commandType);
                break;
            case ADMIN:
                checkFlag = adminCommands.contains(commandType);
                break;
        }
        return checkFlag;
    }


}
