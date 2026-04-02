package Source.app;

import Source.model.*;
import Source.service.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        WorkFlowService service = new WorkFlowService();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\nMINI WORKFLOW ENGINE");
            System.out.println("1 Add Task");
            System.out.println("2 Add Dependency");
            System.out.println("3 Complete Task");
            System.out.println("4 List Tasks");
            System.out.println("5 Exit");

            System.out.print("Choice: ");
            String choice = sc.nextLine();

            try {

                switch (choice) {

                    case "1":

                        System.out.print("Task ID: ");
                        String id = sc.nextLine();

                        System.out.print("Task Name: ");
                        String name = sc.nextLine();

                        service.addTask(new Task(id, name));
                        break;

                    case "2":

                        System.out.print("Task ID: ");
                        String task = sc.nextLine();

                        System.out.print("Depends On: ");
                        String dep = sc.nextLine();

                        service.addDependency(task, dep);
                        break;

                    case "3":

                        System.out.print("Task ID: ");
                        service.completeTask(sc.nextLine());
                        break;

                    case "4":

                        service.listTasks();
                        break;

                    case "5":

                        System.out.println("Thank You For Giving Your TIME !!");
                        return;

                    default:
                        System.out.println("Invalid option");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}