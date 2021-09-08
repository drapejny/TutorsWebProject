package by.slizh.tutorsweb.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncoder {

    private PasswordEncoder() {
    }

    public static String encodePassword(String password) {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(hashed);
        return hashed;
    }

    public static boolean checkPassword(String pass, String hash){
        return BCrypt.checkpw(pass,hash);
    }
}
