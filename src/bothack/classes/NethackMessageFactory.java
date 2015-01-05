package bothack.classes;

import bothack.agents.behaviours.NethackBehaviour;
import bothack.agents.messages.DeathMessage;
import bothack.agents.messages.ErrorMessage;
import bothack.agents.messages.UpdateMessage;

/**
 * Created by administrator on 12/27/14.
 */
public class NethackMessageFactory {

    private Nethack dungeon;
    private NethackBehaviour nethackBehaviour;

    public NethackMessageFactory() {
        dungeon = null;
        nethackBehaviour = null;
    }

    public NethackMessageFactory(Nethack dungeon, NethackBehaviour nb){
        this.nethackBehaviour = nb;
        this.dungeon = dungeon;
    }

    public  UpdateMessage getUpdateMessage(){
        UpdateMessage result = new UpdateMessage();

        for(Object o : dungeon.getObjectContainer()){
            if(o instanceof NethackChoiceObject){
                result.setChoice((NethackChoiceObject)o);
            }
            else if(o instanceof NethackMenuObject){
                result.setMenu((NethackMenuObject)o);
            }
            else if(o instanceof NethackTextWindowObject){
                result.getTextMenus().add((NethackTextWindowObject)o);
            }
            else if(o instanceof NethackMessageObject){
                result.getMessages().add((NethackMessageObject)o);
            }
        }
        if(nethackBehaviour.isVerbose()){
            result.setMap(dungeon.getTheMap());
            result.setAvatar(dungeon.getAvatar());
        }
        result.setCommand(dungeon.getLastCommandPrompt());

        return result;
    }
    public ErrorMessage getErrorMessage(){
        ErrorMessage result = new ErrorMessage();
        for(Object o : dungeon.getObjectContainer()){
            if( o instanceof bothack.classes.Error){
                result.setError((bothack.classes.Error) o);
            }
        }
        return result;
    }

    public DeathMessage getDeathMessage(){
        DeathMessage result = new DeathMessage();

        for(Object o : dungeon.getObjectContainer()){
            if(o instanceof NethackChoiceObject){
                result.setChoice((NethackChoiceObject)o);
            }
            else if(o instanceof NethackMenuObject){
                result.setMenu((NethackMenuObject)o);
            }
            else if(o instanceof NethackTextWindowObject){
                result.getTextMenus().add((NethackTextWindowObject)o);
            }
            else if(o instanceof NethackMessageObject){
                result.getMessages().add((NethackMessageObject)o);
            }
        }
        result.setMap(dungeon.getTheMap());
        result.setAvatar(dungeon.getAvatar());
        result.setCommand(dungeon.getLastCommandPrompt());

        return result;
    }

    public Object getMessage(){
        if(dungeon.getLastCommandPrompt().getPrompt().contains("dead")){
            DeathMessage result = new DeathMessage();
            for(Object o : dungeon.getObjectContainer()){
                if(o instanceof NethackChoiceObject){
                    result.setChoice((NethackChoiceObject)o);
                }
                else if(o instanceof NethackMenuObject){
                    result.setMenu((NethackMenuObject)o);
                }
                else if(o instanceof NethackTextWindowObject){
                    result.getTextMenus().add((NethackTextWindowObject)o);
                }
                else if(o instanceof NethackMessageObject){
                    result.getMessages().add((NethackMessageObject)o);
                }
            }
            result.setMap(dungeon.getTheMap());
            result.setAvatar(dungeon.getAvatar());
            result.setCommand(dungeon.getLastCommandPrompt());

            return result;
        }
        else{
            UpdateMessage result = new UpdateMessage();

            for(Object o : dungeon.getObjectContainer()){
                if(o instanceof NethackChoiceObject){
                    result.setChoice((NethackChoiceObject)o);
                }
                else if(o instanceof NethackMenuObject){
                    result.setMenu((NethackMenuObject)o);
                }
                else if(o instanceof NethackTextWindowObject){
                    result.getTextMenus().add((NethackTextWindowObject)o);
                }
                else if(o instanceof NethackMessageObject){//tu jest jakiś błąd srpawdzic !
                    result.getMessages().add((NethackMessageObject)o);
                }
            }
            if(nethackBehaviour.isVerbose()){
                result.setMap(dungeon.getTheMap());
                result.setAvatar(dungeon.getAvatar());
            }
            result.setCommand(dungeon.getLastCommandPrompt());

            return result;
        }
    }
}
