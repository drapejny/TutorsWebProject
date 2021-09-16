package by.slizh.tutorsweb.controller.command;

import by.slizh.tutorsweb.controller.command.impl.*;

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
    EDIT_PROFILE_PAGE(new GoToEditProfilePage());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
