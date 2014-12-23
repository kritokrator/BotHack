package bothack.classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by administrator on 12/20/14.
 */
@XmlRootElement
public class NethackMessageObject {
    private String message;

    public NethackMessageObject(String message) {
        this.message = message;
    }

    public NethackMessageObject() {
        message = "";
    }

    @XmlElement
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void parseInput(String input){
        Pattern p = Pattern.compile("\".*\"");
        Matcher m = p.matcher(input);
        String result = "";
        while(m.find()){
            result = m.group();
            setMessage(result.substring(1,result.length()-1));

        }
    }
}
