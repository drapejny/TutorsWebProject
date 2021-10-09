package by.slizh.tutorsweb.controller.filter;

import by.slizh.tutorsweb.controller.command.SessionAttribute;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter(filterName = "PageFilter", urlPatterns = {"*.jsp"})
public class PageFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        chain.doFilter(request, response);
    }
}
