package bothack.agents.behaviours;

import bothack.agents.SocketTest;
import bothack.agents.messages.*;
import bothack.classes.*;
import jade.core.behaviours.OneShotBehaviour;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.Socket;

/**
 * Created by administrator on 12/29/14.
 */
public class SocketTransferBehaviour extends OneShotBehaviour {
    private Object content;
    private int port;
    public SocketTransferBehaviour(Object content, int port){
        this.content = content;
        this.port = port;
    }
    @Override
    public void action() {
        if(myAgent instanceof SocketTest){
            try {
                Socket socket = new Socket(((SocketTest) myAgent).getLoginHost(),port);
                if(socket.isConnected()){
                    BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    StringWriter writer = new StringWriter();
                    JAXBContext jaxbContext = JAXBContext.newInstance(LoginMessage.class, Address.class, ErrorMessage.class,
                            bothack.classes.Error.class, SetupMessage.class, QuitMessage.class, InteractMessage.class);
                    Marshaller marshaller = jaxbContext.createMarshaller();
                    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                    marshaller.marshal(content,writer);
                    socketWriter.write(writer.toString());
                    socketWriter.newLine();
                    socketWriter.flush();
                    while(!socketReader.ready()){

                    }
                    String input = socketReader.readLine();
                    System.out.println(myAgent.getName() + " : Response accepted");
                    myAgent.addBehaviour(new SocketAgentProcessingBehaviour(input));
                    writer.close();
                    socketReader.close();
                    socketWriter.close();
                    socket.close();
                }
                else{
                    System.err.println("Socket not connected");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JAXBException e) {
                e.printStackTrace();
            }

        }
        else{
            System.out.println(myAgent.getName() + " : used wrong behaviour SendControlMessageBehaviour");
        }
    }
}
