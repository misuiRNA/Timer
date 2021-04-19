package timer.task;

public class Worker implements Runnable {
	
	TaskQueue taskQueue;
	
	public Worker(TaskQueue taskQueue) {
		this.taskQueue = taskQueue;
	}
	
	public void start() {
		new Thread(this).start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				taskQueue.poll().exec();;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public TaskQueue getTaskQueue() {
		return taskQueue;
	}
}
