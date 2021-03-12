package toDoList;

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoListTest {
    private static ToDoList toDoLy = new ToDoList();

    @BeforeAll
    public static void add2TasksToTheList() {
        String input = "task1\nproject1\n2021-05-03";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(inputStream);
        toDoLy.addTask();

        String input2 = "task2\nproject2\n2021-04-15";
        InputStream inputStream2 = new ByteArrayInputStream(input2.getBytes());

        System.setIn(inputStream2);
        toDoLy.addTask();

        String input3 = "task3\nproject1\n2021-04-15";
        InputStream inputStream3 = new ByteArrayInputStream(input3.getBytes());

        System.setIn(inputStream3);
        toDoLy.addTask();
    }

    @Test
    public void checksForCorrectListSize() {
        assertEquals(3, toDoLy.getToDoList().size());
    }

    @Test
    public void checksForCorrectTaskName() {
        assertEquals("task1", toDoLy.getToDoList().get(0).getName());
    }

    @Test
    public void checksForCorrectTaskProject() {
        assertEquals("project2", toDoLy.getToDoList().get(1).getProject());
    }

    @Test
    public void checksForCorrectTaskDueDate() {
        assertEquals("2021-04-15", toDoLy.getToDoList().get(1).getDueDate().toString());
    }

    @Test
    public void userSuccessfullyRemovesTaskByName() {
        String input = "task2";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        assertTrue(toDoLy.removeTask());
    }

    @Test
    public void userGetsErrMsgWhenNameDoesntMatch() {

        String input = "task4";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outputStream));

        toDoLy.findTaskByName();

        String expectedOutput = "You don't have a task with that name." + System.getProperty("line.separator");

        assertEquals(expectedOutput, outputStream.toString());
    }


    @Test
    public void userGetsErrMsgWhenNoTasksHasThatProject() {
        String input = "project450";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String expectedOutput = "\nPlease enter a project name:\n\nNo tasks found for that project!" + System.getProperty("line.separator");

        toDoLy.showTasksByDateOrProject("2");

        assertEquals(expectedOutput, outputStream.toString());

    }


    @Test
    public void userGetsMsgWhenNoTasksHasThatDueDate() {
        String input = "2021-04-16";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String expectedOutput = "\nPlease enter a Due Date (YYYY-MM-DD):\n\nNo tasks found for that due date!" + System.getProperty("line.separator");

        toDoLy.showTasksByDateOrProject("1");

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void returnsTrueWhenTaskIsSuccessfullyMarkedAsDone() {
        String input = "task2";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        assertTrue(toDoLy.markTaskAsDoneOnTheList());
    }

    @Test
    public void returnsFalseWhenTaskisAlreadyMarkedDone() {
        String input = "task1";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        toDoLy.markTaskAsDoneOnTheList();

        String input2 = "task1";
        InputStream inputStream2 = new ByteArrayInputStream(input2.getBytes());
        System.setIn(inputStream2);

        assertFalse(toDoLy.markTaskAsDoneOnTheList());
    }

    @Test
    public void checkCompletedTaskCount() {
        assertEquals(2, toDoLy.completedTasksCount());
    }

    @Test
    public void checkNotCompletedTaskCount() {
        assertEquals(3, toDoLy.notCompletedTasksCount());
    }

}
