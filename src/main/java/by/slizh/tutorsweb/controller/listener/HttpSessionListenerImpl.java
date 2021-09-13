package by.slizh.tutorsweb.controller.listener;

import by.slizh.tutorsweb.controller.command.PagePath;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.Locale;

import static by.slizh.tutorsweb.controller.command.SessionAttribute.*;

@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        session.setAttribute(LOCALE, Locale.getDefault().toString());
       // session.setAttribute(CURRENT_PAGE, PagePath.MAIN_PAGE);
    }
}
