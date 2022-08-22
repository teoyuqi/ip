package duke.task;

public class ToDo extends Task {
    /**
     * Constructor method for `Event`.
     * @param description Description of task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor method for `Event`.
     * @param description Description of task.
     * @param isDone      Whether task is done.
     */
    public ToDo(String description, Boolean isDone) {
        super(description, isDone);
    }

    /**
     * Override 'toString' method to return status and description of 'ToDo'
     * object.
     * @return [T][COMPLETION STATUS][TASK DESCRIPTION]
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * To produce a String with "|" delimiters for storing the task's data
     * into a text file.
     * @return "todo|[COMPLETION STATUS]|[TASK DESCRIPTION]|"
     */
    public String toFileFormat() {
        return "todo" + "|" + super.toFileFormat();
    }

}