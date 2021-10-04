package by.slizh.tutorsweb.controller;

import java.io.*;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.exception.CommandException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        Router router = null;
        try {
            router = command.execute(request);
        } catch (CommandException e) {
            e.printStackTrace();
            router = new Router(PagePath.ERROR_PAGE, Router.RouteType.REDIRECT);
        }
        switch (router.getRouteType()) {
            case FORWARD:
                request.getRequestDispatcher(router.getPagePath()).forward(request, response);
                break;
            case REDIRECT:
                response.sendRedirect(request.getContextPath() + router.getPagePath());
                break;
        }
    }

    public void destroy() {
    }
}