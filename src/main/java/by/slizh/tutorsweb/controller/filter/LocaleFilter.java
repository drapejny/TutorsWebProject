package by.slizh.tutorsweb.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter(filterName = "LocaleFilter", urlPatterns = {"/*"})
public class LocaleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String uri = httpServletRequest.getRequestURI();
        System.out.println(httpServletRequest.getQueryString());
        System.out.println(uri);
        System.out.println(request.getAttribute("averageRating"));
        chain.doFilter(request, response);

    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

}