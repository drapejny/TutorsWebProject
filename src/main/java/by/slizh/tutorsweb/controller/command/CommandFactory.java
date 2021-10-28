package by.slizh.tutorsweb.controller.command;

import jakarta.servlet.http.HttpServletRequest;

/**
 * The CommandFactory creates command.
 */
public class CommandFactory {

    private static CommandFactory instance = new CommandFactory();

    private CommandFactory() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static CommandFactory getInstance() {
        return instance;
    }

    /**
     * Create command instance by.
     *
     * @param request the {@link HttpServletRequest} request
     * @return the command
     */
    public Command createCommand(HttpServletRequest request) {
        String commandName = request.getParameter(RequestParameter.COMMAND);
        Command command;
        if (commandName != null) {
            try {
                CommandType commandType = CommandType.valueOf(commandName.toUpperCase());
                command = commandType.getCommand();
            } catch (IllegalArgumentException e) {
                command = CommandType.DEFAULT.getCommand();
            }
        } else {
            command = CommandType.DEFAULT.getCommand();
        }
        return command;
    }
}
