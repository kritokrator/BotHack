package bothack.agents.behaviours;

import bothack.agents.NethackAgent;
import bothack.agents.messages.InteractMessage;
import bothack.agents.messages.QuitMessage;
import bothack.agents.messages.SetupMessage;

import java.io.StringReader;
import java.lang.*;

import bothack.classes.NethackChoice;
import bothack.classes.NethackDirectionObject;
import bothack.classes.NethackMenuChoice;
import bothack.classes.NethackStringObject;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Created by krito on 11/23/14.
 */
public class NethackJadeMessageAcceptingBehaviour extends CyclicBehaviour {
    private MessageTemplate request = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
    private NethackBehaviour nethackBehaviour;

    public NethackJadeMessageAcceptingBehaviour(NethackBehaviour nb){
        super();
        nethackBehaviour = nb;
    }
    @Override
    public void action() {
        ACLMessage msg = myAgent.receive(request);
        if(msg != null)
        {
            try {
                System.out.println("Message received");
                JAXBContext context = JAXBContext.newInstance(
                    QuitMessage.class, InteractMessage.class, SetupMessage.class, NethackMenuChoice.class, NethackChoice.class,bothack.interfaces.Command.class,
                        NethackDirectionObject.class, NethackStringObject.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                if(myAgent instanceof NethackAgent){
                    ((NethackAgent) myAgent).setLastMessageAccepted(System.currentTimeMillis());
                    System.out.println("NethackAgent : Request message received");
                    Object o = unmarshaller.unmarshal(new StringReader(msg.getContent()));
                    if(o instanceof SetupMessage ){
                        if(((NethackAgent) myAgent).cookieChecker(((SetupMessage) o).getCookie(),null,msg.getSender())){
                            nethackBehaviour.setup((SetupMessage)o,msg.getSender(),null);
                        }
                    }
                    else if(o instanceof InteractMessage){
                        if(((NethackAgent) myAgent).cookieChecker(((InteractMessage) o).getCookie(),null,msg.getSender())) {
                            nethackBehaviour.processInteractMessage((InteractMessage) o, msg.getSender(), null);
                        }
                    }
                    else if(o instanceof QuitMessage){
                        if(((NethackAgent) myAgent).cookieChecker(((QuitMessage) o).getCookie(),null,msg.getSender())) {
                            nethackBehaviour.quit((QuitMessage) o, msg.getSender(), null);
                            nethackBehaviour.done();
                            myAgent.removeBehaviour(nethackBehaviour);
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
