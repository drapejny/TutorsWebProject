package by.slizh.tutorsweb.listener;

import by.slizh.tutorsweb.command.PagePath;
import by.slizh.tutorsweb.command.SessionAttribute;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.Locale;

import static by.slizh.tutorsweb.command.SessionAttribute.*;

@WebListener
public class SessionListenerImpl implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        System.out.println("sessionCreated");
        HttpSession session = event.getSession();
        session.setAttribute(LOCALE, Locale.getDefault());
        session.setAttribute(CURRENT_PAGE, PagePath.MAIN_PAGE);
    }
}
