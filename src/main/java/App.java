import java.util.Scanner;
public class App {
    public void run() {
        greet();
        Scanner input = new Scanner(System.in);
        boolean run = true;
        while (run) {
            String instr = input.nextLine();
            if (instr.equals("bye")) {
                run = false;
                exit();
            } else {
                String line = """
                    -------------------------------
                """;
                System.out.println("    " + instr + "\n" + line);
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
}
