package bothack.agents.behaviours;

import bothack.agents.NethackAgent;
import bothack.classes.Nethack;
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
import java.util.concurrent.CyclicBarrier;

/**
 * Created by krito on 11/23/14.
 */
public class MessageAcceptingBehaviour extends CyclicBehaviour {
    private MessageTemplate quit = MessageTemplate.MatchPerformative(ACLMessage.DISCONFIRM);

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        System.out.println("Message received");
        if(msg != null){
            if(msg.getPerformative() == ACLMessage.REQUEST){
                if(myAgent instanceof NethackAgent){
                    System.out.println("NethackAgent : Request message received");
                    ((NethackAgent) myAgent).getGame().setup("118","104","109","99");
                }
            }
            else if(msg.getPerformative() == ACLMessage.DISCONFIRM){
                if(myAgent instanceof NethackAgent){
                    System.out.println("NethackAgent: Disconfirm message received");
                    ((NethackAgent) myAgent).getGame().quitFast();
                }
            }
            else if(msg.getPerformative() == ACLMessage.INFORM){
                if(myAgent instanceof NethackAgent){
                    System.out.println("NethackAgent : Inform message received");
                    try {
                        ((NethackAgent) myAgent).getGame().action(Command.GO_NORTH);
                    } catch (NotYetImplementedException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if(msg.getPerformative() == ACLMessage.CONFIRM){
                if(myAgent instanceof NethackAgent){
                    try {
                        JAXBContext context = JAXBContext.newInstance(PlayerCharacter.class);
                        Marshaller jaxbMarshaller = context.createMarshaller();
                        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
                        jaxbMarshaller.marshal(((NethackAgent) myAgent).getGame().getAvatar(),System.out);

                    } catch (JAXBException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if(msg.getPerformative() == ACLMessage.CFP){
                if(myAgent instanceof NethackAgent){
                    try {
                        JAXBContext context = JAXBContext.newInstance(NethackMap.class);
                        Marshaller jaxbMarshaller = context.createMarshaller();
                        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
                        jaxbMarshaller.marshal(((NethackAgent) myAgent).getGame().getTheMap(),System.out);

                    } catch (JAXBException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else{
            block();
        }

    }
}
