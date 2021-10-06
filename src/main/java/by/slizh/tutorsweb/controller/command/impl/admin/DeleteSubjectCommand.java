package by.slizh.tutorsweb.controller.command.impl.admin;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.Subject;
import by.slizh.tutorsweb.model.service.SubjectService;
import by.slizh.tutorsweb.model.service.impl.SubjectServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class DeleteSubjectCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        SubjectService subjectService = SubjectServiceImpl.getInstance();
        int subjectId = Integer.parseInt(request.getParameter(RequestParameter.SUBJECT_ID));
        try {
            subjectService.deleteSubjectById(subjectId);
            List<Subject> subjects = subjectService.findAllSubjects();
            request.getServletContext().setAttribute(RequestAttribute.SUBJECTS, subjects);
            return new Router(PagePath.ALL_SUBJECTS_PAGE, Router.RouteType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Executing delete subject command error", e);
            throw new CommandException("Executing delete subject command error", e);
        }

    }
}
