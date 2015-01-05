package bothack.classes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by administrator on 11/9/14.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class NethackChoiceObject implements Serializable {
    @XmlElement
    private String text;
    @XmlElement
    private String choices;
    @XmlElement
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
                this.choices = result.get(1).substring(1,result.get(1).length()-1);
                this.auto = Integer.parseInt(result.get(2));
            }
            else{
                System.err.println("Error occured the regexp used in the constructor returned more results than was expected");
                text="Error occured the regexp used in the constructor returned more results than was expected";
                choices="";
                auto = -1;
            }
            if(choices.length() == 0){
                String partial = null;
                choices ="";
                Pattern p2 = Pattern.compile("\\[(\\$)?([a-z]+-?)*");
                Matcher m2 = p2.matcher(input);
                while(m2.find()){
                    partial = m2.group();
                }
                if(partial == null){
                    choices ="?*";
                }
                else{
                    char c = partial.charAt(1);
                    for(int i = 1; i < partial.length();i++){
                        if(partial.charAt(i) != '-'){
                            choices+=partial.charAt(i);
                            c = partial.charAt(i);
                        }
                        else{
                            while(++c != partial.charAt(i+1)){
                                choices+=c;
                            }
                        }
                    }
                    choices += "?*";
                }
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
