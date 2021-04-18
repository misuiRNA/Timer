package test;

import static test.TimerFactory.*;

import timer.core.Engine;

public class TimerTest {
    
    
    public static void main(String args[]) {
        Engine clock = Engine.instance();
        
        clock.register(newTimerListenerAsOneSec());
        clock.register(newTimerListenerAsTowSec());
        clock.register(newTimerListenerAsFiveSec());
        
        clock.start();
    }
    
}
