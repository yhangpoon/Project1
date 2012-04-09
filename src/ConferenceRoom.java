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
    private boolean available;

    /**
     * Default constructor.
     */
    public ConferenceRoom() {
        this.available = false;
    }

    /**
     * Use the conference room and locks it.
     */
    public void useRoom() {
        this.available = false;
    }

    /**
     * Release the conference room and unlocks it.
     */
    public void releaseRoom() {
        this.available = true;
    }

    /**
     * Return the status of the conference room.
     * 
     * @return available - status of the room
     */
    public boolean available() {
        return this.available;
    }

}
