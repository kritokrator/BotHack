package bothack.agents.behaviours;

import bothack.agents.BotAgent;
import bothack.agents.messages.ErrorMessage;
import bothack.agents.messages.UpdateMessage;
import bothack.classes.*;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

/**
 * Created by krito on 11/30/14.
 */
public class GenericMessageAcceptingBehaviour extends CyclicBehaviour {
    private Unmarshaller unmarshaller;
    private JAXBContext jaxbContext;
    public GenericMessageAcceptingBehaviour(){
        super();
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
        ACLMessage msg = myAgent.receive();
        if(msg != null){
            /*System.out.println(myAgent.getName() + " got message form : " + msg.getSender().getName());
            System.out.println(myAgent.getName() + " message containing : " + msg.getContent());*/
            if(myAgent instanceof BotAgent){
                /*((BotAgent) myAgent).getGui().printOutput(myAgent.getName() + " got message form : " + msg.getSender().getName());
                ((BotAgent) myAgent).getGui().printOutput(myAgent.getName() + " message containing : " + msg.getContent());*/
                StringReader reader = new StringReader(msg.getContent());
                try {
                    Object o = unmarshaller.unmarshal(reader);
                    if(o instanceof Address){
                        ((BotAgent) myAgent).setCookie(((Address) o).getCookie());
                        ((BotAgent) myAgent).setPort(((Address) o).getPort());
                        ((BotAgent) myAgent).setDungeon(new AID(((Address) o).getDungeon(), AID.ISLOCALNAME));
                    }
                    else if(o instanceof UpdateMessage){
                        if(((UpdateMessage) o).getCommand() != null){
                            ((BotAgent) myAgent).getGui().printOutput(((UpdateMessage) o).getCommand().getPrompt());
                        }
                        if(((UpdateMessage) o).getMenu() != null){
                            NethackMenuObject menu = ((UpdateMessage) o).getMenu();
                            ((BotAgent) myAgent).setMenu(menu);
                            for(NethackMenuItem item : menu.getItems()){
                                ((BotAgent) myAgent).getGui().printOutput(item.getDescription());
                                ((BotAgent) myAgent).getGui().printOutput(String.valueOf(item.getSymbol()));
                                ((BotAgent) myAgent).getGui().printOutput(String.valueOf(item.getGroupAcc()));
                            }
                        }
                        if(!((UpdateMessage) o).getTextMenus().isEmpty()){
                            ((BotAgent) myAgent).getGui().printOutput("there is a text menu here");
                        }
                        if (!((UpdateMessage) o).getMessages().isEmpty()){
                            for(NethackMessageObject message : ((UpdateMessage) o).getMessages()){
                                ((BotAgent) myAgent).getGui().printOutput(message.getMessage());
                            }
                        }
                        if(((UpdateMessage) o).getChoice() != null){
                            NethackChoiceObject choice = ((UpdateMessage) o).getChoice();
                            ((BotAgent) myAgent).getGui().printOutput(choice.getText() + " " + choice.getChoices());
                            ((BotAgent) myAgent).setChoice(choice);
                        }
                    }
                    else if(o instanceof ErrorMessage){
                        ((BotAgent) myAgent).getGui().printOutput(((ErrorMessage) o).getError().getText());
                    }
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
            }

        }
        else{
            block();
        }
    }
}
