package bothack.agents.behaviours;

import bothack.agents.NethackAgent;
import bothack.agents.messages.QuitMessage;
import bothack.agents.messages.RequestMessage;
import bothack.agents.messages.SetupMessage;
import bothack.classes.Nethack;
import bothack.classes.NethackMap;
import bothack.classes.PlayerCharacter;
import jade.core.AID;
import jade.core.behaviours.SimpleBehaviour;

/**
 * Created by administrator on 12/4/14.
 */
public class NethackBehaviour extends SimpleBehaviour {
    private String owner;
    private Nethack dungeon;

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

    public NethackBehaviour(String owner){
        dungeon = new Nethack(owner);
        this.owner = owner;
    }
    @Override
    public void action() {

    }

    public void setup(SetupMessage msg){
        if(msg.getRandom()){
            dungeon.setup();
            myAgent.addBehaviour(new visualizationUpdateBehaviour(owner,dungeon.getAvatar(),dungeon.getTheMap()));
        }
        else{
            dungeon.setup(msg.getRole(),msg.getRace(),msg.getGender(),msg.getAlignment());
            myAgent.addBehaviour(new visualizationUpdateBehaviour(owner,dungeon.getAvatar(),dungeon.getTheMap()));
        }
    }
    public void setupB(){}

    public void processMessage(RequestMessage o){
       if(o.getAvatarUpdate()){
           PlayerCharacter avatar = dungeon.getAvatar();
           myAgent.addBehaviour(new ObjectSendingBehaviour(new AID(owner,AID.ISGUID),avatar));
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
    }
    public void quit(){dungeon.quitFast();}

    @Override
    public boolean done() {
        return true;
    }
}
