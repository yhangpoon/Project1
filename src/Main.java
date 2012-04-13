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

        Developer developer1 = new Developer(time);
        Developer developer2 = new Developer(time);
        Developer developer3 = new Developer(time);
        List<Developer> developers1 = new ArrayList<Developer>();
        developers1.add(developer1);
        developers1.add(developer2);
        developers1.add(developer3);
        TeamLeader leader1 = new TeamLeader(time, developers1, room);

        Developer developer4 = new Developer(time);
        Developer developer5 = new Developer(time);
        Developer developer6 = new Developer(time);
        List<Developer> developers2 = new ArrayList<Developer>();
        developers2.add(developer4);
        developers2.add(developer5);
        developers2.add(developer6);
        TeamLeader leader2 = new TeamLeader(time, developers2, room);

        Developer developer7 = new Developer(time);
        Developer developer8 = new Developer(time);
        Developer developer9 = new Developer(time);
        List<Developer> developers3 = new ArrayList<Developer>();
        developers3.add(developer7);
        developers3.add(developer8);
        developers3.add(developer9);
        TeamLeader leader3 = new TeamLeader(time, developers3, room);

        List<TeamLeader> leaders = new ArrayList<TeamLeader>();
        leaders.add(leader1);
        leaders.add(leader2);
        leaders.add(leader3);
        Manager manager = new Manager(time, leaders);

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
