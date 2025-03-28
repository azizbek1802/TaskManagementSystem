import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
    private static final String FILE_NAME = "tasks.txt";
    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(String title, String description) {
        tasks.add(new Task(title, description));
        saveTasksToFile();
    }

    public void showTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public void deleteTask(int index) {
        if (index >= 1 && index <= tasks.size()) {
            tasks.remove(index - 1);
            saveTasksToFile();
        } else {
            System.out.println("Invalid task number!");
        }
    }

    public void markTaskAsCompleted(int index) {
        if (index >= 1 && index <= tasks.size()) {
            tasks.get(index - 1).markAsCompleted();
            saveTasksToFile();
        } else {
            System.out.println("Invalid task number!");
        }
    }

    private void saveTasksToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME) {
            @Override
            public void write(int c) throws IOException {
                super.write(c);
            }
        })) {
            for (Task task : tasks) {
                writer.println(task.getTitle() + "|" + task.getDescription() + "|" + task.isCompleted());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public void loadTasksFromFile() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String[] data = scanner.nextLine().split("\\|");
                    if (data.length == 3) {
                        Task task = new Task(data[0], data[1]);
                        if (Boolean.parseBoolean(data[2])) {
                            task.markAsCompleted();
                        }
                        tasks.add(task);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error loading tasks: " + e.getMessage());
            }
        }
    }
}
