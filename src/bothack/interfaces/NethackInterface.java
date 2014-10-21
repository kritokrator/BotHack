package bothack.interfaces;

/**
 * Created by administrator on 10/21/14.
 */
public interface NethackInterface {
    public void setup();    //no args all default
    public void play();     //no args play is interactive
    public void save();     //no args save current progress to the default location
    public void quit();     // exit the game, destroy everything
}
