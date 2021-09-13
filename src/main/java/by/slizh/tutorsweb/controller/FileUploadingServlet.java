package by.slizh.tutorsweb.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.IOException;

@WebServlet(name = "FileUploadingServlet", value = "/FileUploadingServlet")
public class FileUploadingServlet extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "/uploads/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        String fileName;
        for (Part part : request.getParts()) {
            fileName = part.getSubmittedFileName();
            part.write(uploadPath + File.separator + fileName);
        }
    }
}
