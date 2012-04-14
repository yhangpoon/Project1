import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * This class starts the program.
 * 
 * @author Yin
 * @author Shawn
 * @author Peter
 */
public class Main {

    /**
     * Main method to start the program.
     * 
     * @param args
     *            - Input arguments
     */
    public static void main(String[] args) {

        Calendar time = Calendar.getInstance();

        ConferenceRoom room = new ConferenceRoom();

        Developer developer1 = new Developer(time, "Developer 1");
        Developer developer2 = new Developer(time, "Developer 2");
        Developer developer3 = new Developer(time, "Developer 3");
        List<Developer> developers1 = new ArrayList<Developer>();
        developers1.add(developer1);
        developers1.add(developer2);
        developers1.add(developer3);
        TeamLeader leader1 = new TeamLeader(time, developers1, room,
                "Leader 1");
        for (Developer developer : developers1) {
            developer.setLeader(leader1);
        }

        Developer developer4 = new Developer(time, "Developer 4");
        Developer developer5 = new Developer(time, "Developer 5");
        Developer developer6 = new Developer(time, "Developer 6");
        List<Developer> developers2 = new ArrayList<Developer>();
        developers2.add(developer4);
        developers2.add(developer5);
        developers2.add(developer6);
        TeamLeader leader2 = new TeamLeader(time, developers2, room,
                "Leader 2");
        for (Developer developer : developers2) {
            developer.setLeader(leader2);
        }

        Developer developer7 = new Developer(time, "Developer 7");
        Developer developer8 = new Developer(time, "Developer 8");
        Developer developer9 = new Developer(time, "Developer 9");
        List<Developer> developers3 = new ArrayList<Developer>();
        developers3.add(developer7);
        developers3.add(developer8);
        developers3.add(developer9);
        TeamLeader leader3 = new TeamLeader(time, developers3, room,
                "Leader 3");
        for (Developer developer : developers3) {
            developer.setLeader(leader3);
        }

        List<TeamLeader> leaders = new ArrayList<TeamLeader>();
        leaders.add(leader1);
        leaders.add(leader2);
        leaders.add(leader3);
        Manager manager = new Manager(time, leaders, room, "Manager");
        for (TeamLeader leader : leaders) {
            leader.setManager(manager);
        }

        manager.start();

        for (TeamLeader leader : leaders) {
            leader.start();
        }
        for (Developer developer : developers1) {
            developer.start();
        }
        for (Developer developer : developers2) {
            developer.start();
        }
        for (Developer developer : developers3) {
            developer.start();
        }
    }
}
