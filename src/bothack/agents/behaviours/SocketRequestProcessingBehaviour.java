package bothack.agents.behaviours;

import bothack.agents.LoginAgent;
import bothack.agents.authentication.TestCallbackHandler;
import bothack.agents.messages.LoginMessage;
import bothack.classes.Address;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by administrator on 12/22/14.
 */
public class SocketRequestProcessingBehaviour extends OneShotBehaviour {
    private Socket socket;
    BufferedReader in;
    BufferedWriter out;
    MessageTemplate mt;

    public SocketRequestProcessingBehaviour(Socket socket){
        super();
        this.socket = socket;
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
        System.out.println("requestReceived");
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            JAXBContext jaxbContext = JAXBContext.newInstance(LoginMessage.class,bothack.classes.Error.class,Address.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Marshaller m = jaxbContext.createMarshaller();
            Object o = unmarshaller.unmarshal(in);
            if( o instanceof LoginMessage){
                String name = "admin";//((LoginMessage) o).getName();
                String password = "admin1";((LoginMessage) o).getPassword();
                LoginContext lc = new LoginContext("Test", new TestCallbackHandler(name,password));
                lc.login();
                m.marshal(startNewDungeon(name), out);
            }
            else{
                bothack.classes.Error err = new bothack.classes.Error(126,"Message not recognized");
                m.marshal(err, out);
            }
            socket.close();
        } catch (LoginException e) {
            e.printStackTrace();
            if(e instanceof FailedLoginException){
                bothack.classes.Error err = new bothack.classes.Error(127,"Failed login exception");
                try {
                    JAXBContext jaxbContext = JAXBContext.newInstance(bothack.classes.Error.class);
                    Marshaller m = jaxbContext.createMarshaller();
                    m.marshal(err,out);

                } catch (JAXBException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}
