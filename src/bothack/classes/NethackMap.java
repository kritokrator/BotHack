package bothack.classes;

import bothack.interfaces.NethackMapInterface;

import java.util.HashMap;

/**
 * Created by administrator on 10/22/14.
 */
public class NethackMap implements NethackMapInterface {
    private HashMap<Coordinate,Tile> level;

    public NethackMap(){
        level = new HashMap<Coordinate,Tile>();
    }

    @Override
    public void update(String s) {
        String[] args = s.split(" ");
        Coordinate c = new Coordinate(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
        Tile t = new Tile(args[5],args[2],args[3]);
        if(level.containsKey(c)){

        }

    }

    @Override
    public Tile getTile(Coordinate c) {
        return level.get(c);
    }

    @Override
    public Tile getTile(String s1, String s2) {
        return level.get(new Coordinate(Integer.parseInt(s1),Integer.parseInt(s2)));
    }

    @Override
    public Tile getTile(int x, int y) {
        return level.get(new Coordinate(x,y));
    }

    @Override
    public HashMap<Coordinate, Tile> getMap() {
        return level;
    }
}
