import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class App {
    public static final String LINE = "    -------------------------------\n";
    private List<Task> list;
    public App() {
        list = new ArrayList<>();
    }

    public void run() {
        greet();
        Scanner input = new Scanner(System.in);
        boolean run = true;
        while (run) {
            String in = input.nextLine();
            String[] instr = in.split(" ");
            String task = instr[0].toLowerCase();
            switch (task) {
                case "bye":
                    run = false;
                    exit();
                    break;
                case "list":
                    displayList();
                    break;
                case "mark":
                    markAsDone(Integer.parseInt(instr[1]));
                    break;
                case "unmark":
                    markAsUndone(Integer.parseInt(instr[1]));
                    break;
                case "delete":
                    delete(Integer.parseInt(instr[1]));
                    break;
                default:
                    addToList(in);

            }
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
                    curr = new ToDo(name);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.print("    Please put in a task at least\n" + LINE);
                    return;
                }
                break;
            }
            case "deadline": {
                try {
                    String[] arr = task.split("/");
                    String name = arr[0].substring(9);
                    String date = arr[1].substring(3);
                    curr = new Deadline(name, date);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.print("    Please put in a task at least\n" + LINE);
                    return;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.print("    Please put ur task in the right format\n" + LINE);
                    return;
                }
                break;
            }
            case "event": {
                try {
                    String[] arr = task.split("/");
                    String name = arr[0].substring(6);
                    String start = arr[1].substring(5);
                    String end = arr[2].substring(3);
                    curr = new Event(name, start, end);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.print("    Please put in a task at least\n" + LINE);
                    return;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.print("    Please put ur task in the right format\n" + LINE);
                    return;
                }
                break;
            }
            default:
                String err = """
                    o.O What's that? Please specify
                    the type of event(todo, deadline,
                    event etc) in the appropriate
                    formats. I would appreciate it!
                """;
                System.out.print(err + LINE);
                return;
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
        System.out.print("    Great! I'll mark this as done then.\n" + "    " + curr.toString() + "\n" + LINE);
    }

    private void markAsUndone(int i) {
        Task curr = list.get(i-1);
        curr.markAsUndone();
        System.out.print("    Okay, I'll mark this as uncompleted.\n" + "    " + curr.toString() + "\n" + LINE);
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
}
