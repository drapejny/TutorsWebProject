package by.slizh.tutorsweb.controller.command.impl.go;

import by.slizh.tutorsweb.controller.command.Command;
import by.slizh.tutorsweb.controller.command.PagePath;
import by.slizh.tutorsweb.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class GoToRegistrationPage implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.REGISTRATION_PAGE, Router.RouteType.FORWARD);
    }
}
