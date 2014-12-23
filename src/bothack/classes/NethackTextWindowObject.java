package bothack.classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by administrator on 12/20/14.
 */
@XmlRootElement
public class NethackTextWindowObject {
    private ArrayList<String> content;
    private boolean active;

    public NethackTextWindowObject(ArrayList<String> content) {
        this.content = content;
        active = true;
    }

    public NethackTextWindowObject() {
        content = new ArrayList<String>();
        active = true;
    }

    @XmlElementWrapper(name="content")
    @XmlElement(name="line")
    public ArrayList<String> getContent() {
        return content;
    }

    public void setContent(ArrayList<String> content) {
        this.content = content;
    }

    public void parseInput(String input){
        Pattern p = Pattern.compile("\".*\"");
        Matcher m = p.matcher(input);
        String result = "";
        while(m.find()){
            result = m.group();
            addLine(result.substring(1,result.length()-1));

        }
    }
    public void addLine(String line){
        content.add(line);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
