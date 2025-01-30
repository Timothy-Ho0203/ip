package Commands;

import TommyTalks.Storage;
import TommyTalks.Ui;

import Exceptions.InvalidArgumentException;

import java.util.Arrays;

/**
 * Commands that display information and help user interaction.
 */
public class HelperCommand extends Command {
    protected String inst;

    public HelperCommand(String inst) {
        this.inst = inst;
    }

    /**
     * Execute the command according to their functionalities.
     * @param taskList Storage that holds all tasks currently.
     * @param ui UI to manage printing of messages.
     * @throws InvalidArgumentException If commands are not given appropriate arguments.
     */
    @Override
    public void execute(Storage taskList, Ui ui) {
        String[] keyword = inst.split(" ");
        String task = keyword[0].toLowerCase();
        switch (task) {
        case "help":
            if (keyword.length != 1) {
                throw new InvalidArgumentException("    Please make sure your command is a single word!");
            }
            ui.help();
            break;
        case "list":
            if (keyword.length != 1) {
                throw new InvalidArgumentException("    Please make sure your command is a\n    single word!");
            }
            taskList.displayTasks();
            break;
        case "mark":
            if (keyword.length == 1) {
                throw new InvalidArgumentException("    Please make sure you specify a task\n    to mark...");
            }
            try {
                taskList.markAsDone(Integer.parseInt(keyword[1]));
            } catch (NumberFormatException | NullPointerException e) {
                throw new InvalidArgumentException("    That does not look like a number,\n" +
                        "    please use a number...");
            }
            break;
        case "unmark":
            if (keyword.length == 1) {
                throw new InvalidArgumentException("    Please make sure you specify a task\n    to mark...");
            }
            try {
                taskList.markAsUndone(Integer.parseInt(keyword[1]));
            } catch (NumberFormatException | NullPointerException e) {
                throw new InvalidArgumentException("    That does not look like a number,\n" +
                        "    please use a number...");
            }
            break;
        case "delete":
            if (keyword.length == 1) {
                throw new InvalidArgumentException("    Please make sure you specify a task\n    to mark...");
            }
            try {
                taskList.delete(Integer.parseInt(keyword[1]));
            } catch (NumberFormatException | NullPointerException e) {
                throw new InvalidArgumentException("    That does not look like right,\n" +
                        "    please use a number...");
            }
            break;
        case "find":
            if (keyword.length == 1) {
                throw new InvalidArgumentException("    Please make sure you specify a \n    keyword to find...");
            }
            try {
                String name = String.join(" ", Arrays.copyOfRange(keyword, 1, keyword.length));
                taskList.find(name);
            } catch (NumberFormatException | NullPointerException e) {
                throw new InvalidArgumentException("    That does not look like a number,\n" +
                        "    please use a number...");
            }
            break;

        case "exit", "bye":
            isExit = false;
            taskList.save();
            ui.exit();
        }
    }
}
