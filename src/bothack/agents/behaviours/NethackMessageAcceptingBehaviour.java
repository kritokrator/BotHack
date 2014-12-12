package bothack.agents.behaviours;

import bothack.agents.NethackAgent;
import bothack.agents.messages.QuitMessage;
import bothack.agents.messages.RequestMessage;
import bothack.agents.messages.SetupMessage;
import bothack.classes.Nethack;

import java.io.StringReader;
import java.lang.*;

import bothack.classes.NethackMap;
import bothack.classes.NotYetImplementedException;
import bothack.classes.PlayerCharacter;
import bothack.interfaces.Command;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by krito on 11/23/14.
 */
public class NethackMessageAcceptingBehaviour extends CyclicBehaviour {
    private MessageTemplate request = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive(request);
        if(msg != null)
        {
            try {
                System.out.println("Message received");
                JAXBContext context = JAXBContext.newInstance(
                    QuitMessage.class, RequestMessage.class, SetupMessage.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                if(myAgent instanceof NethackAgent){
                    System.out.println("NethackAgent : Request message received");
                    String owner = msg.getSender().getName();
                    Object o = unmarshaller.unmarshal(new StringReader(msg.getContent()));
                    if(o instanceof SetupMessage && !(((NethackAgent) myAgent).getDungeons().containsKey(owner))){
                        NethackBehaviour nb = new NethackBehaviour(owner);
                        myAgent.addBehaviour(nb);
                        nb.setup((SetupMessage)o);
                        ((NethackAgent) myAgent).getDungeons().putIfAbsent(owner,nb);
                    }
                    else if(o instanceof  SetupMessage && ((NethackAgent) myAgent).getDungeons().containsKey(owner)){
                        NethackBehaviour nb = new NethackBehaviour(owner);
                        myAgent.addBehaviour(nb);
                        ((NethackAgent) myAgent).getDungeons().replace(owner,nb);
                    }
                    else if(((NethackAgent) myAgent).getDungeons().containsKey(owner)){
                        NethackBehaviour nb = ((NethackAgent) myAgent).getDungeons().get(owner);
                        if(o instanceof QuitMessage){
                            nb.quit();
                            nb.done();
                            ((NethackAgent) myAgent).getDungeons().remove(owner);
                        }
                        else if(o instanceof RequestMessage){
                            nb.processMessage((RequestMessage)o);
                        }
                    }

                }
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
        else{
            block();
        }

    }
}
