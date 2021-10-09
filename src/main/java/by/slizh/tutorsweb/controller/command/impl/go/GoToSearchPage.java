package by.slizh.tutorsweb.controller.command.impl.go;

import by.slizh.tutorsweb.controller.command.Command;
import by.slizh.tutorsweb.controller.command.PagePath;
import by.slizh.tutorsweb.controller.command.RequestAttribute;
import by.slizh.tutorsweb.controller.command.Router;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.service.TutorService;
import by.slizh.tutorsweb.model.service.impl.TutorServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.slizh.tutorsweb.controller.command.SessionAttribute.*;
import static by.slizh.tutorsweb.controller.command.SessionAttribute.SEARCHED_MAX_PRICE;

public class GoToSearchPage implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.removeAttribute(SEARCHED_CITY);
        session.removeAttribute(SEARCHED_SUBJECT_ID);
        session.removeAttribute(SEARCHED_MIN_PRICE);
        session.removeAttribute(SEARCHED_MAX_PRICE);

        TutorService tutorService = TutorServiceImpl.getInstance();

        return new Router(PagePath.SEARCH_PAGE, Router.RouteType.FORWARD);
    }
}
