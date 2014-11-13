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
    DROP ("drop"),                                  // DROP AN ITEM
    DDROP ("ddrop"),                                // DROP A SPECIFIC ITEM, 'D'
    EAT ("eat"),                                    // EAT 'e'
    ENGRAVE("engrave"),                             // ENGRAVE 'e'
    FIRE ("fire"),                                  // fire ammunition from quiver 'f'
    INVENTORY("inv"),                               // show your inventory 'i'
    TYPE_INVENTORY("typeinv"),                      // inventory specific item types 'I'
    OPEN ("open"),                                  // open a door 'o'
    OPTIONS("set"),                                 // show option settings possibly change them
    PAY("pay"),                                     // pay you shopping bill 'p'
    PUT("puton"),                                   // put on an accessory 'P'
    QUAFF("drink"),                                 // drink something 'q'
    QUIVER("wieldquiver"),                          // select ammo for quiver 'Q"
    READ("read"),                                   // read a scroll or a spellbook
    REMOVE_ACCESSORY("remring"),                    // remove an accessory or a ring, etc
    SEARCH ("search"),                              // search for traps and secret doors 's'
    SAVE ("save"),                                  // save the game 'S'
    THROW ("throw"),                                // throw something 't'
    TAKE_OFF ("takeoff"),                           // take off one piece of armour
    VERSION ("simpleversion"),                      // show version 'v'
    VERSION_HISTORY ("history"),                    // show long version and game history
    WIELD ("wield"),                                // wield a weapon 'w'
    WEAR ("wear"),                                  // wear a piece of armour 'W'
    SWAP_WEAPONS ("swap_weapon"),                   // swap wielded and secondary weapons 'z'
    EXPLORE ("enter_explore_mode"),                 // only if defined, enter explore mode 'X'
    ZAP ("zap"),                                    // zap/cast a spell 'z'
    UP ("up"),                                      // go up the staircase '<'
    DOWN ("down"),                                  // do down the staircase '>'
    WHAT_IS ("whatis"),                             // show what type of thing a symbol corresponds '/'
    HELP("help"),                                   // gives help message '?'
    WHAT_DOES("whatdoes"),                          // tell what a command does '&'
    //SHELL ("sh"),                                 // do a shell escape '!"
    SHOW_DISCOVERIES ("discovered"),                // show what type objects have been discovered
    REST ("null"),                                  // rest one move doing nothing '.'
    LOOK_HERE ("look"),                             // look at what is on the floor ':'
    WHAT_MAP ("quickwhatis"),                       // show what type of thing a map symbol on the level corresponds to ';'
    PICK_UP ("pickup"),                             // oick up things at the current location ','
    TOGGLE_PICKUP ("togglepickup"),                 // toggle pickup option on/off '@'
    SHOW_EQUIPMENT ("prinuse"),                     // show all equiment in use '*'
    COUNT_GOLD ("countgold"),                       // count your gold '$'
    KICK ("kick"),                                  // kick ctrl+'D'
    LIST_SPELLS ("listspells"),                     // list known spells '+'
    SHOW_ATTRIBUTES ("attributes"),                 // show your attributes ctrl+'X'

    /// EXTENDED COMMANDS

    PRAY ("pray"),                                  // pray to the gods for help
    ADJUST ("adjust"),                              // adjust inventory letters
    CHAT ("chat"),                                  // talk to someone
    CONDUCT ("conduct"),                            // list challenges you have adhered to
    DIP ("dip"),                                    // dip an object into something
    ENHANCE ("enhance"),                            // advance or check weapons skills
    FORCE ("force"),                                // force a lock
    INVOKE ("invoke"),                              // invoke an object's powers
    JUMP ("jump"),                                  // jump  to the location
    LOOT ("loot"),                                  // loot a box on the floor
    MONSTER ("monster"),                            // use a monster's special ability
    NAME ("name"),                                  // name an item or type of object
    OFFER ("offer"),                                // offer a sacrifice to the gods
    QUIT ("quit"),                                  // exit game without saving
    RIDE ("ride"),                                  // ride/stop riding a monster
    RUB ("rub"),                                    // rub a lamp
    SIT ("sit"),                                    // sit down
    TURN ("turn"),                                  // turn undead
    TWO_WEAPON ("twoweapon"),                       // toggle two-weapon combat
    UNTRAP ("untrap"),                              // untrap something
    //EXTENDED_VERSION ("version"),                 // list compile-time options for this version of nethack
    WIPE ("wipe"),                                  // wipe off your face
    TECHNIQUE ("technique"),                        // perform a technique, slash'em only command
    //redraw screen not implemented
    // display precious message not implemented
    TELEPORT ("teleport"),                          // teleport around level    ctrl + 'T'
    AGAIN ("again"),                                // redo the previous command ctrl + 'A'
    SUSPEND ("suspend"),                            // suspend the game ctrl + 'Z'
    CANCEL_COMMAND(""),                             // cancel the command ctrl + '['
    CALL_MONSTER ("callmon"),                       // coll a particular monster 'C'
    FORCE_FIGHT ("fight"),                          // followed by a direction fight a monster even if you dont't sense it 'F'
    MOVE_UNTIL_NEAR ("movenear"),                   // followed by a direction move until near something
    MOVE("move"),                                   // followed by a dricetion, same as a normal move
    MOVE_NO_PICKUP_FIGHT ("movenopickuporfight"),   // followed by a directioin, move without picking up or fighthin
    MOVE_NO_PIKCUP ("movenopickup"),                // followed by a direction, same as before
    SHOW_WEAPON ("showweapon"),                     // show currently wielded weapon
    SHOW_ARMOUR ("showarmor"),                      // show currently worn armour
    SHOW_RINGS ("showrings"),                       // show currently worn rings
    SHOW_AMULET ("showamulet"),                     // show currently worn amulet
    SHOW_TOOLS ("showtool");                        // show currently used tools
    //lips options
    //OPTIONS ("options"),                          // get all the nethack options


    private String description;
    private Command(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }

}
