package timer.core;

public class TimeCore {
    final static int LEAST_TIMER_UNIT = 1;
    
    private final int timerUnit;
    
    public TimeCore(int timerUnit) {
        this.timerUnit = timerUnit < LEAST_TIMER_UNIT ? LEAST_TIMER_UNIT : timerUnit;
    }
    
    public TimeCore() {
        this(LEAST_TIMER_UNIT);
    }
    
    public void going() {
        try {
            Thread.sleep(timerUnit);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int timeToCount(int timeLen) {
        if (timeLen % timerUnit != 0) {
            throw new RuntimeException("timer length is invalid !");
        }
        return timeLen / timerUnit;
    }
}