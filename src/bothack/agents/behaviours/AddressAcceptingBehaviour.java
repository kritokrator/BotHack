package bothack.agents.behaviours;

import bothack.agents.LoginAgent;
import bothack.agents.messages.ErrorMessage;
import bothack.agents.messages.LoginMessage;
import bothack.classes.Address;
import bothack.classes.QueueElement;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by administrator on 12/28/14.
 */
public class AddressAcceptingBehaviour extends CyclicBehaviour {
    private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL);
    private JAXBContext jaxbContext;
    private Unmarshaller unmarshaller;
    public AddressAcceptingBehaviour(){
        super();
        try {
            jaxbContext = JAXBContext.newInstance(LoginMessage.class, Address.class, bothack.classes.Error.class, ErrorMessage.class);
            unmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void action() {
        ACLMessage msg = myAgent.receive(mt);
        QueueElement elementToRemove = null;
        if(msg != null){
            try {
                StringReader reader = new StringReader(msg.getContent());
                Object o = unmarshaller.unmarshal(reader);
                if(o instanceof Address){
                    ArrayList<QueueElement> queueElements = ((LoginAgent)myAgent).getWaitingQueue();
                    for(QueueElement element : queueElements){
                        if(element.getOperation().equals("address") && msg.getSender().getLocalName().equals(element.getDungeon())){
                            if(element.getRecipientAgent()!=null){
                                ACLMessage reply = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
                                reply.addReceiver(element.getRecipientAgent());
                                reply.setContent(msg.getContent());
                                myAgent.send(reply);
                                elementToRemove = element;
                            }
                            else if(element.getRecipientSocket() != null){
                                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(element.getRecipientSocket().getOutputStream()));
                                writer.write(msg.getContent());
                                writer.newLine();
                                writer.close();
                                element.getRecipientSocket().close();
                                elementToRemove = element;
                            }
                        }
                    }
                    ((LoginAgent)myAgent).removeFromQueue(elementToRemove);
                }
            } catch (JAXBException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }catch(NullPointerException e){
                e.printStackTrace();
            }

        }
        else{
            block();
        }
    }
}
