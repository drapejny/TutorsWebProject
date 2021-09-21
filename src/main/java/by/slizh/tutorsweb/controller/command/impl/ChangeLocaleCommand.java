package by.slizh.tutorsweb.controller.command.impl;

import by.slizh.tutorsweb.controller.command.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {

    private static final String JSP_BEGIN_PATH = "/jsp";

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String locale = request.getParameter(RequestParameter.LOCALE);
        String currentUrl = request.getParameter(RequestParameter.CURRENT_URL);
        String currentPage = currentUrl.substring(currentUrl.indexOf(JSP_BEGIN_PATH));
        session.setAttribute(SessionAttribute.LOCALE, locale);
        Router router = new Router(currentPage, Router.RouteType.FORWARD);
        return router;
    }
}
