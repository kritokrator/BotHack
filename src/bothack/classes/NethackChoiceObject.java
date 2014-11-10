package bothack.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by administrator on 11/9/14.
 */
public class NethackChoiceObject implements Serializable {
    private String text;
    private String choices;
    private int auto;

    public NethackChoiceObject(){
        text="";
        choices="";
        auto = 0;
    }

    public NethackChoiceObject(String input){
        ArrayList<String> result = new ArrayList<String>();
        if(!input.contains("nhapi-yn-function")){
            text="Wrong message in the Nethack interpreter was used to create this Object. Check source";
            choices="";
            auto = -1;
        }
        else{
            Pattern p = Pattern.compile("(\"([^\"]|\\\\\")*\")|([0-9]+)");
            Matcher m = p.matcher(input);
            while(m.find()){
                result.add(m.group());
            }
            //this should contain only three strings taken from the nhapi-yn-function ouput
            //if it contains more then an error has occured
            if((result.size() == 3)){
                this.text = result.get(0);
                this.choices = result.get(1);
                this.auto = Integer.parseInt(result.get(2));
            }
            else{
                System.err.println("Error occured the regexp used in the constructor returned more results than was expected");
                text="Error occured the regexp used in the constructor returned more results than was expected";
                choices="";
                auto = -1;
            }


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
