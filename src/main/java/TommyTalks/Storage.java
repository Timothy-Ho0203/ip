package TommyTalks;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static final String LINE = "    ------------------------------------\n";
    private String filePath;
    private List<Task> list;
    public Storage(String filePath) {
        this.filePath = filePath;
        loadFromFile();
    }

    private void loadFromFile() {
        this.list = new ArrayList<>();
        File f = new File(filePath);
        if (!f.exists()) {
            try {
                f.getParentFile().mkdirs();
                f.createNewFile();
            } catch (IOException | SecurityException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
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
        list.add(curr);
    }

    public void saveToFile(Task task) {
        list.add(task);
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
        int size = list.size();
        String result = String.format(base, task, size);
        System.out.print(result + LINE);
    }

    public void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("");
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (Task task : list) {
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
        for (Task task : list) {
            String result = String.format(base, count);
            sb.append("    " + result + task.toString() + "\n");
            count += 1;
        }
        System.out.print(sb + LINE);
    }

    public void markAsDone(int i) {
        Task curr = list.get(i-1);
        curr.markAsDone();
        System.out.print("    Great! I'll mark this as done then.\n" + "    " + curr + "\n" + LINE);
    }

    public void markAsUndone(int i) {
        Task curr = list.get(i-1);
        curr.markAsUndone();
        System.out.print("    Okay, I'll mark this as uncompleted.\n" + "    " + curr + "\n" + LINE);
    }

    public void delete(int i) {
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

    /**
     * Finds all tasks with matching keyword(s)
     */
    public void find(String keyword) {
        String queryMsg = """
                \tAre these what you're looking for?
                """;
        int count = 1;
        StringBuilder sb = new StringBuilder(queryMsg);
        String base = "%d. ";
        for (Task task : list) {
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
            System.out.print(noneFound + LINE);
        } else {
            System.out.print(sb + LINE);
        }
    }
}
