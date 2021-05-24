package timer.task;

public abstract class AbstractTask implements Task {

    public final static int DEFAULT_WORKER_ID = -1;
    
    private long bindWorkerId;
    
    public AbstractTask() {
        this(DEFAULT_WORKER_ID);
    }
    
    public AbstractTask(long workerId) {
        bindWorkerId = workerId;
    }
    
    @Override
    public long bindWorkerId() {
        return bindWorkerId;
    }

}
