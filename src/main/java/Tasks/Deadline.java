package Tasks;

public class Deadline extends Task {
    protected String date;
    public Deadline(String name, String date) {
        super(name);
        this.date = date;
    }

    @Override
    public String getKeyInfo() {
        return "deadline," + super.getKeyInfo() + "," + date;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + date + ")";
    }
}
