import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

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
    private Manager manager;

    /**
     * The team leader team of developers
     */
    private final HashMap<Developer, Boolean> team = new HashMap<Developer, Boolean>();

    /**
     * Default Constructor.
     * 
     * @param manager
     *            - The assigned project manager
     */
    public TeamLeader(Calendar time, List<Developer> devs) {
        super.startTime = time;
        currentTime = Calendar.getInstance();
        currentTime.set(Calendar.YEAR, Calendar.MONTH, Calendar.DATE, 8, 0);
        this.arrived = false;
        for (int i = 0; i < devs.size(); i++) {
            this.team.put(devs.get(i), false);
        }
    }

    /**
     * Answers Developer's question.
     */
    public void answerQuestion() {
        Random gen = new Random();
        int askMan = gen.nextInt(2);
        if (askMan % 2 == 0) {
            this.manager.answerQuestion();
        } else {
            // TODO
        }
    }

    /**
     * Checks to see of all developers in the team have arrived
     * 
     * @return true if the team has arrived false if the team has not arrived
     */
    private boolean hasTeamArrived() {
        Collection<Boolean> temp = team.values();
        return !temp.contains(false);
    }

    /**
     * Registers the arrival of a team member
     * 
     * @param dev
     *            the developer that has arrived
     */
    public void notifyArrival(Developer dev) {
        this.team.put(dev, true);
    }

    /**
     * Override for the run method in the Thread class.
     */
    @Override
    public void run() {
        // TODO Auto-generated method stub

    }

}
