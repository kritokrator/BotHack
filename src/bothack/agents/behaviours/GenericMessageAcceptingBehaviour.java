package bothack.agents.behaviours;

import bothack.agents.BotAgent;
import bothack.agents.MapAgent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Created by krito on 11/30/14.
 */
public class GenericMessageAcceptingBehaviour extends CyclicBehaviour {
    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if(msg != null){
            System.out.println(myAgent.getName() + " got message form : " + msg.getSender().getName());
            System.out.println(myAgent.getName() + " message containing : " + msg.getContent());
            if(myAgent instanceof BotAgent){
                ((BotAgent) myAgent).getGui().printOutput(myAgent.getName() + " got message form : " + msg.getSender().getName());
                ((BotAgent) myAgent).getGui().printOutput(myAgent.getName() + " message containing : " + msg.getContent());
            }
            if(myAgent instanceof MapAgent){
                ((BotAgent) myAgent).getGui().printOutput(myAgent.getName() + " got message form : " + msg.getSender().getName());
                ((BotAgent) myAgent).getGui().printOutput(myAgent.getName() + " message containing : " + msg.getContent());
            }
        }
        else{
            block();
        }
    }
}
