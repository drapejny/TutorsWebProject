package by.slizh.tutorsweb.controller.filter;

import by.slizh.tutorsweb.controller.command.PagePath;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * The PageFilter filter direct access to jsp pages.
 */
@WebFilter(urlPatterns = {"/jsp/*"})
public class PageFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.sendRedirect(request.getContextPath() + PagePath.GO_TO_MAIN_PAGE);
    }
}
