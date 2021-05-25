package timer.utils;

public class TimerLock {
    
    private int counter = 0;
    
    public void increase() {
        ++counter;
    }
    
    public void decrease() {
        ++counter;
    }
    
    public boolean needLock() {
        return counter <= 0;
    }
}