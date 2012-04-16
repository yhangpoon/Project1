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
            ConferenceRoom confRoom, String id) {
        super.startTime = time;
        super.name = id;
        this.conferenceRoom = confRoom;
        this.arrived = false;
        for (int i = 0; i < devs.size(); i++) {
            this.team.put(devs.get(i), false);
        }
        this.available = true;
        this.workingTime = 0l;
        this.lunchTime = 0l;
        this.waitingTime = 0l;
        this.meetingTime = 0l;
    }
    
    /**
     * Sets the manager for this team leader.
     * 
     * @param man the one project manager for the company
     */
    public void setManager(Manager man) {
        this.manager = man;
    }

    /**
     * Answers Developer's question.
     */
    public synchronized void answerQuestion() {
        if (this.available) {
            this.available = false;
            Random gen = new Random();
            int askMan = gen.nextInt(10);
            if (askMan % 2 == 0) {
                System.out.println(getTimeInString() + " " + this.name
                        + " cant answer the question");
                System.out.println(getTimeInString() + " " + this.name
                        + " asks " + manager.getEmployeeName()
                        + " the question");
                this.manager.answerQuestion();
                this.available = true;
            } else {
                System.out.println(getTimeInString() + ":" + this.name
                        + " answers the question");
                this.available = true;
            }
        } else {
            System.out.println(getTimeInString() + ":" + this.name
                    + " is not available");
            this.manager.answerQuestion();
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
    public synchronized void notifyArrival(Developer dev)
            throws InterruptedException {
        this.team.put(dev, true);
        this.wait();
    }

    public synchronized void endMeeting() {
        this.notifyAll();
    }

    /**
<<<<<<< HEAD
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
        long tempStart;
        long tempEnd;
        Random rand = new Random();
        try {
            sleep(rand.nextInt(300));
        } catch (InterruptedException e1) {
            System.err.println(e1.toString());
        }
        this.arrivalTime = getTime();
        System.out.println(getTimeInString() + " " + this.name
                + " arrives at the company");
        System.out.println(getTimeInString() + " " + this.name
                + " knocks on manager's door");
        tempStart = getTime();
        manager.notifyArrival(this);
        tempEnd = getTime();
        this.meetingTime += tempEnd - tempStart;
        System.out.println(getTimeInString() + " " + this.name
                + " waits for team members to arrive");
        while (!this.hasTeamArrived()) {
            try {
                sleep(10);
                this.waitingTime += 10;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
            System.out.println(getTimeInString() + " " + this.name
                    + " brings team to the conference room");
            tempStart = getTime();
            conferenceRoom.lockRoom();
            this.endMeeting();
            tempEnd = getTime();
            this.meetingTime += tempEnd - tempStart;
            System.out.println(getTimeInString() + " " + this.name
                    + " finishes meeting");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        boolean atWork = true;
        boolean hadUpdateMeeting = false;
        boolean ateLunch = false;
        System.out.println(getTimeInString() + " " + this.name
                + " is hard at work");
        while (atWork) {
            int task = rand.nextInt(600000);

            // check to see if its time for the update meeting.
            if (this.available && !hadUpdateMeeting && getTime() >= 4800) {
                // TODO meeting at 4:00
                this.available = false;
                System.out.println(getTimeInString() + " " + this.name
                        + " goes to update meeting");
                try {
                    tempStart = getTime();
                    conferenceRoom.projectStatusMeeting();
                    tempEnd = getTime();
                    this.meetingTime += tempEnd - tempStart;
                    hadUpdateMeeting = true;
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                this.available = true;
            }

            // asking questions
            if (this.available && task < 1) {
                System.out.println(getTimeInString() + " " + this.name
                        + " askes " + manager.getEmployeeName()
                        + " the question");
                tempStart = getTime();
                manager.answerQuestion();
                tempEnd = getTime();
                this.waitingTime += tempEnd - tempStart;
            }

            // TODO randomly decide to go to lunch
            if (!ateLunch && this.available && task > 3 && task < 10) {
                this.available = false;
                System.out.println(getTimeInString() + " " + this.name
                        + " goes to lunch");

                ateLunch = true;
                try {
                    this.lunchTime = 30 + rand.nextInt(31);
                    sleep(this.lunchTime);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                this.available = true;
            }

            if (hadUpdateMeeting && getTime() - this.arrivalTime >= 4800) {
                // TODO leave after 8 Hours
                try {
                    sleep(rand.nextInt(280));
                    this.officeTime = getTime() - this.arrivalTime;
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(getTimeInString() + " " + this.name
                        + " leaves work");
                atWork = false;
            }
        }
    }
}
