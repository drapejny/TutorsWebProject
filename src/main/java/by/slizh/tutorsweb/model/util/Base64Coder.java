package by.slizh.tutorsweb.model.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * The type Base 64 coder.
 */
public final class Base64Coder {

    private static final Logger logger = LogManager.getLogger();

    /**
     * Encode input stream to Base64 string.
     *
     * @param inputStream the input stream
     * @return encoded Base64 string
     */
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

    /**
     * Decode Base64 string to input stream.
     *
     * @param base64String the Base64 string
     * @return the decoded input stream
     */
    public static InputStream decode(String base64String) {
        byte[] bytes = Base64.getDecoder().decode(base64String);
        return new ByteArrayInputStream(bytes);
    }
}
