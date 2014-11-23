package bothack.agents;

import jade.core.Agent;

/**
 * Created by administrator on 11/21/14.
 */
public class BotAgent extends Agent {
    @Override
    public void setup(){
        try{
            System.out.println("BothackAgent: prepping up the agent for interaction");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            System.out.println("BothackAgent: Agent ready for the game");
        }
    }
}
