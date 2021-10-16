package by.slizh.tutorsweb.controller.filter;

import by.slizh.tutorsweb.controller.command.PagePath;
import by.slizh.tutorsweb.controller.command.SessionAttribute;
import by.slizh.tutorsweb.model.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/jsp/*"})
public class PageFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        System.out.println("PageFilter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.sendRedirect(request.getContextPath() + PagePath.GO_TO_MAIN_PAGE);
        //chain.doFilter(request, response);
    }
}
