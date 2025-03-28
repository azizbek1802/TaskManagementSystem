import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        manager.loadTasksFromFile();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nTask Manager\n1. Add Task\n2. Show Tasks\n3. Mark as Completed\n4. Delete Task\n5. Exit\nChoose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    manager.addTask(title, description);
                    break;
                case 2:
                    manager.showTasks();
                    break;
                case 3:
                    System.out.print("Enter task number to mark as completed: ");
                    int completeIndex = scanner.nextInt();
                    manager.markTaskAsCompleted(completeIndex);
                    break;
                case 4:
                    System.out.print("Enter task number to delete: ");
                    int deleteIndex = scanner.nextInt();
                    manager.deleteTask(deleteIndex);
                    break;
                case 5:
                    System.out.println("Exiting Task Manager...");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }

    }
}