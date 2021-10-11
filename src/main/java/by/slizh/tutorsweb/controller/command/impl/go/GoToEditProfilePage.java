package by.slizh.tutorsweb.controller.command.impl.go;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

import static by.slizh.tutorsweb.controller.command.RequestAttribute.SUCCESSFUL_EDIT_DATA;
import static by.slizh.tutorsweb.controller.command.RequestAttribute.SUCCESSFUL_EDIT_PASSWORD;

public class GoToEditProfilePage implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        if (request.getSession().getAttribute(SUCCESSFUL_EDIT_DATA) != null) {
            request.setAttribute(SUCCESSFUL_EDIT_DATA, request.getSession().getAttribute(SUCCESSFUL_EDIT_DATA));
            request.getSession().removeAttribute(SUCCESSFUL_EDIT_DATA);
        }
        if(request.getSession().getAttribute(SUCCESSFUL_EDIT_PASSWORD) != null){
            request.setAttribute(SUCCESSFUL_EDIT_PASSWORD,request.getSession().getAttribute(SUCCESSFUL_EDIT_PASSWORD));
            request.getSession().removeAttribute(SUCCESSFUL_EDIT_PASSWORD);
        }
        return new Router(PagePath.EDIT_PROFILE_PAGE, Router.RouteType.FORWARD);
    }
}
