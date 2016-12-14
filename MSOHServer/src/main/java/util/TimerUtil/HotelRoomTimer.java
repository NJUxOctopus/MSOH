package util.TimerUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * 自动更新房间信息的工具类
 * <p>
 * Created by zqh on 2016/12/14.
 */
public class HotelRoomTimer {
    Timer timer;

    public HotelRoomTimer() {
        Date time = getTime();
        timer = new Timer();
        timer.schedule(new HotelRoomTimerTask(), time);
    }

    /**
     * 每早八点自动更新房间信息
     *
     * @return
     */
    private Date getTime() {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);

        Date time = calendar.getTime();

        return time;
    }
}
