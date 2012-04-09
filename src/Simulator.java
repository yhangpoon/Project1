import java.util.Calendar;

/**
 * 
 */

/**
 * @author sst8696
 * 
 */
public class Simulator {

    private static Calendar beginCalendar;
    private static Calendar simCalendar;
    private static Simulator thisSimulator;

    public Simulator getSimulator() {
        if (thisSimulator == null) {
            return thisSimulator = new Simulator();
        }
        return thisSimulator;
    }

    private Simulator() {
        beginCalendar = Calendar.getInstance();
        simCalendar = Calendar.getInstance();
        simCalendar.set(beginCalendar.YEAR, beginCalendar.MONTH,
                beginCalendar.DATE, 8, 0);
    }

    public static Calendar getTime() {
        Calendar currentCalendar = Calendar.getInstance();
        int simulatedTime = (int) ((currentCalendar.getTimeInMillis() - beginCalendar
                .getTimeInMillis()) / 10);

        simulatedTime = simulatedTime * 60 * 1000;

        simCalendar.setTimeInMillis(simulatedTime
                + simCalendar.getTimeInMillis());
        return simCalendar;

    }
}
