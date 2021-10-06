package by.slizh.tutorsweb.controller.command.impl.admin;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.Subject;
import by.slizh.tutorsweb.model.service.SubjectService;
import by.slizh.tutorsweb.model.service.impl.SubjectServiceImpl;
import by.slizh.tutorsweb.model.validator.SubjectValidator;
import by.slizh.tutorsweb.model.validator.impl.SubjectValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Locale;

import static by.slizh.tutorsweb.controller.command.RequestAttribute.*;

public class AddSubjectCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String locale = (String) request.getSession().getAttribute(SessionAttribute.LOCALE);
        SubjectService subjectService = SubjectServiceImpl.getInstance();
        SubjectValidator subjectValidator = SubjectValidatorImpl.getInstance();
        String subjectName = request.getParameter(RequestParameter.SUBJECT_NAME);
        if (subjectValidator.validateSubjectName(subjectName)) {
            Subject subject = new Subject(subjectName);
            try {
                if (subjectService.addSubject(subject)) {
                    List<Subject> subjects = (List<Subject>) request.getServletContext().getAttribute(SUBJECTS);
                    subjects.add(subject);
                    return new Router(PagePath.ALL_SUBJECTS_PAGE, Router.RouteType.FORWARD);
                } else {
                    return new Router(PagePath.MAIN_PAGE, Router.RouteType.FORWARD);
                }
            } catch (ServiceException e) {
                logger.error("Executing delete subject command error", e);
                throw new CommandException("Executing delete subject command error", e);
            }
        } else {
            request.setAttribute(RequestAttribute.ERROR_WRONG_DATA, MessageManager.valueOf(locale.toUpperCase(Locale.ROOT)).getMessage(ERROR_WRONG_DATA));
            return new Router(PagePath.ALL_SUBJECTS_PAGE, Router.RouteType.FORWARD);
        }

    }
}
