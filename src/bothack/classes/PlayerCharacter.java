package bothack.classes;
/**
 * Created by krito on 10/29/14.
 */
public class PlayerCharacter {

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

    public PlayerCharacter(String alignment, String gender, String race, String role, Integer strength, Integer dexterity, Integer wisdom, Integer condition, Integer charisma, Long gold, Long healthPoints, Long currentHealthPoints, Long power, Long currentPower, Long armourClass, Long experiencePoints, String rank, String dungeon, Integer dungeonLevel, Long time, Boolean confusion, Boolean hunger, Boolean sick, Boolean blind, Boolean stunned, Boolean hallucination, Boolean slimed, Boolean encumbrance) {
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
    }

    public PlayerCharacter() {
        this.alignment = "";
        this.gender = "";
        this.race = "";
        this.role = "";
        this.strength = 0;
        this.dexterity = 0;
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
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerCharacter that = (PlayerCharacter) o;

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
    public int hashCode() {
        int result = alignment != null ? alignment.hashCode() : 0;
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (race != null ? race.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (strength != null ? strength.hashCode() : 0);
        result = 31 * result + (dexterity != null ? dexterity.hashCode() : 0);
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
        result = 31 * result + (dungeon != null ? dungeon.hashCode() : 0);
        result = 31 * result + (dungeonLevel != null ? dungeonLevel.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (confusion != null ? confusion.hashCode() : 0);
        result = 31 * result + (hunger != null ? hunger.hashCode() : 0);
        result = 31 * result + (sick != null ? sick.hashCode() : 0);
        result = 31 * result + (blind != null ? blind.hashCode() : 0);
        result = 31 * result + (stunned != null ? stunned.hashCode() : 0);
        result = 31 * result + (hallucination != null ? hallucination.hashCode() : 0);
        result = 31 * result + (slimed != null ? slimed.hashCode() : 0);
        result = 31 * result + (encumbrance != null ? encumbrance.hashCode() : 0);
        return result;
    }

    private String alignment;
    private String gender;
    private String race;
    private String role;

    private Integer strength;
    private Integer dexterity;
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

    private String dungeon;
    private Integer dungeonLevel;
    private Long time;

    private Boolean confusion;
    private Boolean hunger;
    private Boolean sick;
    private Boolean blind;
    private Boolean stunned;
    private Boolean hallucination;
    private Boolean slimed;
    private Boolean encumbrance;
}
