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

import static by.slizh.tutorsweb.controller.command.RequestAttribute.USERS_ON_SEARCH_USERS_PAGE_NUMBER;
import static by.slizh.tutorsweb.controller.command.RequestParameter.PAGE_NUMBER;
import static by.slizh.tutorsweb.controller.command.RequestParameter.SEARCH_LINE;

public class SearchUsersCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private final UserService userService = UserServiceImpl.getInstance();
    private final TutorService tutorService = TutorServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String searchLine = request.getParameter(SEARCH_LINE);
        String pageNumber = request.getParameter(PAGE_NUMBER);
        int offset;
        int page;
        try {
            page = Integer.parseInt(pageNumber);
            offset = (page - 1) * USERS_ON_SEARCH_USERS_PAGE_NUMBER;
            if (page < 1) {
                page = 1;
                offset = 0;
            }
        } catch (NumberFormatException | NullPointerException e) {
            page = 1;
            offset = 0;
        }
        if (searchLine == null) {
            if (request.getSession().getAttribute(SEARCH_LINE) != null) {
                searchLine = (String) request.getSession().getAttribute(SEARCH_LINE);
            } else {
                searchLine = "";
            }
        }
        try {
            List<User> users = userService.searchUsers(searchLine, offset, USERS_ON_SEARCH_USERS_PAGE_NUMBER);
            Map<User, Tutor> tutorsMap = tutorService.findTutorsByUsers(users);
            int usersCount = userService.countSearchedUsers(searchLine);
            int pageCount = usersCount % USERS_ON_SEARCH_USERS_PAGE_NUMBER == 0 ? usersCount / USERS_ON_SEARCH_USERS_PAGE_NUMBER : usersCount / USERS_ON_SEARCH_USERS_PAGE_NUMBER + 1;
            request.setAttribute(RequestAttribute.USERS, users);
            request.setAttribute(RequestAttribute.TUTORS_MAP, tutorsMap);
            request.setAttribute(RequestAttribute.SEARCH_LINE, searchLine);
            request.setAttribute(RequestAttribute.PAGE_NUM, page);
            request.setAttribute(RequestAttribute.PAGE_COUNT, pageCount);
        } catch (ServiceException e) {
            logger.error("Executing searchUsers command error", e);
            throw new CommandException("Executing searchUsers command error", e);
        }
        return new Router(PagePath.SEARCH_USERS_PAGE, Router.RouteType.FORWARD);
    }
}
