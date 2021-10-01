package by.slizh.tutorsweb.controller.command.impl.go;

import by.slizh.tutorsweb.controller.command.Command;
import by.slizh.tutorsweb.controller.command.PagePath;
import by.slizh.tutorsweb.controller.command.Router;
import by.slizh.tutorsweb.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.slizh.tutorsweb.controller.command.SessionAttribute.*;
import static by.slizh.tutorsweb.controller.command.SessionAttribute.SEARCHED_MAX_PRICE;

public class GoToSearchPage implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.removeAttribute(SEARCHED_CITY);
        session.removeAttribute(SEARCHED_SUBJECT_ID);
        session.removeAttribute(SEARCHED_MIN_PRICE);
        session.removeAttribute(SEARCHED_MAX_PRICE);
        return new Router(PagePath.SEARCH_PAGE, Router.RouteType.FORWARD);
    }
}
