package Commands;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import Exceptions.InvalidArgumentException;
import Exceptions.InvalidFormatException;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import TommyTalks.Storage;
import TommyTalks.Ui;

/**
 * Commands that create tasks for the user
 */
public class TaskCommand extends Command {
    protected String task;

    public TaskCommand(String task) {
        this.task = task;
    }

    /**
     * Execute the command according to their functionalities.
     *
     * @param taskList Storage that holds all tasks currently.
     * @param ui UI to manage printing of messages.
     * @throws InvalidArgumentException If commands are not given appropriate arguments.
     * @throws InvalidFormatException If date and time are not given in
     *     DD MM YYYY or 24-hour format.
     */
    @Override
    public String execute(Storage taskList, Ui ui) {
        Task curr = null;
        String type = task.split(" ", 2)[0].toLowerCase();
        switch(type) {
        case "todo":
            try {
                String name = task.substring(5);
                if (name.isBlank()) {
                    throw new InvalidArgumentException("    Please put in a task at least");
                }
                curr = new ToDo(name.trim());
            } catch (StringIndexOutOfBoundsException e) {
                throw new InvalidArgumentException("    Please put in a task at least");
            }
            break;
        case "deadline":
            try {
                String[] arr = task.split("/");
                String name = arr[0].substring(9);
                String date = arr[1].substring(3);
                if (name.isBlank() || date.isBlank()) {
                    throw new InvalidArgumentException("    Please don't leave any blanks");
                }

                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MM yyyy");
                LocalDate dueDate = LocalDate.parse(date.trim(), format);
                curr = new Deadline(name.trim(), dueDate);
            } catch (StringIndexOutOfBoundsException e) {
                throw new InvalidArgumentException("    Please give some arguments");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidArgumentException("    Please put ur task in the right format");
            } catch (DateTimeParseException e) {
                throw new InvalidFormatException("    Please put your date as DD MM YYYY");
            }
            break;
        case "event":
            try {
                String[] arr = task.split("/");
                String name = arr[0].substring(6);
                String start = arr[1].substring(5);
                String end = arr[2].substring(3);
                if (name.isBlank() || start.isBlank() || end.isBlank()) {
                    throw new InvalidArgumentException("    Please don't leave any blanks");
                }

                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
                LocalDateTime startDay = LocalDateTime.parse(start.trim(), format);
                LocalDateTime endDay = LocalDateTime.parse(end.trim(), format);
                curr = new Event(name.trim(), startDay, endDay);
            } catch (StringIndexOutOfBoundsException e) {
                throw new InvalidArgumentException("    Please give some arguments");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidArgumentException("    Please put ur task in the right format");
            } catch (DateTimeParseException e) {
                throw new InvalidFormatException("    Please input ur start/end as DD MM YYYY HH:MM "
                        + "in 24 hour format");
            }
            break;
        }
        assert curr != null : "No valid event was created";
        String response = taskList.saveToFile(curr);
        return response;
    }
}
