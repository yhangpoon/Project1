/**
 * This model describes a Team Leader. It extends Programmer.
 * 
 * @author Yin
 * @author Shawn
 * @author Peter
 */
public class TeamLeader extends Employee {

    /**
     * The Team Leader's project manager.
     */
    private final Manager manager;

    /**
     * Default Constructor.
     * 
     * @param manager
     *            - The assigned project manager
     */
    public TeamLeader(Manager manager) {
        this.manager = manager;
        this.arrived = false;
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
        // TODO Auto-generated method stub

    }

}
