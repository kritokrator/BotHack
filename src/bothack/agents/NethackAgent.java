package bothack.agents;

import bothack.agents.behaviours.*;
import bothack.agents.messages.ErrorMessage;
import bothack.classes.Address;
import jade.core.AID;
import jade.core.Agent;
import jade.core.NotFoundException;
import jade.core.behaviours.ThreadedBehaviourFactory;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class NethackAgent extends Agent {
    private ServerSocket socket;
    private NethackBehaviour nb;
    private NethackJadeMessageAcceptingBehaviour jadeMessages;
    private NethackSocketMessageAcceptingBehaviour socketMessages;
    private ThreadedBehaviourFactory tbf;
    private AID loginAgent;
    private ArrayList<AID> visualizers;
    private String cookie;
    private Object[] args;
    private long lastMessageAccepted;
    private long delta;

    @Override
    public void setup(){
        tbf = new ThreadedBehaviourFactory();
        delta = 3600;
        args = getArguments();
        cookie = (String)args[0];
        loginAgent = new AID((String)args[2],AID.ISLOCALNAME);
        visualizers = new ArrayList<AID>();
        //start the socket
        try {
            socket = new ServerSocket(0);
        //find visualizers
            findVisualizers();
        //add behaviours
            nb = new NethackBehaviour((String)args[1]);
            addBehaviour(nb);
            socketMessages = new NethackSocketMessageAcceptingBehaviour(socket,nb);
            addBehaviour(tbf.wrap(socketMessages));
            jadeMessages = new NethackJadeMessageAcceptingBehaviour(nb);
        addBehaviour(jadeMessages);
        //send status response to the login agent
            Address address = new Address();
            address.setPort(socket.getLocalPort());
            address.setCookie(cookie);
            address.setDungeon((String)args[1]);
            addBehaviour(new ObjectSendingBehaviour(null,loginAgent,address,ACLMessage.ACCEPT_PROPOSAL));
            addBehaviour(new TimerBehaviour(delta));
            lastMessageAccepted = System.currentTimeMillis();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };
    @Override
    public void takeDown(){
        try {
            //finish all cyclic behaviours
            tbf.interrupt(socketMessages);
            jadeMessages.done();
            removeBehaviour(jadeMessages);
            //close the socket
            socket.close();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    };
    public AID findNethack(){
        DFAgentDescription[] result = null;
        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();

        sd.setType("dungeon_login");
        dfAgentDescription.addServices(sd);
        try{
            result = DFService.search(this,dfAgentDescription);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        if(result.length != 1){
            return null;
        }
        else {
            return result[0].getName();
        }
    }
    private boolean findVisualizers(){
        DFAgentDescription[] result = null;
        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();

        sd.setType("dupa");
        dfAgentDescription.addServices(sd);
        SearchConstraints searchConstraints = new SearchConstraints();
        searchConstraints.setMaxResults(new Long(-1));
        try{
            result = DFService.search(this, dfAgentDescription,searchConstraints);
            if(result.length>0){
                for(int i =0; i<result.length;i++){
                    visualizers.add(result[i].getName());
                }
            }
            else {
                return false;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return true;
    };

    public boolean cookieChecker(String cookie, Socket socket, AID receiver){
        if(cookie.equals(this.cookie)){
            return true;
        }
        else{
            bothack.classes.Error error = new bothack.classes.Error(129,"NethackAgent received an incorrect cookie");
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setError(error);
            addBehaviour(new ObjectSendingBehaviour(socket,receiver,errorMessage));
            return false;
        }
    }


    public ServerSocket getSocket() {
        return socket;
    }

    public void setSocket(ServerSocket socket) {
        this.socket = socket;
    }

    public NethackBehaviour getNb() {
        return nb;
    }

    public void setNb(NethackBehaviour nb) {
        this.nb = nb;
    }

    public AID getLoginAgent() {
        return loginAgent;
    }

    public void setLoginAgent(AID loginAgent) {
        this.loginAgent = loginAgent;
    }

    public ArrayList<AID> getVisualizers() {
        return visualizers;
    }

    public void setVisualizers(ArrayList<AID> visualizers) {
        this.visualizers = visualizers;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public long getLastMessageAccepted() {
        return lastMessageAccepted;
    }

    public void setLastMessageAccepted(long lastMessageAccepted) {
        this.lastMessageAccepted = lastMessageAccepted;
    }

    public long getDelta() {
        return delta;
    }

    public void setDelta(long delta) {
        this.delta = delta;
    }
}