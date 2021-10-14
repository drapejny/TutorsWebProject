package by.slizh.tutorsweb.controller.command.impl;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.service.UserService;
import by.slizh.tutorsweb.model.service.impl.UserServiceImpl;
import by.slizh.tutorsweb.model.validator.UserValidator;
import by.slizh.tutorsweb.model.validator.impl.UserValidatorImpl;
import by.slizh.tutorsweb.util.Base64Coder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static by.slizh.tutorsweb.controller.command.RequestParameter.*;
import static by.slizh.tutorsweb.controller.command.RequestAttribute.*;

public class RegistrationCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute(SessionAttribute.LOCALE);
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);

        UserService service = UserServiceImpl.getInstance();
        UserValidator userValidator = UserValidatorImpl.getInstance();

        try {
            if (service.isEmailExist(email)) {
                request.setAttribute(ERROR_EMAIL_EXISTS, MessageManager.valueOf(locale.toUpperCase(Locale.ROOT)).getMessage(ERROR_EMAIL_EXISTS));
                return new Router(PagePath.REGISTRATION_PAGE, Router.RouteType.FORWARD);
            }

        } catch (ServiceException e) {
            logger.error("Executing registration command error", e);
            throw new CommandException("Executing registration command error", e);
        }

        String passwordRepeat = request.getParameter(PASSWORD_REPEAT);

        if (userValidator.validateFirstName(firstName) && userValidator.validateLastName(lastName)
                && userValidator.validateEmail(email) && userValidator.validatePassword(password)
                && password.equals(passwordRepeat)) {
            try {
                service.registrate(firstName, lastName, email, password);
            } catch (ServiceException e) {
                logger.error("Executing registration command error", e);
                throw new CommandException("Executing registration command error", e);
            }
            session.setAttribute(SUCCESS_REGISTRATION_MESSAGE,MessageManager.valueOf(locale.toUpperCase(Locale.ROOT)).getMessage(SUCCESS_REGISTRATION_MESSAGE));
            return new Router(PagePath.GO_TO_LOGIN_PAGE, Router.RouteType.REDIRECT);
        } else {
            request.setAttribute(ERROR_WRONG_DATA, MessageManager.valueOf(locale.toUpperCase(Locale.ROOT)).getMessage(ERROR_WRONG_DATA));
            return new Router(PagePath.REGISTRATION_PAGE, Router.RouteType.FORWARD);
        }
    }
}
