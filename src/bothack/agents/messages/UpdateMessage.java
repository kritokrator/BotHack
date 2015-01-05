package bothack.agents.messages;

import bothack.classes.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by administrator on 12/27/14.
 */
@XmlRootElement
public class UpdateMessage {
    private NethackCommandObject command;
    private NethackChoiceObject choice;
    private NethackMenuObject menu;
    private ArrayList<NethackTextWindowObject> textMenus;
    private ArrayList<NethackMessageObject> messages;
    private PlayerCharacter avatar;
    private NethackMap map;

    public UpdateMessage() {
        command = null;
        choice = null;
        menu = null;
        textMenus = new ArrayList<NethackTextWindowObject>();
        messages = new ArrayList<NethackMessageObject>();
        avatar = null;
        map = null;
    }

    public UpdateMessage(NethackCommandObject command, NethackChoiceObject choice, NethackMenuObject menu, ArrayList<NethackTextWindowObject> textMenus, ArrayList<NethackMessageObject> messages, PlayerCharacter avatar, NethackMap map) {
        this.command = command;
        this.choice = choice;
        this.menu = menu;
        this.textMenus = textMenus;
        this.messages = messages;
        this.avatar = avatar;
        this.map = map;
    }

    @XmlElement
    public NethackCommandObject getCommand() {
        return command;
    }

    public void setCommand(NethackCommandObject command) {
        this.command = command;
    }

    @XmlElement
    public NethackChoiceObject getChoice() {
        return choice;
    }

    public void setChoice(NethackChoiceObject choice) {
        this.choice = choice;
    }

    @XmlElement
    public NethackMenuObject getMenu() {
        return menu;
    }

    public void setMenu(NethackMenuObject menu) {
        this.menu = menu;
    }

    @XmlElementWrapper(name = "textMenus")
    @XmlElement(name = "textMenu")
    public ArrayList<NethackTextWindowObject> getTextMenus() {
        return textMenus;
    }

    public void setTextMenus(ArrayList<NethackTextWindowObject> textMenus) {
        this.textMenus = textMenus;
    }

    @XmlElementWrapper(name = "messages")
    @XmlElement(name = "message")
    public ArrayList<NethackMessageObject> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<NethackMessageObject> messages) {
        this.messages = messages;
    }

    @XmlElement
    public PlayerCharacter getAvatar() {
        return avatar;
    }

    public void setAvatar(PlayerCharacter avatar) {
        this.avatar = avatar;
    }

    @XmlElement
    public NethackMap getMap() {
        return map;
    }

    public void setMap(NethackMap map) {
        this.map = map;
    }
}
