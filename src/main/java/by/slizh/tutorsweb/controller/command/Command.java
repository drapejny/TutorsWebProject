package by.slizh.tutorsweb.controller.command;

import by.slizh.tutorsweb.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The interface Command for Controller.
 */
public interface Command {

    /**
     * Method, which run command code.
     *
     * @param request instance of {@link HttpServletRequest} from controller.
     * @return {@link Router} instance.
     * @throws CommandException in case of any exceptions in method
     */
    Router execute(HttpServletRequest request) throws CommandException;
}
