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

public class TaskCommand extends Command {
    protected String task;

    public TaskCommand(String task) {
        this.task = task;
    }

    @Override
    public void execute(Storage taskList, Ui ui) {
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
                throw new InvalidFormatException("    Please input ur start/end as DD MM YYYY HH:MM " +
                        "in 24 hour format");
            }
            break;
        }
        taskList.saveToFile(curr);
    }
}
