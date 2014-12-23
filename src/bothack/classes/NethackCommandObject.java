package bothack.classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by administrator on 11/12/14.
 */
@XmlRootElement
public class NethackCommandObject implements Serializable{
    private String prompt;
    private Integer code;
    public NethackCommandObject(){
        prompt = "";
    }
    public NethackCommandObject(String input){
        Pattern p = Pattern.compile("[a-z]+");
        Matcher m = p.matcher(input);
        if(m.find()){
            this.prompt = m.group();
        }
        if(prompt.equals("command")){
            code = new Integer(1);
        }
        else if(prompt.equals("number")){
            code = new Integer(2);
        }
        else if(prompt.equals("menu")){
            code = new Integer(3);
        }
        else if(prompt.equals("string")){
            code = new Integer (4);
        }
        else if(prompt.equals("direction")){
            code = new Integer(5);
        }
        else if(prompt.equals("dummy")){
            code = new Integer(6);
        }
        else{
            code = new Integer(0);
        }
    }

    @XmlElement
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @XmlElement
    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
