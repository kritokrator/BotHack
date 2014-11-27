package bothack.agents;

import bothack.agents.behaviours.MessageAcceptingBehaviour;
import bothack.agents.behaviours.NethackRegisteringBehaviour;
import bothack.classes.Nethack;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

/**
 * Created by administrator on 11/21/14.
 */
public class NethackAgent extends Agent{
    private Nethack game;

    public Nethack getGame() {
        return game;
    }

    public void setGame(Nethack game) {
        this.game = game;
    }

    @Override
    public void setup(){
        try{
            System.out.println("NethackAgent: Prepping up the environment");
            game = new Nethack();
            registerNethack();
            addBehaviour(new MessageAcceptingBehaviour());
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
