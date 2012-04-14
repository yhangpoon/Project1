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
     * Availability of the Leader.
     */
    private boolean available;

    /**
     * The Team Leader's project manager.
     */
    private Manager manager;
    
    /**
     * Stores the time that the team leader arrived.
     */
    private long arivalTime;

    /**
     * The team leader team of developers.
     */
    private final HashMap<Developer, Boolean> team = new HashMap<Developer, Boolean>();

    /**
     * Default Constructor.
     * 
     * @param manager
     *            - The assigned project manager
     */
    public TeamLeader(Calendar time, List<Developer> devs,
            ConferenceRoom confRoom) {
        super.startTime = time;
        this.conferenceRoom = confRoom;
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
     * Checks to see of all developers in the team have arrived.
     * 
     * @return true if the team has arrived false if the team has not arrived
     */
    private boolean hasTeamArrived() {
        Collection<Boolean> temp = team.values();
        return !temp.contains(false);
    }

    /**
     * Registers the arrival of a team member.
     * 
     * @param dev
     *            the developer that has arrived
     * @throws InterruptedException 
     */
    public void notifyArrival(Developer dev) throws InterruptedException {
        this.team.put(dev, true);
        this.wait();
    }

    /**
     * Gets the availability of the Leader.
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Override for the run method in the Thread class.
     */
    @Override
    public void run() {
        
        // TODO wait random
        this.arivalTime = currentTime.getTimeInMillis();
        manager.notifyArrival(this);
        // TODO do manager meeting
        while (!this.hasTeamArrived()) {
            try {
                this.wait(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
            conferenceRoom.lockRoom();
            this.notifyAll();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        boolean atWork = true;
        System.out.println("Team Leader "+Thread.currentThread().getName()
                            +" is hard at work");
        while(atWork){
            Random rand = new Random();
            int task = rand.nextInt(10);
            
            if (task == 0){
                manager.answerQuestion();
            }
        
            //TODO randomly decide to go to lunch
        
            if (currentTime.getTimeInMillis() == 4800) {
                //TODO meeting at 4:00
            }
        
            if (currentTime.getTimeInMillis() - arivalTime > 4800) {
                //TODO leave after 8 Hours
                atWork = false;
            }
        }
    }
}
