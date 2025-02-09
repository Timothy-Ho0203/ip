package TommyTalks;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Saves and loads tasks from/to hard disk.
 * Stores the tasks in an ArrayList.
 */
public class Storage {
    public static final String LINE = "    ------------------------------------\n";
    private String filePath;
    private List<Task> tasks;
    public Storage(String filePath) {
        this.filePath = filePath;
        loadFromFile();
    }

    /**
     * Reads tasks from hard disk and populate task list.
     */
    private void loadFromFile() {
        this.tasks = new ArrayList<>();
        File f = new File(filePath);
        if (!f.exists()) {
            try {
                f.getParentFile().mkdirs();
                f.createNewFile();
            } catch (IOException | SecurityException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
        // Populate tasks
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = reader.readLine()) != null) {
                addToList(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Populates task list of Storage object.
     * Serves as a helper function when Storage is initialized.
     *
     * @param line text read from file containing previous task descriptions.
     */
    private void addToList(String line) {
        String[] data = line.split(",");
        Task curr = null;
        switch (data[0]) {
        case "todo":
            curr = new ToDo(data[1]);
            break;
        case "deadline":
            DateTimeFormatter formatDeadline = DateTimeFormatter.ofPattern("dd MM yyyy");
            LocalDate dueDate = LocalDate.parse(data[3].trim(), formatDeadline);
            curr = new Deadline(data[1], dueDate);
            break;
        case "event":
            DateTimeFormatter formatEvent = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
            LocalDateTime startDay = LocalDateTime.parse(data[3].trim(), formatEvent);
            LocalDateTime endDay = LocalDateTime.parse(data[4].trim(), formatEvent);
            curr = new Event(data[1], startDay, endDay);
            break;
        }
        if (data[2].equals("true")) {
            curr.markAsDone();
        }
        tasks.add(curr);
    }

    /**
     * Adds task to task list and writes task description to file.
     */
    public String saveToFile(Task task) {
        tasks.add(task);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            writer.append(task.getKeyInfo());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        String base = """
            Added:
            %s
            You have %d tasks in the list.
        """;
        int size = tasks.size();
        String result = String.format(base, task, size);
        return result;
    }

    /**
     * Saves the list of tasks in file to hard disk.
     */
    public void save() {
        // Reset the file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write("");
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
        // Write the final tasks before closing
        try  {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            for (Task task : tasks) {
                writer.append(task.getKeyInfo());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    /**
     * Lists out all tasks currently available regardless of completion status.
     */
    public String displayTasks() {
        int count = 1;
        StringBuilder sb = new StringBuilder("    Here are the tasks you have: \n");
        String base = "%d. ";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String result = String.format(base, count);
            sb.append("    " + result + task.toString() + "\n");
            count += 1;
        }
        return sb.toString();
    }

    /**
     * Marks the task specified at i relative to the task list as completed.
     *
     * @param i index of the task to be marked.
     */
    public String markAsDone(int i) {
        Task curr = tasks.get(i-1);
        curr.markAsDone();
        return "Great! I'll mark this as done then.";
    }

    /**
     * Marks the task specified at i relative to the task list as uncompleted.
     *
     * @param i index of the task to be marked.
     */
    public String markAsUndone(int i) {
        Task curr = tasks.get(i-1);
        curr.markAsUndone();
        //System.out.print("    Okay, I'll mark this as uncompleted.\n" + "    " + curr + "\n" + LINE);
        return "Okay, I'll mark this as uncompleted";
    }

    /**
     * Deletes the task specified at i relative to the task list.
     *
     * @param i index of the task to be deleted.
     */
    public String delete(int i) {
        Task curr = tasks.get(i-1);
        tasks.remove(i-1);

        String base = """
            Removed:
            %s
            Now, you have %d tasks in the list.
        """;
        int size = tasks.size();
        String result = String.format(base, curr, size);
        return result;
    }

    /**
     * Finds all tasks with matching keyword(s)
     */
    public String find(String keyword) {
        String queryMsg = """
                \tAre these what you're looking for?
                """;
        int count = 1;
        StringBuilder sb = new StringBuilder(queryMsg);
        String base = "%d. ";
        for (Task task : tasks) {
            String name = task.getName().toLowerCase();
            if (name.contains(keyword.toLowerCase())) {
                String result = String.format(base, count);
                sb.append("    " + result + task + "\n");
                count += 1;
            }
        }
        if (count == 1) {
            String noneFound = """
                    \tLooks like you have no
                    \tmatching tasks...
                    """;
            return noneFound;
        } else {
            return sb.toString();
        }
    }
}
