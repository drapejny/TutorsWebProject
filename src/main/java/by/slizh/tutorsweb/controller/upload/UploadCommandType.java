package by.slizh.tutorsweb.controller.upload;

import by.slizh.tutorsweb.controller.command.Command;
import by.slizh.tutorsweb.controller.upload.impl.DefaultUploadCommand;
import by.slizh.tutorsweb.controller.upload.impl.UploadTutorPhotoCommand;
import by.slizh.tutorsweb.controller.upload.impl.UploadUserPhotoCommand;

/**
 * The enum UploadCommandType provides {@link UploadCommand} instance.
 */
public enum UploadCommandType {
    DEFAULT(new DefaultUploadCommand()),
    UPLOAD_USER_PHOTO(new UploadUserPhotoCommand()),
    UPLOAD_TUTOR_PHOTO(new UploadTutorPhotoCommand());

    private UploadCommand command;

    UploadCommandType(UploadCommand command) {
        this.command = command;
    }

    public UploadCommand getCommand() {
        return command;
    }
}
