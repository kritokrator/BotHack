package bothack.agents.messages;

import bothack.classes.NethackChoice;
import bothack.classes.NethackMenuChoice;
import bothack.interfaces.Command;

import javax.xml.bind.annotation.*;

/**
 * Created by krito on 11/29/14.
 */
@XmlRootElement
public class RequestMessage {
    private Boolean mapUpdate;
    private Boolean avatarUpdate;
    private Command action;
    private NethackChoice choice;
    private NethackMenuChoice menuChoice;

    public RequestMessage(){
        mapUpdate = false;
        avatarUpdate = false;
        action = null;
        choice = null;
        menuChoice = null;
    }
    @XmlElement
    public Boolean getMapUpdate() {
        return mapUpdate;
    }

    public void setMapUpdate(Boolean mapUpdate) {
        this.mapUpdate = mapUpdate;
    }
    @XmlElement
    public Boolean getAvatarUpdate() {
        return avatarUpdate;
    }

    public void setAvatarUpdate(Boolean avatarUpdate) {
        this.avatarUpdate = avatarUpdate;
    }

    @XmlElement
    public Command getAction() {
        return action;
    }

    public void setAction(Command action) {
        this.action = action;
    }

    @XmlElement
    public NethackChoice getChoice() {
        return choice;
    }

    public void setChoice(NethackChoice choice) {
        this.choice = choice;
    }

    @XmlElement
    public NethackMenuChoice getMenuChoice() {
        return menuChoice;
    }

    public void setMenuChoice(NethackMenuChoice menuChoice) {
        this.menuChoice = menuChoice;
    }
}
