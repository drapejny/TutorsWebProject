package by.slizh.tutorsweb.command.impl;

import by.slizh.tutorsweb.command.Command;
import by.slizh.tutorsweb.command.PagePath;
import by.slizh.tutorsweb.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class GoToLoginPage implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.LOGIN_PAGE, Router.RouteType.REDIRECT);
    }
}
