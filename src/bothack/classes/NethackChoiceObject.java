package bothack.classes;

import java.io.Serializable;

/**
 * Created by administrator on 11/9/14.
 */
public class NethackChoiceObject implements Serializable {
    private String text;
    private String choices;
    private Integer auto;

    public NethackChoiceObject(){
        text="";
        choices="";
        auto = 0;
    }

    public NethackChoiceObject(String input){
        if(!input.contains("nhapi-yn-function")){
            text="Wrong message in the Nethack interperer was used to create this Object. Check source";
            choices="";
            auto = -1;
        }
        else{
            //placeholder for the substrings


        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getChoices() {
        return choices;
    }

    public void setChoices(String choices) {
        this.choices = choices;
    }

    public Integer getAuto() {
        return auto;
    }

    public void setAuto(Integer auto) {
        this.auto = auto;
    }
}
