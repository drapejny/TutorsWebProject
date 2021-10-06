package by.slizh.tutorsweb.controller.upload;

import by.slizh.tutorsweb.controller.command.Command;
import by.slizh.tutorsweb.controller.command.CommandFactory;
import by.slizh.tutorsweb.controller.command.CommandType;
import by.slizh.tutorsweb.controller.command.RequestParameter;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Locale;

public class UploadCommandFactory {

    private static UploadCommandFactory instance = new UploadCommandFactory();

    private UploadCommandFactory() {
    }

    public static UploadCommandFactory getInstance() {
        return instance;
    }

    public UploadCommand createCommand(HttpServletRequest request) {
        String commandName = request.getParameter(RequestParameter.COMMAND);
        UploadCommand command;
        if (commandName == null) {
            return UploadCommandType.DEFAULT.getCommand();
        } else {
            try {
                UploadCommandType commandType = UploadCommandType.valueOf(commandName.toUpperCase(Locale.ROOT));
                command = commandType.getCommand();
            } catch (IllegalArgumentException e) {
                command = UploadCommandType.DEFAULT.getCommand();
            }
        }
        return command;
    }
}
