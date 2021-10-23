package by.slizh.tutorsweb.controller.command.impl;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.model.service.UserService;
import by.slizh.tutorsweb.model.service.impl.UserServiceImpl;
import by.slizh.tutorsweb.model.validator.UserValidator;
import by.slizh.tutorsweb.model.validator.impl.UserValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

import static by.slizh.tutorsweb.controller.command.RequestAttribute.*;
import static by.slizh.tutorsweb.controller.command.RequestParameter.*;

public class EditPasswordCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private final UserService service = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute(SessionAttribute.LOCALE);
        String password = request.getParameter(PASSWORD);
        String newPassword = request.getParameter(NEW_PASSWORD);
        UserValidator validator = UserValidatorImpl.getInstance();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        if (validator.validatePassword(password) && validator.validatePassword(newPassword)) {
            boolean isCorrectPassword = false;
            try {
                isCorrectPassword = service.checkPassword(user, password);
            } catch (ServiceException e) {
                logger.error("Failed to check password in edit password command", e);
                throw new CommandException("Failed to check password in edit password command", e);
            }
            if (isCorrectPassword) {
                try {
                    service.updatePassword(user, newPassword);
                    request.getSession().setAttribute(SUCCESSFUL_EDIT_PASSWORD, MessageManager.valueOf(locale.toUpperCase(Locale.ROOT)).getMessage(SUCCESSFUL_EDIT_PASSWORD));
                    if (user.getRole() == User.Role.TUTOR) {
                        return new Router(PagePath.GO_TO_EDIT_TUTOR_PROFILE_PAGE, Router.RouteType.REDIRECT);
                    } else {
                        return new Router(PagePath.GO_TO_EDIT_PROFILE_PAGE, Router.RouteType.REDIRECT);
                    }
                } catch (ServiceException e) {
                    logger.error("Failed to update password in edit password command", e);
                    throw new CommandException("Failed to update password in edit password command", e);
                }
            } else {
                request.setAttribute(RequestAttribute.ERROR_WRONG_PASSWORD, MessageManager.valueOf(locale.toUpperCase(Locale.ROOT)).getMessage(ERROR_WRONG_PASSWORD));
            }
        } else {
            request.setAttribute(RequestAttribute.ERROR_WRONG_DATA, MessageManager.valueOf(locale.toUpperCase(Locale.ROOT)).getMessage(ERROR_WRONG_DATA));
        }
        if (user.getRole() == User.Role.TUTOR) {
            return new Router(PagePath.EDIT_TUTOR_PROFILE_PAGE, Router.RouteType.FORWARD);
        } else {
            return new Router(PagePath.EDIT_PROFILE_PAGE, Router.RouteType.FORWARD);
        }
    }
}
