package by.slizh.tutorsweb.controller.command.impl;

import by.slizh.tutorsweb.controller.command.Command;
import by.slizh.tutorsweb.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return null;
    }
}
