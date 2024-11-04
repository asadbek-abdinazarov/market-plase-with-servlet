package javachi.biz.marketplaseservlet.utils;

import jakarta.validation.constraints.NotNull;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {
    //1) 1234 - > sdfsdnfs%^&dlnsldngs53u4985^&*""DSFdlkgn
    //2) 1234 - > dsfsldfslfnsjvskjdnvsk

    public static String encode(@NotNull String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(@NotNull String password, @NotNull String encodedPassword) {
        return BCrypt.checkpw(password, encodedPassword);
    }
}

