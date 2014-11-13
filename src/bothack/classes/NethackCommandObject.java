package bothack.classes;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by administrator on 11/12/14.
 */
public class NethackCommandObject implements Serializable{
    private String prompt;
    public NethackCommandObject(String input){
        Pattern p = Pattern.compile("[a-z]+");
        Matcher m = p.matcher(input);
        if(m.find()){
            this.prompt = m.group();
        }
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
