package by.slizh.tutorsweb.controller.filter;

import by.slizh.tutorsweb.controller.command.CommandType;
import by.slizh.tutorsweb.model.entity.User;

import static by.slizh.tutorsweb.controller.command.CommandType.*;

import java.util.EnumSet;
import java.util.Set;

public class RoleCommandProvider {

    private static RoleCommandProvider instance;

    private EnumSet<CommandType> guestCommands = EnumSet.of(
            DEFAULT,
            LOGIN_PAGE,
            MAIN_PAGE,
            REGISTRATION_PAGE,
            SEARCH_PAGE,
            TUTOR_PROFILE_PAGE,
            CHANGE_LOCALE
    );

    private EnumSet<CommandType> userCommands = EnumSet.of(DEFAULT,
            MAIN_PAGE);

    private EnumSet<CommandType> tutorCommands = EnumSet.of(DEFAULT);

    private EnumSet<CommandType> adminCommands = EnumSet.of(DEFAULT);

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
                System.out.println(11);
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
