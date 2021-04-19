package timer.core;

public class TimeCore {
	private final static int DEFAULT_TIMER_UNIT = 1;
	
	private final int timerUnit;
    
	public TimeCore(int timerUnit) {
		this.timerUnit = timerUnit < DEFAULT_TIMER_UNIT ? DEFAULT_TIMER_UNIT : timerUnit;
	}
	
	public TimeCore() {
		this(DEFAULT_TIMER_UNIT);
	}
	
	public void going() {
		try {
			Thread.sleep(timerUnit);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int timeToCount(int timeLen) {
//		if (timeLen % timerUnit != 0) {
//			throw new Exception("timer length is invalid !");
//		}
		return timeLen / timerUnit;
	}
}