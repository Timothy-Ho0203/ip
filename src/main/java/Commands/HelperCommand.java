package Commands;

import java.util.Arrays;

import TommyTalks.Storage;
import TommyTalks.Ui;

import Exceptions.InvalidArgumentException;

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
     *
     * @param taskList Storage that holds all tasks currently.
     * @param ui UI to manage printing of messages.
     * @throws InvalidArgumentException If commands are not given appropriate arguments.
     */
    @Override
    public String execute(Storage taskList, Ui ui) {
        String[] keyword = inst.split(" ");
        String task = keyword[0].toLowerCase();
        String response = "test";
        switch (task) {
        case "help":
            if (keyword.length != 1) {
                throw new InvalidArgumentException("Please make sure your command is a single word!");
            }
            response = ui.help();
            break;
        case "list":
            if (keyword.length != 1) {
                throw new InvalidArgumentException("Please make sure your command is a\nsingle word!");
            }
            response = taskList.displayTasks();
            break;
        case "mark":
            if (keyword.length == 1) {
                throw new InvalidArgumentException("Please make sure you specify a task\nto mark...");
            }
            try {
                response = taskList.markAsDone(Integer.parseInt(keyword[1]));
                break;
            } catch (NumberFormatException | NullPointerException e) {
                throw new InvalidArgumentException("That does not look like a number,\n"
                        + "please use a number...");
            }
        case "unmark":
            if (keyword.length == 1) {
                throw new InvalidArgumentException("Please make sure you specify a task\nto mark...");
            }
            try {
                response = taskList.markAsUndone(Integer.parseInt(keyword[1]));
            } catch (NumberFormatException | NullPointerException e) {
                throw new InvalidArgumentException("That does not look like a number,\n"
                        + "please use a number...");
            }
            break;
        case "delete":
            if (keyword.length == 1) {
                throw new InvalidArgumentException("Please make sure you specify a task\nto mark...");
            }
            try {
                response = taskList.delete(Integer.parseInt(keyword[1]));
            } catch (NumberFormatException | NullPointerException e) {
                throw new InvalidArgumentException("That does not look like right,\n"
                        + "please use a number...");
            }
            break;
        case "find":
            if (keyword.length == 1) {
                throw new InvalidArgumentException("Please make sure you specify a \nkeyword to find...");
            }
            try {
                String name = String.join(" ", Arrays.copyOfRange(keyword, 1, keyword.length));
                response = taskList.find(name);
            } catch (NumberFormatException | NullPointerException e) {
                throw new InvalidArgumentException("That does not look like a number,\n"
                        + "please use a number...");
            }
            break;
        case "exit", "bye":
            isExit = true;
            taskList.save();
            response = ui.exit();
            break;
        default:
            return "Something went wrong with the helper command...";
        }
        return response;
    }
}
