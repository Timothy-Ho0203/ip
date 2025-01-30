import Exceptions.*;
import Tasks.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class App {
    public static final String LINE = "    ------------------------------------\n";
    private Storage data;
    private boolean isStupid;
    public App() {
        isStupid = false;
        data = new Storage("./ip/data/TommyTalks.txt");
    }

    public void run() {
        greet();
        Scanner input = new Scanner(System.in);
        boolean run = true;
        int count = 0;
        while (run) {
            if (count > 2) {
                isStupid = true;
            }
            String in = input.nextLine();
            if (in.equalsIgnoreCase("bye")) {
                exit();
                break;
            }
            try {
                parseInput(in);
            } catch (InvalidArgumentException | InvalidFormatException e) {
                count += 1;
                System.out.println(e.getMessage());
                System.out.print(LINE);
            }
        }
    }

    private void parseInput(String inst) {
        String[] keyword = inst.split(" ", 2);
        String task = keyword[0].toLowerCase();
        switch (task) {
            case "help":
                if (keyword.length != 1) {
                    throw new InvalidArgumentException("    Please make sure your command is a single word!");
                }
                help();
                break;
            case "list":
                if (keyword.length != 1) {
                    throw new InvalidArgumentException("    Please make sure your command is a\n    single word!");
                }
                displayList();
                break;
            case "mark":
                if (keyword.length == 1) {
                    throw new InvalidArgumentException("    Please make sure you specify a task\n    to mark...");
                }
                try {
                    markAsDone(Integer.parseInt(keyword[1]));
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
                    markAsUndone(Integer.parseInt(keyword[1]));
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
                    delete(Integer.parseInt(keyword[1]));
                } catch (NumberFormatException | NullPointerException e) {
                    throw new InvalidArgumentException("    That does not look like a number,\n" +
                            "    please use a number...");
                }
                break;
            default:
                addToList(inst);
        }
    }
    private void greet() {
        String greetings = """
            ------------------------------------
            Hello! Welcome to TommyTalks
            What can i do for you?
            ------------------------------------
        """;
        System.out.print(greetings);
    }

    private void exit() {
        data.save();
        String bye = """
            Bye, hope to see you again!
            ------------------------------------
        """
        ;
        System.out.println(bye);
    }

    private void addToList(String task) {
        Task curr = null;
        String type = task.split(" ")[0].toLowerCase();
        switch(type) {
            case "todo": {
                try {
                    String name = task.substring(5);
                    if (name.isBlank()) {
                        throw new InvalidArgumentException("    Please put in a task at least");
                    }
                    curr = new ToDo(name.trim());
                } catch (StringIndexOutOfBoundsException e) {
                    throw new InvalidArgumentException("    Please put in a task at least");
                }
                break;
            }
            case "deadline": {
                try {
                    String[] arr = task.split("/");
                    String name = arr[0].substring(9);
                    String date = arr[1].substring(3);
                    if (name.isBlank() || date.isBlank()) {
                        throw new InvalidArgumentException("    Please don't leave any blanks");
                    }
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MM yyyy");
                    LocalDate dueDate = LocalDate.parse(date.trim(), format);
                    curr = new Deadline(name.trim(), dueDate);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new InvalidArgumentException("    Please give some arguments");
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new InvalidArgumentException("    Please put ur task in the right format");
                } catch (DateTimeParseException e) {
                    throw new InvalidFormatException("    Please put your date as DD MM YYYY");
                }
                break;
            }
            case "event": {
                try {
                    String[] arr = task.split("/");
                    String name = arr[0].substring(6);
                    String start = arr[1].substring(5);
                    String end = arr[2].substring(3);
                    if (name.isBlank() || start.isBlank() || end.isBlank()) {
                        throw new InvalidArgumentException("    Please don't leave any blanks");
                    }
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
                    LocalDateTime startDay = LocalDateTime.parse(start.trim(), format);
                    LocalDateTime endDay = LocalDateTime.parse(end.trim(), format);
                    curr = new Event(name.trim(), startDay, endDay);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new InvalidArgumentException("    Please give some arguments");
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new InvalidArgumentException("    Please put ur task in the right format");
                } catch (DateTimeParseException e) {
                    throw new InvalidFormatException("    Please input ur start/end as DD MM YYYY HH:MM " +
                            "in 24 hour format");
                }
                break;
            }
            default:
                if (!isStupid) {
                    String err = """
                            \to.O What's that? Please specify
                            \tthe type of event(todo, deadline,
                            \tevent etc) in the appropriate
                            \tformats. I would appreciate it!""";
                    throw new InvalidFormatException(err);
                } else {
                    String help = """
                            \tYou seem a lil lost, would you
                            \tlike to view all possible
                            \tcommands using help?""";
                    throw new InvalidFormatException(help);
                }
        }
        data.saveToFile(curr);
    }

    private void displayList() {
        System.out.print(data.displayTasks() + LINE);
    }

    private void markAsDone(int i) {
        data.markAsDone(i);
    }

    private void markAsUndone(int i) {
        data.markAsUndone(i);
    }

    private void delete(int i) {
        data.delete(i);
    }

    private void help() {
        String txt = """
                \t------------------------------------
                \tHere is the list of commands
                \t------------------------------------
                \tlist
                \tView all tasks
                \t------------------------------------
                \tmark <i>
                \tMarks task i as completed
                \t------------------------------------
                \tunmark <i>
                \tMarks task i as uncompleted
                \t------------------------------------
                \tdelete <i>
                \tDeletes task i from the list
                \t------------------------------------
                \ttodo <name>
                \tAdds ToDo Task <name> to list
                \t------------------------------------
                \tdeadline <name> /by <date>
                \tAdds Deadline Task <name> due on
                \t<date> to list
                \t------------------------------------
                \tevent <name> /from <start> /to <end>
                \tAdds Event Task <name> starting from
                \t<start> to <end> to list
                \t------------------------------------
                """;
        System.out.print(txt);
    }
}
