package Tasks;

/**
 * Parent class to handle functionalities of tasks.
 */
public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    private String getStatus() {
        return (isDone ? "X" : " ");
    }

    public String getName() {
        return this.name;
    }

    /**
     * Returns key information (name, completion status) of the task.
     * e.g. (test,false)
     *
     * @return key information of the task presented in csv format
     */
    public String getKeyInfo() {
        return name + "," + isDone;
    }
    @Override
    public String toString(){
        return "[" + getStatus() + "] " + this.name;
    }

}
