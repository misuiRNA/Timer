package timer.task;

import java.util.LinkedList;

import timer.utils.TimerLock;

public class TaskQueue {
    private static TimerLock global_lock = new TimerLock();
    
    LinkedList<Task> tasks;
    TimerLock lock;
    
    public TaskQueue() {
        tasks = new LinkedList<Task>();
        lock = global_lock;
    }
    
    public void push(Task task) {
        synchronized (lock) {
            tasks.add(task);
            lock.increase();
            // TODO try to optimize, do not notify every time
            lock.notify();
        }
    }
    
    // TODO checkout
    public Task poll() throws InterruptedException {
        Task task = null;
        synchronized (lock) {
            if (lock.needLock()) {
                lock.wait();
            }
            task = tasks.poll();
            if (null != task) {
                lock.decrease();
            }
        }
        return task;
    }

   // TODO try to optimize - not thread safety 
    public boolean hasMore() {
        return tasks.size() > 0;
    }
}

