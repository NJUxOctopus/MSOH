package util;

import java.sql.Timestamp;

/**
 * Created by Pxr on 16/12/7.
 */
public class CalculateDays {
    Timestamp checkInDate;
    Timestamp checkOutDate;

    public CalculateDays(Timestamp checkInDate, Timestamp checkOutDate) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public long calculate() {
        long oneDay = 1000 * 60 * 60 * 24;
        long days = (checkInDate.getTime() - checkOutDate.getTime()) / oneDay;//算共住多少天
        return days;
    }
}
