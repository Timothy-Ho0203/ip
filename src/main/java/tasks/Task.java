package tasks;

import exceptions.InvalidArgumentException;

/**
 * Parent class to handle functionalities of tasks.
 */
public class Task implements Comparable<Task> {
    protected String name;
    protected boolean isDone;
    protected int priority;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
        this.priority = Integer.MAX_VALUE;
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

    public void setPriority(int i) {
        if (i <= 0) {
            throw new InvalidArgumentException("Please ensure you use a positive number");
        }
        this.priority = i;
    }
    @Override
    public String toString(){
        return "[" + getStatus() + "] " + this.name;
    }

    /**
     * Allows for task objects to be compared to each other according to
     * their priority. Ties will be broken by the lexicographically larger
     * task name
     *
     * @param task the object to be compared.
     * @return -1 if this < task, 0 if this.equals(task), 1 if this > task
     */
    @Override
    public int compareTo(Task task) {
        if (this.priority < task.priority) {
            return -1;
        } else if (this.priority == task.priority){
            return this.name.compareTo(task.name);
        } else {
            return 1;
        }
    }
}
