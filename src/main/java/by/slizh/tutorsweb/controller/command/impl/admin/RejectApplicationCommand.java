package by.slizh.tutorsweb.controller.command.impl.admin;

import by.slizh.tutorsweb.controller.command.Command;
import by.slizh.tutorsweb.controller.command.PagePath;
import by.slizh.tutorsweb.controller.command.RequestParameter;
import by.slizh.tutorsweb.controller.command.Router;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.service.TutorService;
import by.slizh.tutorsweb.model.service.impl.TutorServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class RejectApplicationCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String tutorId = request.getParameter(RequestParameter.TUTOR_ID);
        TutorService tutorService = TutorServiceImpl.getInstance();
        try {
            tutorService.deleteTutorById(Integer.parseInt(tutorId));
        } catch (ServiceException e) {
            logger.error("Executing rejectApplication command error", e);
            throw new CommandException();
        }
        return new Router(PagePath.GO_TO_ALL_APPLICATIONS_PAGE, Router.RouteType.REDIRECT);
    }
}
