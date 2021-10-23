package by.slizh.tutorsweb.controller.upload;

import by.slizh.tutorsweb.controller.command.Router;
import by.slizh.tutorsweb.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.InputStream;

/**
 * The interface UploadCommand.
 */
public interface UploadCommand {

    /**
     * Method, which run command code.
     *
     * @param request instance of {@link HttpServletRequest}.
     * @param inputStream inputStream of content.
     * @return {@link Router} instance.
     * @throws CommandException in case of any exceptions in method
     */
    Router execute(HttpServletRequest request, InputStream inputStream) throws CommandException;
}
