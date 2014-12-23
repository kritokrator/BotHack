package bothack.classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by administrator on 12/19/14.
 */
@XmlRootElement
public class MenuOption {
    private int choice;
    private int category;

    public MenuOption(int choice, int category) {
        this.choice = choice;
        this.category = category;
    }

    public MenuOption() {
        choice = 0;
        category = 0;
    }

    @XmlElement
    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    @XmlElement
    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
