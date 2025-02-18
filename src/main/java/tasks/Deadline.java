package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate date;
    public Deadline(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    /**
     * Returns key information (name, completion status, end date) of the task.
     * Date is taken in DD MM YYYY format.
     * e.g. (deadline,test,false,01 01 2025)
     *
     * @return key information of the task presented in csv format
     */
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
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}
