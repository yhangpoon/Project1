import java.util.concurrent.atomic.AtomicInteger;

/**
 * This model describes the conference room.
 * 
 * @author Yin
 * @author Shawn
 * @author Peter
 */
public class ConferenceRoom {

    /**
     * Status of the conference room, whether it is in used or not.
     */
    private AtomicInteger available;
    
    /**
     * Keeps track of the number of people in the conference room.
     */
    private int occupants;

    /**
     * Default constructor.
     */
    public ConferenceRoom() {
        this.available = new AtomicInteger(1);
    }

    /**
     * Use the conference room and locks it.
     */
    public synchronized void useRoom() throws InterruptedException {
        if (occupants <3){
            occupants += 1;
            this.wait();
        } else {
            this.notifyAll();
        }
        this.wait(150);
    }

    /**
     * Release the conference room and unlocks it.
     */
    public void releaseRoom() {
        this.available.set(1);
    }

    /**
     * Return the status of the conference room.
     * 
     * @return available - status of the room
     * @throws InterruptedException 
     */
    public void lockRoom() throws InterruptedException {
        if (!this.available.compareAndSet(1, 0)) {
            this.wait();
        }
    }

}
