package timer.task;


// TODO try to optimize - silly interface
public interface Task {
    void exec();

    long period();
    
    long bindWorkerId();
}
