package commands;

import tommyTalks.Storage;
import tommyTalks.Ui;

/**
 * Parent class for commands that user inputs.
 */
public abstract class Command {
    protected boolean isExit = false;

    /**
     * Carries out the intended logic of the specific task
     *
     * @param taskList Storage that holds all tasks
     * @param ui UI to manage default messages
     * @return String message of successful addition of Deadline to taskList
     */
    public abstract String execute(Storage taskList, Ui ui);

    public boolean getExitStatus() {
        return isExit;
    }
}
