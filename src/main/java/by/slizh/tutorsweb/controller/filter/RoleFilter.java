package by.slizh.tutorsweb.controller.filter;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.model.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.EnumSet;
import java.util.Locale;

import static by.slizh.tutorsweb.controller.command.CommandType.*;

/**
 * The RoleFilter filter all requests and provide secure access by user role.
 */
@WebFilter(urlPatterns = "/controller")
public class RoleFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();

    private final EnumSet<CommandType> guestCommands = EnumSet.of(
            DEFAULT,
            CONFIRMATION_PAGE,
            LOGIN_PAGE,
            MAIN_PAGE,
            REGISTRATION_PAGE,
            SEARCH_PAGE,
            TUTOR_PROFILE_PAGE,
            LOGIN,
            REGISTRATION,
            SEARCH,
            VERIFICATION,
            CHANGE_LOCALE,
            ABOUT_US_PAGE
    );

    private final EnumSet<CommandType> userCommands = EnumSet.of(
            DEFAULT,
            EDIT_PROFILE_PAGE,
            MAIN_PAGE,
            SEARCH_PAGE,
            TUTOR_PROFILE_PAGE,
            ADD_APPLICATION,
            ADD_FEEDBACK,
            BECOME_TUTOR,
            DELETE_FEEDBACK,
            EDIT_FEEDBACK,
            EDIT_PASSWORD,
            EDIT_PROFILE,
            LOGOUT,
            SEARCH,
            CHANGE_LOCALE,
            ABOUT_US_PAGE
    );

    private final EnumSet<CommandType> tutorCommands = EnumSet.of(
            DEFAULT,
            EDIT_TUTOR_PROFILE_PAGE,
            MAIN_PAGE,
            SEARCH_PAGE,
            TUTOR_PROFILE_PAGE,
            DELETE_FEEDBACK,
            EDIT_PASSWORD,
            EDIT_TUTOR_PROFILE,
            LOGOUT,
            SEARCH,
            CHANGE_LOCALE,
            ABOUT_US_PAGE
    );

    private final EnumSet<CommandType> adminCommands = EnumSet.of(
            DEFAULT,
            ACCEPT_APPLICATION,
            ADD_ADMIN,
            ADD_SUBJECT,
            BLOCK_USER,
            DELETE_ADMIN,
            DELETE_SUBJECT,
            REJECT_APPLICATION,
            SEARCH_USERS,
            UNBLOCK_USER,
            ALL_ADMINS_PAGE,
            ALL_APPLICATIONS_PAGE,
            ALL_SUBJECTS_PAGE,
            APPLICATION_PAGE,
            EDIT_PROFILE_PAGE,
            MAIN_PAGE,
            SEARCH_PAGE,
            SEARCH_USERS_PAGE,
            TUTOR_PROFILE_PAGE,
            ADD_FEEDBACK,
            DELETE_FEEDBACK,
            EDIT_FEEDBACK,
            EDIT_PASSWORD,
            EDIT_PROFILE,
            LOGOUT,
            SEARCH,
            CHANGE_LOCALE,
            ABOUT_US_PAGE);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if (session.getAttribute(SessionAttribute.USER) == null) {
            User user = new User.UserBuilder()
                    .setRole(User.Role.GUEST)
                    .createUser();
            session.setAttribute(SessionAttribute.USER, user);
        }
        String commandName = request.getParameter(RequestParameter.COMMAND);
        CommandType commandType;
        if (commandName != null) {
            try {
                commandType = CommandType.valueOf(commandName.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException e) {
                logger.error("Invalid command name", e);
                commandType = CommandType.DEFAULT;
            }
            if (commandType != CommandType.CHANGE_LOCALE) {
                if (request.getQueryString() != null) {
                    session.setAttribute(SessionAttribute.CURRENT_URL, "/controller?" + request.getQueryString());
                }
            }
        } else {
            commandType = CommandType.DEFAULT;
        }
        User user = (User) session.getAttribute(SessionAttribute.USER);
        if (!checkCommand(user.getRole(), commandType)) {
            response.sendRedirect(request.getContextPath() + PagePath.GO_TO_MAIN_PAGE);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private boolean checkCommand(User.Role role, CommandType commandType) {
        return switch (role) {
            case GUEST -> guestCommands.contains(commandType);
            case USER -> userCommands.contains(commandType);
            case TUTOR -> tutorCommands.contains(commandType);
            case ADMIN -> adminCommands.contains(commandType);
        };
    }
}
