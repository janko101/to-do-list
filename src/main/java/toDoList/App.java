/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package toDoList;

import java.util.Arrays;
import java.util.Scanner;

/**
 * This is main class of the project
 *
 * @author Janko
 * @version 1.0
 * @since 2021-03-11
 **/
public class App {
    public static String filename = "todolisty.obj";


    /**
     * main method to run the command line based "ToDoList" application
     *
     * @param args array of String holding command line parameters
     */
    public static void main(String[] args) {
        ToDoList toDoListy = new ToDoList();
        String choice = "0";

        try {
            toDoListy.readFromFile(filename);

            Scanner userInput = new Scanner(System.in);

            while (!choice.equals("6")) {
                Menus.mainMenu(toDoListy.completedTasksCount(), toDoListy.notCompletedTasksCount());
                choice = userInput.nextLine();

                switch (choice) {
                    case "1" -> {
                        toDoListy.showFormattedList();
                        Menus.showListMenu();
                        toDoListy.showTasksByDateOrProject(userInput.nextLine());
                    }
                    case "2" -> {
                        toDoListy.addTask();
                        toDoListy.showFormattedList();
                    }

                    case "3" -> {
                        toDoListy.showFormattedList();
                        toDoListy.removeTask();
                        toDoListy.showFormattedList();
                    }
                    case "4" -> {
                        toDoListy.showFormattedList();
                        toDoListy.markTaskAsDoneOnTheList();
                        toDoListy.showFormattedList();
                    }
                    case "5" -> {
                        toDoListy.showFormattedList();
                        Menus.showEditTaskMenu();
                        toDoListy.editTask(userInput.nextLine(), userInput);
                        toDoListy.showFormattedList();
                    }
                    case "6" -> {
                    }
                    default -> System.out.println("\nWe don't have that choice, please type a number from given choices!");
                    // Thread.sleep(3000);
                }
            }

            toDoListy.writeToFile(filename);

            System.out.println("\nYour ToDoListy is saved! Bye, Bye...");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

    }
}
