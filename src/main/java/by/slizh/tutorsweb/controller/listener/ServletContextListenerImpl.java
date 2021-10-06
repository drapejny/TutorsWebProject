package by.slizh.tutorsweb.controller.listener;

import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.connection.ConnectionPool;
import by.slizh.tutorsweb.model.entity.Subject;
import by.slizh.tutorsweb.model.service.SubjectService;
import by.slizh.tutorsweb.model.service.impl.SubjectServiceImpl;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ConnectionPool.getInstance();
        SubjectService subjectService = SubjectServiceImpl.getInstance();
        final String SUBJECTS = "subjects";
        try {
            List<Subject> subjects = subjectService.findAllSubjects();
            event.getServletContext().setAttribute(SUBJECTS, subjects);
        } catch (ServiceException e) {
            logger.error("Can't find subjects while context initialized", e);
            //// TODO: 20.09.2021 какой exception кидать и стоит ли
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().destroyPool();
    }
}
