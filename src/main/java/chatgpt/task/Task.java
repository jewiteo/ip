package chatgpt.task;

import chatgpt.exception.ChatBotException;

/**
 * The Task class represents the abstract template for all Tasks to follow.
 */
public abstract class Task {
    /** description/task name **/
    private String task;
    /** Status on whether the task has been completed **/
    private boolean isCompleted;
    /** note of the task **/
    private String note;

    /**
     * Constructor of new task given the description and note (if applicable),
     * whereby default the task is not completed. If there is no note, it is saved
     * as "NA".
     *
     * @param task is the description/task name
     * @param note of the task if applicable otherwise it is "NA"
     * @throws IllegalArgumentException if the description of the task is empty
     */
    public Task(String task, String note) throws IllegalArgumentException {
        if (task.isEmpty() || task.equals(" ")) {
            throw new IllegalArgumentException("\tOh no!!(0o0) Description cannot be empty");
        }
        this.task = task;
        this.note = note;
        this.isCompleted = false;
    }

    /**
     * Constructor of new task given the description, note (if applicable) and
     * completion status. f there is no note, it is saved as "NA".
     *
     * @param task is the description/task name
     * @param note of the task if applicable otherwise it is "NA"
     * @throws IllegalArgumentException if the description of the task is empty
     */
    public Task(String task, String note, Boolean isCompleted) throws IllegalArgumentException {
        if (task.isEmpty() || task.equals(" ")) {
            throw new IllegalArgumentException("\tOh no!!(0o0) Description cannot be empty");
        }
        this.task = task;
        this.note = note;
        this.isCompleted = isCompleted;
    }

    /**
     * Sets the completion status of the task to the given boolean.
     *
     * @param isCompleted represents the new completion status of the task
     * @throws ChatBotException if the task already has the same status as the input
     */
    public void setCompleted(boolean isCompleted)
            throws ChatBotException {
        if (this.isCompleted == isCompleted) {
            throw new ChatBotException("\tIt seems the task has already been marked as such");
        }
        this.isCompleted = isCompleted;
    }

    /**
     * Returns the task as a String in the appropriate format for displaying.
     *
     * @return task as a string in the appropriate format for displaying
     */
    @Override
    public String toString() {
        return isCompleted ? "[X] " + task : "[ ] " + task;
    }

    /**
     * Returns the task as a String in the appropriate format for saving.
     *
     * @return task as a string in the appropriate format for saving
     */
    public String toPrint() {
        String s = task + "|" + note;
        return isCompleted ? "|1|" + s : "|0|" + s;
    }

    /**
     * Returns the note of the task formatted as a String.
     * If there is no notes, a default message is returned.
     *
     * @return task as a string in the appropriate format for saving
     */
    public String toShowNote() {
        if (note.equals("NA")) {
            return "No note has been added to this task";
        } else {
            return "Short note of the task: " + note.trim();
        }
    }
}
