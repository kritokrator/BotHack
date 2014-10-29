package bothack.interfaces;

import bothack.classes.Coordinate;
import bothack.classes.Tile;

/**
 * Created by administrator on 10/22/14.
 */
public interface NethackMapInterface {
    public void update(String s);
    public Tile getTile(Coordinate c);
    public Tile getTile(String s1, String s2);
    public Tile getTile(int x, int y);
    public java.util.HashMap<Coordinate, Tile> getMap();
}
