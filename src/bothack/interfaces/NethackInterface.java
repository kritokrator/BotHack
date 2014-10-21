package bothack.interfaces;

import bothack.classes.NotYetImplementedException;

import java.io.IOException;

/**
 * Created by administrator on 10/21/14.
 */
public interface NethackInterface {
    public void setup()throws NotYetImplementedException;    //no args all default
    public void play() throws NotYetImplementedException;     //no args play is interactive
    public void save() throws NotYetImplementedException;     //no args save current progress to the default location
    public void quit() throws NotYetImplementedException;     // exit the game, destroy everything
    public String readNethackLine() throws IOException;
}
