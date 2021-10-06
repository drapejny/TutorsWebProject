package by.slizh.tutorsweb.controller.upload.impl;

import by.slizh.tutorsweb.controller.command.Router;
import by.slizh.tutorsweb.controller.upload.UploadCommand;
import by.slizh.tutorsweb.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.InputStream;

public class DefaultUploadCommand implements UploadCommand {
    @Override
    public Router execute(HttpServletRequest request, InputStream inputStream) throws CommandException {
        return null;
    }
}
