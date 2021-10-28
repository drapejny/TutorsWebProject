package by.slizh.tutorsweb.controller.command.impl;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.Feedback;
import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.model.service.FeedbackService;
import by.slizh.tutorsweb.model.service.impl.FeedbackServiceImpl;
import by.slizh.tutorsweb.model.validator.FeedbackValidator;
import by.slizh.tutorsweb.model.validator.impl.FeedbackValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public class AddFeedbackCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private final FeedbackService feedbackService = FeedbackServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String text = request.getParameter(RequestParameter.TEXT);
        String rating = request.getParameter(RequestParameter.RATING);
        int tutorId = Integer.parseInt(request.getParameter(RequestParameter.TUTOR_ID));
        int userID = ((User) request.getSession().getAttribute(SessionAttribute.USER)).getUserId();
        FeedbackValidator feedbackValidator = FeedbackValidatorImpl.getInstance();
        if (text != null) {
            text = text.replaceAll("\r\n", "\n");
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
                    logger.error("Executing addFeedback command error", e);
                    throw new CommandException("Executing addFeedback command error", e);
                }
            }
        }
        String pagePath = PagePath.GO_TO_TUTOR_PROFILE_PAGE + tutorId;
        return new Router(pagePath, Router.RouteType.REDIRECT);
    }
}
