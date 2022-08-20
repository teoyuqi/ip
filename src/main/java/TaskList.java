package main.java;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    /**
     * 'List' object attribute to store String inputs given.
     */
    private static final List<Task> taskList = new ArrayList<>();

    /**
     * Get description as String from input.
     * @param input String of words given by user as input
     * @return      Return description of input task.
     */
    private String getDescription(String input) {
        if (input.startsWith("deadline")) {
            return input.substring(9, input.indexOf("/by ") - 1);
        } else if (input.startsWith("event")) {
            return input.substring(6, input.indexOf("/at ") - 1);
        } else if (input.startsWith("todo")){
            return input.substring(5);
        }
        return null;
    }

    /**
     * Get date as String from input.
     * @param input String of words given by user as input
     * @return      Return date of input task.
     */
    private String getDate(String input) {
        if (input.startsWith("deadline")) {
            return input.substring(input.indexOf("/by ") + 4);
        } else if (input.startsWith("event")) {
            return input.substring(input.indexOf("/at ") + 4);
        }
        return null;
    }

    /**
     * Method to add String input to 'inputList'.
     * @param input String object to be added to 'inputList'.
     */
    public void add(String input) {
        String[] inputArr = input.split(" ");

        Task newTask = null;
        switch (inputArr[0]) {
            case "deadline":
                newTask = new Deadline(getDescription(input), getDate(input));
                break;
            case "event":
                newTask = new Event(getDescription(input), getDate(input));
                break;
            case "todo":
                newTask = new ToDo(getDescription(input));
                break;
        }

        taskList.add(newTask);
        int size = this.getSize();
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + newTask);
        System.out.printf("Now you have %d task%s in the list.%n%n",
                size, size == 1 ? "" : "s");
    }


    /**
     * Method to delete a task.
     * @param input String user input command to delete a task.
     */
    public void delete(String input) {
        int index = Integer.parseInt(input.split(" ")[1]);
        String taskToDelete = taskList.get(index).toString();

        taskList.remove(index);
        int size = this.getSize();

        System.out.println("Got it. I've removed this task:");
        System.out.println("   " + taskToDelete);
        System.out.printf("Now you have %d task%s in the list.%n%n",
                size, size == 1 ? "" : "s");
    }

    /**
     * Override 'toString' method of 'TaskList' object.
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(
                String.format("Here are the tasks in your list:%n"));
        for (int i = 0; i < taskList.toArray().length; i++) {
            res.append(String.format("%d. %s%n", i + 1, taskList.get(i)));
        }
        return res.toString();
    }

    /**
     * Mark 'Task' at given index as done.
     * @param index Index of task to be done. 1 based indexing.
     */
    public void markDone(int index) {
        taskList.get(index - 1).markDone();
        System.out.printf("Nice! I've marked this task as done:%n   %s%n%n",
                taskList.get(index - 1));
    }

    /**
     * Mark 'Task' at given index as undone.
     * @param index Index of task to be undone. 1 based indexing.
     */
    public void markUnDone(int index) {
        taskList.get(index - 1).markUnDone();
        System.out.printf(
                "OK, I've marked this task as not done yet:%n   %s%n%n",
                taskList.get(index - 1));
    }

    /**
     * Method to get number of tasks in 'taskList'.
     * @return Number of tasks in 'taskList'.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Return 'taskList' as format to be saved in hard disk.
     * @return String of 'taskList' as format to be saved in file.
     */
    public String toFile() {
        StringBuilder res = new StringBuilder();
        for (Task task : taskList) {
            res.append(task.toFileFormat() + "\n");
        }
        return res.toString();
    }

    /**
     * Method to add tasks from file to `taskList`.
     * @param dataArgs Array containing details of task to be
     *                 added to `inputList`.
     */
    public void addFromFile(String[] dataArgs) {
        Boolean isDone = dataArgs[1].equals("true");
        Task newTask = null;

        switch (dataArgs[0]) {
        case ("deadline"):
            newTask = new Deadline(dataArgs[2], dataArgs[3], isDone);
            break;

        case ("event"):
            newTask = new Event(dataArgs[2], dataArgs[3], isDone);
            break;

        case ("todo"):
            newTask = new ToDo(dataArgs[2], isDone);
            break;
        }
        taskList.add(newTask);
    }
}