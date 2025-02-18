package commands;

import tommyTalks.Storage;
import tommyTalks.Ui;

/**
 * Command for exiting the program
 */
public class ExitCommand extends Command {
    protected String inst;

    public ExitCommand(String inst) {
        this.inst = inst;
    }

    @Override
    public String execute(Storage taskList, Ui ui) {
        String[] keywords = inst.split(" ");
        isExit = true;
        taskList.save();
        return ui.exit();
    }
}
