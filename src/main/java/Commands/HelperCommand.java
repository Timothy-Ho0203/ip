package Commands;

import TommyTalks.Storage;
import TommyTalks.Ui;
import Exceptions.InvalidArgumentException;

public class HelperCommand extends Command {
    protected String inst;

    public HelperCommand(String inst) {
        this.inst = inst;
    }
    @Override
    public void execute(Storage taskList, Ui ui) {
        String[] keyword = inst.split(" ", 2);
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
