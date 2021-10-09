package by.slizh.tutorsweb.controller.command.impl.admin;

import by.slizh.tutorsweb.controller.command.Command;
import by.slizh.tutorsweb.controller.command.PagePath;
import by.slizh.tutorsweb.controller.command.RequestParameter;
import by.slizh.tutorsweb.controller.command.Router;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.Subject;
import by.slizh.tutorsweb.model.entity.Tutor;
import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.model.service.SubjectService;
import by.slizh.tutorsweb.model.service.TutorService;
import by.slizh.tutorsweb.model.service.UserService;
import by.slizh.tutorsweb.model.service.impl.SubjectServiceImpl;
import by.slizh.tutorsweb.model.service.impl.TutorServiceImpl;
import by.slizh.tutorsweb.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

import static by.slizh.tutorsweb.controller.command.RequestAttribute.*;

public class AcceptApplicationCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String userId = request.getParameter(RequestParameter.USER_ID);
        UserService userService = UserServiceImpl.getInstance();
        TutorService tutorService = TutorServiceImpl.getInstance();
        try {
            userService.makeUserToTutor(Integer.parseInt(userId));
            List<Tutor> applications = tutorService.findApplications(DEFAULT_OFFSET, APPLICATIONS_ON_PAGE_NUMBER);
            int applicationsCount = tutorService.countApplications();
            int pageCount = applicationsCount % APPLICATIONS_ON_PAGE_NUMBER == 0 ? applicationsCount / APPLICATIONS_ON_PAGE_NUMBER : applicationsCount / APPLICATIONS_ON_PAGE_NUMBER + 1;
            request.setAttribute(APPLICATIONS, applications);
            request.setAttribute(PAGE_NUM, FIRST_PAGE_NUMBER);
            request.setAttribute(PAGE_COUNT, pageCount);
        } catch (ServiceException e) {
            logger.error("Executing acceptApplication command error", e);
            throw new CommandException("Executing acceptApplication command error", e);
        }
        return new Router(PagePath.ALL_APPLICATIONS_PAGE, Router.RouteType.FORWARD);
    }
}
