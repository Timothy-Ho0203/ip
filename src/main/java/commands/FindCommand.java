package commands;

import exceptions.InvalidArgumentException;
import tommyTalks.Storage;
import tommyTalks.Ui;

import java.util.Arrays;

/**
 * Command for finding a task
 */
public class FindCommand extends Command {
    protected String inst;

    public FindCommand(String inst) {
        this.inst = inst;
    }

    @Override
    public String execute(Storage taskList, Ui ui) {
        String[] keywords = inst.split(" ");
        String response;
        if (keywords.length == 1) {
            throw new InvalidArgumentException("Please make sure you specify a \nkeywords to find...");
        }
        try {
            String name = String.join(" ", Arrays.copyOfRange(keywords, 1, keywords.length));
            response = taskList.find(name);
        } catch (NumberFormatException | NullPointerException e) {
            throw new InvalidArgumentException("That does not look like a number,\n"
                    + "please use a number...");
        }
        return response;
    }
}
