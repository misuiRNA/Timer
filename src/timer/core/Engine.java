package timer.core;

import java.util.HashMap;
import java.util.Map;

import timer.task.Task;
import timer.task.TaskQueue;
import timer.task.Worker;

public class Engine {
    private Map<Integer, Trigger> triggerMap = new HashMap<Integer, Trigger>();
    private Worker worker = new Worker(new TaskQueue());;
    private TimeCore timeCore;
    
    public Engine() { 
        timeCore = new TimeCore();
    }
    
    public Engine(int timeUnit) { 
        timeCore = new TimeCore(timeUnit);
    }
    
    public void start() {
        worker.start();
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
            trigger = new Trigger(timeCore.timeToCount(timerLen), worker.getTaskQueue());
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
