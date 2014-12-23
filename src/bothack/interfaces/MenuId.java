package bothack.interfaces;

/**
 * Created by administrator on 12/21/14.
 */
public enum MenuId {
    StartMenuId(18),
    SelectMenuId(19),
    CreateMenuWindowId(26),
    DisplayMenuId(20),
    CreateTextWindowId(26);
    private final int id;
    private MenuId(int  id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
}
