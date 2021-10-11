package by.slizh.tutorsweb.controller.filter;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.model.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Locale;

@WebFilter(urlPatterns = "/controller")
public class RoleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("roleFilter start");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);

        filterChain.doFilter(servletRequest, servletResponse);  //// TODO: 07.10.2021 удалить это и расскомментить код выше

//        String commandName = request.getParameter(RequestParameter.COMMAND);
//        CommandType commandType;
//        if (commandName != null) {
//            commandType = CommandType.valueOf(commandName.toUpperCase(Locale.ROOT));
//        } else {
//            commandType = CommandType.DEFAULT;
//        }
//        RoleCommandProvider roleCommandProvider = RoleCommandProvider.getInstance();
//        if (!roleCommandProvider.checkCommand(user.getRole(), commandType)) {
//            response.sendRedirect(request.getContextPath() + PagePath.MAIN_PAGE);
//        } else {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
    }
}
