package commands;

import exceptions.InvalidArgumentException;
import tommyTalks.Storage;
import tommyTalks.Ui;

/**
 * Command for adjusting the priority of a task
 */
public class PriorityCommand extends Command {
    protected String inst;

    public PriorityCommand(String inst) {
        this.inst = inst;
    }

    @Override
    public String execute(Storage taskList, Ui ui) {
        String[] keywords = inst.split(" ");
        String response;
        if (keywords.length < 3) {
            throw new InvalidArgumentException("Please give a task and priority level to set");
        }
        try {
            response = taskList.setPriority(Integer.parseInt(keywords[1]), Integer.parseInt(keywords[2]));
        } catch (NumberFormatException | NullPointerException e) {
            throw new InvalidArgumentException("That does not look like right,\n"
                    + "please use a number...");
        }
        return response;
    }
}
