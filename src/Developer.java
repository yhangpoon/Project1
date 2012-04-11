import java.util.Calendar;

/**
 * This model describes the Developer. It extends Programmer.
 * 
 * @author Yin
 * @author Shawn
 * @author Peter
 */
public class Developer extends Employee {

    /**
     * The time
     */
    private Calendar time;

    /**
     * The Developer's Team Leader
     */
    private final TeamLeader leader;

    /**
     * Default Constructor.
     * 
     * @param leader
     *            - Assigned Team Leader
     */
    public Developer(TeamLeader leader, Calendar time) {
        this.leader = leader;
        this.time = time;
        this.arrived = false;
    }

    /**
     * Ask Team Leader or Project Manager question. Question will first go to
     * Team Leader if he/she is available; otherwise, it will go to the Project
     * Manager.
     */
    public void askQuestion() {
        leader.answerQuestion();
    }

    /**
     * Override for the run method in the Thread class.
     */
    @Override
    public void run() {
        // TODO Auto-generated method stub

    }

}
