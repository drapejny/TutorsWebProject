package by.slizh.tutorsweb.controller.command.impl;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.model.service.UserService;
import by.slizh.tutorsweb.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class VerificationCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        UserService service = UserServiceImpl.getInstance();
        String userId = request.getParameter(RequestParameter.USER_ID);
        try {
            service.verify(userId);
        } catch (ServiceException e) {
            throw new CommandException("Executing verify command error", e);
        }
        request.getSession().removeAttribute(SessionAttribute.USER);
        return new Router(PagePath.LOGIN_PAGE, Router.RouteType.REDIRECT);

    }
}
