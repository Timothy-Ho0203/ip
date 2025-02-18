package commands;

import exceptions.InvalidArgumentException;
import exceptions.InvalidFormatException;
import tasks.Deadline;
import tasks.Task;
import tommyTalks.Storage;
import tommyTalks.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Command to create a Deadline Tasks
 */
public class DeadlineCommand extends Command {
    protected String task;

    public DeadlineCommand(String task) {
        this.task = task;
    }

    /**
     * Creates a Deadline task
     *
     * @param taskList Storage that holds all tasks
     * @param ui UI to manage default messages
     * @return String message of successful addition of Deadline to taskList
     * @throws InvalidArgumentException If the input contains inappropriate arguments
     * @throws InvalidFormatException If date and time are not given in DD MM YYYY
     */
    @Override
    public String execute(Storage taskList, Ui ui) {
        Task curr;
        try {
            // Split the input into appropriate sections from "deadline name /by date"
            String[] arr = task.split("/");
            if (arr.length > 2) {
                throw new InvalidArgumentException("Please only leave 1 date");
            }
            String name = arr[0].substring(9);
            String date = arr[1].substring(3);
            if (name.isBlank() || date.isBlank()) {
                throw new InvalidArgumentException("Please don't leave any blanks");
            }

            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MM yyyy");
            LocalDate dueDate = LocalDate.parse(date.trim(), format);
            curr = new Deadline(name.trim(), dueDate);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("Please give some arguments");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("Please put ur task in the right format");
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("Please put your date as a valid DD MM YYYY");
        }
        String response = taskList.saveToFile(curr);
        return response;
    }
}
