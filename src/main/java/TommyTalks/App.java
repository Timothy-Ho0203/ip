package TommyTalks;

import Commands.Command;
import Commands.HelperCommand;
import Commands.InvalidCommand;
import Commands.TaskCommand;

import Exceptions.InvalidArgumentException;
import Exceptions.InvalidFormatException;

public class App {
    public static final String LINE = "    ------------------------------------\n";
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
            case "list", "help", "mark", "unmark", "delete", "find", "sort",
                    "priority", "exit", "bye" -> new HelperCommand(inst);
            case "todo", "deadline", "event" -> new TaskCommand(inst);
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
