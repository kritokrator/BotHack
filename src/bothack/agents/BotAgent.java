package bothack.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

/**
 * Created by administrator on 11/21/14.
 */
public class BotAgent extends Agent {

    private AID dungeon;

    @Override
    public void setup(){
        try{
            System.out.println("BothackAgent: prepping up the agent for interaction");
            dungeon = findNethack();
            if(dungeon == null)
                throw new NullPointerException();
            System.out.println("BothackAgent : found nethack dungeon");

        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            System.out.println("BothackAgent: Agent ready for the game");
        }
    }

    @Override
    public void takeDown(){

    }

    public AID findNethack(){
        DFAgentDescription[] result = null;
        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();

        sd.setType("nethack");
        dfAgentDescription.addServices(sd);
        try{
            result = DFService.search(this,dfAgentDescription);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        if(result.length != 1){
            return null;
        }
        else {
            return result[0].getName();
        }
    }
}
