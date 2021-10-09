package by.slizh.tutorsweb.controller.command.impl.admin;

import by.slizh.tutorsweb.controller.command.Command;
import by.slizh.tutorsweb.controller.command.PagePath;
import by.slizh.tutorsweb.controller.command.RequestParameter;
import by.slizh.tutorsweb.controller.command.Router;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.Subject;
import by.slizh.tutorsweb.model.entity.Tutor;
import by.slizh.tutorsweb.model.service.SubjectService;
import by.slizh.tutorsweb.model.service.TutorService;
import by.slizh.tutorsweb.model.service.impl.SubjectServiceImpl;
import by.slizh.tutorsweb.model.service.impl.TutorServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.slizh.tutorsweb.controller.command.RequestAttribute.*;
import static by.slizh.tutorsweb.controller.command.RequestAttribute.PAGE_COUNT;

public class RejectApplicationCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String tutorId = request.getParameter(RequestParameter.TUTOR_ID);
        TutorService tutorService = TutorServiceImpl.getInstance();
        try {
            tutorService.deleteTutorById(Integer.parseInt(tutorId));
            List<Tutor> applications = tutorService.findApplications(DEFAULT_OFFSET, APPLICATIONS_ON_PAGE_NUMBER);
            int applicationsCount = tutorService.countApplications();
            int pageCount = applicationsCount % APPLICATIONS_ON_PAGE_NUMBER == 0 ? applicationsCount / APPLICATIONS_ON_PAGE_NUMBER : applicationsCount / APPLICATIONS_ON_PAGE_NUMBER + 1;
            request.setAttribute(APPLICATIONS, applications);
            request.setAttribute(PAGE_NUM, FIRST_PAGE_NUMBER);
            request.setAttribute(PAGE_COUNT, pageCount);
        } catch (ServiceException e) {
            logger.error("Executing rejectApplication command error", e);
            throw new CommandException();
        }
        return new Router(PagePath.ALL_APPLICATIONS_PAGE, Router.RouteType.FORWARD);
    }
}
