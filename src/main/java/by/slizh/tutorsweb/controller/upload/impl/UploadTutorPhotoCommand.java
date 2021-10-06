package by.slizh.tutorsweb.controller.upload.impl;

import by.slizh.tutorsweb.controller.command.PagePath;
import by.slizh.tutorsweb.controller.command.Router;
import by.slizh.tutorsweb.controller.command.SessionAttribute;
import by.slizh.tutorsweb.controller.upload.UploadCommand;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.Tutor;
import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.model.service.UserService;
import by.slizh.tutorsweb.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;

public class UploadTutorPhotoCommand implements UploadCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, InputStream inputStream) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        Tutor tutor = (Tutor) request.getSession().getAttribute(SessionAttribute.USER);
        try {
            userService.updatePhoto(tutor, inputStream);
            return new Router(PagePath.EDIT_TUTOR_PROFILE_PAGE, Router.RouteType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Executing uploadTutorPhoto command error", e);
            throw new CommandException("Executing uploadTutorPhoto command error", e);
        }
    }
}
