package test;

import static test.TimerFactory.*;

import timer.core.Engine;

public class TimerTest {
    
    
    public static void main(String args[]) {
        Engine clock = new Engine(3);
        
        clock.register(newTimerListenerAsOneSec(0));
        clock.register(newTimerListenerAsTowSec());
        clock.register(newTimerListenerAsFiveSec());
        
        clock.start();
    }
    
}
