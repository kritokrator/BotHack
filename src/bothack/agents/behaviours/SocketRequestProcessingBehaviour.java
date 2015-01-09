package bothack.agents.behaviours;

import bothack.agents.LoginAgent;
import bothack.agents.authentication.NethackCallbackHandler;
import bothack.agents.messages.ErrorMessage;
import bothack.agents.messages.LoginMessage;
import bothack.classes.*;
import bothack.classes.Error;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.MessageTemplate;
import jade.wrapper.StaleProxyException;

import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.Socket;

/**
 * Created by administrator on 12/22/14.
 */
public class SocketRequestProcessingBehaviour extends OneShotBehaviour {
    private Socket socket;
    BufferedReader in;
    BufferedWriter out;
    MessageTemplate mt;
    StringReader reader;
    StringWriter writer;

    public SocketRequestProcessingBehaviour(Socket socket){
        super();
        this.socket = socket;
    }

    public bothack.classes.Error startNewDungeon(String name,AID requestJade, Socket requestSocket){
        String dungeon = "dungeon_"+name;
        String cookie = "";
        Integer portNumber =0;
        AID dungeonAddress = new AID(dungeon,AID.ISLOCALNAME);
        cookie = ((LoginAgent)myAgent).getCookieManager().getCookie();
        Object[] args = {cookie,dungeon,myAgent.getLocalName()};
        QueueElement queueElement = new QueueElement("address",dungeon,requestJade,requestSocket);
        ((LoginAgent) myAgent).addToQueue(queueElement);
        //start the dungeon
        try {
            myAgent.getContainerController().createNewAgent(dungeon,"bothack.agents.NethackAgent",args).start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
            return new Error(145,"Failed to create new agent");
        }
        return null;
    }

    @Override
    public void action() {
        System.out.println("requestReceived");
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer = new StringWriter();
            String input;
            JAXBContext jaxbContext = JAXBContext.newInstance(LoginMessage.class,bothack.classes.Error.class,Address.class, ErrorMessage.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Marshaller m = jaxbContext.createMarshaller();
            input = in.readLine();
            reader = new StringReader(input);
            Object o = unmarshaller.unmarshal(reader);
            if( o instanceof LoginMessage){
                String name = ((LoginMessage) o).getName();
                String password = ((LoginMessage) o).getPassword();
                LoginContext lc = new LoginContext("Test", new NethackCallbackHandler(name,password));
                lc.login();
                /*m.marshal(startNewDungeon(name), out);*/
                Error err = startNewDungeon(name, null, socket);
                if(null != err){
                    m.marshal(err, writer);
                    out.write(writer.toString());
                    out.newLine();
                    out.flush();
                    out.close();
                    socket.close();
                    return;
                }
            }
            else{
                bothack.classes.Error err = new bothack.classes.Error(126,"Message not recognized");
                m.marshal(err, writer);
                out.write(writer.toString());
                out.newLine();
                out.flush();
                out.close();
                socket.close();
                System.err.println(myAgent.getName() + " : Message not recognized");
            }
        } catch (LoginException e) {
            e.printStackTrace();
            if(e instanceof FailedLoginException){
                bothack.classes.Error err = new bothack.classes.Error(127,"Failed login exception");
                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.setError(err);
                try {
                    JAXBContext jaxbContext = JAXBContext.newInstance(bothack.classes.Error.class,ErrorMessage.class);
                    Marshaller m = jaxbContext.createMarshaller();
                    m.marshal(errorMessage, writer);
                    out.write(writer.toString());
                    out.newLine();
                    out.flush();
                    out.close();
                    socket.close();
                } catch (JAXBException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
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
