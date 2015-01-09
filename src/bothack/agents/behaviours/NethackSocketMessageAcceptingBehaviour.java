package bothack.agents.behaviours;

import bothack.agents.NethackAgent;
import bothack.agents.messages.ErrorMessage;
import bothack.agents.messages.InteractMessage;
import bothack.agents.messages.QuitMessage;
import bothack.agents.messages.SetupMessage;
import bothack.classes.NethackChoice;
import bothack.classes.NethackDirectionObject;
import bothack.classes.NethackMenuChoice;
import bothack.classes.NethackStringObject;
import jade.core.behaviours.CyclicBehaviour;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by administrator on 12/24/14.
 */
public class NethackSocketMessageAcceptingBehaviour extends CyclicBehaviour {
    private ServerSocket mainSocket;
    private NethackBehaviour nethackBehaviour;

    public NethackSocketMessageAcceptingBehaviour(ServerSocket socket,NethackBehaviour nb){
        super();
        mainSocket = socket;
        nethackBehaviour = nb;
    }

    @Override
    public void action() {
        try {
            Socket requestSocket = mainSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(requestSocket.getInputStream()));
            String input = reader.readLine();
            StringReader stringReader = new StringReader(input);
            JAXBContext context = JAXBContext.newInstance(
                    QuitMessage.class, InteractMessage.class, SetupMessage.class, NethackMenuChoice.class,
                    NethackChoice.class,bothack.interfaces.Command.class,
                    NethackDirectionObject.class, NethackStringObject.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Object message = unmarshaller.unmarshal(stringReader);
            if(myAgent instanceof NethackAgent){
                ((NethackAgent) myAgent).setLastMessageAccepted(System.currentTimeMillis());
                System.out.println("NethackAgent : Request message received");
                if(message instanceof SetupMessage ){
                    if(((NethackAgent) myAgent).cookieChecker(((SetupMessage) message).getCookie(),requestSocket,null))
                        nethackBehaviour.setup((SetupMessage) message,null,requestSocket);
                    else{
                        reader.close();
                        requestSocket.close();
                    }
                }
                else if(message instanceof InteractMessage){
                    if(((NethackAgent) myAgent).cookieChecker(((InteractMessage) message).getCookie(),requestSocket,null)){
                        nethackBehaviour.processInteractMessage((InteractMessage)message,null,requestSocket);
                    }
                    else{
                        reader.close();
                        requestSocket.close();
                    }
                }
                else if(message instanceof QuitMessage){
                    if(((NethackAgent) myAgent).cookieChecker(((QuitMessage) message).getCookie(),requestSocket,null)){
                        nethackBehaviour.quit((QuitMessage)message,null,requestSocket);
                        nethackBehaviour.done();
                        myAgent.removeBehaviour(nethackBehaviour);
                        reader.close();
                        requestSocket.close();
                    }
                    else{
                        reader.close();
                        requestSocket.close();
                    }

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
