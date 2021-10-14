package by.slizh.tutorsweb.controller.command.impl.admin;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.Tutor;
import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.model.service.TutorService;
import by.slizh.tutorsweb.model.service.UserService;
import by.slizh.tutorsweb.model.service.impl.TutorServiceImpl;
import by.slizh.tutorsweb.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

import static by.slizh.tutorsweb.controller.command.RequestParameter.SEARCH_LINE;

public class SearchUsersCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String searchLine = request.getParameter(SEARCH_LINE);
        if (searchLine == null) {
            searchLine = (String) request.getSession().getAttribute(SEARCH_LINE);

        }
        UserService userService = UserServiceImpl.getInstance();
        TutorService tutorService = TutorServiceImpl.getInstance();
        try {
            List<User> users = userService.searchUsers(searchLine);
            Map<User, Tutor> tutorsMap = tutorService.findTutorsByUsers(users);
            request.setAttribute(RequestAttribute.USERS, users);
            request.setAttribute(RequestAttribute.TUTORS_MAP, tutorsMap);
        } catch (ServiceException e) {
            logger.error("Executing searchUsers command error", e);
            throw new CommandException("Executing searchUsers command error", e);
        }
        return new Router(PagePath.SEARCH_USERS_PAGE, Router.RouteType.FORWARD);
    }
}
