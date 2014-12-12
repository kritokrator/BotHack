package bothack.agents;

import bothack.agents.behaviours.NethackBehaviour;
import bothack.agents.behaviours.NethackMessageAcceptingBehaviour;
import bothack.agents.behaviours.visualSubscribersAcceptingBehaviour;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by administrator on 11/21/14.
 */
public class NethackAgent extends Agent{
    private HashMap<String,NethackBehaviour> dungeons;
    private ArrayList<AID> guis;
    public HashMap<String, NethackBehaviour> getDungeons() {
        return dungeons;
    }

    public ArrayList<AID> getGuis() {
        return guis;
    }

    public void setGuis(ArrayList<AID> guis) {
        this.guis = guis;
    }

    public void setDungeons(HashMap<String, NethackBehaviour> dungeons) {
        this.dungeons = dungeons;
    }

    @Override
    public void setup(){
        try{
            System.out.println("NethackAgent: Prepping up the environment");
            dungeons = new HashMap<String, NethackBehaviour>();
            guis = new ArrayList<AID>();
            registerNethack();
            addBehaviour(new NethackMessageAcceptingBehaviour());
            addBehaviour(new visualSubscribersAcceptingBehaviour());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            System.out.println("NethackAgent: ready for messages");
        }
    }

    @Override
    protected void takeDown(){
        System.out.println("NethackAgent : Shutting down");
        deregisterNethack();
        for(NethackBehaviour nb : dungeons.values()){
            nb.quit();
            nb.done();
            dungeons.remove(nb.getOwner());
        }
    }

    public void registerNethack(){
        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.setName(this.getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("nethack");
        sd.setName(this.getLocalName()+"-nethack");
        dfAgentDescription.addServices(sd);
        try{
            DFService.register(this, dfAgentDescription);
        }
        catch(Exception e){
            System.out.println("NethackAgent: NethackService failed to register");
            e.printStackTrace();
        }
        finally{
            System.out.println("NethackAgent: NethackService registered");
        }

    }

    public void deregisterNethack(){
        try{
            DFService.deregister(this);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
