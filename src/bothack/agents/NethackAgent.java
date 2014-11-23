package bothack.agents;

import jade.core.Agent;
/**
 * Created by administrator on 11/21/14.
 */
public class NethackAgent extends Agent{

    @Override
    public void setup(){
        try{
            System.out.println("NethackAgent: Prepping up the environment");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            System.out.println("NethackAgent: ready for messages");
        }

    }
}
