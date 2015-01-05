package bothack.agents.messages;

import bothack.classes.NethackChoice;
import bothack.classes.NethackDirectionObject;
import bothack.classes.NethackMenuChoice;
import bothack.classes.NethackStringObject;
import bothack.interfaces.Command;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by administrator on 12/27/14.
 */
@XmlRootElement
public class InteractMessage {
    private String cookie;
    private Command action;
    private NethackChoice choice;
    private NethackDirectionObject direction;
    private NethackStringObject text;
    private NethackMenuChoice menuOption;
    private boolean mapRequest;
    private boolean avatarRequest;

    public InteractMessage() {
        cookie="";
        action = null;
        choice = null;
        direction = null;
        text = null;
        menuOption = null;
        mapRequest = false;
        avatarRequest = false;
    }

    public InteractMessage(String cookie, Command action, NethackChoice choice, NethackDirectionObject direction, NethackStringObject text, NethackMenuChoice menuOption, boolean mapRequest, boolean avatarRequest) {
        this.cookie = cookie;
        this.action = action;
        this.choice = choice;
        this.direction = direction;
        this.text = text;
        this.menuOption = menuOption;
        this.mapRequest = mapRequest;
        this.avatarRequest = avatarRequest;
    }
    @XmlElement
    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
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
    public NethackDirectionObject getDirection() {
        return direction;
    }

    public void setDirection(NethackDirectionObject direction) {
        this.direction = direction;
    }

    @XmlElement
    public NethackStringObject getText() {
        return text;
    }

    public void setText(NethackStringObject text) {
        this.text = text;
    }

    @XmlElement
    public NethackMenuChoice getMenuOption() {
        return menuOption;
    }

    public void setMenuOption(NethackMenuChoice menuOption) {
        this.menuOption = menuOption;
    }

    @XmlElement
    public boolean isMapRequest() {
        return mapRequest;
    }

    public void setMapRequest(boolean mapRequest) {
        this.mapRequest = mapRequest;
    }

    @XmlElement
    public boolean isAvatarRequest() {
        return avatarRequest;
    }

    public void setAvatarRequest(boolean avatarRequest) {
        this.avatarRequest = avatarRequest;
    }
}
