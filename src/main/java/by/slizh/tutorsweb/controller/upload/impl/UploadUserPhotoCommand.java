package by.slizh.tutorsweb.controller.upload.impl;

import by.slizh.tutorsweb.controller.command.PagePath;
import by.slizh.tutorsweb.controller.command.Router;
import by.slizh.tutorsweb.controller.command.SessionAttribute;
import by.slizh.tutorsweb.controller.upload.UploadCommand;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.model.service.UserService;
import by.slizh.tutorsweb.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;

public class UploadUserPhotoCommand implements UploadCommand {

    private static final Logger logger = LogManager.getLogger();

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request, InputStream inputStream) throws CommandException {
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
        try {
            userService.updatePhoto(user, inputStream);
            return new Router(PagePath.GO_TO_EDIT_PROFILE_PAGE, Router.RouteType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Executing uploadUserPhoto command error", e);
            throw new CommandException("Executing uploadUserPhoto command error", e);
        }
    }
}
