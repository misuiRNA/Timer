package timer.task;

import java.util.ArrayList;
import java.util.List;

public class Worker implements Runnable {
    
    private static long workerSegNumber = 0;
    
    List<TaskQueue> priorTaskQueues;
    TaskQueue commQueue;
    private long id;
    
    public Worker(TaskQueue taskQueue) {
        priorTaskQueues = new ArrayList<TaskQueue>(8);
        commQueue = taskQueue;
        id = workerSegNumber;
        ++workerSegNumber;
    }
    
    public void start() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                // TODO try to optimize
                Task task = takeTask();
                if (null == task && commQueue.hasMore()) {
                    task = commQueue.poll();
                }
                if (null != task) {
                    task.exec();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private Task takeTask() throws InterruptedException {
        for (TaskQueue queue : priorTaskQueues) {
            if (queue.hasMore()) {
                return queue.poll();
            }
        }
        return null;
    }
    
    public long getId() {
        return id;
    }

    public void takeOver(TaskQueue taskQueue) {
        priorTaskQueues.add(taskQueue);
    }
}
