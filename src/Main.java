/**
 * This class starts the program.
 * 
 * @author Yin
 * @author Shawn
 * @author Peter
 */
public class Main {

    /**
     * Main method to start the program
     * 
     * @param args
     *            - Input arguments
     */
    public static void main(String[] args) {

        Manager manager = new Manager();

        TeamLeader leader1 = new TeamLeader(manager);
        Developer developer1 = new Developer(leader1);
        Developer developer2 = new Developer(leader1);
        Developer developer3 = new Developer(leader1);

        TeamLeader leader2 = new TeamLeader(manager);
        Developer developer4 = new Developer(leader2);
        Developer developer5 = new Developer(leader2);
        Developer developer6 = new Developer(leader2);

        TeamLeader leader3 = new TeamLeader(manager);
        Developer developer7 = new Developer(leader3);
        Developer developer8 = new Developer(leader3);
        Developer developer9 = new Developer(leader3);

    }

}
