package by.slizh.tutorsweb.controller.command.impl.admin;

import by.slizh.tutorsweb.controller.command.Command;
import by.slizh.tutorsweb.controller.command.RequestParameter;
import by.slizh.tutorsweb.controller.command.Router;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.model.service.SubjectService;
import by.slizh.tutorsweb.model.service.impl.SubjectServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class DeleteSubjectCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        SubjectService subjectService = SubjectServiceImpl.getInstance();
        int subjectId = Integer.parseInt(request.getParameter(RequestParameter.SUBJECT_ID));

    }
}
