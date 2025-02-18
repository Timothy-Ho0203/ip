package commands;

import exceptions.InvalidArgumentException;
import tommyTalks.Storage;
import tommyTalks.Ui;

/**
 * Command for viewing a help list
 */
public class HelpCommand extends Command {
    protected String inst;

    public HelpCommand(String inst) {
        this.inst = inst;
    }

    @Override
    public String execute(Storage taskList, Ui ui) {
        String[] keywords = inst.split(" ");
        String response;
        if (keywords.length != 1) {
            throw new InvalidArgumentException("Please make sure your command is a single word!");
        }
        response = ui.help();
        return response;
    }
}
