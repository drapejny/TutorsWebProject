package by.slizh.tutorsweb.controller.command;

import by.slizh.tutorsweb.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public interface Command {

    Router execute(HttpServletRequest request) throws CommandException;
}
