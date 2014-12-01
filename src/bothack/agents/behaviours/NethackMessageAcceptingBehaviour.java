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
                    Object o = unmarshaller.unmarshal(new StringReader(msg.getContent()));
                    if(o instanceof SetupMessage) {
                        System.out.println("NethackAgent : Setup message received");
                        ((NethackAgent) myAgent).getGame().setup("118", "104", "109", "99");
                    }
                    else if(o instanceof QuitMessage){
                        System.out.println("NethackAgent : Quit message received");
                        ((NethackAgent) myAgent).getGame().quitFast();
                    }
                    else if(o instanceof RequestMessage){
                        if(((RequestMessage) o).getMapUpdate()){
                            System.out.println("NethackAgent : Map update not implemented");
                            //prepare the message and return the object
                        }
                        else if(((RequestMessage) o).getAvatarUpdate()){
                            System.out.println("NethackAgent : player update not implemented");
                            //prepare the message and return the object
                        }
                        else if(((RequestMessage) o).getAction() != null){
                            System.out.println("NethackAgent : Action message received");
                            Object returnValue =  ((NethackAgent) myAgent).getGame().action(((RequestMessage) o).getAction());
                            //prepare the message and return the object
                            myAgent.addBehaviour(new ObjectSendingBehaviour(msg.getSender(),returnValue));
                        }
                        else if(((RequestMessage) o).getChoice() != null){
                            Object returnValue = ((NethackAgent) myAgent).getGame().action(((RequestMessage) o).getChoice());
                            //prepare the message and return the object
                            myAgent.addBehaviour(new ObjectSendingBehaviour(msg.getSender(),returnValue));
                        }
                        else if(((RequestMessage) o).getMenuChoice()!=null){
                            Object returnValue = ((NethackAgent) myAgent).getGame().action(((RequestMessage) o).getMenuChoice());
                            //prepare the message and return the object
                            //add oneshot message Object sending behaviour
                            myAgent.addBehaviour(new ObjectSendingBehaviour(msg.getSender(),returnValue));
                        }
                        else{
                            System.out.println("NethackAgent : Request message with no action accepted");
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
