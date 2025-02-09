package Commands;

import TommyTalks.Storage;
import TommyTalks.Ui;

/**
 * Commands that deal with invalid user inputs that are neither
 * helper commands or task creation commands.
 */
public class InvalidCommand extends Command {
    protected String inst;

    public InvalidCommand(String inst) {
        this.inst = inst;
    }

    @Override
    public String execute(Storage taskList, Ui ui) {
        return ui.handleInvalidCommand();
    }
}
