package timer.task;

public class TaskProcessor implements Runnable {
	
	TaskQueue taskQueue;
	
	public TaskProcessor(TaskQueue taskQueue) {
		this.taskQueue = taskQueue;
	}
	
	public void startWithNewThread() {
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
}
