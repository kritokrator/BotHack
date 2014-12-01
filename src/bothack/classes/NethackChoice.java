package bothack.classes;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * Created by administrator on 11/13/14.
 */
@XmlRootElement
public class NethackChoice implements Serializable {
    private char choice;

    public NethackChoice(){
        choice = '0';
    }
    public NethackChoice(int c){
        choice = (char)c;
    }

    /*@XmlElement(name="choice")
    public String getChoiceString(){
        return String.valueOf(choice);
    }

    public void setChoiceString(String c){
        choice = c.charAt(0);
    }
    */
    @XmlElement
    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = (char)choice;
    }
}
