package by.slizh.tutorsweb.controller.command.impl;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.Subject;
import by.slizh.tutorsweb.model.entity.Tutor;
import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.model.service.SubjectService;
import by.slizh.tutorsweb.model.service.TutorService;
import by.slizh.tutorsweb.model.service.impl.SubjectServiceImpl;
import by.slizh.tutorsweb.model.service.impl.TutorServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class BecomeTutorCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private final TutorService tutorService = TutorServiceImpl.getInstance();
    private final SubjectService subjectService = SubjectServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
        try {
            Optional<Tutor> tutor = tutorService.findTutorByEmail(user.getEmail());
            if (tutor.isPresent()) {
                List<Subject> subjects = subjectService.findSubjectsByTutorId(tutor.get().getTutorId());
                request.setAttribute(RequestAttribute.SUBJECTS, subjects);
                request.setAttribute(RequestAttribute.APPLICATION, tutor.get());
                return new Router(PagePath.APPLICATION_PAGE, Router.RouteType.FORWARD);
            } else {
                return new Router(PagePath.ADD_APPLICATION_PAGE, Router.RouteType.FORWARD);
            }
        } catch (ServiceException e) {
            logger.error("Executing become tutor command", e);
            throw new CommandException("Executing become tutor command", e);
        }
    }
}
