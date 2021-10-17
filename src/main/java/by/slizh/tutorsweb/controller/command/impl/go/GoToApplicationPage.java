package by.slizh.tutorsweb.controller.command.impl.go;

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

public class GoToApplicationPage implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String tutorId = request.getParameter(RequestParameter.TUTOR_ID);
        TutorService tutorService = TutorServiceImpl.getInstance();
        SubjectService subjectService = SubjectServiceImpl.getInstance();
        try {
            Optional<Tutor> tutor = tutorService.findTutorById(Integer.parseInt(tutorId));
            if (tutor.isPresent() && tutor.get().getRole() == User.Role.USER
                    && tutor.get().getStatus() != User.Status.BLOCKED) {
                request.setAttribute(RequestAttribute.APPLICATION, tutor.get());
                List<Subject> subjects = subjectService.findSubjectsByTutorId(Integer.parseInt(tutorId));
                request.setAttribute(RequestAttribute.SUBJECTS, subjects);
                return new Router(PagePath.APPLICATION_PAGE, Router.RouteType.FORWARD);
            } else {
                return new Router(PagePath.GO_TO_ALL_APPLICATIONS_PAGE, Router.RouteType.FORWARD);
            }

        } catch (ServiceException e) {
            logger.error("Executing goToApplicationPage command error", e);
            throw new CommandException("Executing goToApplicationPage command error", e);
        }

    }
}
