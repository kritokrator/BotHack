package bothack.agents.behaviours;

import bothack.agents.MapAgent;
import bothack.agents.messages.QuitMessage;
import bothack.classes.VisualInterfaceWrapper;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

/**
 * Created by administrator on 12/8/14.
 */
public class MapAgentMessageAcceptingBehaviour extends CyclicBehaviour{
    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if(msg != null){
            if(myAgent instanceof MapAgent){
                if(msg.getPerformative() == ACLMessage.INFORM){
                    try {
                        JAXBContext context = JAXBContext.newInstance(VisualInterfaceWrapper.class, QuitMessage.class);
                        Unmarshaller unmarshaller = context.createUnmarshaller();
                        StringReader reader = new StringReader(msg.getContent());
                        Object content = unmarshaller.unmarshal(reader);
                        if(content instanceof VisualInterfaceWrapper){
                            //((MapAgent) myAgent).getGui().updatePlayers(players);
                            ((MapAgent) myAgent).getGui().updatePlayer(((VisualInterfaceWrapper) content).getOwner(),(VisualInterfaceWrapper)content);
                        }
                        else if(content instanceof QuitMessage){
                            ((MapAgent) myAgent).getGui().removePlayer(((QuitMessage) content).getSender());
                        }
                        else{
                            System.err.println(myAgent.getName() + " wrong class received");
                        }
                    } catch (JAXBException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    System.out.println(myAgent.getName() + " recevied message with an unhandled performative");
                    System.out.println(myAgent.getName() + " message: performative " + msg.getPerformative());
                    if(msg.getContent() != null){
                        System.out.println(myAgent.getName() + " message: content " + msg.getContent());
                    }
                    if(msg.getConversationId() != null){
                        System.out.println(myAgent.getName() + " message: content " + msg.getConversationId());
                    }
                }
            }
            else{
                System.err.println(myAgent.getName() + " is not an instance of bothack.agents.MapAgent");
            }
        }
        else{
            block();
        }

    }
}
