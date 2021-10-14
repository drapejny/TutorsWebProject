package by.slizh.tutorsweb.controller.command.impl.go;

import by.slizh.tutorsweb.controller.command.Command;
import by.slizh.tutorsweb.controller.command.PagePath;
import by.slizh.tutorsweb.controller.command.Router;
import by.slizh.tutorsweb.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;


public class GoToSearchPage implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return new Router(PagePath.SEARCH_PAGE, Router.RouteType.FORWARD);
    }
}
