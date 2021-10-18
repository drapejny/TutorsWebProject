package by.slizh.tutorsweb.controller.command.impl;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.service.UserService;
import by.slizh.tutorsweb.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

import static by.slizh.tutorsweb.controller.command.RequestAttribute.SUCCESS_REGISTRATION_MESSAGE;

public class VerificationCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        UserService service = UserServiceImpl.getInstance();
        String userId = request.getParameter(RequestParameter.USER_ID);
        try {
            if (service.verify(userId)) {
                HttpSession session = request.getSession();
                String locale = (String) session.getAttribute(SessionAttribute.LOCALE);
                session.setAttribute(SUCCESS_REGISTRATION_MESSAGE, MessageManager.valueOf(locale.toUpperCase(Locale.ROOT)).getMessage(SUCCESS_REGISTRATION_MESSAGE));
                return new Router(PagePath.GO_TO_LOGIN_PAGE, Router.RouteType.REDIRECT);
            }
        } catch (ServiceException e) {
            logger.error("Executing verify command error", e);
            throw new CommandException("Executing verify command error", e);
        }
        return new Router(PagePath.MAIN_PAGE, Router.RouteType.FORWARD);

    }
}
