package by.slizh.tutorsweb.controller.command.impl;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.Feedback;
import by.slizh.tutorsweb.model.entity.Tutor;
import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.model.service.FeedbackService;
import by.slizh.tutorsweb.model.service.TutorService;
import by.slizh.tutorsweb.model.service.UserService;
import by.slizh.tutorsweb.model.service.impl.FeedbackServiceImpl;
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
        int rating = Integer.parseInt(request.getParameter(RequestParameter.RATING));
        int tutorId = Integer.parseInt(request.getParameter(RequestParameter.TUTOR_ID));
        int userID = ((User) request.getSession().getAttribute(SessionAttribute.USER)).getUserId();
        FeedbackValidator feedbackValidator = FeedbackValidatorImpl.getInstance();
        FeedbackService feedbackService = FeedbackServiceImpl.getInstance();
        TutorService tutorService = TutorServiceImpl.getInstance();
        UserService userService = UserServiceImpl.getInstance();
        if (feedbackValidator.validateFeedbackText(text)) {
            Feedback feedback = new Feedback.FeedbackBuilder()
                    .setText(text)
                    .setDate(LocalDate.now())
                    .setRating(rating)
                    .setTutorId(tutorId)
                    .setUserId(userID)
                    .createFeedback();

            try {
                feedbackService.addFeedback(feedback);
                Optional<Tutor> tutor = tutorService.findTutorById(tutorId);
                if (tutor.isPresent()) {
                    request.setAttribute(RequestAttribute.TUTOR, tutor.get());
                }
                List<Feedback> feedbacks = feedbackService.findFeedbacksByTutor(tutor.get().getTutorId());
                Map<Feedback, User> feedbackUserMap = userService.findUsersForFeedbacks(feedbacks);

                request.setAttribute(RequestAttribute.FEEDBACKS, feedbacks);
                request.setAttribute(RequestAttribute.USERS, feedbackUserMap);

            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        }
        return new Router(PagePath.TUTOR_PROFILE_PAGE, Router.RouteType.FORWARD);
    }
}
