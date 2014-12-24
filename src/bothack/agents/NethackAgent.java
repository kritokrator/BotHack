package bothack.agents;

import bothack.agents.behaviours.NethackBehaviour;
import bothack.agents.behaviours.NethackMessageAcceptingBehaviour;
import bothack.agents.behaviours.NethackSocketMessageAcceptingBehaviour;
import bothack.classes.NethackMessageObject;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.ThreadedBehaviourFactory;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class NethackAgent extends Agent {
    private ServerSocket socket;
    private NethackBehaviour nb;
    private NethackMessageAcceptingBehaviour jadeMessages;
    private NethackSocketMessageAcceptingBehaviour socketMessages;
    private ThreadedBehaviourFactory tbf;
    private AID loginAgent;
    private ArrayList<AID> visualizers;
    private String cookie;
    private Object[] args;

    @Override
    public void setup(){
        tbf = new ThreadedBehaviourFactory();
        args = getArguments();
        cookie = (String)args[0];
        loginAgent = new AID((String)args[2],AID.ISLOCALNAME);
        //start the socket
        try {
            socket = new ServerSocket(0);
        //find visualizers
            findVisualizers();
        //add behaviours
            nb = new NethackBehaviour((String)args[2]);
            addBehaviour(nb);
            socketMessages = new NethackSocketMessageAcceptingBehaviour();
            addBehaviour(tbf.wrap(socketMessages));
            jadeMessages = new NethackMessageAcceptingBehaviour();
            addBehaviour(jadeMessages);
        //send status response to the login agent
            ACLMessage msg = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
            msg.addReceiver(loginAgent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    };
    @Override
    public void takeDown(){
        //finish all cyclic behaviours
        //close the socket
    };

    private void findVisualizers(){};


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
}