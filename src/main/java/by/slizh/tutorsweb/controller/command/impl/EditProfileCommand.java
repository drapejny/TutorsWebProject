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

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

import static by.slizh.tutorsweb.controller.command.RequestAttribute.*;
import static by.slizh.tutorsweb.controller.command.RequestParameter.*;


public class EditProfileCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute(SessionAttribute.LOCALE);

        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);

        UserValidator validator = UserValidatorImpl.getInstance();
        UserService service = UserServiceImpl.getInstance();

        User user = (User) session.getAttribute(SessionAttribute.USER);
        if (validator.validateFirstName(firstName) && validator.validateLastName(lastName)) {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            try {
                service.updateUser(user);
            } catch (ServiceException e) {
                logger.error("Failed to update user in edit profile command", e);
                throw new CommandException("Failed to update user in edit profile command", e);
            }
            request.getSession().setAttribute(RequestAttribute.SUCCESSFUL_EDIT_DATA, MessageManager.valueOf(locale.toUpperCase(Locale.ROOT)).getMessage(SUCCESSFUL_EDIT_DATA));
            return new Router(PagePath.GO_TO_EDIT_PROFILE_PAGE, Router.RouteType.REDIRECT);
        } else {
            request.setAttribute(RequestAttribute.ERROR_WRONG_DATA, MessageManager.valueOf(locale.toUpperCase(Locale.ROOT)).getMessage(ERROR_WRONG_DATA));
            return new Router(PagePath.EDIT_PROFILE_PAGE, Router.RouteType.FORWARD);
        }
    }
}
