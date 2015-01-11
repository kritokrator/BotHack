package bothack.agents.behaviours;

import bothack.agents.LoginAgent;
import bothack.agents.authentication.NethackCallbackHandler;
import bothack.agents.messages.ErrorMessage;
import bothack.agents.messages.LoginMessage;
import bothack.classes.*;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.wrapper.StaleProxyException;

import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.Socket;

/**
 * Created by administrator on 12/22/14.
 */
public class JadeRequestProcessingBehaviour extends OneShotBehaviour{
    private ACLMessage request;
    MessageTemplate mt;
    public JadeRequestProcessingBehaviour(ACLMessage request){
        super();
        this.request = request;
    }

    public bothack.classes.Error startNewDungeon(String name,AID requestJade, Socket requestSocket){
        String dungeon = "dungeon_"+name;
        String cookie = "";
        Integer portNumber =0;
        AID dungeonAddress = new AID(dungeon,AID.ISLOCALNAME);
        mt = MessageTemplate.and(MessageTemplate.MatchSender(dungeonAddress), MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL));
        cookie = ((LoginAgent)myAgent).getCookieManager().getCookie();
        Object[] args = {cookie,dungeon,myAgent.getLocalName()};
        QueueElement queueElement = new QueueElement("address",dungeon,requestJade,requestSocket);
        ((LoginAgent) myAgent).addToQueue(queueElement);
        //start the dungeon
        try {
            myAgent.getContainerController().createNewAgent(dungeon,"bothack.agents.NethackAgent",args).start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
            return new bothack.classes.Error(145,"Failed to create new agent");
        }
        /*bothack.classes.Error error = new bothack.classes.Error(128,"Unable to connect with the dungeon");
        //wait for the port number
        ACLMessage msg = myAgent.receive(mt);
        if(msg != null){
            try {
                Object o = msg.getContentObject();
                if(o instanceof Address){
                    return (Address)o;
                }
                else if(o instanceof Error){
                    error = (bothack.classes.Error)o;
                }
            } catch (UnreadableException e) {
                e.printStackTrace();
            }
        }
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setError(error);
        return errorMessage;*/
        return null;
    }
    @Override
    public void action() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(LoginMessage.class, Address.class, bothack.classes.Error.class, ErrorMessage.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Marshaller marshaller = jaxbContext.createMarshaller();
            StringWriter writer = new StringWriter();
            StringReader reader = new StringReader(request.getContent());
            Object o = unmarshaller.unmarshal(reader);
            if(o instanceof LoginMessage){
                System.out.println(myAgent.getName() + " : processing a login request form : " + request.getSender().getName());
                String name = ((LoginMessage) o).getName();
                String password = ((LoginMessage) o).getPassword();
                LoginContext lc = new LoginContext("Test", new NethackCallbackHandler(name,password));
                lc.login();
                bothack.classes.Error err = startNewDungeon(name,request.getSender(),null);
                if(err != null ){
                    marshaller.marshal(err, writer);
                    ACLMessage reply = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
                    reply.addReceiver(request.getSender());
                    reply.setContent(writer.toString());
                    myAgent.send(reply);
                    return;
                }
              /*  ACLMessage reply = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
                reply.addReceiver(request.getSender());
                reply.setContent(writer.toString());
                myAgent.send(reply);*/
                System.out.println(myAgent.getName() + " : finished processing a login request form : " + request.getSender().getName());
            }
            else{
                bothack.classes.Error err = new bothack.classes.Error(126,"Message not recognized");
                marshaller.marshal(err, writer);
                ACLMessage reply = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
                reply.addReceiver(request.getSender());
                reply.setContent(writer.toString());
                myAgent.send(reply);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (LoginException e) {
            e.printStackTrace();
            if(e instanceof FailedLoginException){
                bothack.classes.Error err = new bothack.classes.Error(127,"Failed login exception");
                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.setError(err);
                try {
                    JAXBContext jaxbContext = JAXBContext.newInstance(bothack.classes.Error.class,ErrorMessage.class);
                    Marshaller m = jaxbContext.createMarshaller();
                    StringWriter writer = new StringWriter();
                    m.marshal(errorMessage,writer);
                    ACLMessage reply = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
                    reply.addReceiver(request.getSender());
                    reply.setContent(writer.toString());
                    myAgent.send(reply);

                } catch (JAXBException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
