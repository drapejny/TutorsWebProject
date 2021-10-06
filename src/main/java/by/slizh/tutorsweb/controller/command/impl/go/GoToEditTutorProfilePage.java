package by.slizh.tutorsweb.controller.command.impl.go;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.Subject;
import by.slizh.tutorsweb.model.entity.Tutor;
import by.slizh.tutorsweb.model.service.SubjectService;
import by.slizh.tutorsweb.model.service.impl.SubjectServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GoToEditTutorProfilePage implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
//        SubjectService subjectService = SubjectServiceImpl.getInstance();
//        Tutor tutor = (Tutor) request.getSession().getAttribute(SessionAttribute.USER);
//        try {
//            List<Subject> subjectList = subjectService.findSubjectsByTutorId(tutor.getTutorId());
//            request.setAttribute(RequestAttribute.TUTOR_SUBJECTS, subjectList);
            return new Router(PagePath.EDIT_TUTOR_PROFILE_PAGE, Router.RouteType.FORWARD);
//        } catch (ServiceException e) {
//            logger.error("Executing goToEditTutorProfilePage command error", e);
//            throw new CommandException("Executing goToEditTutorProfilePage command error", e);
//        }
    }
}
