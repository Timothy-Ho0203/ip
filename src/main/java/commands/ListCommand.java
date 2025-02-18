package commands;

import exceptions.InvalidArgumentException;
import tommyTalks.Storage;
import tommyTalks.Ui;

/**
 * Command for listing the current tasks
 */
public class ListCommand extends Command {
    protected String inst;

    public ListCommand(String inst) {
        this.inst = inst;
    }

    @Override
    public String execute(Storage taskList, Ui ui) {
        String[] keywords = inst.split(" ");
        String response;
        if (keywords.length != 1) {
            throw new InvalidArgumentException("Please make sure your command is a\nsingle word!");
        }
        response = taskList.displayTasks();
        return response;
    }
}
