package bothack.classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by administrator on 12/19/14.
 */
@XmlRootElement
public class NethackDirectionObject {
    String direction;

    public NethackDirectionObject(){
        direction = "";
    }

    public NethackDirectionObject(String direction){
        this.direction = direction;
    }
    @XmlElement
    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
