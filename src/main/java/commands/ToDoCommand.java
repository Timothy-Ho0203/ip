package commands;

import exceptions.InvalidArgumentException;
import exceptions.InvalidFormatException;
import tasks.Task;
import tasks.ToDo;
import tommyTalks.Storage;
import tommyTalks.Ui;

/**
 * Command for creating a ToDo Task
 */
public class ToDoCommand extends Command {
    protected String task;

    public ToDoCommand(String task) {
        this.task = task;
    }

    /**
     * Creates a ToDo task
     *
     * @param taskList Storage that holds all tasks
     * @param ui UI to manage default messages
     * @return String message of successful addition of Event to taskList
     * @throws InvalidArgumentException If the input contains inappropriate arguments
     */
    @Override
    public String execute(Storage taskList, Ui ui) {
        Task curr;
        try {
            String name = task.substring(5);
            if (name.isBlank()) {
                throw new InvalidArgumentException("Please put in a task at least");
            }
            curr = new ToDo(name.trim());
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("Please put in a task at least");
        }
        String response = taskList.saveToFile(curr);
        return response;
    }
}
