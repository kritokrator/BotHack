package bothack.classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@XmlRootElement
public class NethackMenuObject implements Serializable {
    private int id;
    private String caption;
    private String method;
    private ArrayList<NethackMenuItem> items;


    public NethackMenuObject() {
        this.id = -1;
        this.caption = "";
        this.method = "";
        this.items = new ArrayList<NethackMenuItem>();
    }

    public void parseInput(String input){
        ArrayList<String> tmp = new ArrayList<String>();
        //parses the input string, matches it to the correct regexp and then updates the correct portion of the menu
        if(input.contains("nhapi-add-menu")){
            //parse it and add to the array
            Pattern p = Pattern.compile("-?([0-9])+|\'([\\S])+|(\"([^\"]|\\\\\")*\")|(nil)");
            Matcher m = p.matcher(input);
            while(m.find()){
                tmp.add(m.group());
            }
            if(Integer.parseInt(tmp.get(0)) == this.id && tmp.size() == 8){
                addItem(tmp);
            }
        }
        else if(input.contains("nhapi-end-menu")){
            //parse it and update the caption
            Pattern p = Pattern.compile("([0-9])|(\"([^\"]|\\\\\")*\")");
            Matcher m = p.matcher(input);
            while(m.find()){
                tmp.add(m.group());
            }
            if(Integer.parseInt(tmp.get(0)) == this.id && tmp.size() == 2){
                this.caption = tmp.get(1).substring(1,tmp.get(1).length()-1);
            }
            else{
                //an error has occured
            }

        }
        else if(input.contains("nhapi-select-menu")){
            //parse it and update the selection method
            Pattern p = Pattern.compile("([0-9])|\'([\\S])+");
            Matcher m = p.matcher(input);
            while(m.find()){
                tmp.add(m.group());
            }
            if(Integer.parseInt(tmp.get(0)) == this.id && tmp.size() == 2){
                this.method = tmp.get(1).substring(1);
            }

        }
    }
    public boolean addItem(ArrayList<String> items){
        int menuId = Integer.parseInt(items.get(0));
        long glyph = Long.parseLong(items.get(1));
        long tile = Long.parseLong(items.get(2));
        int symbol = Integer.parseInt(items.get(3));
        int groupAcc = Integer.parseInt(items.get(4));
        String attribute = items.get(5);
        String description = items.get(6);
        boolean preselected = items.get(7).contains("nil") ? false : true;

        NethackMenuItem tmp = new NethackMenuItem(menuId,glyph,tile,symbol,groupAcc,attribute,description,preselected);
        return this.items.add(tmp);
    }
    public boolean addItem(int menuId, long glyph, long tile, int symbol, int groupAcc, String attribute, String description, boolean preselected){
        NethackMenuItem tmp = new NethackMenuItem(menuId,glyph,tile,symbol,groupAcc,attribute,description,preselected);
        return this.items.add(tmp);
    }
    public boolean addItem(NethackMenuItem item){
        return this.items.add(item);
    }


    public NethackMenuObject(int id) {
        this.id = id;
        this.caption = "";
        this.method = "";
        this.items = new ArrayList<NethackMenuItem>();
    }
    public NethackMenuObject(String input){
        Pattern p = Pattern.compile("[0-9]");
        Matcher m = p.matcher(input);
        if(m.find()){
            this.id = Integer.parseInt(m.group());
        }
        this.caption = "";
        this.method = "";
        this.items = new ArrayList<NethackMenuItem>();
    }

    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @XmlElement
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @XmlElementWrapper(name = "items")
    @XmlElement(name="item")
    public ArrayList<NethackMenuItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<NethackMenuItem> items) {
        this.items = items;
    }
}