package bothack.agents;

import bothack.agents.behaviours.GenericMessageAcceptingBehaviour;
import bothack.agents.behaviours.MapAgentMessageAcceptingBehaviour;
import bothack.classes.VisualInterfaceWrapper;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

import java.util.HashMap;

/**
 * Created by krito on 12/1/14.
 */
public class MapAgent extends Agent {
    private AID dungeon;
    private HashMap<String,VisualInterfaceWrapper> players;
    private bothack.agents.gui.MapAgent gui;

    public HashMap<String, VisualInterfaceWrapper> getPlayers() {
        return players;
    }

    public void setPlayers(HashMap<String, VisualInterfaceWrapper> players) {
        this.players = players;
    }

    @Override
    public void setup(){
        dungeon = findNethack();
        if(dungeon == null){
            throw new NullPointerException();
        }
        players = new HashMap<String, VisualInterfaceWrapper>();
        gui = new bothack.agents.gui.MapAgent(this);
        gui.run();
        subscribeToNethack();
        addBehaviour(new MapAgentMessageAcceptingBehaviour());
    }
    @Override
    public void takeDown(){
        gui.dispose();
        System.out.printf("MapAgent : agent %s is shutting down.\n", getName());
    };

    public void subscribeToNethack(){
        ACLMessage msg = new ACLMessage(ACLMessage.SUBSCRIBE);
        msg.addReceiver(dungeon);
        msg.setContent("subscribe");
        send(msg);
    }
    public AID findNethack(){
        DFAgentDescription[] result = null;
        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();

        sd.setType("nethack");
        dfAgentDescription.addServices(sd);
        try{
            result = DFService.search(this, dfAgentDescription);
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

    public AID getDungeon() {
        return dungeon;
    }

    public void setDungeon(AID dungeon) {
        this.dungeon = dungeon;
    }

    public bothack.agents.gui.MapAgent getGui() {
        return gui;
    }

    public void setGui(bothack.agents.gui.MapAgent gui) {
        this.gui = gui;
    }
}
