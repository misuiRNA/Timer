package timer;

import java.util.HashMap;
import java.util.Map;

import timer.driver.TimerDriver;
import timer.listener.TimerListener;

public class Timer {
    private static Timer clock = new Timer();; 
    private Map<Integer, ConceretTimer> alarmClocks = new HashMap<Integer, ConceretTimer>();
    
    private Timer() {
        alarmClocks.clear();
    }
    
    public static Timer instance() {
        if(clock == null) {
            clock = new Timer();
        }
        return clock;
    }
    
    public void drive() {
        for(ConceretTimer clock : alarmClocks.values()) {
            clock.drive();
        }
    }
    
    public void start() {
        new TimerDriver().drive(this);
    }
    
    public void register(TimerListener listener) {
        int timerLen = listener.getTimerLen();
        ConceretTimer alarm = alarmClocks.get(timerLen);
        if(alarm == null) {
            alarm = new ConceretTimer(timerLen);
            alarmClocks.put(timerLen, alarm);
        }
        alarm.register(listener);
    }
    
    public void unRegister(TimerListener listener) {
        ConceretTimer alarm = alarmClocks.get(listener.getTimerLen());
        if(alarm == null) {
            return;
        }
        alarm.unRegister(listener);
        if(alarm.isEmpty()) {
            alarmClocks.remove(listener.getTimerLen());
        }
    }
}
