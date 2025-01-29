package Tasks;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String getKeyInfo() {
        return "todo," + super.getKeyInfo();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
