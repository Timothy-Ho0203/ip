package TommyTalks;

import java.util.Scanner;

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
     * Main driver code to run the app
     */
    public void run() {
        Scanner input = new Scanner(System.in);
        boolean isRunning = true;
        int count = 0;
        while (isRunning) {
            if (count > 2) {
                ui.setStupid();
            }
            String in = input.nextLine();
            try {
                Command c = parseInput(in);
                c.execute(data, ui);
                isRunning = c.getExitStatus();
            } catch (InvalidArgumentException | InvalidFormatException e) {
                count += 1;
                ui.printErrorMessage(e);
            }
        }
    }

    /**
     * Reads the input line from user to determine what command to produce.
     *
     * @param inst input text from user.
     * @return Command to be executed.
     */
    protected Command parseInput(String inst) {
        String[] keyword = inst.split(" ", 2);
        String type = keyword[0].toLowerCase();

        Command c = switch (type) {
            case "list", "help", "mark", "unmark", "delete", "exit", "bye" -> new HelperCommand(inst);
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
            Command c = parseInput(input);
            response = c.execute(data, ui);
            isExit = c.getExitStatus();
        } catch (InvalidArgumentException | InvalidFormatException e) {
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
