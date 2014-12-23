package bothack.classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by administrator on 12/20/14.
 */
@XmlRootElement
public class Error {
    private Integer code;
    private String text;

    public Error(){
        code = new Integer(-1);
        text = "";
    }

    public Error(Integer c, String t){
        code = c;
        text = t;
    }

    @XmlElement
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @XmlElement
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
