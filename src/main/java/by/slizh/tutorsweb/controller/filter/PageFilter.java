package by.slizh.tutorsweb.controller.filter;

import by.slizh.tutorsweb.controller.command.SessionAttribute;
import by.slizh.tutorsweb.model.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

//@WebFilter(urlPatterns = {"/*"})
public class PageFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("pageFilter");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute(SessionAttribute.USER) == null) {
            User user = new User.UserBuilder()
                    .setRole(User.Role.GUEST)
                    .createUser();
            session.setAttribute(SessionAttribute.USER, user);
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/jsp/main.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }
}
