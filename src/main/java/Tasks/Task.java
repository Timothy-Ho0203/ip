package Tasks;

public class Task {
    protected String name;
    protected boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsUndone() {
        this.done = false;
    }

    private String getStatus() {
        return (done ? "X" : " ");
    }
    @Override
    public String toString(){
        return "[" + getStatus() + "] " + this.name;
    }

}
