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
    public TeamLeader(Calendar time, List<Developer> developers,
            ConferenceRoom confRoom, String id) {
        super.startTime = time;
        super.name = id;
        this.conferenceRoom = confRoom;
        this.arrived = false;
        for (int i = 0; i < developers.size(); i++) {
            this.team.put(developers.get(i), false);
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
     * @param man
     *            the one project manager for the company
     */
    public void setManager(Manager manager) {
        this.manager = manager;
    }

    /**
     * Answers Developer's question.
     */
    public synchronized void answerQuestion() {
        if (available) {
            available = false;
            Random gen = new Random();
            int askMan = gen.nextInt(10);
            if (askMan % 2 == 0) {
                System.out.println(getTimeInString() + " " + name
                        + " cant answer the question");
                System.out.println(getTimeInString() + " " + name + " asks "
                        + manager.getEmployeeName() + " the question");
                manager.answerQuestion();
                available = true;
            } else {
                System.out.println(getTimeInString() + ":" + name
                        + " answers the question");
                available = true;
            }
        } else {
            System.out.println(getTimeInString() + ":" + name
                    + " is not available");
            manager.answerQuestion();
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
        team.put(dev, true);
        this.wait();
    }

    /**
     * Notifies everyone to proceed.
     */
    public synchronized void notifyEveryone() {
        this.notifyAll();
    }

    /**
     * Override for the run method in the Thread class.
     */
    @Override
    public void run() {
        // Each event's starting time
        long eventStartTime;

        // Random Number Generator
        Random rand = new Random();

        // Whether the employee has had lunch or not
        boolean ateLunch = false;

        // Arrive Work
        try {
            sleep(rand.nextInt(300));
        } catch (InterruptedException e1) {
            System.err.println(e1.toString());
        }
        arrivalTime = getTime();
        arrived();
        System.out.println(getTimeInString() + " " + name
                + " arrives at the company");

        // Daily 15 minutes morning meeting with manager
        System.out.println(getTimeInString() + " " + name
                + " knocks on manager's door");
        eventStartTime = getTime();
        manager.notifyArrival(this);
        meetingTime += getTime() - eventStartTime;

        // Daily 15 minutes morning meeting with team
        System.out.println(getTimeInString() + " " + name
                + " waits for team members to arrive");
        while (!hasTeamArrived()) {
            try {
                sleep(10);
                waitingTime += 10;
            } catch (InterruptedException e) {
                System.err.print(e.toString());
            }
        }

        // Team has arrived and have daily 15 minutes morning meeting
        System.out.println(getTimeInString() + " " + name
                + " brings team to the conference room");
        eventStartTime = getTime();
        try {
            conferenceRoom.lockRoom();
        } catch (InterruptedException e) {
            System.err.print(e.toString());
        } finally {
            notifyEveryone();
        }
        meetingTime += getTime() - eventStartTime;
        System.out.println(getTimeInString() + " " + name
                + " finishes meeting");

        for (Developer developer : team.keySet()) {
            team.put(developer, false);
        }

        while (hasArrived()) {
            int task = rand.nextInt(550000);

            // Ask Questions
            if (available && task < 1) {
                System.out.println(getTimeInString() + " " + name + " askes "
                        + manager.getEmployeeName() + " the question");
                eventStartTime = getTime();
                manager.answerQuestion();

                waitingTime += getTime() - eventStartTime;
            }

            // Randomly Goes to Lunch
            if (!ateLunch && available && task > 3 && task < 10) {
                available = false;
                System.out.println(getTimeInString() + " " + name
                        + " goes to lunch");

                ateLunch = true;
                try {
                    lunchTime = 300 + rand.nextInt(300);
                    sleep(lunchTime);
                } catch (InterruptedException e) {
                    System.err.print(e.toString());
                }
                available = true;
            }

            // Project Status meeting
            if (available && getTime() >= 4800 && getTime() < 5100) {
                available = false;
                while (!hasTeamArrived()) {
                    try {
                        sleep(10);
                        waitingTime += 10;
                    } catch (InterruptedException e) {
                        System.err.print(e.toString());
                    }
                }
                notifyEveryone();
                System.out.println(getTimeInString() + " " + name
                        + " goes to the project status meeting");
                try {
                    eventStartTime = getTime();
                    manager.notifyArrival(this);
                    conferenceRoom.projectStatusMeeting();
                } catch (InterruptedException e) {
                    System.err.print(e.toString());
                }
                available = true;
                meetingTime += getTime() - eventStartTime;
            }

            // Leave work after 8 hours of work
            if (getTime() - arrivalTime >= 4800) {
                if (getTime() < 5400) {
                    try {
                        sleep(rand.nextInt((int) (5400 - getTime())));
                    } catch (InterruptedException e) {
                        System.err.print(e.toString());
                    }
                }
                officeTime = getTime() - arrivalTime;
                System.out.println(getTimeInString() + " " + name
                        + " leaves work");
                left();
            }

        } // End While Loop

    } // End Run
}
