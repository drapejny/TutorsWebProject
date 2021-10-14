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
import java.util.Locale;

import static by.slizh.tutorsweb.controller.command.RequestAttribute.ERROR_DELETE_SUBJECT;
import static by.slizh.tutorsweb.controller.command.RequestAttribute.SUCCESSFUL_DELETE_SUBJECT;


public class DeleteSubjectCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String locale = (String) request.getSession().getAttribute(SessionAttribute.LOCALE);
        SubjectService subjectService = SubjectServiceImpl.getInstance();
        int subjectId = Integer.parseInt(request.getParameter(RequestParameter.SUBJECT_ID));
        try {
            if (subjectService.deleteSubjectById(subjectId)) {
                List<Subject> subjects = subjectService.findAllSubjects();
                request.getServletContext().setAttribute(RequestAttribute.SUBJECTS, subjects);
                request.getSession().setAttribute(SUCCESSFUL_DELETE_SUBJECT, MessageManager.valueOf(locale.toUpperCase(Locale.ROOT)).getMessage(SUCCESSFUL_DELETE_SUBJECT));
                return new Router(PagePath.GO_TO_ALL_SUBJECTS_PAGE, Router.RouteType.REDIRECT);
            } else {
                request.setAttribute(ERROR_DELETE_SUBJECT, MessageManager.valueOf(locale.toUpperCase(Locale.ROOT)).getMessage(ERROR_DELETE_SUBJECT));
                return new Router(PagePath.ALL_SUBJECTS_PAGE, Router.RouteType.FORWARD);
            }
        } catch (ServiceException e) {
            logger.error("Executing delete subject command error", e);
            throw new CommandException("Executing delete subject command error", e);
        }

    }
}
