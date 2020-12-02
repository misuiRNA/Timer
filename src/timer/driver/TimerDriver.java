package timer.driver;

import timer.Timer;

public class TimerDriver {
    
    private static int TIMER_UNIT = 5;
    
    public void drive(Timer timer) {
        while(true) {
            try {
                Thread.sleep(TIMER_UNIT);
                timer.drive();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    public static int timerLenToTimes(int timerLen) {
        return timerLen / TIMER_UNIT;
    }
    
}
