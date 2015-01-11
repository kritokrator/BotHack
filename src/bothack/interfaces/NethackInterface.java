package bothack.interfaces;

import bothack.classes.*;

/**
 * Created by administrator on 10/21/14.
 */
public interface NethackInterface {
    public void setup()throws NotYetImplementedException;   //no args all default
    public void save() throws NotYetImplementedException;   //no args save current progress to the default location
    public void quit() throws NotYetImplementedException;   // exit the game, destroy everything

    public boolean action(NethackStringObject s);

    public boolean action(NethackDirectionObject d);

    //public void action(Command c) throws NotYetImplementedException;
    public boolean action(Command c);
    public boolean action(NethackChoice nc);
    public boolean action(NethackMenuChoice nmc);
    public void setup(String role,String race,String gender,String alignment);
}
