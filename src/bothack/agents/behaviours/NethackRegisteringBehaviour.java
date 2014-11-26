package bothack.agents.behaviours;

import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

/**
 * Created by krito on 11/23/14.
 */
public class NethackRegisteringBehaviour extends OneShotBehaviour {
    @Override
    public void action() {
        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.setName(this.myAgent.getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("nethack");
        sd.setName(this.myAgent.getLocalName()+"-nethack");
        dfAgentDescription.addServices(sd);
        try{
            DFService.register(this.myAgent,dfAgentDescription);
        }
        catch(Exception e){
            System.out.println("NethackAgent: NethackService failed to register");
            e.printStackTrace();
        }
        finally{
            System.out.println("NethackAgent: NethackService registered");
        }
    }
}
