package by.slizh.tutorsweb.command;

import java.util.Locale;
import java.util.ResourceBundle;

public enum MessageManager {
    EN_EN(ResourceBundle.getBundle("prop.message", new Locale("en", "EN"))),
    RU_EU(ResourceBundle.getBundle("prop.message", new Locale("ru", "RU")));

    private ResourceBundle bundle;

    MessageManager(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public String getMessage(String key) {
        return bundle.getString(key);
    }
}
