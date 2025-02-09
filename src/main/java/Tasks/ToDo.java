package Tasks;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns key information (name, completion status) of the task.
     * e.g. (todo,test,false)
     *
     * @return key information of the task presented in csv format
     */
    @Override
    public String getKeyInfo() {
        return "todo," + super.getKeyInfo();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
