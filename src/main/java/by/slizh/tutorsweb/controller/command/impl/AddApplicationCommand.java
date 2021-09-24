package by.slizh.tutorsweb.controller.command.impl;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.Subject;
import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.model.service.TutorService;
import by.slizh.tutorsweb.model.service.impl.TutorServiceImpl;
import by.slizh.tutorsweb.model.validator.TutorValidator;
import by.slizh.tutorsweb.model.validator.impl.TutorValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static by.slizh.tutorsweb.controller.command.RequestParameter.*;

public class AddApplicationCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Map<String, String[]> tutorMap = request.getParameterMap();
        TutorValidator tutorValidator = TutorValidatorImpl.getInstance();

        if (tutorValidator.validateTutorMap(tutorMap)) {
            User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
            TutorService service = TutorServiceImpl.getInstance();
            try {
                service.createTutor(user, tutorMap);
            } catch (ServiceException e) {
                logger.error("Executing add application command error", e);
                throw new CommandException("Executing add application command error", e);
            }
            return new Router(PagePath.MAIN_PAGE, Router.RouteType.REDIRECT);
        } else {
            return new Router(PagePath.ADD_APPLICATION_PAGE, Router.RouteType.FORWARD);
        }


    }
}
