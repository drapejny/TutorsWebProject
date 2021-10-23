package by.slizh.tutorsweb.controller.command.impl.go;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.model.service.UserService;
import by.slizh.tutorsweb.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GoToAllAdminsPage implements Command {

    private static final Logger logger = LogManager.getLogger();

    private final String MAIN_ADMIN_EMAIL = "admin@admin.com";
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
        if (user.getEmail().equals(MAIN_ADMIN_EMAIL)) {
            try {
                List<User> admins = userService.findAllAdmins();
                request.setAttribute(RequestAttribute.ADMINS, admins);
                return new Router(PagePath.ALL_ADMINS_PAGE, Router.RouteType.FORWARD);
            } catch (ServiceException e) {
                logger.error("Executing goToAllAdminsPage command error", e);
                throw new CommandException("Executing goToAllAdminsPage command error", e);
            }
        }
        return new Router(PagePath.MAIN_PAGE, Router.RouteType.FORWARD);
    }
}
