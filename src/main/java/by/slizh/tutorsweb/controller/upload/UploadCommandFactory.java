package by.slizh.tutorsweb.controller.upload;

import by.slizh.tutorsweb.controller.command.RequestParameter;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Locale;

/**
 * The UploadCommandFactory creates {@link UploadCommand}.
 */
public class UploadCommandFactory {

    private static UploadCommandFactory instance = new UploadCommandFactory();

    private UploadCommandFactory() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static UploadCommandFactory getInstance() {
        return instance;
    }

    /**
     * Create upload command.
     *
     * @param request the request
     * @return the upload command
     */
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
