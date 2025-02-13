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
        String[] keywords = inst.split(" ");
        String task = keywords[0].toLowerCase();
        String response = "test";
        switch (task) {
        case "help":
            if (keywords.length != 1) {
                throw new InvalidArgumentException("Please make sure your command is a single word!");
            }
            response = ui.help();
            break;
        case "list":
            if (keywords.length != 1) {
                throw new InvalidArgumentException("Please make sure your command is a\nsingle word!");
            }
            response = taskList.displayTasks();
            break;
        case "mark":
            if (keywords.length == 1) {
                throw new InvalidArgumentException("Please make sure you specify a task\nto mark...");
            }
            try {
                response = taskList.markAsDone(Integer.parseInt(keywords[1]));
                break;
            } catch (NumberFormatException | NullPointerException e) {
                throw new InvalidArgumentException("That does not look like a number,\n"
                        + "please use a number...");
            }
        case "unmark":
            if (keywords.length == 1) {
                throw new InvalidArgumentException("Please make sure you specify a task\nto mark...");
            }
            try {
                response = taskList.markAsUndone(Integer.parseInt(keywords[1]));
            } catch (NumberFormatException | NullPointerException e) {
                throw new InvalidArgumentException("That does not look like a number,\n"
                        + "please use a number...");
            }
            break;
        case "delete":
            if (keywords.length == 1) {
                throw new InvalidArgumentException("Please make sure you specify a task\nto mark...");
            }
            try {
                response = taskList.delete(Integer.parseInt(keywords[1]));
            } catch (NumberFormatException | NullPointerException e) {
                throw new InvalidArgumentException("That does not look like right,\n"
                        + "please use a number...");
            }
            break;
        case "find":
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
            break;
        case "exit", "bye":
            isExit = true;
            taskList.save();
            response = ui.exit();
            break;
        case "sort":
            taskList.sort();
            response = taskList.displayTasks();
            break;
        case "priority":
            if (keywords.length < 3) {
                throw new InvalidArgumentException("Please give a task and priority level to set");
            }
            try {
                response = taskList.setPriority(Integer.parseInt(keywords[1]), Integer.parseInt(keywords[2]));
            } catch (NumberFormatException | NullPointerException e) {
                throw new InvalidArgumentException("That does not look like right,\n"
                        + "please use a number...");
            }
            break;
        default:
            return "Something went wrong with the helper command...";
        }
        return response;
    }
}
