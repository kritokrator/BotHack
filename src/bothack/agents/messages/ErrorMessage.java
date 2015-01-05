package bothack.agents.messages;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by administrator on 12/27/14.
 */
@XmlRootElement
public class ErrorMessage {
    private bothack.classes.Error error;

    public ErrorMessage() {
        error = null;
    }

    public ErrorMessage(bothack.classes.Error error) {
        this.error = error;
    }

    @XmlElement
    public bothack.classes.Error getError() {
        return error;
    }

    public void setError(bothack.classes.Error error) {
        this.error = error;
    }
}
