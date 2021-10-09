package by.slizh.tutorsweb.controller.command.impl;

import by.slizh.tutorsweb.controller.command.*;
import by.slizh.tutorsweb.exception.CommandException;
import by.slizh.tutorsweb.exception.ServiceException;
import by.slizh.tutorsweb.model.entity.Subject;
import by.slizh.tutorsweb.model.entity.Tutor;
import by.slizh.tutorsweb.model.service.SubjectService;
import by.slizh.tutorsweb.model.service.TutorService;
import by.slizh.tutorsweb.model.service.impl.SubjectServiceImpl;
import by.slizh.tutorsweb.model.service.impl.TutorServiceImpl;
import by.slizh.tutorsweb.model.validator.TutorValidator;
import by.slizh.tutorsweb.model.validator.UserValidator;
import by.slizh.tutorsweb.model.validator.impl.TutorValidatorImpl;
import by.slizh.tutorsweb.model.validator.impl.UserValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static by.slizh.tutorsweb.controller.command.RequestAttribute.ERROR_WRONG_DATA;
import static by.slizh.tutorsweb.controller.command.RequestAttribute.SUCCESSFUL_EDIT_DATA;
import static by.slizh.tutorsweb.controller.command.RequestParameter.*;

public class EditTutorProfileCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute(SessionAttribute.LOCALE);

        TutorValidator tutorValidator = TutorValidatorImpl.getInstance();
        UserValidator userValidator = UserValidatorImpl.getInstance();

        TutorService tutorService = TutorServiceImpl.getInstance();
        SubjectService subjectService = SubjectServiceImpl.getInstance();

        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        String phone = request.getParameter(PHONE);
        String info = request.getParameter(INFORMATION);
        String price = request.getParameter(PRICE);

        boolean isActive = false;
        if (request.getParameter(IS_ACTIVE) != null) {
            isActive = true;
        }

        String[] subjectIds = request.getParameterValues(SUBJECT);

        if (userValidator.validateFirstName(firstName) && userValidator.validateLastName(lastName)
                && tutorValidator.validatePhone(phone) && tutorValidator.validateInfo(info)
                && tutorValidator.validatePrice(price) && subjectIds != null) {
            Tutor tutor = (Tutor) session.getAttribute(SessionAttribute.USER);
            tutor.setFirstName(firstName);
            tutor.setLastName(lastName);
            tutor.setPhone(phone);
            tutor.setInfo(info);
            tutor.setIsActive(isActive);
            tutor.setPricePerHour(Integer.parseInt(price));
            List<Integer> newSubjects = Arrays.stream(subjectIds)
                    .map(s -> Integer.parseInt(s))
                    .collect(Collectors.toList());
            List<Integer> oldSubjects = ((List<Subject>) session.getAttribute(RequestAttribute.SUBJECTS)).stream()
                    .map(s -> s.getSubjectId())
                    .collect(Collectors.toList());
            try {
                tutorService.updateTutor(tutor);
                subjectService.editTutorSubjects(tutor.getTutorId(), oldSubjects, newSubjects);
                List<Subject> subjects = subjectService.findSubjectsByTutorId(tutor.getTutorId());
                session.setAttribute(SessionAttribute.SUBJECTS, subjects);
                request.setAttribute(RequestAttribute.SUCCESSFUL_EDIT_DATA, MessageManager.valueOf(locale.toUpperCase(Locale.ROOT)).getMessage(SUCCESSFUL_EDIT_DATA));
            } catch (ServiceException e) {
                logger.error("Executing editTutorProfile command error", e);
                throw new CommandException("Executing editTutorProfile command error", e);
            }
        } else {
            request.setAttribute(RequestAttribute.ERROR_WRONG_DATA, MessageManager.valueOf(locale.toUpperCase(Locale.ROOT)).getMessage(ERROR_WRONG_DATA));
        }
        return new Router(PagePath.EDIT_TUTOR_PROFILE_PAGE, Router.RouteType.FORWARD);
    }
}
