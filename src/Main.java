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

        Simulator simulator = Simulator.getSimulator();
        Manager manager = new Manager();
        Employee employee1 = new TeamLeader(manager);

    }

}
