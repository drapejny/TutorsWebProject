package by.slizh.tutorsweb.controller.command.impl.go;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.Feedback;
import by.slizh.tutorsweb.model.entity.Subject;
import by.slizh.tutorsweb.model.entity.Tutor;
import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.model.service.FeedbackService;
import by.slizh.tutorsweb.model.service.SubjectService;
import by.slizh.tutorsweb.model.service.TutorService;
import by.slizh.tutorsweb.model.service.UserService;
import by.slizh.tutorsweb.model.service.impl.FeedbackServiceImpl;
import by.slizh.tutorsweb.model.service.impl.SubjectServiceImpl;
import by.slizh.tutorsweb.model.service.impl.TutorServiceImpl;
import by.slizh.tutorsweb.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class GoToTutorProfilePage implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        String tutorIdParameter = request.getParameter(RequestParameter.TUTOR_ID);
        int tutorId;
        if (tutorIdParameter == null) {
            tutorId = (Integer) request.getSession().getAttribute(RequestAttribute.TUTOR_ID);
        } else {
            tutorId = Integer.parseInt(tutorIdParameter);
            request.getSession().removeAttribute(RequestAttribute.TUTOR_ID);
        }

        TutorService tutorService = TutorServiceImpl.getInstance();
        SubjectService subjectService = SubjectServiceImpl.getInstance();
        FeedbackService feedbackService = FeedbackServiceImpl.getInstance();
        UserService userService = UserServiceImpl.getInstance();
        try {
            Optional<Tutor> tutor = tutorService.findTutorById(tutorId);
            if (tutor.isPresent()) {
                request.setAttribute(RequestAttribute.TUTOR, tutor.get());
            }
            List<Subject> subjects = subjectService.findSubjectsByTutorId(tutorId);
            List<Feedback> feedbacks = feedbackService.findFeedbacksByTutor(tutorId);
            Map<Feedback, User> feedbackUserMap = userService.findUsersForFeedbacks(feedbacks);

            request.setAttribute(RequestAttribute.SUBJECTS, subjects);
            request.setAttribute(RequestAttribute.FEEDBACKS, feedbacks);
            request.setAttribute(RequestAttribute.USERS, feedbackUserMap);

            return new Router(PagePath.TUTOR_PROFILE_PAGE, Router.RouteType.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException("Executing search command error", e);
        }
    }
}