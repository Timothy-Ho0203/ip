package Tasks;

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

    public String getKeyInfo() {
        return name + "," + isDone;
    }
    @Override
    public String toString(){
        return "[" + getStatus() + "] " + this.name;
    }

}
