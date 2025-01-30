package TommyTalks;

import Commands.Command;
import Commands.HelperCommand;
import Commands.InvalidCommand;
import Commands.TaskCommand;

import Exceptions.InvalidFormatException;
import Exceptions.InvalidArgumentException;

import java.util.Scanner;

public class App {
    public static final String LINE = "    ------------------------------------\n";
    private Storage data;
    private Ui ui;
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
                ui.errorMessage(e);
            }
        }
    }

    /**
     * Reads the input line from user to determine what command to produce.
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
}
