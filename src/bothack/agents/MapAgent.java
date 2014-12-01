package bothack.agents;

import bothack.agents.behaviours.GenericMessageAcceptingBehaviour;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

/**
 * Created by krito on 12/1/14.
 */
public class MapAgent extends Agent {
    private AID dungeon;
    private bothack.agents.gui.MapAgent gui;

    @Override
    public void setup(){
        dungeon = findNethack();
        if(dungeon == null){
            throw new NullPointerException();
        }
        addBehaviour(new GenericMessageAcceptingBehaviour());
    }
    @Override
    public void takeDown(){
        gui.dispose();
        System.out.printf("MapAgent : agent %s is shutting down.\n", getName());
    };

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
