package bothack.agents.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by krito on 11/29/14.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SetupMessage {
    @XmlElement
    private Boolean random;
    @XmlElement
    private String role;
    @XmlElement
    private String race;
    @XmlElement
    private String gender;
    @XmlElement
    private String alignment;

    public SetupMessage(String role, String race, String gender, String alignment) {
        this.random = false;
        this.role = role;
        this.race = race;
        this.gender = gender;
        this.alignment = alignment;
    }

    public SetupMessage() {
        this.random = true;
    }

    public Boolean getRandom() {
        return random;
    }

    public void setRandom(Boolean random) {
        this.random = random;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }
}
