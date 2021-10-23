package by.slizh.tutorsweb.controller.command.impl;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.model.service.TutorService;
import by.slizh.tutorsweb.model.service.impl.TutorServiceImpl;
import by.slizh.tutorsweb.model.validator.TutorValidator;
import by.slizh.tutorsweb.model.validator.impl.TutorValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.Map;

import static by.slizh.tutorsweb.controller.command.RequestAttribute.ERROR_WRONG_DATA;
import static by.slizh.tutorsweb.controller.command.RequestParameter.*;

public class AddApplicationCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private final TutorService service = TutorServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String locale = (String) request.getSession().getAttribute(LOCALE);
        Map<String, String[]> tutorMap = request.getParameterMap();
        TutorValidator tutorValidator = TutorValidatorImpl.getInstance();
        if (tutorValidator.validateTutorMap(tutorMap)) {
            User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
            try {
                service.createTutor(user, tutorMap);
            } catch (ServiceException e) {
                logger.error("Executing add application command error", e);
                throw new CommandException("Executing add application command error", e);
            }
            return new Router(PagePath.GO_TO_APPLICATION_PAGE, Router.RouteType.REDIRECT);
        } else {
            request.setAttribute(RequestAttribute.ERROR_WRONG_DATA, MessageManager.valueOf(locale.toUpperCase(Locale.ROOT)).getMessage(ERROR_WRONG_DATA));
            return new Router(PagePath.ADD_APPLICATION_PAGE, Router.RouteType.FORWARD);
        }


    }
}
