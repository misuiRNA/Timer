package timer.core;

import static timer.core.Engine.timeLenToTimeUnitTimes;

import java.util.ArrayList;
import java.util.List;

import timer.task.Task;

public final class Timer {
    private int MAX_TIMES_OF_TIME_UNIT;
    private int timeCounter;
    private List<Task> listeners = new ArrayList<Task>();

    public Timer(int timerLength) {
        this.MAX_TIMES_OF_TIME_UNIT = timeLenToTimeUnitTimes(timerLength);
        this.timeCounter = MAX_TIMES_OF_TIME_UNIT;
        listeners.clear();
    }

    void timeGoing() {
        --timeCounter;
        if(timeCounter <= 0) {
            timeOut();
            timeCounter = MAX_TIMES_OF_TIME_UNIT;
        }
    }

    private void timeOut() {
        for(Task lis : listeners) {
            lis.exec();
        }
    }

    public void register(Task listener) {
        listeners.add(listener);
    }

    public void unRegister(Task listener) {
        listeners.remove(listener);
    }

    public boolean useful() {
        return listeners.isEmpty();
    }
}
