package timer.task;

import java.util.LinkedList;

public class TaskQueue {
	LinkedList<Task> tasks;
	
	public TaskQueue() {
		tasks = new LinkedList<Task>();
	}
	
	public void push(Task task) {
		synchronized (tasks) {
			final boolean needNotify = tasks.isEmpty(); 
			tasks.add(task);
			if (needNotify) {
				tasks.notify();
			}
		}
	}
	
	public Task poll() throws InterruptedException {
		Task task = null;
		synchronized (tasks) {
			while ((task = tasks.poll()) == null) {
				tasks.wait();
			}
		}
		return task;
	}
	
}
