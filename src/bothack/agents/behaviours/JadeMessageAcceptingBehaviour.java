package bothack.agents.behaviours;

import bothack.agents.LoginAgent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 * Created by administrator on 12/22/14.
 */
public class JadeMessageAcceptingBehaviour extends CyclicBehaviour {
    private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive(mt);
        if(msg !=null){
            if(myAgent instanceof LoginAgent) {
                System.out.println(myAgent.getName() + " : Received a request");
                JadeRequestProcessingBehaviour jadeRequestProcessingBehaviour = new JadeRequestProcessingBehaviour(msg);
                myAgent.addBehaviour(jadeRequestProcessingBehaviour);
            }
            else{
                System.out.println(myAgent.getName()+" has been given wrong behaviour to execute");
            }

        }
        else {
            block();
        }

    }
}
