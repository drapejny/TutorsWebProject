package by.slizh.tutorsweb.controller.command.impl;

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
import by.slizh.tutorsweb.model.validator.FeedbackValidator;
import by.slizh.tutorsweb.model.validator.impl.FeedbackValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EditFeedbackCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        int feedbackId = Integer.parseInt(request.getParameter(RequestParameter.FEEDBACK_ID));
        String text = request.getParameter(RequestParameter.TEXT);
        String rating = request.getParameter(RequestParameter.RATING);
        int tutorId = Integer.parseInt(request.getParameter(RequestParameter.TUTOR_ID));
        FeedbackValidator feedbackValidator = FeedbackValidatorImpl.getInstance();
        FeedbackService feedbackService = FeedbackServiceImpl.getInstance();
        TutorService tutorService = TutorServiceImpl.getInstance();
        UserService userService = UserServiceImpl.getInstance();
        SubjectService subjectService = SubjectServiceImpl.getInstance();
        if (feedbackValidator.validateFeedbackText(text) && rating != null) {
            try {
                Optional<Feedback> feedback = feedbackService.findFeedbackById(feedbackId);
                if (feedback.isPresent()) {
                    feedback.get().setText(text);
                    feedback.get().setRating(Integer.parseInt(rating));
                    feedback.get().setDate(LocalDate.now());
                    feedbackService.updateFeedback(feedback.get());
                }

            } catch (ServiceException e) {
                logger.error("Executing editFeedback command error", e);
                throw new CommandException("Executing editFeedback command error", e);
            }
        }
        try {
            Optional<Tutor> tutor = tutorService.findTutorById(tutorId);
            if (tutor.isPresent()) {
                request.setAttribute(RequestAttribute.TUTOR, tutor.get());
            }
            List<Feedback> feedbacks = feedbackService.findFeedbacksByTutor(tutorId);
            List<Subject> subjects = subjectService.findSubjectsByTutorId(tutorId);
            Map<Feedback, User> feedbackUserMap = userService.findUsersForFeedbacks(feedbacks);
            request.setAttribute(RequestAttribute.FEEDBACKS, feedbacks);
            request.setAttribute(RequestAttribute.USERS, feedbackUserMap);
            request.setAttribute(RequestAttribute.SUBJECTS, subjects);
        } catch (ServiceException e) {
            logger.error("Executing editFeedback command error", e);
            throw new CommandException("Executing editFeedback command error", e);
        }
        return new Router(PagePath.TUTOR_PROFILE_PAGE, Router.RouteType.FORWARD);
    }
}
