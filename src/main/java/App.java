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
            if (instr[0].equalsIgnoreCase("bye")) {
                run = false;
                exit();
            } else if (instr[0].equalsIgnoreCase("list")) {
                displayList();
            } else if (instr[0].equalsIgnoreCase("mark")) {
                markAsDone(Integer.parseInt(instr[1]));
            } else if (instr[0].equalsIgnoreCase("unmark")) {
                markAsUndone(Integer.parseInt(instr[1]));
            } else {
                addToList(instr[0]);
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
        list.add(new Task(task));
        String base = """
            Added: %s
        """;
        String result = String.format(base, task);
        System.out.print(result + LINE);
    }

    private void displayList() {
        int count = 1;
        StringBuilder sb = new StringBuilder("Here are the tasks you have: \n");
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
        System.out.print("Great! I'll mark this as done then.\n" + "    " + curr.toString() + "\n" + LINE);
    }

    private void markAsUndone(int i) {
        Task curr = list.get(i-1);
        curr.markAsUndone();
        System.out.print("Okay, I'll mark this as uncompleted.\n" + "    " + curr.toString() + "\n" + LINE);
    }
}
