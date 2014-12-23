package bothack.classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by administrator on 12/19/14.
 */
@XmlRootElement
public class NethackStringObject {
    private String text;

    public NethackStringObject(){
        text = "";
    }
    public NethackStringObject(String text){
        this.text = text;
    }

    @XmlElement
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
