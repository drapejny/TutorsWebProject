package by.slizh.tutorsweb.controller.command.impl;

import by.slizh.tutorsweb.controller.command.Command;
import by.slizh.tutorsweb.controller.command.PagePath;
import by.slizh.tutorsweb.controller.command.Router;
import by.slizh.tutorsweb.controller.command.SessionAttribute;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.model.service.TutorService;
import by.slizh.tutorsweb.model.service.impl.TutorServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteApplication implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        TutorService service = TutorServiceImpl.getInstance();
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
        try {
            service.deleteTutorByEmail(user.getEmail());
            return new Router(PagePath.PROFILE_PAGE, Router.RouteType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Executing become tutor command", e);
            throw new CommandException("Executing become tutor command", e);
        }
    }
}
