package by.slizh.tutorsweb.controller.command.impl.admin;

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

public class UnblockUserCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute(SessionAttribute.LOCALE);
        String userId = request.getParameter(RequestParameter.USER_ID);
        UserService userService = UserServiceImpl.getInstance();
        try {
            if (userService.unblockUser(Integer.parseInt(userId))) {
                request.setAttribute(RequestAttribute.SUCCESSFUL_USER_UNBLOCK, MessageManager.valueOf(locale.toUpperCase(Locale.ROOT)).getMessage(RequestAttribute.SUCCESSFUL_USER_UNBLOCK));
            }
        } catch (ServiceException e) {
            logger.error("Executing unblockUser command error", e);
            throw new CommandException("Executing unblockUser command error", e);
        }
        return new Router(PagePath.SEARCH_USERS_PAGE, Router.RouteType.FORWARD);
    }
}
