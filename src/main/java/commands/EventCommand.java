package commands;

import exceptions.InvalidArgumentException;
import exceptions.InvalidFormatException;
import tasks.Event;
import tasks.Task;
import tommyTalks.Storage;
import tommyTalks.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Command for creating an Event Task
 */
public class EventCommand extends Command {
    protected String task;

    public EventCommand(String task) {
        this.task = task;
    }

    /**
     * Creates an event task
     *
     * @param taskList Storage that holds all tasks
     * @param ui UI to manage default messages
     * @return String message of successful addition of Event to taskList
     * @throws InvalidArgumentException If the input contains inappropriate arguments
     * @throws InvalidFormatException If date and time are not given in DD MM YYYY
     * or 24-hour format
     */
    @Override
    public String execute(Storage taskList, Ui ui) {
        Task curr;
        try {
            // Split the input into appropriate sections from "event name /from date /to date"
            String[] arr = task.split("/");
            String name = arr[0].substring(6);
            String start = arr[1].substring(5);
            String end = arr[2].substring(3);
            if (name.isBlank() || start.isBlank() || end.isBlank()) {
                throw new InvalidArgumentException("Please don't leave any blanks");
            }

            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
            LocalDateTime startDay = LocalDateTime.parse(start.trim(), format);
            LocalDateTime endDay = LocalDateTime.parse(end.trim(), format);
            curr = new Event(name.trim(), startDay, endDay);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("Please give some arguments");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("Please put ur task in the right format");
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("Please input ur start/end as DD MM YYYY HH:MM "
                    + "in 24 hour format");
        }
        String response = taskList.saveToFile(curr);
        return response;
    }
}
