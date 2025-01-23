
public class App {
    public void run() {
        greet();
        exit();
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
