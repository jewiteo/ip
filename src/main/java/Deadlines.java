import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    private LocalDateTime deadline;

    public Deadlines(String task, String deadline) {
        super(task);
        if (deadline.equals(" ")){
            throw new IllegalArgumentException("\t Oh no!![@.@] Deadline cannot be empty" +
                    "\n\t Enter the deadline in the format: deadline <Task> /by <Deadline>");
        }
        try {
            LocalDate date = LocalDate.parse(deadline.split(" ")[1]);
            String hours = deadline.split(" ")[2].substring(0,2);
            String minutes = deadline.split(" ")[2].substring(2);
            LocalTime time = LocalTime.of(Integer.valueOf(hours),
                    Integer.valueOf(minutes));
            this.deadline = LocalDateTime.of(date, time);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("\t Please enter the deadline in the following format:" +
                    "\n\t yyyy-mm-dd hhmm (e.g 2024-09-05 1440)");
        }

    }

    public Deadlines(String task, String deadline, Boolean isCompleted) {
        super(task, isCompleted);
        if (deadline.equals(" ")){
            throw new IllegalArgumentException("\t Oh no!![@.@] Deadline cannot be empty" +
                    "\n\t Enter the deadline in the format: deadline <Task> /by <Deadline>");
        }
        try {
            LocalDate date = LocalDate.parse(deadline.split(" ")[1]);
            String hours = deadline.split(" ")[2].substring(0,2);
            String minutes = deadline.split(" ")[2].substring(2);
            LocalTime time = LocalTime.of(Integer.valueOf(hours),
                    Integer.valueOf(minutes));
            this.deadline = LocalDateTime.of(date, time);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("\t Please enter the deadline in the following format:" +
                    "\n\t yyyy-mm-dd hhmm (e.g 2024-09-05 1440)");
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" dd LLL uuuu HH:mm");
        return "[D]" + super.toString() +
                "(by:" + this.deadline.format(formatter) + ")";
    }
    @Override
    public String toPrint() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" uuuu-MM-dd HHmm");
        return "deadline " + super.toPrint() +
                "|" + this.deadline.format(formatter);
    }
}
