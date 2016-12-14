package util.TimerUtil;

import init.DailyUpdater.DailyOrderUpdater;

import java.util.TimerTask;

/**
 * Created by zqh on 2016/12/14.
 */
public class OrderTimerTask extends TimerTask {
    /**
     * The action to be performed by this timer task.
     */
    public void run() {
        new DailyOrderUpdater();
    }
}
