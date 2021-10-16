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
        System.out.println("RoleFilter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if (session.getAttribute(SessionAttribute.USER) == null) {
            System.out.println("---guest created---");
            User user = new User.UserBuilder()
                    .setRole(User.Role.GUEST)
                    .createUser();
            session.setAttribute(SessionAttribute.USER, user);
        }
        String commandName = request.getParameter(RequestParameter.COMMAND);
        CommandType commandType;
        if (commandName != null) {
            commandType = CommandType.valueOf(commandName.toUpperCase(Locale.ROOT));
        } else {
            commandType = CommandType.DEFAULT;
        }
        RoleCommandProvider roleCommandProvider = RoleCommandProvider.getInstance();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        if (!roleCommandProvider.checkCommand(user.getRole(), commandType)) {
            response.sendRedirect(request.getContextPath() + PagePath.GO_TO_MAIN_PAGE);
            // request.getRequestDispatcher(PagePath.MAIN_PAGE).forward(request, response);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
