package by.slizh.tutorsweb.controller;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.User;
import by.slizh.tutorsweb.model.service.UserService;
import by.slizh.tutorsweb.model.service.impl.UserServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Base64;

@WebServlet(name = "FileUploadingServlet", value = "/uploadServlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class FileUploadingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
//        Part filePart = request.getPart("photo");
//        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//        InputStream fileContent = filePart.getInputStream();
//        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
//        File uploadDir = new File(uploadPath);
//        if (!uploadDir.exists()) {
//            uploadDir.mkdir();
//        }
//        String fileName;
//        for (Part part : request.getParts()) {
//            fileName = part.getSubmittedFileName();
//            part.write(uploadPath + File.separator + fileName);
//        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService service = UserServiceImpl.getInstance();
        Part filePart = request.getPart("photo");
        InputStream fileContent = filePart.getInputStream();
        byte[] bytes = fileContent.readAllBytes();
        System.out.println(request.getSession().getAttribute("user") == null);
        try {
            System.out.println(1);
            User user = (User) request.getSession().getAttribute("user");
            service.updatePhoto(user, fileContent);
            request.getSession().setAttribute("user", user);
        } catch (ServiceException e) {
            System.out.println(2);
            e.printStackTrace();
        }
        System.out.println(3);
        String base64String = Base64.getEncoder().encodeToString(bytes);
        request.getSession().setAttribute("userPhoto", base64String);
        request.getRequestDispatcher(PagePath.EDIT_PROFILE_PAGE).forward(request, response);
    }
}
