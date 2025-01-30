package Commands;

import TommyTalks.Storage;
import TommyTalks.Ui;

public class InvalidCommand extends Command {
    protected String inst;

    public InvalidCommand(String inst) {
        this.inst = inst;
    }

    @Override
    public void execute(Storage taskList, Ui ui) {
        ui.invalidCommand();
    }
}
