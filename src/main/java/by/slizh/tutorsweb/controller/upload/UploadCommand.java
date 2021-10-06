package by.slizh.tutorsweb.controller.upload;

import by.slizh.tutorsweb.controller.command.Router;
import by.slizh.tutorsweb.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.InputStream;

public interface UploadCommand {

    Router execute(HttpServletRequest request, InputStream inputStream) throws CommandException;
}
