package bothack.interfaces;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by administrator on 10/22/14.
 */
@XmlRootElement
@XmlEnum
@XmlType(name="color")
public enum Color {
    @XmlEnumValue("0")
    BLACK ("0"),
    @XmlEnumValue("1")
    RED ("1"),
    @XmlEnumValue("2")
    GREEN ("2"),
    @XmlEnumValue("3")
    BROWN ("3"),
    @XmlEnumValue("4")
    BLUE ("4"),
    @XmlEnumValue("5")
    MAGENTA ("5"),
    @XmlEnumValue("6")
    CYAN ("6"),
    @XmlEnumValue("7")
    GRAY ("7"),
    @XmlEnumValue("8")
    DARKGRAY ("8"),
    @XmlEnumValue("9")
    ORANGE ("9"),
    @XmlEnumValue("10")
    BRIGHTGREEN ("10"),
    @XmlEnumValue("11")
    YELLOW ("11"),
    @XmlEnumValue("12")
    BRIGHTBLUE ("12"),
    @XmlEnumValue("13")
    BRIGHTMAGENTA ("13"),
    @XmlEnumValue("14")
    BRIGHTCYAN ("14"),
    @XmlEnumValue("15")
    WHITE ("15");

    private String index;
    private Color(String index){
        this.index = index;
    }
    public String getIndex(){
        return index;
    }

}
