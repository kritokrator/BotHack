package bothack.interfaces;

import bothack.classes.NethackChoice;
import bothack.classes.NethackMenuChoice;
import bothack.classes.NotYetImplementedException;

import java.io.IOException;

/**
 * Created by administrator on 10/21/14.
 */
public interface NethackInterface {
    public void setup()throws NotYetImplementedException;   //no args all default
    public void save() throws NotYetImplementedException;   //no args save current progress to the default location
    public void quit() throws NotYetImplementedException;   // exit the game, destroy everything
    //public void action(Command c) throws NotYetImplementedException;
    public Object action(Command c);
    public Object action(NethackChoice nc);
    public Object action(NethackMenuChoice nmc);
    public void setup(String role,String race,String gender,String alignment);
}
