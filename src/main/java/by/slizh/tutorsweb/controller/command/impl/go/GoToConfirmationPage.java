package by.slizh.tutorsweb.controller.command.impl.go;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Locale;

import static by.slizh.tutorsweb.controller.command.RequestAttribute.SUCCESS_REGISTRATION_MESSAGE;

public class GoToConfirmationPage implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return new Router(PagePath.CONFIRMATION_PAGE, Router.RouteType.FORWARD);
    }
}
