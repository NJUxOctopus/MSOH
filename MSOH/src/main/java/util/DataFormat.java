package util;

/**
 * Created by Pxr on 16/11/23.
 */
public class DataFormat {
    public static final String Password_Format = "[0-9A-Za-z_]{6,16}$";
    public static final String Email_Format = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@" +
            "([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
}
