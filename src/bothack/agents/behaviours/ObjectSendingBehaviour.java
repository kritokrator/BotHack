package bothack.agents.behaviours;

import bothack.agents.messages.*;
import bothack.classes.*;
import bothack.interfaces.Command;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.Socket;

/**
 * Created by krito on 11/30/14.
 */
public class ObjectSendingBehaviour extends OneShotBehaviour{
    private AID recipient;
    private Object content;
    private int performative;
    private Socket socket;

    public ObjectSendingBehaviour(Socket socket,AID r, Object c){
        recipient = r;
        content = c;
        performative = ACLMessage.INFORM;
        this.socket = socket;
    }
    public ObjectSendingBehaviour(Socket socket, AID r, Object c,int p){
        recipient = r;
        content = c;
        performative = p;
        this.socket = socket;
    }



    @Override
    public void action() {
        try {
            if(recipient != null)
                System.out.printf("NethackAgent : sending message from %s to %s\n",myAgent.getName(),recipient.getName());
            if(socket != null)
                System.out.printf("NethackAgent : sending message from %s to %s: %s\n",myAgent.getName(),socket.getInetAddress().toString(), socket.getPort());

            JAXBContext context = JAXBContext.newInstance(NethackMap.class, LoginMessage.class,Address.class,ErrorMessage.class,
                    PlayerCharacter.class, NethackMenuObject.class, NethackMenuItem.class, NethackChoiceObject.class,
                    NethackChoice.class,NethackStringObject.class,NethackDirectionObject.class, NethackTextWindowObject.class,
                    NethackCommandObject.class,Command.class, InteractMessage.class, QuitMessage.class, SetupMessage.class,UpdateMessage.class);
            Marshaller marshaller = context.createMarshaller();
            StringWriter writer = new StringWriter();
            if(socket == null && recipient != null){
                ACLMessage msg = new ACLMessage(performative);
                msg.addReceiver(recipient);
                marshaller.marshal(content,writer);
                msg.setContent(writer.toString());
                myAgent.send(msg);
            }
            else if(recipient == null && socket != null) {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                marshaller.marshal(content,writer);
                bufferedWriter.write(writer.toString());
                bufferedWriter.newLine();
                bufferedWriter.flush();
                bufferedWriter.close();
                socket.close();
            }


        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
