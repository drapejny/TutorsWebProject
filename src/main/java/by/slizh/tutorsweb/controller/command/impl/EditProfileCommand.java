package by.slizh.tutorsweb.controller.command.impl;

import by.slizh.tutorsweb.controller.command.Command;
import by.slizh.tutorsweb.controller.command.Router;
import by.slizh.tutorsweb.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class EditProfileCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}
