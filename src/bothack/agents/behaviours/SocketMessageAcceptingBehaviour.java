package bothack.agents.behaviours;

import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by administrator on 12/22/14.
 */
public class SocketMessageAcceptingBehaviour extends CyclicBehaviour {
    private ServerSocket serverSocket;
    private ThreadedBehaviourFactory requestProcessingFactory;
    public SocketMessageAcceptingBehaviour(ServerSocket socket){
        serverSocket = socket;
        requestProcessingFactory = new ThreadedBehaviourFactory();

    }
    @Override
    public void action() {
        try {
            while(true){
                myAgent.addBehaviour(requestProcessingFactory.wrap(new SocketRequestProcessingBehaviour(serverSocket.accept())));
            }

        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
