package bothack.agents.behaviours;

import bothack.agents.NethackAgent;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.ArrayList;

/**
 * Created by administrator on 12/8/14.
 */
public class visualSubscribersAcceptingBehaviour extends CyclicBehaviour {
    private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.SUBSCRIBE);
    @Override
    public void action() {
        ACLMessage msg = myAgent.receive(mt);
        if(msg != null){
            if(myAgent instanceof NethackAgent) {
                String content = msg.getContent();
                if (content.equals("subscribe")) {
                    System.out.println(myAgent.getName() + " received SUBSCRIBE message from" + msg.getSender().getName());
                    ArrayList<AID> guis = ((NethackAgent) myAgent).getVisualAgents();
                    guis.add(msg.getSender());
                    ((NethackAgent) myAgent).setVisualAgents(guis);
                    for(NethackBehaviour nb : ((NethackAgent) myAgent).getDungeons().values()){
                        myAgent.addBehaviour(new visualizationUpdateBehaviour(nb.getOwner(),nb.getDungeon().getAvatar(),nb.getDungeon().getTheMap()));
                    }
                } else if (content.equals("unsubscribe")) {
                    ArrayList<AID> guis = ((NethackAgent) myAgent).getVisualAgents();
                    guis.remove(msg.getSender());
                    ((NethackAgent) myAgent).setVisualAgents(guis);

                } else {
                    ACLMessage reply = new ACLMessage(ACLMessage.REFUSE);
                    reply.addReceiver(msg.getSender());
                    reply.setInReplyTo(msg.getConversationId());
                    myAgent.send(reply);
                }
            }
            else{
                System.err.println(myAgent.getName() + " is not an instance of NethackAgent. Received suvscribe message for NethackAgent");
            }
        }
        else{
            block();
        }

    }
}
