package test;

import timer.task.Task;

public class TimerFactory {
    
    public static Task newTimerListenerAsOneSec() {
        return new Task() {
            @Override
            public void exec() {
                System.out.println("[Timer] ======== 1s");
            }

            @Override
            public int period() {
                return 1000;
            }
        };
    }
    
    public static Task newTimerListenerAsTowSec() {
        return new Task() {
            @Override
            public void exec() {
                System.out.println("[Timer] ======== 2s");
            }

            @Override
            public int period() {
                return 2000;
            }
        };
    }

    public static Task newTimerListenerAsFiveSec() {
        return new Task() {
            @Override
            public void exec() {
                System.out.println("[Timer] ======== 5s");
            }

            @Override
            public int period() {
                return 5000;
            }
        };
    }
}
