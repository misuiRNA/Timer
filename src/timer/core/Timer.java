package timer.core;

import static timer.core.Engine.timeLenToTimeUnitTimes;

import java.util.ArrayList;
import java.util.List;

import timer.task.Task;
import timer.task.TaskQueue;

public final class Timer {
    private int MAX_TIMES_OF_TIME_UNIT;
    private int timeCounter;
    private List<Task> tasks = new ArrayList<Task>();
    private TaskQueue taskQueue;

    public Timer(int timerLength, TaskQueue taskQueue) {
        this.MAX_TIMES_OF_TIME_UNIT = timeLenToTimeUnitTimes(timerLength);
        this.timeCounter = MAX_TIMES_OF_TIME_UNIT;
        tasks.clear();
        this.taskQueue = taskQueue;
    }

    void timeGoing() {
        --timeCounter;
        if(timeCounter <= 0) {
            timeOut();
            timeCounter = MAX_TIMES_OF_TIME_UNIT;
        }
    }

    private void timeOut() {
        for(Task task : tasks) {
        	taskQueue.push(task);
        }
    }

    public void register(Task listener) {
        tasks.add(listener);
    }

    public void unRegister(Task listener) {
        tasks.remove(listener);
    }

    public boolean useful() {
        return tasks.isEmpty();
    }
}
