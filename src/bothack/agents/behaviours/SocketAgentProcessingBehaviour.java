package bothack.agents.behaviours;

import bothack.agents.SocketTest;
import bothack.agents.SocketTest;
import bothack.agents.messages.DeathMessage;
import bothack.agents.messages.ErrorMessage;
import bothack.agents.messages.UpdateMessage;
import bothack.classes.*;
import jade.core.behaviours.OneShotBehaviour;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.net.Socket;

/**
 * Created by administrator on 12/31/14.
 */
public class SocketAgentProcessingBehaviour extends OneShotBehaviour {
    private String input;
    private Unmarshaller unmarshaller;
    private JAXBContext jaxbContext;

    public SocketAgentProcessingBehaviour(String input){
        this.input = input;
        try {
            jaxbContext = JAXBContext.newInstance(Address.class, UpdateMessage.class, NethackMenuObject.class,NethackMenuItem.class,
                    NethackChoiceObject.class, NethackDirectionObject.class,NethackStringObject.class,NethackCommandObject.class,
                    NethackMessageObject.class,ErrorMessage.class);
            unmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void action() {
        if (myAgent instanceof SocketTest)
            try {
                StringReader reader = new StringReader(input);
                Object o = unmarshaller.unmarshal(reader);
                if (o instanceof Address) {
                    ((SocketTest) myAgent).setCookie(((Address) o).getCookie());
                    ((SocketTest) myAgent).setNethackPort(((Address) o).getPort());
                }
                else if(o instanceof UpdateMessage){
                    if(((UpdateMessage) o).getCommand() != null){
                        ((SocketTest) myAgent).getGui().printOutput(((UpdateMessage) o).getCommand().getPrompt());
                    }
                    if(((UpdateMessage) o).getMenu() != null){
                        NethackMenuObject menu = ((UpdateMessage) o).getMenu();
                        ((SocketTest) myAgent).setMenu(menu);
                        for(NethackMenuItem item : menu.getItems()){
                            ((SocketTest) myAgent).getGui().printOutput(item.getDescription());
                            ((SocketTest) myAgent).getGui().printOutput(String.valueOf(item.getSymbol()));
                            ((SocketTest) myAgent).getGui().printOutput(String.valueOf(item.getGroupAcc()));
                        }
                    }
                    if(!((UpdateMessage) o).getTextMenus().isEmpty()){
                        ((SocketTest) myAgent).getGui().printOutput("there is a text menu here");
                    }
                    if (!((UpdateMessage) o).getMessages().isEmpty()){
                        for(NethackMessageObject message : ((UpdateMessage) o).getMessages()){
                            ((SocketTest) myAgent).getGui().printOutput(message.getMessage());
                        }
                    }
                    if(((UpdateMessage) o).getChoice() != null){
                        NethackChoiceObject choice = ((UpdateMessage) o).getChoice();
                        ((SocketTest) myAgent).getGui().printOutput(choice.getText() + " " + choice.getChoices());
                        ((SocketTest) myAgent).setChoice(choice);
                    }
                }
                else if(o instanceof ErrorMessage){
                    ((SocketTest) myAgent).getGui().printOutput(((ErrorMessage) o).getError().getText());
                }

            } catch (JAXBException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
    }
}
