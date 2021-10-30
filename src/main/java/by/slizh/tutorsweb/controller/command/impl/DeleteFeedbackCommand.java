package by.slizh.tutorsweb.controller.command.impl;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.service.FeedbackService;
import by.slizh.tutorsweb.model.service.impl.FeedbackServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteFeedbackCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private final FeedbackService feedbackService = FeedbackServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        int feedbackId = Integer.parseInt(request.getParameter(RequestParameter.FEEDBACK_ID));
        int tutorId = Integer.parseInt(request.getParameter(RequestParameter.TUTOR_ID));
        try {
            feedbackService.deleteFeedbackById(feedbackId);
            request.getSession().setAttribute(RequestAttribute.TUTOR_ID, tutorId);
        } catch (ServiceException e) {
            logger.error("Executing deleteFeedback command error", e);
            throw new CommandException("Executing deleteFeedback command error", e);
        }
        String pagePath = PagePath.GO_TO_TUTOR_PROFILE_PAGE + tutorId;
        return new Router(pagePath, Router.RouteType.REDIRECT);
    }
}
