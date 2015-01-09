package bothack.agents.behaviours;

import bothack.agents.NethackAgent;
import jade.core.behaviours.CyclicBehaviour;

/**
 * Created by administrator on 1/8/15.
 */
public class TimerBehaviour extends CyclicBehaviour {
    private long delta;
    public TimerBehaviour(long delta){
        super();
        this.delta = delta;
    }
    @Override
    public void action() {
        if(myAgent instanceof NethackAgent)
        {
            long startTime = ((NethackAgent) myAgent).getLastMessageAccepted();
            long tick = System.currentTimeMillis();
            long difference = (tick - startTime)/1000;
            if(delta == difference){
                ((NethackAgent) myAgent).getNb().quit(null,null,null);
                ((NethackAgent) myAgent).getNb().done();
                ((NethackAgent) myAgent).doDelete();
            }
        }
    }
}
