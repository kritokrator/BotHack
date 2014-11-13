package bothack.classes;

import java.io.Serializable;

/**
 * Created by administrator on 11/13/14.
 */
public class NethackChoice implements Serializable {
    private char choice;

    public NethackChoice(){
        choice = '0';
    }
    public NethackChoice(int c){
        choice = (char)c;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = (char)choice;
    }
}
