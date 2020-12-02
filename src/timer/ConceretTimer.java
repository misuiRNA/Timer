package timer;

import java.util.ArrayList;
import java.util.List;
import timer.listener.TimerListener;
import static timer.driver.TimerDriver.*;

public class ConceretTimer {
    private int MAX_TIME_FOR_FIVE_MS;
    private int times;
    private List<TimerListener> listeners = new ArrayList<TimerListener>();

    public ConceretTimer(int timerLength) {
        this.MAX_TIME_FOR_FIVE_MS = timerLenToTimes(timerLength);
        this.times = MAX_TIME_FOR_FIVE_MS;
        listeners.clear();
    }

    public void drive() {
        --times;
        if(times <= 0) {
            timeOut();
            times = MAX_TIME_FOR_FIVE_MS;
        }
    }

    private void timeOut() {
        for(TimerListener lis : listeners) {
            lis.timeOut();
        }
    }

    public void register(TimerListener listener) {
        listeners.add(listener);
    }

    public void unRegister(TimerListener listener) {
        listeners.remove(listener);
    }

    public boolean isEmpty() {
        return listeners.isEmpty();
    }
}
