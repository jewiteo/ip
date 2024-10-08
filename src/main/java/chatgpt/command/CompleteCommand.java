package chatgpt.command;

import chatgpt.exception.ChatBotException;
import chatgpt.storage.Storage;
import chatgpt.task.Task;
import chatgpt.task.TaskList;
import chatgpt.ui.Ui;

/**
 *  The CompleteCommand class represents a command to mark a Task from the TaskList
 *  as complete/incomplete.
 */
public class CompleteCommand extends Command {
    /** Represents whether the task has been completed **/
    private boolean isCompleted;
    /** Represents the index of the class within the TaskList **/
    private int index;

    /**
     * The constructor for an CompleteCommand that marks the given task as
     * complete or incomplete.
     *
     * @param index of the task within the TaskList
     * @param isCompleted represents the new status of the task
     */
    public CompleteCommand(int index, boolean isCompleted) {
        this.index = index;
        this.isCompleted = isCompleted;
    }

    /**
     * {@inheritDoc}
     *
     * In CompleteCommand, it gets the task at the given index from the TaskList
     * and marks it as complete/incomplete.
     *
     * @param tasks that tracks the application's tasks
     * @param ui that handles the printing and reading on inputs and outputs
     * @param storage that handles saving and reading text file with saved data
     * @return String to show that a task has been set as complete/incomplete
     * @throws ChatBotException if index is out of bounds
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws ChatBotException {
        Task task = updateCompletion(tasks);
        storage.save(tasks);
        if (isCompleted) {
            return ui.showCompleteTask(task);
        } else {
            return ui.showUncompleteTask(task);
        }
    }

    private Task updateCompletion(TaskList tasks) throws ChatBotException {
        if (index > tasks.size()) {
            throw new ChatBotException("\tNo task exists for that index");
        }
        Task task = tasks.get(index - 1);
        task.setCompleted(isCompleted);
        return task;
    }
}
