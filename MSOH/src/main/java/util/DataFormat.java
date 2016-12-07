package util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Pxr on 16/11/23.
 */
public class DataFormat {
    public static final String Password_Format = "[0-9A-Za-z_]{6,16}$";
    public static final String Email_Format = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@" +
            "([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    public static final String Phone_Format = "[0-9]{11,11}$";
    public static final String ID_Format = "[0-9]{18,18}$";

    public static final long LastestExecutedTime = 1000*60*60*4;//最晚执行时间在预计到达后4小时

    public static String code(String ID) {
        String result = "";
        for (int i = 0; i < ID.length(); i++) {
            char ch = ID.charAt(i);
            result += (char) (ch - 10);
        }
        return result;
    }

    public static String reCode(String ID) {
        String result = "";
        for (int i = 0; i < ID.length(); i++) {
            char c = ID.charAt(i);
            result += (char) (c + 10);
        }
        return result;
    }

}
