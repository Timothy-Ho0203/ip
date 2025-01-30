package Tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;
    public Event(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns key information (name, completion status, start date, end date) of the task.
     * Date is taken in DD MM YYYY and time in 24-hour format.
     * e.g. (event,test,false,01 01 2025 12:00, 01 01 2025 15:00)
     * @return key information of the task presented in csv format
     */
    @Override
    public String getKeyInfo() {
        DateTimeFormatter formatEvent = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
        String formattedStart = start.format(formatEvent);
        String formattedEnd = end.format(formatEvent);
        return "event," + super.getKeyInfo() + "," + formattedStart + "," + formattedEnd;
    }


    @Override
    public String toString() {
        DateTimeFormatter formatEvent = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
        String formattedStart = start.format(formatEvent);
        String formattedEnd = end.format(formatEvent);
        return "[E]" + super.toString() + "(from: " + formattedStart + " to: " + formattedEnd + ")";
    }
}
