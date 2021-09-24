package by.slizh.tutorsweb.controller;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.model.service.UserService;
import by.slizh.tutorsweb.model.service.impl.UserServiceImpl;
import by.slizh.tutorsweb.util.Base64Coder;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

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
        UserService service = UserServiceImpl.getInstance();
        Part filePart = request.getPart(RequestParameter.PHOTO);
        InputStream fileContent = filePart.getInputStream();
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
        try {
            service.updatePhoto(user, fileContent);
        } catch (ServiceException e) {
            logger.error("Can't update user photo", e);
            throw new ServletException("Can't update user photo", e);
        }
        request.getRequestDispatcher(PagePath.EDIT_PROFILE_PAGE).forward(request, response);
    }
}
