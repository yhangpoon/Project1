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
     * Default constructor.
     */
    public ConferenceRoom() {
        this.available = new AtomicInteger(1);
    }
    
    public void projectStatusMeeting() throws InterruptedException{
        this.wait();
    }
    
    /**
     * Return the status of the conference room.
     * 
     * @return available - status of the room
     * @throws InterruptedException 
     */
    public void lockRoom() throws InterruptedException {
        while (!this.available.compareAndSet(1, 0)) {
            this.wait();
        }
        this.wait(150);
        this.available.set(1);
        this.notifyAll();
    }
}