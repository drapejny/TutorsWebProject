package by.slizh.tutorsweb.controller.command.impl.admin;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.model.service.UserService;
import by.slizh.tutorsweb.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteAdminCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private static final String MAIN_ADMIN_EMAIL = "admin@admin.com";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
        if (user.getEmail().equals(MAIN_ADMIN_EMAIL)) {
            String userId = request.getParameter(RequestParameter.USER_ID);
            try {
                userService.deleteAdmin(Integer.parseInt(userId));
                return new Router(PagePath.GO_TO_ALL_ADMINS_PAGE, Router.RouteType.REDIRECT);
            } catch (ServiceException e) {
                logger.error("Executing deleteAdmin command error", e);
                throw new CommandException("Executing deleteAdmin command error", e);
            }
        }
        return new Router(PagePath.MAIN_PAGE, Router.RouteType.FORWARD);
    }
}
