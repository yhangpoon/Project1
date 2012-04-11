import java.util.Calendar;

/**
 * This model describes the Project Manager.
 * 
 * @author Yin
 * @author Shawn
 * @author Peter
 */
public class Manager extends Employee {

    /**
     * The time
     */
    private Calendar time;

    /**
     * Default Constructor.
     */
    public Manager(Calendar time) {
        this.time = time;
    }

    /**
     * Answers Developer's question.
     */
    public void answerQuestion() {
        // TODO
    }

    /**
     * Override for the run method in the Thread class.
     */
    @Override
    public void run() {

    }
}
