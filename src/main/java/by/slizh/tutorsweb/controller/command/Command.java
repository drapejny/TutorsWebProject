package by.slizh.tutorsweb.controller.command;

import by.slizh.tutorsweb.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The interface Command.
 */
public interface Command {

    /**
     * Method, which run command code.
     *
     * @param request instance of {@link HttpServletRequest} from controller.
     * @return {@link Router} instance.
     * @throws CommandException in case of any service exceptions in method
     */
    Router execute(HttpServletRequest request) throws CommandException;
}
