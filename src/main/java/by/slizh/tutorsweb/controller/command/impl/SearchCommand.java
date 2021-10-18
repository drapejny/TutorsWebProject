package by.slizh.tutorsweb.controller.command.impl;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.Tutor;
import by.slizh.tutorsweb.model.service.TutorService;
import by.slizh.tutorsweb.model.service.impl.TutorServiceImpl;
import by.slizh.tutorsweb.model.validator.TutorValidator;
import by.slizh.tutorsweb.model.validator.impl.TutorValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.slizh.tutorsweb.controller.command.RequestAttribute.*;

public class SearchCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static final String DEFAULT_SORT = "default_sort";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        TutorValidator tutorValidator = TutorValidatorImpl.getInstance();
        TutorService tutorService = TutorServiceImpl.getInstance();

        String pageNumber = request.getParameter(RequestParameter.PAGE_NUMBER);
        String sort = request.getParameter(RequestParameter.SORT);
        String city = request.getParameter(RequestParameter.CITY);
        String subjectId = request.getParameter(RequestParameter.SUBJECT);
        String minPrice = request.getParameter(RequestParameter.MIN_PRICE);
        String maxPrice = request.getParameter(RequestParameter.MAX_PRICE);

        if (sort == null) {
            sort = DEFAULT_SORT;
        }

        if (pageNumber == null) {

            if (tutorValidator.validateCity(city) && tutorValidator.validatePrice(minPrice)
                    && tutorValidator.validatePrice(maxPrice)) {
                request.setAttribute(SORT, sort);
                try {
                    List<Tutor> tutors = tutorService.searchTutors(Integer.parseInt(subjectId), city, Integer.parseInt(minPrice), Integer.parseInt(maxPrice), DEFAULT_OFFSET, TUTORS_ON_SEARCH_PAGE_NUMBER, sort);
                    request.setAttribute(RequestAttribute.TUTORS, tutors);
                    int searchedRecordsCount = tutorService.countSearchedRecords(Integer.parseInt(subjectId), city, Integer.parseInt(minPrice), Integer.parseInt(maxPrice));
                    int pagesCount = searchedRecordsCount % TUTORS_ON_SEARCH_PAGE_NUMBER == 0 ? searchedRecordsCount / TUTORS_ON_SEARCH_PAGE_NUMBER : searchedRecordsCount / TUTORS_ON_SEARCH_PAGE_NUMBER + 1;
                    request.setAttribute(PAGE_NUM, 1);
                    request.setAttribute(PAGE_COUNT, pagesCount);
                } catch (ServiceException e) {
                    logger.error("Executing search command error", e);
                    throw new CommandException("Executing search command error", e);
                }
            }
        } else {
            request.setAttribute(PAGE_NUM, Integer.parseInt(pageNumber));
            request.setAttribute(SORT, sort);
            try {
                int offset = (Integer.parseInt(pageNumber) - 1) * TUTORS_ON_SEARCH_PAGE_NUMBER;
                List<Tutor> tutors = tutorService.searchTutors(Integer.parseInt(subjectId), city, Integer.parseInt(minPrice), Integer.parseInt(maxPrice), offset, TUTORS_ON_SEARCH_PAGE_NUMBER, sort);
                int searchedRecordsCount = tutorService.countSearchedRecords(Integer.parseInt(subjectId), city, Integer.parseInt(minPrice), Integer.parseInt(maxPrice));
                int pagesCount = searchedRecordsCount % TUTORS_ON_SEARCH_PAGE_NUMBER == 0 ? searchedRecordsCount / TUTORS_ON_SEARCH_PAGE_NUMBER : searchedRecordsCount / TUTORS_ON_SEARCH_PAGE_NUMBER + 1;
                request.setAttribute(TUTORS, tutors);
                request.setAttribute(PAGE_COUNT, pagesCount);
                request.setAttribute(SORT, sort);
            } catch (ServiceException e) {
                logger.error("Executing search command error", e);
                throw new CommandException("Executing search command error", e);
            }
        }
        request.setAttribute(SUBJECT_ID, subjectId);
        request.setAttribute(CITY, city);
        request.setAttribute(MIN_PRICE, minPrice);
        request.setAttribute(MAX_PRICE, maxPrice);
        return new Router(PagePath.SEARCH_PAGE, Router.RouteType.FORWARD);
    }
}
