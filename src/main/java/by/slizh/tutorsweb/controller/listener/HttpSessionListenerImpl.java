package by.slizh.tutorsweb.controller.listener;

import by.slizh.tutorsweb.controller.command.SessionAttribute;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.Locale;

/**
 * The type HttpSessionListener.
 */
@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        session.setAttribute(SessionAttribute.LOCALE, Locale.getDefault().toString());
    }
}
