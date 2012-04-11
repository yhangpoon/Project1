import java.util.Calendar;
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
     * The time
     */
    private Calendar time;

    /**
     * The Team Leader's project manager.
     */
    private Manager manager;

    /**
     * The team leader team of developers
     */
    private HashMap<Developer, Boolean> team = new HashMap<Developer, Boolean>();
    
    /**
     * Default Constructor.
     * 
     * @param manager
     *            - The assigned project manager
     */
    public TeamLeader(Calendar time, List<Developer> devs) {
        this.time = time;
        this.arrived = false;
        for (int i=0; i<devs.size(); i++){
            this.team.put(devs.get(i), false);
        }
    }

    /**
     * Answers Developer's question.
     */
    public void answerQuestion() {
        Random gen = new Random();
        int askMan = gen.nextInt(2);
        if (askMan%2 == 0){
            this.manager.answerQuestion();
        } else {
            //TODO
        }
    }

    /**
     * Override for the run method in the Thread class.
     */
    @Override
    public void run() {
        // TODO Auto-generated method stub

    }

}
