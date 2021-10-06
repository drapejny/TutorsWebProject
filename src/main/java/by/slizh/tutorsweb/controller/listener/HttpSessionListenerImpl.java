package by.slizh.tutorsweb.controller.listener;

import by.slizh.tutorsweb.controller.command.PagePath;
import by.slizh.tutorsweb.controller.command.SessionAttribute;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.Subject;
import by.slizh.tutorsweb.model.service.SubjectService;
import by.slizh.tutorsweb.model.service.impl.SubjectServiceImpl;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Locale;

import static by.slizh.tutorsweb.controller.command.SessionAttribute.*;

@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        session.setAttribute(LOCALE, Locale.getDefault().toString());
    }
}
