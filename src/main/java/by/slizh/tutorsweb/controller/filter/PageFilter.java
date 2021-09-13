package by.slizh.tutorsweb.controller.filter;

import by.slizh.tutorsweb.controller.command.SessionAttribute;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter(filterName = "PageFilter", urlPatterns = {"*.jsp"})
public class PageFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String contextPath = httpServletRequest.getContextPath();
        int len = contextPath.length();
        String uri = httpServletRequest.getRequestURI();
        String path = uri.substring(len);
        System.out.println("doFilter" + "  " + path);
        httpServletRequest.getSession().setAttribute(SessionAttribute.CURRENT_PAGE, path);
        chain.doFilter(request, response);
    }
}
