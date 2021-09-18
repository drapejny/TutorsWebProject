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

import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "FileUploadingServlet", value = "/uploadServlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class FileUploadingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService service = UserServiceImpl.getInstance();
        Part filePart = request.getPart("photo");
        InputStream fileContent = filePart.getInputStream();
        User user = (User) request.getSession().getAttribute("user");
        try {
            service.updatePhoto(user, fileContent);
        } catch (ServiceException e) {
            throw new ServletException("Can't update user photo", e);
        }
        request.getRequestDispatcher(PagePath.EDIT_PROFILE_PAGE).forward(request, response);
    }
}
