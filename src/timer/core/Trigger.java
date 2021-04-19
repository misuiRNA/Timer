package timer.core;


import java.util.ArrayList;
import java.util.List;

import timer.task.Task;
import timer.task.TaskQueue;

public final class Trigger {
    private int initCount;
    private int counter;
    private final List<Task> tasks = new ArrayList<Task>();
    private final TaskQueue taskQueue;

    public Trigger(int maxCount, TaskQueue taskQueue) {
        this.initCount = maxCount;
        this.counter = maxCount;
        tasks.clear();
        this.taskQueue = taskQueue;
    }

    void update() {
        --counter;
        if(counter <= 0) {
            trigger();
            counter = initCount;
        }
    }

    private void trigger() {
        for(Task task : tasks) {
        	taskQueue.push(task);
        }
    }

    public void register(Task task) {
        tasks.add(task);
    }

    public void unRegister(Task task) {
        tasks.remove(task);
    }

    public boolean useful() {
        return !tasks.isEmpty();
    }
}
