package test;

import timer.Timer;
import static test.TimerFactory.*;

public class TimerTest {
    
    
    public static void main(String args[]) {
        Timer clock = Timer.instance();
        
        clock.register(newTimerListenerAsOneSec());
        clock.register(newTimerListenerAsTowSec());
        clock.register(newTimerListenerAsFiveSec());
        
        clock.start();
    }
    
}
