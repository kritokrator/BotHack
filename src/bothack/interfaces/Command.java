package bothack.interfaces;

/**
 * Created by administrator on 10/22/14.
 */

public enum Command {
    GO_NORTH ("gonorth"),                           //  'k'
    GO_NORTH_ON_TOP ("gonorthontop"),               //  shift+'k'
    GO_NORTH_NEAR ("gonorthnear"),                  //  ctrl+shift+'k'
    GO_NORTHWEST ("gonorthwest"),                   // 'y'
    GO_NORTHWEST_ON_TOP ("gonorthwestontop"),       //  shift+'y'
    GO_NORTHWEST_NEAR ("gonorthwestnear"),          //  ctrl+shift+'y'
    GO_WEST ("gowest"),                             // 'h'
    GO_WEST_ON_TOP ("gowestontop"),                 // shift+'h'
    GO_WEST_NEAR ("gowestnear"),                    // ctrl+shift+'h'
    GO_SOUTHWEST ("gosouthwest"),                   // 'b'
    GO_SOUTHWEST_ON_TOP ("gosouthwestontop"),       // shift +'b'
    GO_SOUTHWEST_NEAR ("gosouthwestnear"),          // ctrl+shift+'b'
    GO_SOUTH ("gosouth"),                           // 'j'
    GO_SOUTH_ON_TOP ("gosouthontop"),               // shift +'j'
    GO_SOUTH_NEAR ("gosouthnear"),                  // ctrl+shift+'j'
    GO_SOUTHEAST ("gosoutheast"),                   // 'n'
    GO_SOUTHEAST_ON_TOP ("gosoutheastontop"),       // shift+'n'
    GO_SOUTHEAST_NEAR ("gosoutheastnear"),          // ctrl+shift+'n'
    GO_EAST ("goeast"),                             // 'l'
    GO_EAST_ON_TOP ("goeastontop"),                 // shift+'l'
    GO_EAST_NEAR ("goeastnear"),                    // ctrl+shift+'l'
    GO_NORHTEAST ("gonortheast"),                   // 'u'
    GO_NORTHEAST_ON_TOP ("gonortheastontop"),       // shift +'u'
    GO_NORTHEAST_NEAR ("gonortheastnear"),          // ctrl +shift +'u'
    TRAVEL ("travel"),                              // move via shortest-path to point on the map
    IDTRAP ("idtrap"),                              // show the type of trap
    APPLY ("apply"),                                // use a tool
    REMARM ("remarm"),                              // REMOVE ALL ARMOUR
    CLOSE ("close"),                                // CLOSE A DOOR
    DROP ("drop");                                  // DROP AN ITEM

    //TODO retype all the nethack-lisp commands

    private String description;
    private Command(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }

}
