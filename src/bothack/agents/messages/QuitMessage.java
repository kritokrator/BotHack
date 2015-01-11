package bothack.agents.messages;

import jade.core.Agent;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by krito on 11/29/14.
 */
@XmlRootElement
public class QuitMessage {
    private String sender;
    private String cookie;

    public QuitMessage(){
        sender = "";
    }
    public  QuitMessage(Agent owner){
        sender = owner.getLocalName();
    }

    @XmlElement
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @XmlElement
    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
