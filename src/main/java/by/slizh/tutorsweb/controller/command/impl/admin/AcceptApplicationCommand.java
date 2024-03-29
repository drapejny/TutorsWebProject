package by.slizh.tutorsweb.controller.command.impl.admin;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.service.TutorService;
import by.slizh.tutorsweb.model.service.UserService;
import by.slizh.tutorsweb.model.service.impl.TutorServiceImpl;
import by.slizh.tutorsweb.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AcceptApplicationCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private final UserService userService = UserServiceImpl.getInstance();
    private final TutorService tutorService = TutorServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String userId = request.getParameter(RequestParameter.USER_ID);
        try {
            userService.makeUserToTutor(Integer.parseInt(userId));
            List<String> cities = tutorService.findAllCities();
            request.getServletContext().setAttribute(RequestAttribute.CITIES, cities);
        } catch (ServiceException e) {
            logger.error("Executing acceptApplication command error", e);
            throw new CommandException("Executing acceptApplication command error", e);
        }
        return new Router(PagePath.GO_TO_ALL_APPLICATIONS_PAGE, Router.RouteType.REDIRECT);
    }
}