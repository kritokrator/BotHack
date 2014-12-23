package bothack.classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by administrator on 12/22/14.
 */
@XmlRootElement
public class Address {
    private String cookie;
    private Integer port;
    private String dungeon;

    public Address() {
        cookie ="";
        port = -1;
        dungeon="";
    }

    @XmlElement
    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    @XmlElement
    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @XmlElement
    public String getDungeon() {
        return dungeon;
    }

    public void setDungeon(String dungeon) {
        this.dungeon = dungeon;
    }
}
