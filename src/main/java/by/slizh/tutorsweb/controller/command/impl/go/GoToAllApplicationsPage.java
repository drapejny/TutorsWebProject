package by.slizh.tutorsweb.controller.command.impl.go;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.Tutor;
import by.slizh.tutorsweb.model.service.TutorService;
import by.slizh.tutorsweb.model.service.impl.TutorServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.slizh.tutorsweb.controller.command.RequestAttribute.*;

public class GoToAllApplicationsPage implements Command {

    private static final Logger logger = LogManager.getLogger();

    private final TutorService tutorService = TutorServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String pageNumber = request.getParameter(RequestParameter.PAGE_NUMBER);


        int offset;
        int page;
        try {
            offset = (Integer.parseInt(pageNumber) - 1) * APPLICATIONS_ON_PAGE_NUMBER;
            page = Integer.parseInt(pageNumber);
            if (page < 1) {
                page = 1;
                offset = 0;
            }
        } catch (NumberFormatException | NullPointerException e) {
            offset = 0;
            page = 1;
        }

        try {
            List<Tutor> applications = tutorService.findApplications(offset, APPLICATIONS_ON_PAGE_NUMBER);
            int applicationsCount = tutorService.countApplications();
            int pageCount = applicationsCount % APPLICATIONS_ON_PAGE_NUMBER == 0 ? applicationsCount / APPLICATIONS_ON_PAGE_NUMBER : applicationsCount / APPLICATIONS_ON_PAGE_NUMBER + 1;
            request.setAttribute(RequestAttribute.APPLICATIONS, applications);
            request.setAttribute(RequestAttribute.PAGE_NUM, page);
            request.setAttribute(RequestAttribute.PAGE_COUNT, pageCount);
        } catch (ServiceException e) {
            logger.error("Executing goToAllApplications command error", e);
            throw new CommandException("Executing goToAllApplications command error", e);
        }
        return new Router(PagePath.ALL_APPLICATIONS_PAGE, Router.RouteType.FORWARD);
    }
}
