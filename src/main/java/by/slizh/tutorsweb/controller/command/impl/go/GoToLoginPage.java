package by.slizh.tutorsweb.controller.command.impl.go;

import by.slizh.tutorsweb.controller.command.Command;
import by.slizh.tutorsweb.controller.command.PagePath;
import by.slizh.tutorsweb.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

import static by.slizh.tutorsweb.controller.command.RequestAttribute.SUCCESS_REGISTRATION_MESSAGE;

public class GoToLoginPage implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        if(request.getSession().getAttribute(SUCCESS_REGISTRATION_MESSAGE) != null){
            request.setAttribute(SUCCESS_REGISTRATION_MESSAGE,request.getSession().getAttribute(SUCCESS_REGISTRATION_MESSAGE));
            request.getSession().removeAttribute(SUCCESS_REGISTRATION_MESSAGE);
        }
        return new Router(PagePath.LOGIN_PAGE, Router.RouteType.FORWARD);
    }
}
