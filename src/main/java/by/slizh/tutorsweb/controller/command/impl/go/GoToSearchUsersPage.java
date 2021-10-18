package by.slizh.tutorsweb.controller.command.impl.go;

import by.slizh.tutorsweb.controller.command.Command;
import by.slizh.tutorsweb.controller.command.PagePath;
import by.slizh.tutorsweb.controller.command.Router;
import by.slizh.tutorsweb.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.slizh.tutorsweb.controller.command.RequestAttribute.*;

public class GoToSearchUsersPage implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        if (session.getAttribute(SUCCESSFUL_USER_BLOCK) != null) {
            request.setAttribute(SUCCESSFUL_USER_BLOCK, session.getAttribute(SUCCESSFUL_USER_BLOCK));
            session.removeAttribute(SUCCESSFUL_USER_BLOCK);
        }
        if (session.getAttribute(SUCCESSFUL_USER_UNBLOCK) != null) {
            request.setAttribute(SUCCESSFUL_USER_UNBLOCK, session.getAttribute(SUCCESSFUL_USER_UNBLOCK));
            session.removeAttribute(SUCCESSFUL_USER_UNBLOCK);
        }
        return new Router(PagePath.SEARCH_USERS_PAGE, Router.RouteType.FORWARD);
    }
}
