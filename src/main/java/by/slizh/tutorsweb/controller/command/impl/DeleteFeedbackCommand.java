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
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DeleteFeedbackCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        int feedbackId = Integer.parseInt(request.getParameter(RequestParameter.FEEDBACK_ID));
        int tutorId = Integer.parseInt(request.getParameter(RequestParameter.TUTOR_ID));
        FeedbackService feedbackService = FeedbackServiceImpl.getInstance();
        try {
            feedbackService.deleteFeedbackById(feedbackId);
            request.getSession().setAttribute(RequestAttribute.TUTOR_ID,tutorId);
        } catch (ServiceException e) {
            logger.error("Executing deleteFeedback command error", e);
            throw new CommandException("Executing deleteFeedback command error", e);
        }
        return new Router(PagePath.GO_TO_TUTOR_PROFILE_PAGE, Router.RouteType.REDIRECT);
    }
}
