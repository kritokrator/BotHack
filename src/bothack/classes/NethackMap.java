package bothack.classes;

import bothack.interfaces.NethackMapInterface;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by administrator on 10/22/14.
 */
@XmlRootElement
public class NethackMap implements NethackMapInterface, Serializable{

    private HashMap<Coordinate,Tile> level;

    public NethackMap(){
        level = new HashMap<Coordinate,Tile>();
    }

    @Override
    public void update(String s) {
        String[] args = s.split(" ");
        Coordinate c = new Coordinate(Integer.parseInt(args[1]),Integer.parseInt(args[2]));
        Tile t = new Tile(args[6],args[3],args[4]);
        update(c,t);
    }
    @Override
    public void update(Coordinate c, Tile t){
        if(level.containsKey(c)){
            level.replace(c,t);
        }
        else{
            level.put(c,t);
        }
    }
    @Override
    public void update(String x, String y, String character, String color, String glyph)
    {
        Coordinate c = new Coordinate(Integer.parseInt(x),Integer.parseInt(y));
        Tile t = new Tile(character,color,glyph);
        update(c,t);
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
    @XmlElement
    public HashMap<Coordinate, Tile> getMap() {
        return level;
    }
}
