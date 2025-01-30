package TommyTalks;

import Commands.Command;
import Commands.HelperCommand;
import Commands.InvalidCommand;
import Commands.TaskCommand;
import Exceptions.*;

import java.util.Scanner;

public class App {
    public static final String LINE = "    ------------------------------------\n";
    private Storage data;
    private Ui ui;
    public App() {
        data = new Storage("./ip/data/TommyTalks.txt");
        ui = new Ui();
    }

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

    private Command parseInput(String inst) {
        String[] keyword = inst.split(" ", 2);
        String type = keyword[0].toLowerCase();
        Command c = null;
        switch (type) {
        case "list", "help", "mark", "unmark", "delete", "exit", "bye":
            c = new HelperCommand(inst);
            break;
        case "todo", "deadline", "event":
            c = new TaskCommand(inst);
            break;
        default:
            c = new InvalidCommand(inst);
        }
        return c;
    }
}
