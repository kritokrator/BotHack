package bothack.classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by administrator on 11/13/14.
 */
@XmlRootElement
public class NethackMenuChoice {
    private ArrayList<MenuOption> choices;

    public NethackMenuChoice(){
        choices = new ArrayList<MenuOption>();
    }
    public NethackMenuChoice(int c){
        choices = new ArrayList<MenuOption>();
        choices.add(new MenuOption(c,0));
    }
    @XmlElement
    public ArrayList<MenuOption> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<MenuOption> choices) {
        this.choices = choices;
    }

    public String getMenu(){
        String result = "";
        for(MenuOption m : choices){
            result += m.getChoice() + " " + m.getCategory() + " ";
        }
        return result.substring(0,result.length()-1);
    }

}
