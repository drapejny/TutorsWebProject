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

    private static final Integer PAGE_RECORDS_NUMBER = 3;
    private static final Integer FIRST_PAGE_NUMBER = 1;
    private static final Integer DEFAULT_OFFSET = 0;
    private static final String DEFAULT_SORT = "default_sort";
    private static final String DEFAULT_MIN_PRICE = "0";
    private static final String DEFAULT_MAX_PRICE = "1000";

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

        if(sort == null){
            sort = DEFAULT_SORT;
        }

        if (pageNumber == null) {
            System.out.println(1);
            String city = request.getParameter(CITY);
            String subjectId = request.getParameter(SUBJECT);
            String minPrice = request.getParameter(MIN_PRICE);
            String maxPrice = request.getParameter(MAX_PRICE);


            if (tutorValidator.validateCity(city) && tutorValidator.validatePrice(minPrice)
                    && tutorValidator.validatePrice(maxPrice)) {
                System.out.println(2);
                session.setAttribute(SEARCHED_CITY, city);
                session.setAttribute(SEARCHED_SUBJECT_ID, Integer.parseInt(subjectId));
                session.setAttribute(SEARCHED_MIN_PRICE, Integer.parseInt(minPrice));
                session.setAttribute(SEARCHED_MAX_PRICE, Integer.parseInt(maxPrice));
                request.setAttribute(SEARCHED_PAGE_NUMBER, FIRST_PAGE_NUMBER);
                request.setAttribute(SORT,sort);
                try {
                    List<Tutor> tutors = tutorService.searchTutors(Integer.parseInt(subjectId), city, Integer.parseInt(minPrice), Integer.parseInt(maxPrice), DEFAULT_OFFSET, PAGE_RECORDS_NUMBER,sort);
                    request.setAttribute(RequestAttribute.TUTORS, tutors);
                    int searchedRecordsCount = tutorService.countSearchedRecords(Integer.parseInt(subjectId), city, Integer.parseInt(minPrice), Integer.parseInt(maxPrice));
                    int pagesCount = searchedRecordsCount % PAGE_RECORDS_NUMBER == 0 ? searchedRecordsCount / PAGE_RECORDS_NUMBER : searchedRecordsCount / PAGE_RECORDS_NUMBER + 1;
                    request.setAttribute(SEARCHED_PAGES_COUNT, pagesCount);
                    System.out.println(3);
                } catch (ServiceException e) {
                    throw new CommandException("Executing search command error", e);
                }
            }
        } else {
            System.out.println(4);
            request.setAttribute(SEARCHED_PAGE_NUMBER, Integer.parseInt(pageNumber));
            request.setAttribute(SORT,sort);
            try {
                Integer subjectId = (Integer) session.getAttribute(SEARCHED_SUBJECT_ID);
                String city = (String) session.getAttribute(SEARCHED_CITY);
                Integer minPrice = (Integer) session.getAttribute(SEARCHED_MIN_PRICE);
                Integer maxPrice = (Integer) session.getAttribute(SEARCHED_MAX_PRICE);
                int offset = (Integer.parseInt(pageNumber) - 1) * PAGE_RECORDS_NUMBER;
                List<Tutor> tutors = tutorService.searchTutors(subjectId, city, minPrice, maxPrice, offset, PAGE_RECORDS_NUMBER,sort);
                int searchedRecordsCount = tutorService.countSearchedRecords(subjectId, city, minPrice, maxPrice);
                int pagesCount = searchedRecordsCount % PAGE_RECORDS_NUMBER == 0 ? searchedRecordsCount / PAGE_RECORDS_NUMBER : searchedRecordsCount / PAGE_RECORDS_NUMBER + 1;
                request.setAttribute(TUTORS, tutors);
                request.setAttribute(SEARCHED_PAGES_COUNT, pagesCount);
                request.setAttribute(SORT,sort);
                System.out.println(5);
            } catch (ServiceException e) {
                throw new CommandException("Executing search command error", e);
            }
        }
        return new Router(PagePath.SEARCH_PAGE, Router.RouteType.FORWARD);
    }
}
