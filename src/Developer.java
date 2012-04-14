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
    public Developer(Calendar time, String name) {
        currentTime = Calendar.getInstance();
        currentTime.set(Calendar.YEAR, Calendar.MONTH, Calendar.DATE, 8, 0);
        this.startTime = time;
        this.arrived = false;
        this.name=name;
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
       Boolean hasGoneToLunch=false;
       Boolean hasGoneToMeeting=false;
       try{
           leader.notifyArrival(this);
       }catch(InterruptedException e){}
       long begin = System.currentTimeMillis();
       while(System.currentTimeMillis()-begin< 4800 || !hasGoneToMeeting){  
           // Ask team leader a question.
           int askQuestion=ran.nextInt(100);
           if (askQuestion==1){
               System.out.println(name+" has asked leader a question");
               leader.answerQuestion();
           }
           // Lunch
           if(!hasGoneToLunch){
               int goToLunch=ran.nextInt(100);
               if(goToLunch<=50){
                   System.out.println(name+" has gone to lunch");
                   int lunchTime=ran.nextInt(300)+300;
                   try {
                    sleep(lunchTime);
                } catch (InterruptedException e) {}
                   finally{
                       hasGoneToLunch=true;
                   }
               }
           }
           
           // Project Status meeting
           if(getTime().get(Calendar.HOUR_OF_DAY)>=16 && !hasGoneToMeeting){
               try{
                   System.out.println(name+" is going to project status meeting");
                   Calendar fourThirty = Calendar.getInstance();
                   fourThirty.set(Calendar.YEAR, Calendar.MONTH, Calendar.DATE, 16, 30);
                   while(getTime().get(Calendar.MINUTE)<30){
                       sleep(10);
                   }
                   hasGoneToMeeting=true;
               }catch(InterruptedException e){}
           }
       }
    }

}
