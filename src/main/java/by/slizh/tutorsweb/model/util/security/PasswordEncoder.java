package by.slizh.tutorsweb.model.util.security;

import org.mindrot.jbcrypt.BCrypt;

public final class PasswordEncoder {

    private PasswordEncoder() {
    }

    public static String encodePassword(String password) {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        return hashed;
    }

    public static boolean checkPassword(String pass, String hash){
        return BCrypt.checkpw(pass,hash);
    }
}
