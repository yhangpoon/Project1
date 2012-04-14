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
     * Default Constructor.
     * 
     * @param leader
     *            - Assigned Team Leader
     */
    public Developer(Calendar time, String name, ConferenceRoom room) {
        this.startTime = time;
        this.arrived = false;
        this.name = name;
        this.conferenceRoom = room;
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
        Random ran = new Random();
        Boolean hasGoneToLunch = false;
        Boolean hasGoneToMeeting = false;
        try {
            sleep(ran.nextInt(300));
            System.out.println(getTimeInString() + " " + name
                    + " arrived at the company");
            leader.notifyArrival(this);

        } catch (InterruptedException e) {
        }
        while (System.currentTimeMillis() - startTime.getTimeInMillis() < 4800
                || !hasGoneToMeeting) {
            // Ask team leader a question.
            int askQuestion = ran.nextInt(400000);
            if (askQuestion == 1) {
                System.out.println(getTimeInString() + " " + name
                        + " has asked " + leader.getEmployeeName()
                        + " a question");
                leader.answerQuestion();
            }
            // Lunch
            if (!hasGoneToLunch) {
                int goToLunch = ran.nextInt(200000);
                if (goToLunch == 1) {
                    System.out.println(getTimeInString() + " " + name
                            + " has gone to lunch");
                    int lunchTime = ran.nextInt(300) + 300;
                    try {
                        sleep(lunchTime);
                    } catch (InterruptedException e) {
                    }
                    hasGoneToLunch = true;
                }
            }

            // Project Status meeting
            if (getTime() >= 16 && !hasGoneToMeeting) {
                System.out.println(getTimeInString() + " " + name
                        + " is going to project status meeting");
                try {
                    conferenceRoom.projectStatusMeeting();
                } catch (InterruptedException e) {
                }
                hasGoneToMeeting = true;
            }
        }
        try {
            sleep(ran.nextInt(280));
        } catch (InterruptedException e) {
        }
        System.out.println(getTimeInString() + " " + name + " Leaves work");
    }
}
