public class Developer extends Programmer {

    private final TeamLeader leader;

    public Developer(TeamLeader leader) {
        this.leader = leader;
        this.arrived = false;
    }

    public void askQuestion() {
        leader.answerQuestion();
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

    }

}
