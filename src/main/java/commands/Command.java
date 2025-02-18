package commands;

import tommyTalks.Storage;
import tommyTalks.Ui;

/**
 * Parent class for commands that user inputs.
 */
public abstract class Command {
    protected boolean isExit = false;
    public abstract String execute(Storage taskList, Ui ui);

    public boolean getExitStatus() {
        return isExit;
    }
}
