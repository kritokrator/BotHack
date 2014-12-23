package bothack.agents;

import bothack.agents.behaviours.JadeMessageAcceptingBehaviour;
import bothack.agents.behaviours.SocketMessageAcceptingBehaviour;
import bothack.classes.CookieManager;
import jade.core.Agent;
import jade.core.AgentContainer;
import jade.core.NotFoundException;
import jade.core.behaviours.ThreadedBehaviourFactory;
import jade.wrapper.AgentController;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by administrator on 12/22/14.
 */
public class LoginAgent extends Agent {
    private ThreadedBehaviourFactory tbf;
    private SocketMessageAcceptingBehaviour socketListener;
    private ServerSocket socket;
    private JadeMessageAcceptingBehaviour jadeListener;
    private Integer portNumber;
    private CookieManager cookieManager;
    @Override
    public void setup(){
        try {
            System.setProperty("java.security.auth.login.config", "loginAgent.config");
            tbf = new ThreadedBehaviourFactory();
            portNumber =Integer.parseInt((String)getArguments()[0]);
            socket = new ServerSocket(portNumber);
            cookieManager = new CookieManager();
            //initiate both listening behaviours
            socketListener = new SocketMessageAcceptingBehaviour(socket);
            addBehaviour(tbf.wrap(socketListener));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void takeDown(){
        //get the behaviour threads and kill them
        try {
            tbf.interrupt(socketListener);
            socket.close();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CookieManager getCookieManager() {
        return cookieManager;
    }

    public void setCookieManager(CookieManager cookieManager) {
        this.cookieManager = cookieManager;
    }
}
