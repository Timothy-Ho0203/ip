package Commands;

import TommyTalks.Storage;
import TommyTalks.Ui;

public abstract class Command {
    protected boolean isExit = true;
    public abstract void execute(Storage taskList, Ui ui);

    public boolean getExitStatus() {
        return isExit;
    }
}
