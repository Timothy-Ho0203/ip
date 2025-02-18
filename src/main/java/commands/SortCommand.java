package commands;

import tommyTalks.Storage;
import tommyTalks.Ui;

/**
 * Command for sorting the current tasks based on their priority
 */
public class SortCommand extends Command {
    protected String inst;

    public SortCommand(String inst) {
        this.inst = inst;
    }

    @Override
    public String execute(Storage taskList, Ui ui) {
        String[] keywords = inst.split(" ");
        taskList.sort();
        String response = taskList.displayTasks();
        return response;
    }
}
