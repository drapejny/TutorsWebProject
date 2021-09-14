package by.slizh.tutorsweb.controller.command.impl;

import by.slizh.tutorsweb.controller.command.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String locale = request.getParameter(RequestParameter.LOCALE);
        String currentPage= (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
        session.setAttribute(SessionAttribute.LOCALE, locale);
        Router router = new Router(currentPage, Router.RouteType.FORWARD);
        return router;
    }
}