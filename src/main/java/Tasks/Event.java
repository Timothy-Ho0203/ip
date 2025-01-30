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
