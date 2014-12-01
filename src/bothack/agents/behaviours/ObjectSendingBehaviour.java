package bothack.agents.behaviours;

import bothack.agents.messages.QuitMessage;
import bothack.agents.messages.RequestMessage;
import bothack.agents.messages.SetupMessage;
import bothack.classes.*;
import bothack.interfaces.Command;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * Created by krito on 11/30/14.
 */
public class ObjectSendingBehaviour extends OneShotBehaviour{
    private AID recipient;
    private Object content;
    private int performative;

    public ObjectSendingBehaviour(AID r, Object c){
        recipient = r;
        content = c;
        performative = ACLMessage.INFORM;
    }
    public ObjectSendingBehaviour(AID r, Object c,int p){
        recipient = r;
        content = c;
        performative = p;
    }


    @Override
    public void action() {
        try {
            System.out.printf("NethackAgent : sending message from %s to %s\n",myAgent.getName(),recipient.getName());
            ACLMessage msg = new ACLMessage(performative);
            msg.addReceiver(recipient);

            JAXBContext context = JAXBContext.newInstance(NethackMap.class,
                    PlayerCharacter.class, NethackMenuObject.class, NethackMenuItem.class, NethackChoiceObject.class,
                    NethackChoice.class,NethackCommandObject.class,NethackCommandObject.class,Command.class,
                    RequestMessage.class, QuitMessage.class, SetupMessage.class);

            Marshaller marshaller = context.createMarshaller();
            StringWriter writer = new StringWriter();
            marshaller.marshal(content,writer);

            msg.setContent(writer.toString());

            myAgent.send(msg);


        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
