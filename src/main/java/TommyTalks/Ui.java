package TommyTalks;

import Exceptions.InvalidFormatException;
import Tasks.Task;

/**
 * Responsible for most messages and responses from the bot.
 */
public class Ui {
    private boolean isStupid;

    public Ui() {
        isStupid = false;
    }

    /**
     * Print welcome message.
     */
    public String greet() {
        String greetings = """
            Hello! Welcome to TommyTalks
            What can i do for you?
            """;
        return greetings;
    }

    /**
     * Prints help message to show required format of commands.
     */
    public String help() {
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
                \tfind <name>
                \tFinds all tasks that contain <name>
                \tin their description
                \t------------------------------------
                \tpriority <i> <p>
                \tSets the priority of the task at i
                \tto a priority of p
                \t------------------------------------
                \tsort
                \tSorts the list according to priority
                \t------------------------------------
                \tbye
                \tExits the app
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
        return txt;
    }

    /**
     * Prints exit message.
     */
    public String exit() {
        String bye = """
            Bye, hope to see you again!
            """;
        return bye;
    }

    /**
     * Prints the error message specified by e.
     *
     * @param e Error that was caught.
     */
    public String printErrorMessage(Exception e) {
        return e.getMessage();
    }

    public void setStupid() {
        isStupid = true;
    }

    /**
     * Prints message to be displayed when user inputs invalid commands.
     *
     * @throws InvalidFormatException If input is of wrong format.
     */
    public String handleInvalidCommand() {
        if (!isStupid) {
            String err = """
                o.O What's that? Please specify
                the type of event(todo, deadline,
                event etc) in the appropriate
                formats. I would appreciate it!""";
            throw new InvalidFormatException(err);
        } else {
            String help = """
                You seem a lil lost, would you
                like to view all possible
                commands using help?""";
            throw new InvalidFormatException(help);
        }
    }

    /**
     * Prints message to be displayed after successfully adding a task
     *
     * @param task Task that has been added
     * @param i Index of the newly added Task
     * @return String message of a successful addition
     */
    public static String additionMessage(Task task, int i) {
        String base = """
            Added:
            %s
            You have %d tasks in the list.
            """;
        return String.format(base, task, i);
    }

    /**
     * Prints message to be displayed after successfully deleting a task
     *
     * @param task Task to be deleted
     * @param i Index of the deleted Task
     * @return String message of a successful deletion
     */
    public static String deletionMessage(Task task, int i) {
        String base = """
            Removed:
            %s
            Now, you have %d tasks in the list.
        """;
        return String.format(base, task, i);
    }

    public static String noneFoundMessage() {
        String noneFound = """
            Looks like you have no
            matching tasks...
            """;
        return noneFound;
    }

    public static String adjustedPriorityMessage(Task task) {
        String base = """
            I have adjusted the following priority:
            %s
            Please call sort to view your new list.
        """;
        return String.format(base, task);
    }
}
