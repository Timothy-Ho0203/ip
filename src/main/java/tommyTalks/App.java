package tommyTalks;

import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.HelpCommand;
import commands.InvalidCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.PriorityCommand;
import commands.SortCommand;
import commands.ToDoCommand;
import commands.UnmarkCommand;

import exceptions.InvalidArgumentException;
import exceptions.InvalidFormatException;

/**
 * Main controller of parsing and generating inputs
 */
public class App {
    private Storage data;
    private Ui ui;
    private boolean isExit = false;
    private int mistakeCount = 0;
    public App() {
        data = new Storage("./data/TommyTalks.txt");
        ui = new Ui();
    }

    /**
     * Reads the input line from user to determine what command to produce.
     *
     * @param inst input text from user.
     * @return Command to be executed.
     */
    protected Command parseInput(String inst) {
        assert inst != null : "No input was detected";
        String[] keyword = inst.split(" ", 2);
        String type = keyword[0].toLowerCase();

        Command c = switch (type) {
            case "list" -> new ListCommand(inst);
            case "help" -> new HelpCommand(inst);
            case "mark" -> new MarkCommand(inst);
            case "unmark" -> new UnmarkCommand(inst);
            case "delete" -> new DeleteCommand(inst);
            case "find" -> new FindCommand(inst);
            case "sort" -> new SortCommand(inst);
            case "priority" -> new PriorityCommand(inst);
            case "exit", "bye" -> new ExitCommand(inst);
            case "todo" -> new ToDoCommand(inst);
            case "deadline" -> new DeadlineCommand(inst);
            case "event" -> new EventCommand(inst);
            default -> new InvalidCommand(inst);
        };
        return c;
    }

    /**
     * Returns response message according to command
     *
     * @param input text from user dialog box
     * @return response message from Tommy
     */
    public String getResponse(String input) {
        String response;
        try {
            // Get the specific type of command and execute it
            Command c = parseInput(input);
            response = c.execute(data, ui);
            isExit = c.getExitStatus();
        } catch (InvalidArgumentException | InvalidFormatException e) {
            // Customize help message for lost users
            if (mistakeCount > 2) {
                ui.setStupid();
            }
            mistakeCount += 1;
            response = ui.printErrorMessage(e);
        }
        return response;
    }

    public boolean getExitStatus() {
        return isExit;
    }

    public String greet() {
        return ui.greet();
    }
}
