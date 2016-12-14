package util.TimerUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * 自动更新订单状态的工具类
 * <p>
 * Created by zqh on 2016/12/14.
 */
public class OrderTimer {
    Timer timer;

    public OrderTimer() {
        Date time = getTime();
        timer = new Timer();
        timer.schedule(new OrderTimerTask(), time);

    }

    /**
     * 每晚八点自动更新异常订单
     *
     * @return 更新时间
     */
    public Date getTime() {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);

        Date time = calendar.getTime();

        return time;
    }
}
