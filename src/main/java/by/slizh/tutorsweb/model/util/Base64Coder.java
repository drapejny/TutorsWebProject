package by.slizh.tutorsweb.model.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public final class Base64Coder {

    private static final Logger logger = LogManager.getLogger();

    public static String encode(InputStream inputStream) {
        String base64String = "";
        try {
            byte[] bytes = inputStream.readAllBytes();
            base64String = Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            logger.error("Failed to read all bytes from input stream in base64 encoder", e);
        }
        return base64String;
    }

    public static InputStream decode(String base64String) {
        byte[] bytes = Base64.getDecoder().decode(base64String);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        return inputStream;
    }
}
