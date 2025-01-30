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

public class Storage {
    public static final String LINE = "    ------------------------------------\n";
    private String filePath;
    private List<Task> tasks;
    public Storage(String filePath) {
        this.filePath = filePath;
        loadFromFile();
    }

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

    public void saveToFile(Task task) {
        tasks.add(task);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
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
        System.out.print(result + LINE);
    }

    public void save() {
        // Reset the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("");
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
        // Write the final tasks before closing
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                writer.append(task.getKeyInfo());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    public void displayTasks() {
        int count = 1;
        StringBuilder sb = new StringBuilder("    Here are the tasks you have: \n");
        String base = "%d. ";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String result = String.format(base, count);
            sb.append("    " + result + task.toString() + "\n");
            count += 1;
        }
        System.out.print(sb + LINE);
    }

    public void markAsDone(int i) {
        Task curr = tasks.get(i-1);
        curr.markAsDone();
        System.out.print("    Great! I'll mark this as done then.\n" + "    " + curr + "\n" + LINE);
    }

    public void markAsUndone(int i) {
        Task curr = tasks.get(i-1);
        curr.markAsUndone();
        System.out.print("    Okay, I'll mark this as uncompleted.\n" + "    " + curr + "\n" + LINE);
    }

    public void delete(int i) {
        Task curr = tasks.get(i-1);
        tasks.remove(i-1);

        String base = """
            Removed:
            %s
            Now, you have %d tasks in the list.
        """;
        int size = tasks.size();
        String result = String.format(base, curr, size);
        System.out.print(result + LINE);
    }
}
