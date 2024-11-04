package javachi.biz.marketplaseservlet.utils;

import java.util.regex.Pattern;

public class StringUtils {
    public static final Pattern validationEmailPattern = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    public static boolean isEmailValid(String email) {
        return validationEmailPattern.matcher(email).matches();
    }
}
