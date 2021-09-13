package by.slizh.tutorsweb.controller.command;

import java.util.Locale;
import java.util.ResourceBundle;

public enum MessageManager {
    EN_EN(ResourceBundle.getBundle("prop.message", new Locale("en", "EN"),ResourceBundle.Control.getNoFallbackControl(ResourceBundle.Control.FORMAT_PROPERTIES))),
    RU_RU(ResourceBundle.getBundle("prop.message", new Locale("ru", "RU")));

    private ResourceBundle bundle;

    MessageManager(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public String getMessage(String key) {
        return bundle.getString(key);
    }
}
