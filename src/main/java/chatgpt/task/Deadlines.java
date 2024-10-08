package chatgpt.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import chatgpt.exception.ChatBotException;

/**
 *  The Deadlines class represents a task that has a deadline and
 *  contains both the task description/task name and deadline.
 */
public class Deadlines extends Task {
    /** Represents the deadline of the task **/
    private LocalDateTime deadline;

    /**
     * Constructor of a new deadline task that has the task description, note (if applicable)
     * and deadline of the given task. If there is no note, it is saved
     * as "NA".
     *
     * @param task is the description/task name
     * @param note of the task if applicable otherwise it is "NA"
     * @param deadline is the deadline of the given task
     * @throws ChatBotException if deadline is not given or given in the wrong format
     */
    public Deadlines(String task, String note, String deadline) throws ChatBotException {
        super(task, note);
        if (deadline.equals(" ")) {
            throw new ChatBotException("\t Oh no!![@.@] Deadline cannot be empty"
                    + "\n\t Enter the deadline in the format: deadline <Task> /by <Deadline>");
        }
        try {
            LocalDate date = LocalDate.parse(deadline.split(" ")[1]);
            String hours = deadline.split(" ")[2]
                    .substring(0, 2);
            String minutes = deadline.split(" ")[2]
                    .substring(2);
            LocalTime time = LocalTime.of(Integer.parseInt(hours),
                    Integer.parseInt(minutes));
            this.deadline = LocalDateTime.of(date, time);
        } catch (DateTimeException e) {
            throw new ChatBotException("\t Please enter the deadline in the following format:"
                    + "\n\t yyyy-mm-dd hhmm (e.g 2024-09-05 1440)");
        }

    }

    /**
     * Constructor of a deadline task that may be completed and contains the task description,
     * note (if applicable) and deadline of the given task. If there is no note,
     * it is saved as "NA".
     *
     * @param task is the description/task name
     * @param note of the task if applicable otherwise it is "NA"
     * @param deadline is the deadline of hte given task
     * @throws ChatBotException if deadline is not given or given in the wrong format
     */
    public Deadlines(String task, String note, String deadline,
                     boolean isCompleted) throws ChatBotException {
        super(task, note, isCompleted);
        if (deadline.equals(" ")) {
            throw new ChatBotException("\t Oh no!![@.@] Deadline cannot be empty"
                    + "\n\t Enter the deadline in the format: deadline <Task> /by <Deadline>");
        }
        try {
            LocalDate date = LocalDate.parse(deadline.split(" ")[1]);
            String hours = deadline.split(" ")[2]
                    .substring(0, 2);
            String minutes = deadline.split(" ")[2]
                    .substring(2);
            LocalTime time = LocalTime.of(Integer.parseInt(hours),
                    Integer.parseInt(minutes));
            this.deadline = LocalDateTime.of(date, time);
        } catch (DateTimeException e) {
            throw new ChatBotException("\t Please enter the deadline in the following format:"
                    + "\n\t yyyy-mm-dd hhmm (e.g 2024-09-05 1440)");
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" dd LLL uuuu HH:mm");
        return "[D]" + super.toString()
                + "(by:" + this.deadline.format(formatter) + ")";
    }
    @Override
    public String toPrint() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" uuuu-MM-dd HHmm");
        return "D " + super.toPrint()
                + "|" + this.deadline.format(formatter);
    }
}
