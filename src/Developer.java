import java.util.Calendar;
import java.util.Random;

/**
 * This model describes the Developer. It extends Programmer.
 * 
 * @author Yin
 * @author Shawn
 * @author Peter
 */
public class Developer extends Employee {

    /**
     * The Developer's Team Leader.
     */
    private TeamLeader leader;

    /**
     * The length of the developers meetings.
     */
    private final long meetingDuration;

    /**
     * Default Constructor.
     * 
     * @param leader
     *            - Assigned Team Leader
     */
    public Developer(Calendar time, String name, ConferenceRoom room) {
        this.lunchTime = 0l;
        this.waitingTime = 0l;
        this.meetingTime = 0l;
        this.workingTime = 0l;
        this.officeTime = 0l;
        this.arrived = false;
        this.name = name;
        this.startTime = time;
        this.conferenceRoom = room;
        this.meetingDuration = 150l;
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
     * Set the leader.
     */
    public void setLeader(TeamLeader leader) {
        this.leader = leader;
    }

    /**
     * Override for the run method in the Thread class.
     */
    @Override
    public void run() {
        // Random number generator
        Random rand = new Random();

        // Each event's starting time
        long eventStartTime;

        // Whether the developer has had lunch or not
        Boolean ateLunch = false;

        // Whether the developer has been to the status meeting or not.
        Boolean hadStatusMeeting = false;

        // Arrive work
        try {
            sleep(rand.nextInt(300));
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
        arrivalTime = getTime();
        arrived();
        System.out.println(getTimeInString() + " " + name
                + " arrives at the company");

        // Daily 15 minutes morning meeting
        eventStartTime = getTime();
        try {
            System.out.println(getTimeInString() + " " + name + " notifies "
                    + leader.name + " of arrival");
            leader.notifyArrival(this);

            /*
             * Leader should print out that he ended the meeting before I print
             * that I have left.
             */
            sleep(1);
            System.out.println(getTimeInString() + " " + name
                    + " returns from the standup meeting");
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
        waitingTime += getTime() - eventStartTime - meetingDuration;
        meetingTime += meetingDuration;

        while (hasArrived()) {
            // A random number that allows tasks to occur.
            int task = rand.nextInt(400000);

            // Randomly Goes to Lunch
            if (!ateLunch) {
                // The developer needs to go to lunch by 2PM=3600ms
                if (task == 1 || getTime() >= 3600) {
                    System.out.println(getTimeInString() + " " + name
                            + " goes to lunch");
                    lunchTime = rand.nextInt(300) + 300;
                    try {
                        sleep(lunchTime);
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                    System.out.println(getTimeInString() + " " + name
                            + " returns from lunch");
                    ateLunch = true;
                }
            }

            // Ask team leader a question.
            if (task == 2) {
                System.out.println(getTimeInString() + " " + name + " askes "
                        + leader.getEmployeeName() + " a question");
                eventStartTime = getTime();
                leader.answerQuestion();
                waitingTime += getTime() - eventStartTime;
            }

            // Project Status meeting
            if (getTime() >= 4800 && !hadStatusMeeting) {
                System.out.println(getTimeInString() + " " + name
                        + " goes to the project status meeting");
                eventStartTime = getTime();
                try {
                    leader.notifyArrival(this);
                    conferenceRoom.projectStatusMeeting();
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
                hadStatusMeeting = true;
                waitingTime += getTime() - eventStartTime - meetingDuration;
                meetingTime += meetingDuration;
                try {
                    /*
                     * Manager should print out that he ended the meeting before
                     * I print that I have left.
                     */
                    sleep(1);
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
                System.out.println(getTimeInString() + " " + name
                        + " returns from the status meeting");
            }

            // Leave work after 8 hours of work
            if (hadStatusMeeting && getTime() - arrivalTime >= 4800) {
                if (getTime() < 5400) {
                    try {
                        sleep(rand.nextInt((int) (5400 - getTime())));
                    } catch (InterruptedException e) {
                        System.err.print(e.getMessage());
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
