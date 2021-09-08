package by.slizh.tutorsweb;

import java.io.*;

import by.slizh.tutorsweb.command.Command;
import by.slizh.tutorsweb.command.CommandFactory;
import by.slizh.tutorsweb.command.Router;
import by.slizh.tutorsweb.command.SessionAttribute;
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
        System.out.println("processRequest");
        Command command = CommandFactory.getInstance().createCommand(request);
        Router router = command.execute(request);
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.CURRENT_PAGE, router.getPagePath());
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