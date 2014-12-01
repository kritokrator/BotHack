package bothack.classes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by administrator on 11/10/14.
 */
@XmlRootElement(name="item")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class NethackMenuItem implements Serializable{
    private int menuId;                 //the id of the parent menu
    private long glyph;                 //currently unnecessary but better be safe than sorry
    private long tile;                  //currently unnecessary but better be safe than sorry
    private int symbol;                 //actually a char; the character which represents the chosen option
    private int groupAcc;               //currently unnecessary but better be safe than sorry
    private String attribute;           //attribute of the option
    private String description;         //contains the description of this item
    private boolean preselected;        //set to true if the option is preselected;

    public NethackMenuItem() {
        this.menuId = -1;
        this.glyph = -1;
        this.tile = -1;
        this.symbol = -1;
        this.groupAcc = groupAcc;
        this.attribute = "";
        this.description = "";
        this.preselected = false;
    }

    public NethackMenuItem(int menuId, long glyph, long tile, int symbol, int groupAcc, String attribute, String description, boolean preselected) {
        this.menuId = menuId;
        this.glyph = glyph;
        this.tile = tile;
        this.symbol = symbol;
        this.groupAcc = groupAcc;
        this.attribute = attribute;
        this.description = description;
        this.preselected = preselected;
    }

    @XmlElement
    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    @XmlElement
    public long getGlyph() {
        return glyph;
    }

    public void setGlyph(long glyph) {
        this.glyph = glyph;
    }

    @XmlElement
    public long getTile() {
        return tile;
    }

    public void setTile(long tile) {
        this.tile = tile;
    }

    @XmlElement
    public int getSymbol() {
        return symbol;
    }

    public void setSymbol(int symbol) {
        this.symbol = symbol;
    }

    @XmlElement
    public int getGroupAcc() {
        return groupAcc;
    }

    public void setGroupAcc(int groupAcc) {
        this.groupAcc = groupAcc;
    }

    @XmlElement
    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement
    public boolean isPreselected() {
        return preselected;
    }

    public void setPreselected(boolean preselected) {
        this.preselected = preselected;
    }
}
