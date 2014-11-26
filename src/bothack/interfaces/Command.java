package bothack.interfaces;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by administrator on 10/22/14.
 */
@XmlRootElement
@XmlType(name="command")
@XmlEnum
public enum Command {
    @XmlEnumValue("gonorth")
    GO_NORTH ("gonorth"),                           //  'k'
    @XmlEnumValue("gonorthontop")
    GO_NORTH_ON_TOP ("gonorthontop"),               //  shift+'k'
    @XmlEnumValue("gonorthnear")
    GO_NORTH_NEAR ("gonorthnear"),                  //  ctrl+shift+'k'
    @XmlEnumValue("gonorthwest")
    GO_NORTHWEST ("gonorthwest"),                   // 'y'
    @XmlEnumValue("gonorthwestontop")
    GO_NORTHWEST_ON_TOP ("gonorthwestontop"),       //  shift+'y'
    @XmlEnumValue("gonorthwestnear")
    GO_NORTHWEST_NEAR ("gonorthwestnear"),          //  ctrl+shift+'y'
    @XmlEnumValue("gowest")
    GO_WEST ("gowest"),                             // 'h'
    @XmlEnumValue("gowestontop")
    GO_WEST_ON_TOP ("gowestontop"),                 // shift+'h'
    @XmlEnumValue("gowestnear")
    GO_WEST_NEAR ("gowestnear"),                    // ctrl+shift+'h'
    @XmlEnumValue("gosouthwest")
    GO_SOUTHWEST ("gosouthwest"),                   // 'b'
    @XmlEnumValue("gosouthwestontop")
    GO_SOUTHWEST_ON_TOP ("gosouthwestontop"),       // shift +'b'
    @XmlEnumValue("gosouthwestnear")
    GO_SOUTHWEST_NEAR ("gosouthwestnear"),          // ctrl+shift+'b'
    @XmlEnumValue("gosouth")
    GO_SOUTH ("gosouth"),                           // 'j'
    @XmlEnumValue("gosouthontop")
    GO_SOUTH_ON_TOP ("gosouthontop"),               // shift +'j'
    @XmlEnumValue("gosouthnear")
    GO_SOUTH_NEAR ("gosouthnear"),                  // ctrl+shift+'j'
    @XmlEnumValue("gosoutheast")
    GO_SOUTHEAST ("gosoutheast"),                   // 'n'
    @XmlEnumValue("gosoutheastontop")
    GO_SOUTHEAST_ON_TOP ("gosoutheastontop"),       // shift+'n'
    @XmlEnumValue("gosoutheastnear")
    GO_SOUTHEAST_NEAR ("gosoutheastnear"),          // ctrl+shift+'n'
    @XmlEnumValue("goeast")
    GO_EAST ("goeast"),                             // 'l'
    @XmlEnumValue("goeastontop")
    GO_EAST_ON_TOP ("goeastontop"),                 // shift+'l'
    @XmlEnumValue("goeastnear")
    GO_EAST_NEAR ("goeastnear"),                    // ctrl+shift+'l'
    @XmlEnumValue("gonortheast")
    GO_NORTHEAST ("gonortheast"),                   // 'u'
    @XmlEnumValue("gonortheastontop")
    GO_NORTHEAST_ON_TOP ("gonortheastontop"),       // shift +'u'
    @XmlEnumValue("gonortheastnear")
    GO_NORTHEAST_NEAR ("gonortheastnear"),          // ctrl +shift +'u'
    @XmlEnumValue("travel")
    TRAVEL ("travel"),                              // move via shortest-path to point on the map
    @XmlEnumValue("idtrap")
    IDTRAP ("idtrap"),                              // show the type of trap
    @XmlEnumValue("apply")
    APPLY ("apply"),                                // use a tool
    @XmlEnumValue("remarm")
    REMARM ("remarm"),                              // REMOVE ALL ARMOUR
    @XmlEnumValue("close")
    CLOSE ("close"),                                // CLOSE A DOOR
    @XmlEnumValue("drop")
    DROP ("drop"),                                  // DROP AN ITEM
    @XmlEnumValue("ddrop")
    DDROP ("ddrop"),                                // DROP A SPECIFIC ITEM, 'D'
    @XmlEnumValue("eat")
    EAT ("eat"),                                    // EAT 'e'
    @XmlEnumValue("engrave")
    ENGRAVE("engrave"),                             // ENGRAVE 'e'
    @XmlEnumValue("fire")
    FIRE ("fire"),                                  // fire ammunition from quiver 'f'
    @XmlEnumValue("inv")
    INVENTORY("inv"),                               // show your inventory 'i'
    @XmlEnum("typeinv")
    TYPE_INVENTORY("typeinv"),                      // inventory specific item types 'I'
    @XmlEnumValue("open")
    OPEN ("open"),                                  // open a door 'o'
    @XmlEnumValue("set")
    OPTIONS("set"),                                 // show option settings possibly change them
    @XmlEnumValue("pay")
    PAY("pay"),                                     // pay you shopping bill 'p'
    @XmlEnumValue("puton")
    PUT("puton"),                                   // put on an accessory 'P'
    @XmlEnumValue("drink")
    QUAFF("drink"),                                 // drink something 'q'
    @XmlEnumValue("wieldquiver")
    QUIVER("wieldquiver"),                          // select ammo for quiver 'Q"
    @XmlEnumValue("read")
    READ("read"),                                   // read a scroll or a spellbook
    @XmlEnumValue("remring")
    REMOVE_ACCESSORY("remring"),                    // remove an accessory or a ring, etc
    @XmlEnumValue("search")
    SEARCH ("search"),                              // search for traps and secret doors 's'
    @XmlEnumValue("save")
    SAVE ("save"),                                  // save the game 'S'
    @XmlEnumValue("throw")
    THROW ("throw"),                                // throw something 't'
    @XmlEnumValue("takeoff")
    TAKE_OFF ("takeoff"),                           // take off one piece of armour
    @XmlEnumValue("simpleversion")
    VERSION ("simpleversion"),                      // show version 'v'
    @XmlEnumValue("history")
    VERSION_HISTORY ("history"),                    // show long version and game history
    @XmlEnumValue("wield")
    WIELD ("wield"),                                // wield a weapon 'w'
    @XmlEnumValue("wear")
    WEAR ("wear"),                                  // wear a piece of armour 'W'
    @XmlEnumValue("swap_weapon")
    SWAP_WEAPONS ("swap_weapon"),                   // swap wielded and secondary weapons 'z'
    @XmlEnumValue("enter_explore_mode")
    EXPLORE ("enter_explore_mode"),                 // only if defined, enter explore mode 'X'
    @XmlEnumValue("zap")
    ZAP ("zap"),                                    // zap/cast a spell 'z'
    @XmlEnumValue("up")
    UP ("up"),                                      // go up the staircase '<'
    @XmlEnumValue("down")
    DOWN ("down"),                                  // do down the staircase '>'
    @XmlEnumValue("whatis")
    WHAT_IS ("whatis"),                             // show what type of thing a symbol corresponds '/'
    @XmlEnumValue("help")
    HELP("help"),                                   // gives help message '?'
    @XmlEnumValue("whatdoes")
    WHAT_DOES("whatdoes"),                          // tell what a command does '&'
    //SHELL ("sh"),                                 // do a shell escape '!"
    @XmlEnumValue("discovered")
    SHOW_DISCOVERIES ("discovered"),                // show what type objects have been discovered
    @XmlEnumValue("null")
    REST ("null"),                                  // rest one move doing nothing '.'
    @XmlEnumValue("look")
    LOOK_HERE ("look"),                             // look at what is on the floor ':'
    @XmlEnumValue("quickwhatis")
    WHAT_MAP ("quickwhatis"),                       // show what type of thing a map symbol on the level corresponds to ';'
    @XmlEnumValue("pickup")
    PICK_UP ("pickup"),                             // oick up things at the current location ','
    @XmlEnumValue("togglepickup")
    TOGGLE_PICKUP ("togglepickup"),                 // toggle pickup option on/off '@'
    @XmlEnumValue("prinuse")
    SHOW_EQUIPMENT ("prinuse"),                     // show all equiment in use '*'
    @XmlEnumValue("countgold")
    COUNT_GOLD ("countgold"),                       // count your gold '$'
    @XmlEnumValue("kick")
    KICK ("kick"),                                  // kick ctrl+'D'
    @XmlEnumValue("listspells")
    LIST_SPELLS ("listspells"),                     // list known spells '+'
    @XmlEnumValue("attributes")
    SHOW_ATTRIBUTES ("attributes"),                 // show your attributes ctrl+'X'

    /// EXTENDED COMMANDS
    @XmlEnumValue("pray")
    PRAY ("pray"),                                  // pray to the gods for help
    @XmlEnumValue("adjust")
    ADJUST ("adjust"),                              // adjust inventory letters
    @XmlEnumValue("chat")
    CHAT ("chat"),                                  // talk to someone
    @XmlEnumValue("conduct")
    CONDUCT ("conduct"),                            // list challenges you have adhered to
    @XmlEnumValue("dip")
    DIP ("dip"),                                    // dip an object into something
    @XmlEnumValue("enhance")
    ENHANCE ("enhance"),                            // advance or check weapons skills
    @XmlEnumValue("force")
    FORCE ("force"),                                // force a lock
    @XmlEnumValue("invoke")
    INVOKE ("invoke"),                              // invoke an object's powers
    @XmlEnumValue("jump")
    JUMP ("jump"),                                  // jump  to the location
    @XmlEnumValue("loot")
    LOOT ("loot"),                                  // loot a box on the floor
    @XmlEnumValue("monster")
    MONSTER ("monster"),                            // use a monster's special ability
    @XmlEnumValue("name")
    NAME ("name"),                                  // name an item or type of object
    @XmlEnumValue("offer")
    OFFER ("offer"),                                // offer a sacrifice to the gods
    @XmlEnumValue("quit")
    QUIT ("quit"),                                  // exit game without saving
    @XmlEnumValue("ride")
    RIDE ("ride"),                                  // ride/stop riding a monster
    @XmlEnumValue("rub")
    RUB ("rub"),                                    // rub a lamp
    @XmlEnumValue("sit")
    SIT ("sit"),                                    // sit down
    @XmlEnumValue("turn")
    TURN ("turn"),                                  // turn undead
    @XmlEnumValue("twoweapon")
    TWO_WEAPON ("twoweapon"),                       // toggle two-weapon combat
    @XmlEnumValue("untrap")
    UNTRAP ("untrap"),                              // untrap something
    //EXTENDED_VERSION ("version"),                 // list compile-time options for this version of nethack
    @XmlEnumValue("wipe")
    WIPE ("wipe"),                                  // wipe off your face
    @XmlEnumValue("technique")
    TECHNIQUE ("technique"),                        // perform a technique, slash'em only command
    //redraw screen not implemented
    // display precious message not implemented
    @XmlEnumValue("teleport")
    TELEPORT ("teleport"),                          // teleport around level    ctrl + 'T'
    @XmlEnumValue("again")
    AGAIN ("again"),                                // redo the previous command ctrl + 'A'
    @XmlEnumValue("suspend")
    SUSPEND ("suspend"),                            // suspend the game ctrl + 'Z'
    @XmlEnumValue("")
    CANCEL_COMMAND(""),                             // cancel the command ctrl + '['
    @XmlEnumValue("callmon")
    CALL_MONSTER ("callmon"),                       // coll a particular monster 'C'
    @XmlEnumValue("fight")
    FORCE_FIGHT ("fight"),                          // followed by a direction fight a monster even if you dont't sense it 'F'
    @XmlEnumValue("movenear")
    MOVE_UNTIL_NEAR ("movenear"),                   // followed by a direction move until near something
    @XmlEnumValue("move")
    MOVE("move"),                                   // followed by a dricetion, same as a normal move
    @XmlEnumValue("movenopickuporfight")
    MOVE_NO_PICKUP_FIGHT ("movenopickuporfight"),   // followed by a directioin, move without picking up or fighthin
    @XmlEnumValue("movenopickup")
    MOVE_NO_PIKCUP ("movenopickup"),                // followed by a direction, same as before
    @XmlEnumValue("showweapon")
    SHOW_WEAPON ("showweapon"),                     // show currently wielded weapon
    @XmlEnumValue("showarmor")
    SHOW_ARMOUR ("showarmor"),                      // show currently worn armour
    @XmlEnumValue("showrings")
    SHOW_RINGS ("showrings"),                       // show currently worn rings
    @XmlEnumValue("showamulet")
    SHOW_AMULET ("showamulet"),                     // show currently worn amulet
    @XmlEnumValue("showtool")
    SHOW_TOOLS ("showtool");                        // show currently used tools
    //lisp options
    //OPTIONS ("options"),                          // get all the nethack options


    private String description;
    private Command(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }

}
