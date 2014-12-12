package bothack.classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by administrator on 12/8/14.
 */
@XmlRootElement
public class VisualInterfaceWrapper {
    String owner;
    NethackMap map;
    PlayerCharacter playerCharacter;

    public VisualInterfaceWrapper() {
    }

    @XmlElement
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @XmlElement
    public NethackMap getMap() {
        return map;
    }

    public void setMap(NethackMap map) {
        this.map = map;
    }

    @XmlElement
    public PlayerCharacter getPlayerCharacter() {
        return playerCharacter;
    }

    public void setPlayerCharacter(PlayerCharacter playerCharacter) {
        this.playerCharacter = playerCharacter;
    }
}
