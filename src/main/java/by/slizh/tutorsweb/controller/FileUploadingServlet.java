package by.slizh.tutorsweb.controller;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.controller.upload.UploadCommand;
import by.slizh.tutorsweb.controller.upload.UploadCommandFactory;
import by.slizh.tutorsweb.exception.CommandException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type FileUploadingServlet.
 */
@WebServlet(name = "FileUploadingServlet", value = "/uploadServlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class FileUploadingServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UploadCommand command = UploadCommandFactory.getInstance().createCommand(request);
        Part filePart = request.getPart(RequestParameter.FILE);
        InputStream fileContent = filePart.getInputStream();
        Router router;
        try {
            router = command.execute(request, fileContent);
        } catch (CommandException e) {
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
}
