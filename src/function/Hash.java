package function;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Hash {

    static SecureRandom random = new SecureRandom();
    String curentPassword;
    String passwordInput;

    public Hash(String curentPassword, String passwordInput) {
        this.curentPassword = curentPassword;
        this.passwordInput = passwordInput;
    }

    public static String hashPasswords() throws NoSuchAlgorithmException {
        return null;
    }
}
