package bothack.agents.behaviours;

import bothack.agents.NethackAgent;
import bothack.agents.messages.InteractMessage;
import bothack.agents.messages.QuitMessage;
import bothack.agents.messages.RequestMessage;
import bothack.agents.messages.SetupMessage;
import bothack.classes.Nethack;
import bothack.classes.NethackMap;
import bothack.classes.NethackMessageFactory;
import bothack.classes.PlayerCharacter;
import jade.core.AID;
import jade.core.behaviours.SimpleBehaviour;

import java.net.Socket;

/**
 * Created by administrator on 12/4/14.
 */
public class NethackBehaviour extends SimpleBehaviour {
    private String owner;
    private Nethack dungeon;
    private NethackMessageFactory factory;
    private boolean verbose;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Nethack getDungeon() {
        return dungeon;
    }

    public void setDungeon(Nethack dungeon) {
        this.dungeon = dungeon;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public NethackBehaviour(String owner){
        dungeon = new Nethack(owner);
        this.owner = owner;
        verbose = true;
        factory = new NethackMessageFactory(dungeon,this);

    }
    @Override
    public void action() {

    }

    public void setup(SetupMessage msg,AID recipient, Socket requestSocket){
        if(msg.getRandom()){
            dungeon.setup();
            myAgent.addBehaviour(new visualizationUpdateBehaviour(owner,dungeon.getAvatar(),dungeon.getTheMap()));
            myAgent.addBehaviour(new ObjectSendingBehaviour(requestSocket,recipient,factory.getMessage()));

        }
        else{
            dungeon.setup(msg.getRole(),msg.getRace(),msg.getGender(),msg.getAlignment());
            myAgent.addBehaviour(new visualizationUpdateBehaviour(owner,dungeon.getAvatar(),dungeon.getTheMap()));
            myAgent.addBehaviour(new ObjectSendingBehaviour(requestSocket,recipient,factory.getMessage()));
        }
    }
    public void setupB(){}

    public void processInteractMessage(InteractMessage message,AID recipient, Socket socket){
        if(message.isMapRequest()){
            NethackMap map = dungeon.getTheMap();
            myAgent.addBehaviour(new ObjectSendingBehaviour(socket,recipient,map));
        }
        else if(message.isAvatarRequest())
        {
            PlayerCharacter avatar = dungeon.getAvatar();
            myAgent.addBehaviour(new ObjectSendingBehaviour(socket,recipient,avatar));
        }
        else if(message.getAction() != null){
            if(dungeon.action(message.getAction())){
                myAgent.addBehaviour(new ObjectSendingBehaviour(socket,recipient,factory.getMessage()));
                if (dungeon.isUpdate()) {
                    myAgent.addBehaviour(new visualizationUpdateBehaviour(owner, dungeon.getAvatar(), dungeon.getTheMap()));
                    dungeon.setUpdate(false);
                }
            }
            else{
                myAgent.addBehaviour(new ObjectSendingBehaviour(socket,recipient,factory.getErrorMessage()));
            }
        }
        else if(message.getChoice() != null){
            if(dungeon.action(message.getChoice())){
                myAgent.addBehaviour(new ObjectSendingBehaviour(socket,recipient,factory.getMessage()));
                if (dungeon.isUpdate()) {
                    myAgent.addBehaviour(new visualizationUpdateBehaviour(owner, dungeon.getAvatar(), dungeon.getTheMap()));
                    dungeon.setUpdate(false);
                }
            }
            else{
                myAgent.addBehaviour(new ObjectSendingBehaviour(socket,recipient,factory.getErrorMessage()));
            }
        }
        else if(message.getDirection() != null){
            if(dungeon.action(message.getDirection())){
                myAgent.addBehaviour(new ObjectSendingBehaviour(socket,recipient,factory.getMessage()));
                if (dungeon.isUpdate()) {
                    myAgent.addBehaviour(new visualizationUpdateBehaviour(owner, dungeon.getAvatar(), dungeon.getTheMap()));
                    dungeon.setUpdate(false);
                }
            }
            else{
                myAgent.addBehaviour(new ObjectSendingBehaviour(socket,recipient,factory.getErrorMessage()));
            }
        }
        else if(message.getMenuOption() != null){
            if(dungeon.action(message.getMenuOption())){
                myAgent.addBehaviour(new ObjectSendingBehaviour(socket,recipient,factory.getMessage()));
                if (dungeon.isUpdate()) {
                    myAgent.addBehaviour(new visualizationUpdateBehaviour(owner, dungeon.getAvatar(), dungeon.getTheMap()));
                    dungeon.setUpdate(false);
                }
            }
            else{
                myAgent.addBehaviour(new ObjectSendingBehaviour(socket,recipient,factory.getErrorMessage()));
            }
        }
        else if(message.getText() != null){
            if(dungeon.action(message.getText())){
                myAgent.addBehaviour(new ObjectSendingBehaviour(socket,recipient,factory.getMessage()));
                if (dungeon.isUpdate()) {
                    myAgent.addBehaviour(new visualizationUpdateBehaviour(owner, dungeon.getAvatar(), dungeon.getTheMap()));
                    dungeon.setUpdate(false);
                }
            }
            else{
                myAgent.addBehaviour(new ObjectSendingBehaviour(socket,recipient,factory.getErrorMessage()));
            }
        }
    }

/*    public void processMessage(RequestMessage o){
       if(o.getAvatarUpdate()){

       }
       else if(o.getMapUpdate()){
           NethackMap map = dungeon.getTheMap();
           myAgent.addBehaviour(new ObjectSendingBehaviour(new AID(owner,AID.ISGUID),map));
        }
       else if(o.getAction() != null){
           Object content = dungeon.action(o.getAction());
           if(content == null){
               //either quit or save method has been executed and null pointer was returned
               //((NethackAgent) myAgent).getDungeons().remove(owner);
               done();
           }
           else {
               myAgent.addBehaviour(new ObjectSendingBehaviour(new AID(owner, AID.ISGUID), content));
               if (dungeon.isUpdate()) {
                   myAgent.addBehaviour(new visualizationUpdateBehaviour(owner, dungeon.getAvatar(), dungeon.getTheMap()));
                   dungeon.setUpdate(false);
               }
           }
       }
        else if(o.getChoice() != null){
           Object content = dungeon.action(o.getChoice());
           if(content == null){
               //either quit or save method has been executed and null pointer was returned
               //((NethackAgent) myAgent).getDungeons().remove(owner);
               done();
           }
           else {
               myAgent.addBehaviour(new ObjectSendingBehaviour(new AID(owner, AID.ISGUID), content));
               if (dungeon.isUpdate()) {
                   myAgent.addBehaviour(new visualizationUpdateBehaviour(owner, dungeon.getAvatar(), dungeon.getTheMap()));
                   dungeon.setUpdate(false);
               }
           }
       }
        else if(o.getMenuChoice()!= null){
           Object content = dungeon.action(o.getMenuChoice());
           myAgent.addBehaviour(new ObjectSendingBehaviour(new AID(owner,AID.ISGUID),content));
           if(dungeon.isUpdate()){
               myAgent.addBehaviour(new visualizationUpdateBehaviour(owner,dungeon.getAvatar(),dungeon.getTheMap()));
               dungeon.setUpdate(false);
           }
       }
        else{
           System.out.printf("NethackAgent dungeon owner %s received empty request message\n", owner);
       }
    }*/
    public void quit(QuitMessage quitMessage,AID sender,Socket socket)
    {
        dungeon.quitFast();
    }

    @Override
    public boolean done() {
        return true;
    }
}
