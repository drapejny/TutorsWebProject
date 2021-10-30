package by.slizh.tutorsweb.model.util.security;

import org.mindrot.jbcrypt.BCrypt;

/**
 * The type PasswordEncoder.
 */
public final class PasswordEncoder {

    private PasswordEncoder() {
    }

    /**
     * Encode password.
     *
     * @param password the password
     * @return hashed password
     */
    public static String encodePassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Check password.
     *
     * @param pass the pass
     * @param hash the hash
     * @return true if hashed pass equals hash
     */
    public static boolean checkPassword(String pass, String hash){
        return BCrypt.checkpw(pass,hash);
    }
}
