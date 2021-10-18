package by.slizh.tutorsweb.controller.command.impl.go;

import by.slizh.tutorsweb.controller.command.Command;
import by.slizh.tutorsweb.controller.command.PagePath;
import by.slizh.tutorsweb.controller.command.Router;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.model.entity.Subject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.slizh.tutorsweb.controller.command.RequestAttribute.*;

import java.util.List;

public class GoToAllSubjectsPage implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        if (session.getAttribute(SUCCESSFUL_ADD_SUBJECT) != null) {
            request.setAttribute(SUCCESSFUL_ADD_SUBJECT, session.getAttribute(SUCCESSFUL_ADD_SUBJECT));
            session.removeAttribute(SUCCESSFUL_ADD_SUBJECT);
        }
        if (session.getAttribute(SUCCESSFUL_DELETE_SUBJECT) != null) {
            request.setAttribute(SUCCESSFUL_DELETE_SUBJECT, session.getAttribute(SUCCESSFUL_DELETE_SUBJECT));
            session.removeAttribute(SUCCESSFUL_DELETE_SUBJECT);
        }
        List<Subject> subjects = (List<Subject>) request.getServletContext().getAttribute(SUBJECTS);
        request.setAttribute(SUBJECTS, subjects);
        return new Router(PagePath.ALL_SUBJECTS_PAGE, Router.RouteType.FORWARD);

    }
}
