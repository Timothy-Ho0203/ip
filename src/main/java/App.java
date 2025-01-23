import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class App {
    String LINE = "    -------------------------------\n";
    List<String> list;
    public App() {
        list = new ArrayList<>();
    }

    public void run() {
        greet();
        Scanner input = new Scanner(System.in);
        boolean run = true;
        while (run) {
            String instr = input.nextLine();
            if (instr.equalsIgnoreCase("bye")) {
                run = false;
                exit();
            } else if (instr.equalsIgnoreCase("list")) {
                displayList();
            } else {
                addToList(instr);
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
        list.add(task);
        String base = """
            Added: %s
        """;
        String result = String.format(base, task);
        System.out.print(result + LINE);
    }

    private void displayList() {
        int count = 1;
        StringBuilder sb = new StringBuilder();
        String base = "%d. ";
        for (String task : list) {
            String result = String.format(base, count);
            sb.append("    " + result + task + "\n");
            count += 1;
        }
        String res = sb.toString();
        System.out.print(res + LINE);
    }
}
