package by.slizh.tutorsweb.controller.listener;

import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ServletRequestAttributeListenerImpl implements ServletRequestAttributeListener {
    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
    }
}
