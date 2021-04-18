package timer.core;

import java.util.HashMap;
import java.util.Map;

import timer.task.Task;

public class Engine {
    private static int DEFAULT_TIMER_UNIT = 1;
	
    private static Engine engine = new Engine();; 
    private Map<Integer, Timer> timers = new HashMap<Integer, Timer>();
    
    private Engine() {
        timers.clear();
    }
    
    public static Engine instance() {
        if(engine == null) {
            engine = new Engine();
        }
        return engine;
    }
    
    private void timeGoing() {
        for(Timer timer : timers.values()) {
            timer.timeGoing();
        }
    }
    
    public void start() {
        new CoreDriver(DEFAULT_TIMER_UNIT).drive();
    }
    
    public void register(Task task) {
        int timerLen = task.getExecutPeriod();
        Timer timer = timers.get(timerLen);
        if(timer == null) {
            timer = new Timer(timerLen);
            timers.put(timerLen, timer);
        }
        timer.register(task);
    }
    
    public void unRegister(Task task) {
        Timer timer = timers.get(task.getExecutPeriod());
        if(timer == null) {
            return;
        }
        timer.unRegister(task);
        if(timer.useful()) {
            timers.remove(task.getExecutPeriod());
        }
    }
    
    class CoreDriver {
    	private int timerUnit;
        
    	CoreDriver(int timerUnit) {
    		this.timerUnit = timerUnit;
    	}
    	
        public void drive() {
            while(true) {
                try {
                    Thread.sleep(timerUnit);
                    timeGoing();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    static int timeLenToTimeUnitTimes(int timerLen) {
        return timerLen / DEFAULT_TIMER_UNIT;
    }
}
