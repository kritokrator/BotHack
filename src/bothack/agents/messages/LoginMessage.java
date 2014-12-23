package bothack.agents.messages;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by administrator on 12/22/14.
 */
@XmlRootElement
public class LoginMessage {
    String name;
    String password;

    public LoginMessage(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public LoginMessage() {
        name = "";
        password = "";
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
