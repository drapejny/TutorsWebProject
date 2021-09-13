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

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static by.slizh.tutorsweb.controller.command.RequestParameter.*;
import static by.slizh.tutorsweb.controller.command.RequestAttribute.*;

public class RegistrationCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute(SessionAttribute.LOCALE);
        UserValidator validator = UserValidatorImpl.getInstance();

        Map<String, String> userMap = new HashMap<>();
        userMap.put(FIRST_NAME, request.getParameter(FIRST_NAME));
        userMap.put(LAST_NAME, request.getParameter(LAST_NAME));
        userMap.put(EMAIL, request.getParameter(EMAIL));
        userMap.put(PASSWORD, request.getParameter(PASSWORD));
        userMap.put(PHONE, request.getParameter(PHONE));
        userMap.put(CITY, request.getParameter(CITY));

        UserService service = UserServiceImpl.getInstance();

        try {
            if (service.isEmailExist(userMap.get(EMAIL))) {
                request.setAttribute(REGISTRATION_USER_DATA, userMap);
                request.setAttribute(ERROR_EMAIL_EXISTS, userMap);
                return new Router(PagePath.REGISTRATION_PAGE, Router.RouteType.FORWARD);
            }

        } catch (ServiceException e) {
            throw new CommandException("Executing registration command error", e);
        }

        String password = request.getParameter(PASSWORD);
        String passwordRepeat = request.getParameter(PASSWORD_REPEAT);

        if (service.validateUserData(userMap) && password.equals(passwordRepeat)) {
            try {
                service.registrate(userMap);
            } catch (ServiceException e) {
                throw new CommandException("Executing registration command error", e);
            }
            return new Router(PagePath.CONFIRMATION_PAGE, Router.RouteType.REDIRECT);
        } else {
            request.setAttribute(REGISTRATION_USER_DATA, userMap);
            request.setAttribute(WRONG_REGISTRATION_DATA, MessageManager.valueOf(locale.toUpperCase(Locale.ROOT)).getMessage(WRONG_REGISTRATION_DATA));
            return new Router(PagePath.REGISTRATION_PAGE, Router.RouteType.FORWARD);
        }


    }
}
