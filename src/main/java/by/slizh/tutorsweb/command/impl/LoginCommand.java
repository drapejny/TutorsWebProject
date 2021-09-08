package by.slizh.tutorsweb.command.impl;

import by.slizh.tutorsweb.command.*;
import by.slizh.tutorsweb.entity.User;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.service.UserService;
import by.slizh.tutorsweb.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class LoginCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        System.out.println(1);
        HttpSession session = request.getSession();
        Router router = null;
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        UserService userService = UserServiceImpl.getInstance();
        try {
            Optional<User> user = userService.authenticate(email, password);
            if(user.isPresent()){
                System.out.println(2);
                session.setAttribute(SessionAttribute.USER,user.get());
                session.setAttribute(SessionAttribute.ROLE,user.get().getRole());
                router = new Router(PagePath.MAIN_PAGE, Router.RouteType.REDIRECT);
            } else{
                System.out.println(3);
                request.setAttribute("errorLogInMessage",MessageManager.RU_EU.getMessage("message.loginerror"));
                router = new Router(PagePath.LOGIN_PAGE, Router.RouteType.FORWARD);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return router;
    }
}
