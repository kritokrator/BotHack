package bothack.agents;

import bothack.agents.behaviours.GenericMessageAcceptingBehaviour;
import bothack.agents.behaviours.ObjectSendingBehaviour;
import bothack.agents.messages.QuitMessage;
import bothack.agents.messages.RequestMessage;
import bothack.agents.messages.SetupMessage;
import bothack.interfaces.Command;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.introspection.ACLMessage;

/**
 * Created by administrator on 11/21/14.
 */
public class BotAgent extends Agent {

    private bothack.agents.gui.BotAgent gui;
    private AID dungeon;

    @Override
    public void setup(){
        try{
            System.out.println("BothackAgent: prepping up the agent for interaction");
            dungeon = findNethack();
            if(dungeon == null)
               throw new NullPointerException();
            System.out.println("BothackAgent : found nethack dungeon");
            gui = new bothack.agents.gui.BotAgent(this);
            gui.run();
            addBehaviour(new GenericMessageAcceptingBehaviour());


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
        gui.dispose();
        System.out.println("Agent : "+ getName()+" is shutting down");

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

    public bothack.agents.gui.BotAgent getGui() {
        return gui;
    }

    public void setGui(bothack.agents.gui.BotAgent gui) {
        this.gui = gui;
    }

    public AID getDungeon() {
        return dungeon;
    }

    public void setDungeon(AID dungeon) {
        this.dungeon = dungeon;
    }
}
