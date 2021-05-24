package test;

import timer.task.AbstractTask;
import timer.task.Task;

public class TimerFactory {
    
    private static void threadBlock() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    
    public static Task newTimerListenerAsOneSec(long workerId) {
        return new AbstractTask(workerId) {
            @Override
            public void exec() {
                System.out.println("[Timer][Worker-Id=" + Thread.currentThread().getId() + "] ======== 1s");
                threadBlock();
            }

            @Override
            public long period() {
                return 1000;
            }
        };
    }
    
    public static Task newTimerListenerAsTowSec() {
        return new AbstractTask() {
            @Override
            public void exec() {
                threadBlock();
                System.out.println("[Timer][Worker-Id=" + Thread.currentThread().getId() + "] ======== 2s");
            }

            @Override
            public long period() {
                return 2000;
            }
        };
    }

    public static Task newTimerListenerAsFiveSec() {
        return new AbstractTask() {
            @Override
            public void exec() {
                threadBlock();
                System.out.println("[Timer][Worker-Id=" + Thread.currentThread().getId() + "]  ======== 5s");
            }

            @Override
            public long period() {
                return 5000;
            }
        };
    }
}
