package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate date;
    public Deadline(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    @Override
    public String getKeyInfo() {
        DateTimeFormatter formatDeadline = DateTimeFormatter.ofPattern("dd MM yyyy");
        String formattedDate = date.format(formatDeadline);
        return "deadline," + super.getKeyInfo() + "," + formattedDate;
    }
    @Override
    public String toString() {
        DateTimeFormatter formatDeadline = DateTimeFormatter.ofPattern("dd MM yyyy");
        String formattedDate = date.format(formatDeadline);
        return "[D]" + super.toString() + "(by: " + formattedDate + ")";
    }
}
