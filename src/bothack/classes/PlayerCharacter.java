package bothack.classes;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;

/**
 * Created by krito on 10/29/14.
 */
public class PlayerCharacter {

    public PlayerCharacter(String alignment, String gender, String race, String role, Integer strength, Integer intelligence, Integer dexterity,
                           Integer wisdom, Integer condition, Integer charisma, Long gold, Long healthPoints, Long currentHealthPoints,
                           Long power, Long currentPower, Long armourClass, Long experiencePoints, String rank, String name, String dungeon,
                           Integer dungeonLevel, Long time, Boolean confusion, Boolean hunger, Boolean sick, Boolean blind, Boolean stunned,
                           Boolean hallucination, Boolean slimed, Boolean encumbrance, Boolean monster, Boolean HD, Integer level) {
        this.alignment = alignment;
        this.gender = gender;
        this.race = race;
        this.role = role;
        this.strength = strength;
        this.dexterity = dexterity;
        this.wisdom = wisdom;
        this.condition = condition;
        this.charisma = charisma;
        this.gold = gold;
        this.healthPoints = healthPoints;
        this.currentHealthPoints = currentHealthPoints;
        this.power = power;
        this.currentPower = currentPower;
        this.armourClass = armourClass;
        this.experiencePoints = experiencePoints;
        this.rank = rank;
        this.name = name;
        this.dungeon = dungeon;
        this.dungeonLevel = dungeonLevel;
        this.time = time;
        this.confusion = confusion;
        this.hunger = hunger;
        this.sick = sick;
        this.blind = blind;
        this.stunned = stunned;
        this.hallucination = hallucination;
        this.slimed = slimed;
        this.encumbrance = encumbrance;
        this.monster = monster;
        this.HD = HD;
        this.level = level;
        this.intelligence = intelligence;

    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getDexterity() {
        return dexterity;
    }

    public void setDexterity(Integer dexterity) {
        this.dexterity = dexterity;
    }

    public Integer getWisdom() {
        return wisdom;
    }

    public void setWisdom(Integer wisdom) {
        this.wisdom = wisdom;
    }

    public Integer getCondition() {
        return condition;
    }

    public void setCondition(Integer condition) {
        this.condition = condition;
    }

    public Integer getCharisma() {
        return charisma;
    }

    public void setCharisma(Integer charisma) {
        this.charisma = charisma;
    }

    public Long getGold() {
        return gold;
    }

    public void setGold(Long gold) {
        this.gold = gold;
    }

    public Long getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(Long healthPoints) {
        this.healthPoints = healthPoints;
    }

    public Long getCurrentHealthPoints() {
        return currentHealthPoints;
    }

    public void setCurrentHealthPoints(Long currentHealthPoints) {
        this.currentHealthPoints = currentHealthPoints;
    }

    public Long getPower() {
        return power;
    }

    public void setPower(Long power) {
        this.power = power;
    }

    public Long getCurrentPower() {
        return currentPower;
    }

    public void setCurrentPower(Long currentPower) {
        this.currentPower = currentPower;
    }

    public Long getArmourClass() {
        return armourClass;
    }

    public void setArmourClass(Long armourClass) {
        this.armourClass = armourClass;
    }

    public Long getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(Long experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDungeon() {
        return dungeon;
    }

    public void setDungeon(String dungeon) {
        this.dungeon = dungeon;
    }

    public Integer getDungeonLevel() {
        return dungeonLevel;
    }

    public void setDungeonLevel(Integer dungeonLevel) {
        this.dungeonLevel = dungeonLevel;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getConfusion() {
        return confusion;
    }

    public void setConfusion(Boolean confusion) {
        this.confusion = confusion;
    }

    public Boolean getHunger() {
        return hunger;
    }

    public void setHunger(Boolean hunger) {
        this.hunger = hunger;
    }

    public Boolean getSick() {
        return sick;
    }

    public void setSick(Boolean sick) {
        this.sick = sick;
    }

    public Boolean getBlind() {
        return blind;
    }

    public void setBlind(Boolean blind) {
        this.blind = blind;
    }

    public Boolean getStunned() {
        return stunned;
    }

    public void setStunned(Boolean stunned) {
        this.stunned = stunned;
    }

    public Boolean getHallucination() {
        return hallucination;
    }

    public void setHallucination(Boolean hallucination) {
        this.hallucination = hallucination;
    }

    public Boolean getSlimed() {
        return slimed;
    }

    public void setSlimed(Boolean slimed) {
        this.slimed = slimed;
    }

    public Boolean getEncumbrance() {
        return encumbrance;
    }

    public void setEncumbrance(Boolean encumbrance) {
        this.encumbrance = encumbrance;
    }

    public Boolean getMonster() {
        return monster;
    }

    public void setMonster(Boolean monster) {
        this.monster = monster;
    }

    public Boolean getHD() {
        return HD;
    }

    public void setHD(Boolean HD) {
        this.HD = HD;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    public PlayerCharacter() {
        this.name = "";
        this.alignment = "";
        this.gender = "";
        this.race = "";
        this.role = "";
        this.strength = 0;
        this.dexterity = 0;
        this.intelligence = 0;
        this.wisdom = 0;
        this.condition = 0;
        this.charisma = 0;
        this.gold = new Long(0);
        this.healthPoints = new Long(0);
        this.currentHealthPoints = new Long(0);
        this.power = new Long(0);
        this.currentPower = new Long(0);
        this.armourClass = new Long(0);
        this.experiencePoints = new Long(0);
        this.rank = "";
        this.dungeon = "";
        this.dungeonLevel = new Integer(0);
        this.time = new Long(0);
        this.confusion = false;
        this.hunger = false;
        this.sick = false;
        this.blind = false;
        this.stunned = false;
        this.hallucination = false;
        this.slimed = false;
        this.encumbrance = false;
        this.monster = false;
        this.HD =  false;
        this.level = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerCharacter that = (PlayerCharacter) o;

        if (HD != null ? !HD.equals(that.HD) : that.HD != null) return false;
        if (alignment != null ? !alignment.equals(that.alignment) : that.alignment != null) return false;
        if (armourClass != null ? !armourClass.equals(that.armourClass) : that.armourClass != null) return false;
        if (blind != null ? !blind.equals(that.blind) : that.blind != null) return false;
        if (charisma != null ? !charisma.equals(that.charisma) : that.charisma != null) return false;
        if (condition != null ? !condition.equals(that.condition) : that.condition != null) return false;
        if (confusion != null ? !confusion.equals(that.confusion) : that.confusion != null) return false;
        if (currentHealthPoints != null ? !currentHealthPoints.equals(that.currentHealthPoints) : that.currentHealthPoints != null)
            return false;
        if (currentPower != null ? !currentPower.equals(that.currentPower) : that.currentPower != null) return false;
        if (dexterity != null ? !dexterity.equals(that.dexterity) : that.dexterity != null) return false;
        if (dungeon != null ? !dungeon.equals(that.dungeon) : that.dungeon != null) return false;
        if (dungeonLevel != null ? !dungeonLevel.equals(that.dungeonLevel) : that.dungeonLevel != null) return false;
        if (encumbrance != null ? !encumbrance.equals(that.encumbrance) : that.encumbrance != null) return false;
        if (experiencePoints != null ? !experiencePoints.equals(that.experiencePoints) : that.experiencePoints != null)
            return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (gold != null ? !gold.equals(that.gold) : that.gold != null) return false;
        if (hallucination != null ? !hallucination.equals(that.hallucination) : that.hallucination != null)
            return false;
        if (healthPoints != null ? !healthPoints.equals(that.healthPoints) : that.healthPoints != null) return false;
        if (hunger != null ? !hunger.equals(that.hunger) : that.hunger != null) return false;
        if (intelligence != null ? !intelligence.equals(that.intelligence) : that.intelligence != null) return false;
        if (level != null ? !level.equals(that.level) : that.level != null) return false;
        if (monster != null ? !monster.equals(that.monster) : that.monster != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (power != null ? !power.equals(that.power) : that.power != null) return false;
        if (race != null ? !race.equals(that.race) : that.race != null) return false;
        if (rank != null ? !rank.equals(that.rank) : that.rank != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        if (sick != null ? !sick.equals(that.sick) : that.sick != null) return false;
        if (slimed != null ? !slimed.equals(that.slimed) : that.slimed != null) return false;
        if (strength != null ? !strength.equals(that.strength) : that.strength != null) return false;
        if (stunned != null ? !stunned.equals(that.stunned) : that.stunned != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (wisdom != null ? !wisdom.equals(that.wisdom) : that.wisdom != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "PlayerCharacter{" +
                "alignment='" + alignment + '\'' +
                ", gender='" + gender + '\'' +
                ", race='" + race + '\'' +
                ", role='" + role + '\'' +
                ", strength=" + strength +
                ", dexterity=" + dexterity +
                ", intelligence=" + intelligence +
                ", wisdom=" + wisdom +
                ", condition=" + condition +
                ", charisma=" + charisma +
                ", gold=" + gold +
                ", healthPoints=" + healthPoints +
                ", currentHealthPoints=" + currentHealthPoints +
                ", power=" + power +
                ", currentPower=" + currentPower +
                ", armourClass=" + armourClass +
                ", experiencePoints=" + experiencePoints +
                ", rank='" + rank + '\'' +
                ", name='" + name + '\'' +
                ", dungeon='" + dungeon + '\'' +
                ", dungeonLevel=" + dungeonLevel +
                ", time=" + time +
                ", level=" + level +
                ", confusion=" + confusion +
                ", hunger=" + hunger +
                ", sick=" + sick +
                ", blind=" + blind +
                ", stunned=" + stunned +
                ", hallucination=" + hallucination +
                ", slimed=" + slimed +
                ", encumbrance=" + encumbrance +
                ", monster=" + monster +
                ", HD=" + HD +
                '}';
    }

    @Override
    public int hashCode() {
        int result = alignment != null ? alignment.hashCode() : 0;
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (race != null ? race.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (strength != null ? strength.hashCode() : 0);
        result = 31 * result + (dexterity != null ? dexterity.hashCode() : 0);
        result = 31 * result + (intelligence != null ? intelligence.hashCode() : 0);
        result = 31 * result + (wisdom != null ? wisdom.hashCode() : 0);
        result = 31 * result + (condition != null ? condition.hashCode() : 0);
        result = 31 * result + (charisma != null ? charisma.hashCode() : 0);
        result = 31 * result + (gold != null ? gold.hashCode() : 0);
        result = 31 * result + (healthPoints != null ? healthPoints.hashCode() : 0);
        result = 31 * result + (currentHealthPoints != null ? currentHealthPoints.hashCode() : 0);
        result = 31 * result + (power != null ? power.hashCode() : 0);
        result = 31 * result + (currentPower != null ? currentPower.hashCode() : 0);
        result = 31 * result + (armourClass != null ? armourClass.hashCode() : 0);
        result = 31 * result + (experiencePoints != null ? experiencePoints.hashCode() : 0);
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (dungeon != null ? dungeon.hashCode() : 0);
        result = 31 * result + (dungeonLevel != null ? dungeonLevel.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (confusion != null ? confusion.hashCode() : 0);
        result = 31 * result + (hunger != null ? hunger.hashCode() : 0);
        result = 31 * result + (sick != null ? sick.hashCode() : 0);
        result = 31 * result + (blind != null ? blind.hashCode() : 0);
        result = 31 * result + (stunned != null ? stunned.hashCode() : 0);
        result = 31 * result + (hallucination != null ? hallucination.hashCode() : 0);
        result = 31 * result + (slimed != null ? slimed.hashCode() : 0);
        result = 31 * result + (encumbrance != null ? encumbrance.hashCode() : 0);
        result = 31 * result + (monster != null ? monster.hashCode() : 0);
        result = 31 * result + (HD != null ? HD.hashCode() : 0);
        return result;
    }

    private String alignment;
    private String gender;
    private String race;
    private String role;

    private Integer strength;
    private Integer dexterity;
    private Integer intelligence;
    private Integer wisdom;
    private Integer condition;
    private Integer charisma;

    private Long gold;
    private Long healthPoints;
    private Long currentHealthPoints;
    private Long power;
    private Long currentPower;
    private Long armourClass;
    private Long experiencePoints;
    private String rank;
    private String name;

    private String dungeon;
    private Integer dungeonLevel;
    private Long time;
    private Integer level;

    private Boolean confusion;
    private Boolean hunger;
    private Boolean sick;
    private Boolean blind;
    private Boolean stunned;
    private Boolean hallucination;
    private Boolean slimed;
    private Boolean encumbrance;
    private Boolean monster;
    private Boolean HD;                             // I honestsly don't know what this stands for

    public void updateStatus(String name,String rank, Boolean monster,Integer strength, Integer dexterity,Integer wisdom,
                             Integer condition,Integer charisma,String alignment, String dungeon, Integer dungeonLevel,
                             Long gold, Long healthPoints, Long currentHealthPoints, Long power, Long currentPower,
                             Long armourClass, Boolean HD, Integer level, Long experiencePoints, Long time, Boolean confusion,
                             Boolean hunger, Boolean sick, Boolean blind, Boolean stunned, Boolean hallucination, Boolean slimed,
                             Boolean encumbrance, Integer intelligence){
        this.alignment = alignment;
        this.strength = strength;
        this.dexterity = dexterity;
        this.wisdom = wisdom;
        this.condition = condition;
        this.charisma = charisma;
        this.gold = gold;
        this.healthPoints = healthPoints;
        this.currentHealthPoints = currentHealthPoints;
        this.power = power;
        this.currentPower = currentPower;
        this.armourClass = armourClass;
        this.experiencePoints = experiencePoints;
        this.rank = rank;
        this.name = name;
        this.dungeon = dungeon;
        this.dungeonLevel = dungeonLevel;
        this.time = time;
        this.confusion = confusion;
        this.hunger = hunger;
        this.sick = sick;
        this.blind = blind;
        this.stunned = stunned;
        this.hallucination = hallucination;
        this.slimed = slimed;
        this.encumbrance = encumbrance;
        this.monster = monster;
        this.HD = HD;
        this.level = level;
        this.intelligence = intelligence;
    };
    public void updateStatus(String output) throws PlayerUpdateStatusException {
        boolean state1 = false;
        boolean state2 = false;
        boolean state3 = false;
        String[] partialResult;
        String tmp = new String("");
        ArrayList<String> partialStatus = new ArrayList<String>();
        for(int i =0 ; i< output.length(); i++) {
            //this is a crude implementation of a finite state machine
            //if there have been three left parenthesis then a character belongs to a new attribute and is added to the temp string
            //if a closing parens is found then a new attribute is added to the array list
            if (output.charAt(i) == '(' && state1 == true && state2 == true && state3 == false) {
                state3 = true;
                tmp = "";
            }
            else if (output.charAt(i) == '(' && state1 == true && state2 == false && state3 == false)
                state2 = true;
            else if (output.charAt(i) == '(' && state1 == false && state2 == false && state3 == false)
                state1 = true;

            else if (state1 == true && state2 == false && state3 == false && output.charAt(i) == ')') {
                state1 = false;
            } else if (state1 == true && state2 == true && state3 == false && output.charAt(i) == ')')
                state2 = false;
            else if (state1 == true && state2 == true && state3 == true && output.charAt(i) == ')') {
                state3 = false;
                partialStatus.add(tmp);
            } else if (state1 == true && state2 == true && state3 == true) {
                tmp += output.charAt(i);
            } else {
               // throw new PlayerUpdateStatusException();
            }
        }
            //this is a crude way to parse the resulting Array list and add the neccessary attributes to the class
            for(String attr : partialStatus){
                if(attr.contains("name")){
                    partialResult = attr.split(" ");
                    this.name = partialResult[1];
                }
                else if(attr.contains("rank")){
                    partialResult = attr.split(" ");
                    this.rank = partialResult[1];
                }
                else if(attr.contains("monster")) {
                    partialResult = attr.split(" ");
                    if (partialResult[1].contains("nil"))
                        this.monster = false;
                    else {
                        this.monster = true;
                    }
                }
                else if(attr.contains("Align")){
                    partialResult = attr.split(" ");
                    this.alignment = partialResult[1];
                }
                else if(attr.contains("Dungeon")){
                    partialResult = attr.split(" ");
                    this.dungeon = partialResult[1];
                }
                else if(attr.contains("Dlvl")){
                    partialResult = attr.split(" ");
                    this.dungeonLevel = Integer.parseInt(partialResult[1]);
                }
                else if(attr.contains("$")){
                    partialResult = attr.split(" ");
                    this.gold = Long.parseLong(partialResult[1]);
                }
                else if(attr.contains("confusion")){
                    partialResult = attr.split(" ");
                    if (partialResult[1].contains("nil"))
                        this.confusion = false;
                    else {
                        this.confusion = true;
                    }
                }
                else if(attr.contains("hunger")){
                    partialResult = attr.split(" ");
                    if (partialResult[1].contains("nil"))
                        this.hunger = false;
                    else {
                        this.hunger = true;
                    }
                }
                else if(attr.contains("sick")){
                    partialResult = attr.split(" ");
                    if (partialResult[1].contains("nil"))
                        this.sick = false;
                    else {
                        this.sick = true;
                    }
                }
                else if(attr.contains("blind")){
                    partialResult = attr.split(" ");
                    if (partialResult[1].contains("nil"))
                        this.blind = false;
                    else {
                        this.blind = true;
                    }
                }
                else if(attr.contains("stunned")){
                    partialResult = attr.split(" ");
                    if (partialResult[1].contains("nil"))
                        this.stunned = false;
                    else {
                        this.stunned = true;
                    }
                }
                else if(attr.contains("hallucination")){
                    partialResult = attr.split(" ");
                    if (partialResult[1].contains("nil"))
                        this.hallucination = false;
                    else {
                        this.hallucination = true;
                    }
                }
                else if(attr.contains("slimed")){
                    partialResult = attr.split(" ");
                    if (partialResult[1].contains("nil"))
                        this.slimed = false;
                    else {
                        this.slimed = true;
                    }
                }
                else if(attr.contains("encumbrance")){
                    partialResult = attr.split(" ");
                    if (partialResult[1].contains("nil"))
                        this.encumbrance = false;
                    else {
                        this.encumbrance = true;
                    }
                }
                else if(attr.contains("St")){
                    partialResult = attr.split(" ");
                    this.strength = Integer.parseInt(partialResult[1]);
                }
                else if(attr.contains("Dx")){
                    partialResult = attr.split(" ");
                    this.dexterity = Integer.parseInt(partialResult[1]);
                }
                else if(attr.contains("Co")){
                    partialResult = attr.split(" ");
                    this.condition = Integer.parseInt(partialResult[1]);
                }
                else if(attr.contains("In")){
                    partialResult = attr.split(" ");
                    this.intelligence = Integer.parseInt(partialResult[1]);
                }
                else if(attr.contains("Wi")){
                    partialResult = attr.split(" ");
                    this.wisdom = Integer.parseInt(partialResult[1]);
                }
                else if(attr.contains("Ch")){
                    partialResult = attr.split(" ");
                    this.charisma = Integer.parseInt(partialResult[1]);
                }
                else if(attr.contains("HPmax")){
                    partialResult = attr.split(" ");
                    this.healthPoints = Long.parseLong(partialResult[1]);
                }
                else if(attr.contains("HP")){
                    partialResult = attr.split(" ");
                    this.currentHealthPoints = Long.parseLong(partialResult[1]);
                }
                else if(attr.contains("PWmax")){
                    partialResult = attr.split(" ");
                    this.power = Long.parseLong(partialResult[1]);
                }
                else if(attr.contains("PW")){
                    partialResult = attr.split(" ");
                    this.currentPower = Long.parseLong(partialResult[1]);
                }
                else if(attr.contains("AC")){
                    partialResult = attr.split(" ");
                    this.armourClass = Long.parseLong(partialResult[1]);
                }
                else if(attr.contains("HD")){
                    partialResult = attr.split(" ");
                    if (partialResult[1].contains("nil"))
                        this.HD = false;
                    else {
                        this.HD = true;
                    }
                }
                else if(attr.contains("Level")){
                    partialResult = attr.split(" ");
                    this.level = Integer.parseInt(partialResult[1]);
                }
                else if(attr.contains("XP")){
                    partialResult = attr.split(" ");
                    this.experiencePoints = Long.parseLong(partialResult[1]);
                }
                else if(attr.contains("T")){
                    partialResult = attr.split(" ");
                    this.time = Long.parseLong(partialResult[1]);
                }
            }
    }
    public void updateAttributes(){
        //TODO needs to be implemented
    };

}