package bothack.agents.gui;

import bothack.classes.NethackMap;
import bothack.classes.NotYetImplementedException;
import bothack.classes.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by administrator on 12/10/14.
 */
public class Map extends JPanel {

    private HashMap<Tile,JLabel> tileSet;
    private HashMap<Character,JLabel> basicTileSet;
    private HashMap<Character,ImageIcon> iconTileSet;
    private GridLayout layout;
    private ImageIcon iconTest;
    private ImageIcon notImplementedIcon;
    private ImageIcon blankIcon;
    private int mapCols;
    private int mapRows;
    public Map(){
        try {
            basicTileSet = new HashMap<Character, JLabel>();
            tileSet = new HashMap<Tile, JLabel>();
            iconTileSet = new HashMap<Character,ImageIcon>();
            BufferedImage filler = ImageIO.read(new File("resources/tiles/not_implemented.png"));
            notImplementedIcon = new ImageIcon(filler);
            BufferedImage blank = ImageIO.read(new File("resources/tiles/blank.png"));
            blankIcon = new ImageIcon(blank);
            layout = new GridLayout(100,100,0,0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        setSize(2000,2000);
        setLayout(layout);
        mapRows = layout.getRows();
        mapCols = layout.getColumns();
    }

    public int getMapCols() {
        return mapCols;
    }

    public int getMapRows() {
        return mapRows;
    }

    public void updateMap(NethackMap mapUpdate){
        removeAll();
        for(int y = 0; y<mapRows;y++ ){
            for(int x =0; x<mapCols;x++){
                Tile tile = mapUpdate.getTile(x,y);
                if(tile != null){
                    Character symbol = tile.getSymbol();
                    if(iconTileSet.containsKey(symbol)){
                        add(new JLabel(iconTileSet.get(symbol)));
                    }
                    else if((symbol>=65 && symbol <=90) || (symbol>= 97 && symbol <=122)){
                        add(new JLabel(iconTileSet.get('a')));
                    }
                    else{
                        add(new JLabel(notImplementedIcon));
                    }
                    //String msg = "Tile x " + map.
                    //JOptionPane.showMessageDialog(this,);

                }
                else{
                    add(new JLabel(blankIcon));
                }
            }
        }
    };

    public void populateBasicTileSet(){
        try {
            BufferedImage floor = ImageIO.read(new File("resources/tiles/floor.png"));
            JLabel floorLabel = new JLabel(new ImageIcon(floor));
            basicTileSet.put('.',floorLabel);
            iconTileSet.put('.',new ImageIcon(floor));
            BufferedImage armor = ImageIO.read(new File("resources/tiles/armor.png"));
            JLabel armorLabel = new JLabel(new ImageIcon(armor));
            basicTileSet.put('[',armorLabel);
            iconTileSet.put('[',new ImageIcon(armor));
            BufferedImage corridor = ImageIO.read(new File("resources/tiles/corridor.png"));
            JLabel corridorLabel = new JLabel(new ImageIcon(corridor));
            basicTileSet.put('#',corridorLabel);
            iconTileSet.put('#',new ImageIcon(corridor));
            BufferedImage closedDoor = ImageIO.read(new File("resources/tiles/door_closed.png"));
            JLabel closedDoorLabel = new JLabel(new ImageIcon(closedDoor));
            basicTileSet.put('+',closedDoorLabel);
            iconTileSet.put('+',new ImageIcon(closedDoor));
            BufferedImage food = ImageIO.read(new File("resources/tiles/food.png"));
            JLabel foodLabel = new JLabel(new ImageIcon(food));
            basicTileSet.put('%',foodLabel);
            iconTileSet.put('%',new ImageIcon(food));
            BufferedImage gold = ImageIO.read(new File("resources/tiles/gold.png"));
            JLabel goldLabel = new JLabel(new ImageIcon(gold));
            basicTileSet.put('$',goldLabel);
            iconTileSet.put('$',new ImageIcon(gold));
            BufferedImage human = ImageIO.read(new File("resources/tiles/human.png"));
            JLabel humanLabel = new JLabel(new ImageIcon(human));
            basicTileSet.put('@',humanLabel);
            iconTileSet.put('@',new ImageIcon(human));
            BufferedImage item = ImageIO.read(new File("resources/tiles/item.png"));
            JLabel itemLabel = new JLabel(new ImageIcon(item));
            basicTileSet.put('(',itemLabel);
            iconTileSet.put('(',new ImageIcon(item));
            BufferedImage monster_generic = ImageIO.read(new File("resources/tiles/monster_generic.png"));
            JLabel monsterLabel = new JLabel(new ImageIcon(monster_generic));
            basicTileSet.put('a',monsterLabel);
            iconTileSet.put('a',new ImageIcon(monster_generic));
            BufferedImage potion = ImageIO.read(new File("resources/tiles/potion.png"));
            JLabel potionLabel = new JLabel(new ImageIcon(potion));
            basicTileSet.put('!',potionLabel);
            iconTileSet.put('!',new ImageIcon(potion));
            BufferedImage ring = ImageIO.read(new File("resources/tiles/ring.png"));
            JLabel ringLabel = new JLabel(new ImageIcon(potion));
            basicTileSet.put('=',ringLabel);
            iconTileSet.put('=',new ImageIcon(ring));
            BufferedImage scroll = ImageIO.read(new File("resources/tiles/scroll.png"));
            JLabel scrollLabel = new JLabel(new ImageIcon(scroll));
            basicTileSet.put('?',scrollLabel);
            iconTileSet.put('?',new ImageIcon(scroll));
            BufferedImage staircase_down = ImageIO.read(new File("resources/tiles/staircase_down.png"));
            JLabel staircase_downLabel = new JLabel(new ImageIcon(staircase_down));
            basicTileSet.put('>',staircase_downLabel);
            iconTileSet.put('>',new ImageIcon(staircase_down));
            BufferedImage staircase_up = ImageIO.read(new File("resources/tiles/staircase_up.png"));
            JLabel staircase_upLabel = new JLabel(new ImageIcon(staircase_up));
            basicTileSet.put('<',staircase_upLabel);
            iconTileSet.put('<',new ImageIcon(staircase_up));
            BufferedImage trap = ImageIO.read(new File("resources/tiles/trap.png"));
            JLabel trapLabel = new JLabel(new ImageIcon(trap));
            basicTileSet.put('^',trapLabel);
            iconTileSet.put('^',new ImageIcon(trap));
            BufferedImage wall = ImageIO.read(new File("resources/tiles/wall.png"));
            JLabel wallLabel = new JLabel(new ImageIcon(wall));
            basicTileSet.put('|',wallLabel);
            iconTileSet.put('|',new ImageIcon(wall));
            BufferedImage wand = ImageIO.read(new File("resources/tiles/wand.png"));
            JLabel wandLabel = new JLabel(new ImageIcon(wand));
            basicTileSet.put('/',wandLabel);
            iconTileSet.put('/',new ImageIcon(wand));
            BufferedImage weapon = ImageIO.read(new File("resources/tiles/weapon.png"));
            JLabel weaponLabel = new JLabel(new ImageIcon(weapon));
            basicTileSet.put(')',weaponLabel);
            iconTileSet.put(')',new ImageIcon(weapon));
            BufferedImage wallTop = ImageIO.read(new File("resources/tiles/wall_top.png"));
            JLabel wallTopLabel = new JLabel(new ImageIcon(wallTop));
            basicTileSet.put('-',wallTopLabel);
            iconTileSet.put('-',new ImageIcon(wallTop));

            //TODO automatyzacja regexp do cholery!





        } catch (IOException e) {
            e.printStackTrace();
        }

    };

    public void test(){


    /*    for(int i = 0; i<2500;i++){
            if(i%2==0){
                add(new JLabel(iconTest2));

            }
            else {
                add(new JLabel(iconTest));
            }
        }
        validate();
        repaint();*/

/*        Component[] labels = getComponents();
        int parentWidth = getWidth();
        int parentHeight = getHeight();
        int col_width = parentWidth/6;
        int row_height = parentHeight/3;*/
    };


    public void populateTileSet() throws NotYetImplementedException {
        throw new NotYetImplementedException();
    }
}
