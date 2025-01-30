import Exceptions.InvalidFormatException;

public class Ui {
    public static final String LINE = "    ------------------------------------\n";
    private boolean isStupid;

    public Ui() {
        isStupid = false;
        greet();
    }
    private void greet() {
        String logo = """
                ___________                           ___________       __   __           \s
                \\__    ___/___   _____   _____ ___.__.\\__    ___/____  |  | |  | __  ______
                  |    | /  _ \\ /     \\ /     <   |  |  |    |  \\__  \\ |  | |  |/ / /  ___/
                  |    |(  <_> )  Y Y  \\  Y Y  \\___  |  |    |   / __ \\|  |_|    <  \\___ \\\s
                  |____| \\____/|__|_|__/__|_|__/ ____|  |____|  (______/____/__|_|\\/____  >
                                               \\/                                       \\/\s
                """;
        System.out.println("Hello from\n" + logo);
        String greetings = """
            ------------------------------------
            Hello! Welcome to TommyTalks
            What can i do for you?
            ------------------------------------
        """;
        System.out.print(greetings);
    }

    public void help() {
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

    public void exit() {
        String bye = """
            Bye, hope to see you again!
            ------------------------------------
        """
                ;
        System.out.println(bye);
    }

    public void errorMessage(Exception e) {
        System.out.println(e.getMessage());
        System.out.print(LINE);
    }

    public void setStupid() {
        isStupid = true;
    }

    public void invalidCommand() {
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
}
