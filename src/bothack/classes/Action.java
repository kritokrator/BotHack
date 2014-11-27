package bothack.classes;

import bothack.interfaces.ActionInterface;
import bothack.interfaces.Command;
import javax.xml.bind.*;

/**
 * Created by krito on 11/26/14.
 */
public class Action extends ActionInterface {
    private Command command;

    public Action(){
    }
    public Action(Command c){
        command = c;
    }

    public Command getCommand() {
        return command;
    }
}
