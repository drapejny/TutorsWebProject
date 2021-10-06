package by.slizh.tutorsweb.controller.command.impl;

import by.slizh.tutorsweb.controller.command.Command;
import by.slizh.tutorsweb.controller.command.PagePath;
import by.slizh.tutorsweb.controller.command.Router;
import by.slizh.tutorsweb.controller.command.SessionAttribute;
import by.slizh.tutorsweb.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LogOutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.removeAttribute(SessionAttribute.USER);
        session.removeAttribute(SessionAttribute.SUBJECTS);
        return new Router(PagePath.MAIN_PAGE, Router.RouteType.FORWARD);
    }
}
