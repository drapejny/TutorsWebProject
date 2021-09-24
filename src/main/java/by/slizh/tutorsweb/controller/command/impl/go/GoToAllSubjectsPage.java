package by.slizh.tutorsweb.controller.command.impl.go;

import by.slizh.tutorsweb.controller.command.Command;
import by.slizh.tutorsweb.controller.command.PagePath;
import by.slizh.tutorsweb.controller.command.RequestAttribute;
import by.slizh.tutorsweb.controller.command.Router;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.Subject;
import by.slizh.tutorsweb.model.service.SubjectService;
import by.slizh.tutorsweb.model.service.impl.SubjectServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GoToAllSubjectsPage implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        SubjectService subjectService = SubjectServiceImpl.getInstance();
        try {
            List<Subject> subjects = subjectService.findAllSubjects();
            request.setAttribute(RequestAttribute.SUBJECTS, subjects);
            return new Router(PagePath.ALL_SUBJECTS_PAGE, Router.RouteType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Executing go to all subjects page command error", e);
            throw new CommandException("Executing go to all subjects page command error", e);
        }
    }
}
