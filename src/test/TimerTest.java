package test;

import static test.TimerFactory.*;

import timer.core.Engine;

public class TimerTest {
    
    
    public static void main(String args[]) {
        Engine clock = new Engine();
        
        clock.register(newTimerListenerAsOneSec());
        clock.register(newTimerListenerAsTowSec());
        clock.register(newTimerListenerAsFiveSec());
        
        clock.start();
    }
    
}
