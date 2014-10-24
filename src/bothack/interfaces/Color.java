package bothack.interfaces;

/**
 * Created by administrator on 10/22/14.
 */
public enum Color {
    BLACK ("0"),
    RED ("1"),
    GREEN ("2"),
    BROWN ("3"),
    BLUE ("4"),
    MAGENTA ("5"),
    CYAN ("6"),
    GRAY ("7"),
    DARKGRAY ("8"),
    ORANGE ("9"),
    BRIGHTGREEN ("10"),
    YELLOW ("11"),
    BRIGHTBLUE ("12"),
    BRIGHTMAGENTA ("13"),
    BRIGHTCYAN ("14"),
    WHITE ("15");

    private String index;
    private Command(String index){
        this.index = index;
    }
    public String getIndex(){
        return index;
    }

}
