package timer.core;

import java.util.HashMap;
import java.util.Map;

import timer.task.Task;
import timer.task.TaskQueue;
import timer.task.Worker;

public class Engine {
    private Map<Integer, Trigger> triggerMap = new HashMap<Integer, Trigger>();
    TaskQueue taskQueue = new TaskQueue();
    private Worker workers[];
    private TimeCore timeCore;
    
    public Engine(int workerNum) { 
        this(TimeCore.LEAST_TIMER_UNIT, workerNum);
    }
    
    public Engine(int timeUnit, int workerNum) { 
        timeCore = new TimeCore(timeUnit);
        workers = new Worker[workerNum];
    }
    
    public void start() {
        for (int loop = 0; loop < workers.length; ++loop) {
            workers[loop] = new Worker(taskQueue);
            workers[loop].start();
        }
        
        while (true) {
            timeCore.going();
            for (Trigger trigger : triggerMap.values()) {
                trigger.update();
            }
        }
    }
    
    public void register(Task task) {
        int timerLen = task.period();
        Trigger trigger = triggerMap.get(timerLen);
        if (trigger == null) {
            trigger = new Trigger(timeCore.timeToCount(timerLen), taskQueue);
            triggerMap.put(timerLen, trigger);
        }
        trigger.register(task);
    }
    
    public void unRegister(Task task) {
        Trigger trigger = triggerMap.get(task.period());
        if (trigger == null) {
            return;
        }
        trigger.unRegister(task);
        if (!trigger.useful()) {
            triggerMap.remove(task.period());
        }
    }
}
