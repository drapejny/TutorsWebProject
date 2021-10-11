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

public class AddFeedbackCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String text = request.getParameter(RequestParameter.TEXT);
        String rating = request.getParameter(RequestParameter.RATING);
        int tutorId = Integer.parseInt(request.getParameter(RequestParameter.TUTOR_ID));
        int userID = ((User) request.getSession().getAttribute(SessionAttribute.USER)).getUserId();
        FeedbackValidator feedbackValidator = FeedbackValidatorImpl.getInstance();
        FeedbackService feedbackService = FeedbackServiceImpl.getInstance();
        if (feedbackValidator.validateFeedbackText(text) && rating != null) {
            Feedback feedback = new Feedback.FeedbackBuilder()
                    .setText(text)
                    .setDate(LocalDate.now())
                    .setRating(Integer.parseInt(rating))
                    .setTutorId(tutorId)
                    .setUserId(userID)
                    .createFeedback();
            try {
                feedbackService.addFeedback(feedback);
                request.getSession().setAttribute(RequestAttribute.TUTOR_ID, tutorId);
            } catch (ServiceException e) {
                throw new CommandException("Executing addFeedback command error", e);
            }
        }
        return new Router(PagePath.GO_TO_TUTOR_PROFILE_PAGE, Router.RouteType.REDIRECT);
    }
}