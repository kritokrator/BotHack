package bothack.agents;

import bothack.agents.behaviours.GenericMessageAcceptingBehaviour;
import bothack.agents.behaviours.MapAgentMessageAcceptingBehaviour;
import bothack.classes.VisualInterfaceWrapper;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

import java.util.HashMap;

/**
 * Created by krito on 12/1/14.
 */
public class MapAgent extends Agent {
    private bothack.agents.gui.MapAgent gui;

    @Override
    public void setup(){
        registerMap();
        gui = new bothack.agents.gui.MapAgent(this);
        gui.run();
        addBehaviour(new MapAgentMessageAcceptingBehaviour());
    }
    @Override
    public void takeDown(){
        gui.dispose();
        System.out.printf("MapAgent : agent %s is shutting down.\n", getName());
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        ;
    };

    public bothack.agents.gui.MapAgent getGui() {
        return gui;
    }

    public void setGui(bothack.agents.gui.MapAgent gui) {
        this.gui = gui;
    }

    public void registerMap(){
        DFAgentDescription dfad = new DFAgentDescription();
        dfad.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("dupa");;
        sd.setName(getLocalName());
        dfad.addServices(sd);
        try {
            DFService.register(this,dfad);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }

}
