package by.slizh.tutorsweb.controller.command.impl.admin;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.model.service.UserService;
import by.slizh.tutorsweb.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.Optional;

import static by.slizh.tutorsweb.controller.command.RequestAttribute.*;

public class UnblockUserCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute(SessionAttribute.LOCALE);
        String userId = request.getParameter(RequestParameter.USER_ID);
        try {
            Optional<User> unblockedUser = userService.unblockUser(Integer.parseInt(userId));
            if (unblockedUser.isPresent()) {
                request.getSession().setAttribute(SUCCESSFUL_USER_UNBLOCK, MessageManager.valueOf(locale.toUpperCase(Locale.ROOT)).getMessage(SUCCESSFUL_USER_UNBLOCK));
                return new Router(PagePath.GO_TO_SEARCH_USERS_PAGE, Router.RouteType.REDIRECT);
            }
        } catch (ServiceException e) {
            logger.error("Executing unblockUser command error", e);
            throw new CommandException("Executing unblockUser command error", e);
        }
        return new Router(PagePath.SEARCH_USERS_PAGE, Router.RouteType.FORWARD);
    }
}
