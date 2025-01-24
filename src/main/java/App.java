import Exceptions.*;
import Tasks.*;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class App {
    public static final String LINE = "    ------------------------------------\n";
    private List<Task> list;
    private boolean stupid;
    public App() {
        list = new ArrayList<>();
        stupid = false;
    }

    public void run() {
        greet();
        Scanner input = new Scanner(System.in);
        boolean run = true;
        int count = 0;
        while (run) {
            if (count > 2) {
                stupid = true;
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
            -------------------------------
            Hello! Welcome to TommyTalks
            What can i do for you?
            -------------------------------
        """;
        System.out.print(greetings);
    }

    private void exit() {
        String bye = """
            Bye, hope to see you again!
            -------------------------------
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
                    curr = new Deadline(name.trim(), date.trim());
                } catch (StringIndexOutOfBoundsException e) {
                    throw new InvalidArgumentException("    Please give some arguments");
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new InvalidArgumentException("    Please put ur task in the right format");
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
                    curr = new Event(name.trim(), start.trim(), end.trim());
                } catch (StringIndexOutOfBoundsException e) {
                    throw new InvalidArgumentException("    Please give some arguments");
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new InvalidArgumentException("    Please put ur task in the right format");
                }
                break;
            }
            default:
                if (!stupid) {
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
        list.add(curr);
        String base = """
            Added:
            %s
            You have %d tasks in the list.
        """;
        int size = list.size();
        String result = String.format(base, curr, size);
        System.out.print(result + LINE);
    }

    private void displayList() {
        int count = 1;
        StringBuilder sb = new StringBuilder("    Here are the tasks you have: \n");
        String base = "%d. ";
        for (Task task : list) {
            String result = String.format(base, count);
            sb.append("    " + result + task.toString() + "\n");
            count += 1;
        }
        String res = sb.toString();
        System.out.print(res + LINE);
    }

    private void markAsDone(int i) {
        Task curr = list.get(i-1);
        curr.markAsDone();
        System.out.print("    Great! I'll mark this as done then.\n" + "    " + curr + "\n" + LINE);
    }

    private void markAsUndone(int i) {
        Task curr = list.get(i-1);
        curr.markAsUndone();
        System.out.print("    Okay, I'll mark this as uncompleted.\n" + "    " + curr + "\n" + LINE);
    }

    private void delete(int i) {
        Task curr = list.get(i-1);
        list.remove(i-1);
        String base = """
            Removed:
            %s
            Now, you have %d tasks in the list.
        """;
        int size = list.size();
        String result = String.format(base, curr, size);
        System.out.print(result + LINE);
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
                \tAdds Deadline Task <name> due 
                \ton <date> to list
                \t------------------------------------
                \tevent <name> /from <start> /to <end>
                \tAdds Event Task <name> starting from
                \t<start> to <end> to list
                \t------------------------------------
                """;
        System.out.print(txt);
    }
}
