package bothack.agents.behaviours;

import bothack.agents.LoginAgent;
import bothack.agents.authentication.TestCallbackHandler;
import bothack.agents.messages.LoginMessage;
import bothack.classes.*;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
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
import java.lang.Error;

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

    public Object startNewDungeon(String name){
        String dungeon = "dungeon_"+name;
        String cookie = "";
        Integer portNumber =0;
        AID dungeonAddress = new AID(dungeon,AID.ISLOCALNAME);
        mt = MessageTemplate.and(MessageTemplate.MatchSender(dungeonAddress), MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL));
        cookie = ((LoginAgent)myAgent).getCookieManager().getCookie();
        Object[] args = {cookie,dungeon,myAgent.getLocalName()};
        //start the dungeon
        try {
            myAgent.getContainerController().createNewAgent(dungeon,"bothack.agents.NethackAgent",args).start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
        //wait for the port number
        ACLMessage msg = myAgent.receive(mt);
        if(msg != null){
            try {
                Object o = msg.getContentObject();
                if(o instanceof Address){
                    return (Address)o;
                }
                else if(o instanceof Error){
                    return (Error)o;
                }
            } catch (UnreadableException e) {
                e.printStackTrace();
            }
        }
        return new bothack.classes.Error(128,"Unable to connect with the dungeon");
    }
    @Override
    public void action() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(LoginMessage.class, Address.class, bothack.classes.Error.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Marshaller marshaller = jaxbContext.createMarshaller();
            StringWriter writer = new StringWriter();
            StringReader reader = new StringReader(request.getContent());
            Object o = unmarshaller.unmarshal(reader);
            if(o instanceof LoginMessage){
                String name = "admin";//((LoginMessage) o).getName();
                String password = "admin1";((LoginMessage) o).getPassword();
                LoginContext lc = new LoginContext("Test", new TestCallbackHandler(name,password));
                lc.login();
                marshaller.marshal(startNewDungeon(name), writer);
                ACLMessage reply = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
                reply.addReceiver(request.getSender());
                reply.setContent(writer.toString());
                myAgent.send(reply);
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
                try {
                    JAXBContext jaxbContext = JAXBContext.newInstance(bothack.classes.Error.class);
                    Marshaller m = jaxbContext.createMarshaller();
                    StringWriter writer = new StringWriter();
                    m.marshal(err,writer);
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
