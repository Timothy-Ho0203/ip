package commands;

import exceptions.InvalidArgumentException;
import tommyTalks.Storage;
import tommyTalks.Ui;

/**
 * Command for deleting a task
 */
public class DeleteCommand extends Command {
    protected String inst;

    public DeleteCommand(String inst) {
        this.inst = inst;
    }

    @Override
    public String execute(Storage taskList, Ui ui) {
        String[] keywords = inst.split(" ");
        String response;
        if (keywords.length == 1) {
            throw new InvalidArgumentException("Please make sure you specify a task\nto mark...");
        }
        try {
            response = taskList.delete(Integer.parseInt(keywords[1]));
        } catch (NumberFormatException | NullPointerException e) {
            throw new InvalidArgumentException("That does not look like right,\n"
                    + "please use a number...");
        }
        return response;
    }
}
