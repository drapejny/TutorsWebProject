package by.slizh.tutorsweb.controller.command.impl.admin;

import by.slizh.tutorsweb.controller.command.Command;
import by.slizh.tutorsweb.controller.command.Router;
import by.slizh.tutorsweb.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class AddSubjectCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}
