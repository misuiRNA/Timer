package test;

import timer.listener.TimerListener;

public class TimerFactory {
    
    public static TimerListener newTimerListenerAsOneSec() {
        return new TimerListener() {
            @Override
            public void timeOut() {
                System.out.println("[Timer] ======== 1s");
            }

            @Override
            public int getTimerLen() {
                return 1000;
            }
        };
    }
    
    public static TimerListener newTimerListenerAsTowSec() {
        return new TimerListener() {
            @Override
            public void timeOut() {
                System.out.println("[Timer] ======== 2s");
            }

            @Override
            public int getTimerLen() {
                return 2000;
            }
        };
    }

    public static TimerListener newTimerListenerAsFiveSec() {
        return new TimerListener() {
            @Override
            public void timeOut() {
                System.out.println("[Timer] ======== 5s");
            }

            @Override
            public int getTimerLen() {
                return 5000;
            }
        };
    }
}
