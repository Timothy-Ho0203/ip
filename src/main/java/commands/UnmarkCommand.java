package commands;

import exceptions.InvalidArgumentException;
import tommyTalks.Storage;
import tommyTalks.Ui;

/**
 * Command for marking a task as uncompleted
 */
public class UnmarkCommand extends Command {
    protected String inst;

    public UnmarkCommand(String inst) {
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
            response = taskList.markAsUndone(Integer.parseInt(keywords[1]));
        } catch (NumberFormatException | NullPointerException e) {
            throw new InvalidArgumentException("That does not look like a number,\n"
                    + "please use a number...");
        }
        return response;
    }
}
