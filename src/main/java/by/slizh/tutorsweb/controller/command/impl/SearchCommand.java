package by.slizh.tutorsweb.controller.command.impl;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.Tutor;
import by.slizh.tutorsweb.model.service.TutorService;
import by.slizh.tutorsweb.model.service.impl.TutorServiceImpl;
import by.slizh.tutorsweb.model.validator.TutorValidator;
import by.slizh.tutorsweb.model.validator.UserValidator;
import by.slizh.tutorsweb.model.validator.impl.TutorValidatorImpl;
import by.slizh.tutorsweb.model.validator.impl.UserValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static by.slizh.tutorsweb.controller.command.RequestParameter.*;
import static by.slizh.tutorsweb.controller.command.RequestAttribute.*;
import static by.slizh.tutorsweb.controller.command.SessionAttribute.*;

public class SearchCommand implements Command {

    private static final String DEFAULT_SORT = "default_sort";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        TutorValidator tutorValidator = TutorValidatorImpl.getInstance();
        UserValidator userValidator = UserValidatorImpl.getInstance();
        TutorService tutorService = TutorServiceImpl.getInstance();
//        Map<String,String[]> map = request.getParameterMap();
//        for (Map.Entry<String, String[]> entry : map.entrySet()) {
//            System.out.println("ID =  " + entry.getKey() + "  " + Arrays.toString(entry.getValue()));
//        }
        String pageNumber = request.getParameter(PAGE_NUMBER);
        String sort = request.getParameter(SORT);

        if (sort == null) {
            sort = DEFAULT_SORT;
        }

        if (pageNumber == null) {
            String city = request.getParameter(CITY);
            String subjectId = request.getParameter(SUBJECT);
            String minPrice = request.getParameter(MIN_PRICE);
            String maxPrice = request.getParameter(MAX_PRICE);


            if (tutorValidator.validateCity(city) && tutorValidator.validatePrice(minPrice)
                    && tutorValidator.validatePrice(maxPrice)) {
                session.setAttribute(SEARCHED_CITY, city);
                session.setAttribute(SEARCHED_SUBJECT_ID, Integer.parseInt(subjectId));
                session.setAttribute(SEARCHED_MIN_PRICE, Integer.parseInt(minPrice));
                session.setAttribute(SEARCHED_MAX_PRICE, Integer.parseInt(maxPrice));
                request.setAttribute(SEARCHED_PAGE_NUMBER, FIRST_PAGE_NUMBER);
                request.setAttribute(SORT, sort);
                try {
                    List<Tutor> tutors = tutorService.searchTutors(Integer.parseInt(subjectId), city, Integer.parseInt(minPrice), Integer.parseInt(maxPrice), DEFAULT_OFFSET, TUTORS_ON_SEARCH_PAGE_NUMBER, sort);
                    request.setAttribute(RequestAttribute.TUTORS, tutors);
                    int searchedRecordsCount = tutorService.countSearchedRecords(Integer.parseInt(subjectId), city, Integer.parseInt(minPrice), Integer.parseInt(maxPrice));
                    int pagesCount = searchedRecordsCount % TUTORS_ON_SEARCH_PAGE_NUMBER == 0 ? searchedRecordsCount / TUTORS_ON_SEARCH_PAGE_NUMBER : searchedRecordsCount / TUTORS_ON_SEARCH_PAGE_NUMBER + 1;
                    request.setAttribute(SEARCHED_PAGES_COUNT, pagesCount);
                } catch (ServiceException e) {
                    throw new CommandException("Executing search command error", e);
                }
            }
        } else {
            request.setAttribute(SEARCHED_PAGE_NUMBER, Integer.parseInt(pageNumber));
            request.setAttribute(SORT, sort);
            try {
                Integer subjectId = (Integer) session.getAttribute(SEARCHED_SUBJECT_ID);
                String city = (String) session.getAttribute(SEARCHED_CITY);
                Integer minPrice = (Integer) session.getAttribute(SEARCHED_MIN_PRICE);
                Integer maxPrice = (Integer) session.getAttribute(SEARCHED_MAX_PRICE);
                int offset = (Integer.parseInt(pageNumber) - 1) * TUTORS_ON_SEARCH_PAGE_NUMBER;
                List<Tutor> tutors = tutorService.searchTutors(subjectId, city, minPrice, maxPrice, offset, TUTORS_ON_SEARCH_PAGE_NUMBER, sort);
                int searchedRecordsCount = tutorService.countSearchedRecords(subjectId, city, minPrice, maxPrice);
                int pagesCount = searchedRecordsCount % TUTORS_ON_SEARCH_PAGE_NUMBER == 0 ? searchedRecordsCount / TUTORS_ON_SEARCH_PAGE_NUMBER : searchedRecordsCount / TUTORS_ON_SEARCH_PAGE_NUMBER + 1;
                request.setAttribute(TUTORS, tutors);
                request.setAttribute(SEARCHED_PAGES_COUNT, pagesCount);
                request.setAttribute(SORT, sort);
            } catch (ServiceException e) {
                throw new CommandException("Executing search command error", e);
            }
        }
        return new Router(PagePath.SEARCH_PAGE, Router.RouteType.FORWARD);
    }
}
