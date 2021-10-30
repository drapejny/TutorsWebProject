package by.slizh.tutorsweb.controller;

import java.io.*;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.exception.CommandException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * The type MainServlet.
 */
@WebServlet(name = "mainServlet", urlPatterns = "/controller")
public class MainServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = CommandFactory.getInstance().createCommand(request);
        Router router;
        try {
            router = command.execute(request);
            switch (router.getRouteType()) {
                case FORWARD -> request.getRequestDispatcher(router.getPagePath()).forward(request, response);
                case REDIRECT -> response.sendRedirect(request.getContextPath() + router.getPagePath());
                default -> {
                    logger.error("Incorrect route type " + router.getRouteType());
                    response.sendRedirect(request.getContextPath() + PagePath.ERROR_PAGE);
                }
            }
        } catch (CommandException e) {
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            request.getRequestDispatcher(PagePath.ERROR_PAGE).forward(request, response);
        }
    }

    public void destroy() {
    }
}