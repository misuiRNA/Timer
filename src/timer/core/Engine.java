package timer.core;

import java.util.HashMap;
import java.util.Map;

import timer.task.Task;
import timer.task.Worker;
import timer.task.TaskQueue;

public class Engine {
    private static Engine engine; 
    private Map<Integer, Trigger> triggerMap = new HashMap<Integer, Trigger>();
    private TimeCore timeCore = new TimeCore();
    private Worker worker = new Worker(new TaskQueue());;
    
    public static Engine instance() {
        if (engine == null) {
            engine = new Engine();
        }
        return engine;
    }
    
    private Engine() { }
    
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
