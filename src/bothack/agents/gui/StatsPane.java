package bothack.agents.gui;

import bothack.classes.PlayerCharacter;

import javax.swing.*;
import java.awt.*;

/**
 * Created by administrator on 12/9/14.
 */
public class StatsPane extends JPanel {
    private JLabel alignmentLabel = new JLabel();
    private JLabel genderLabel= new JLabel();
    private JLabel raceLabel= new JLabel();
    private JLabel roleLabel= new JLabel();

    private JLabel strengthLabel= new JLabel();
    private JLabel dexterityLabel= new JLabel();
    private JLabel intelligenceLabel= new JLabel();
    private JLabel wisdomLabel= new JLabel();
    private JLabel conditionLabel= new JLabel();
    private JLabel charismaLabel= new JLabel();

    private JLabel goldLabel= new JLabel();
    private JLabel healthPointsLabel= new JLabel();
    private JLabel currentHealthPointsLabel= new JLabel();
    private JLabel powerLabel= new JLabel();
    private JLabel currentPowerLabel= new JLabel();
    private JLabel armourClassLabel= new JLabel();
    private JLabel experiencePointsLabel= new JLabel();
    private JLabel rankLabel= new JLabel();
    private JLabel nameLabel= new JLabel();

    private JLabel dungeonLabel= new JLabel();
    private JLabel dungeonLevelLabel= new JLabel();
    private JLabel timeLabel= new JLabel();
    private JLabel levelLabel= new JLabel();

    private JLabel confusionLabel= new JLabel();
    private JLabel hungerLabel= new JLabel();
    private JLabel sickLabel= new JLabel();
    private JLabel blindLabel= new JLabel();
    private JLabel stunnedLabel= new JLabel();
    private JLabel hallucinationLabel= new JLabel();
    private JLabel slimedLabel= new JLabel();
    private JLabel encumbranceLabel= new JLabel();
    private JLabel monsterLabel= new JLabel();
    private JLabel HDLabel= new JLabel();                             // I honestsly don't know what this stands for

    private JLabel alignmentLabelValue= new JLabel();
    private JLabel genderLabelValue= new JLabel();
    private JLabel raceLabelValue= new JLabel();
    private JLabel roleLabelValue= new JLabel();

    private JLabel strengthLabelValue= new JLabel();
    private JLabel dexterityLabelValue= new JLabel();
    private JLabel intelligenceLabelValue= new JLabel();
    private JLabel wisdomLabelValue= new JLabel();
    private JLabel conditionLabelValue= new JLabel();
    private JLabel charismaLabelValue= new JLabel();

    private JLabel goldLabelValue= new JLabel();
    private JLabel healthPointsLabelValue= new JLabel();
    private JLabel currentHealthPointsLabelValue= new JLabel();
    private JLabel powerLabelValue= new JLabel();
    private JLabel currentPowerLabelValue= new JLabel();
    private JLabel armourClassLabelValue= new JLabel();
    private JLabel experiencePointsLabelValue= new JLabel();
    private JLabel rankLabelValue= new JLabel();
    private JLabel nameLabelValue= new JLabel();

    private JLabel dungeonLabelValue= new JLabel();
    private JLabel dungeonLevelLabelValue= new JLabel();
    private JLabel timeLabelValue= new JLabel();
    private JLabel levelLabelValue= new JLabel();

    private JLabel confusionLabelValue= new JLabel();
    private JLabel hungerLabelValue= new JLabel();
    private JLabel sickLabelValue= new JLabel();
    private JLabel blindLabelValue= new JLabel();
    private JLabel stunnedLabelValue= new JLabel();
    private JLabel hallucinationLabelValue= new JLabel();
    private JLabel slimedLabelValue= new JLabel();
    private JLabel encumbranceLabelValue= new JLabel();
    private JLabel monsterLabelValue= new JLabel();
    private JLabel HDLabelValue= new JLabel();                             // I honestsly don't know what this stands for


    public StatsPane() {
        alignmentLabel.setText("alignment: ");
        genderLabel.setText("gender: ");
        raceLabel.setText("race: ");
        roleLabel.setText("role: ");

        strengthLabel.setText("Strength: ");
        dexterityLabel.setText("Dexterity: ");
        intelligenceLabel.setText("Intelligence: ");
        wisdomLabel.setText("Wisdom: ");
        conditionLabel.setText("Condition: ");
        charismaLabel.setText("Charisma: ");

        goldLabel.setText("Gold: ");
        healthPointsLabel.setText("HP_MAX: ");
        currentHealthPointsLabel.setText("HP_Current: ");
        powerLabel.setText("PW_MAX: ");
        currentPowerLabel.setText("PW_Current: ");
        armourClassLabel.setText("AC: ");
        experiencePointsLabel.setText("EXP: ");
        rankLabel.setText("Rank: ");
        nameLabel.setText("Name: ");

        dungeonLabel.setText("Dungeon: ");
        dungeonLevelLabel.setText("Dungeon level: ");
        timeLabel.setText("Time: ");
        levelLabel.setText("Level: ");

        confusionLabel.setText("Confused: ");
        hungerLabel.setText("Hungry: ");
        sickLabel.setText("Sick: ");
        blindLabel.setText("Blind: ");
        stunnedLabel.setText("Stunned: ");
        hallucinationLabel.setText("Hallucinating: ");
        slimedLabel.setText("Slimed: ");
        encumbranceLabel.setText("Encumrance: ");
        monsterLabel.setText("Monster_status: ");
        HDLabel.setText("HD: ");                             // I honestsly don't know what this stands for


        setLayout(new GridLayout(33, 2));
        add(nameLabel);
        add(nameLabelValue);
        add(strengthLabel);
        add(strengthLabelValue);
        add(dexterityLabel);
        add(dexterityLabelValue);
        add(intelligenceLabel);
        add(intelligenceLabelValue);
        add(wisdomLabel);
        add(wisdomLabelValue);
        add(conditionLabel);
        add(conditionLabelValue);
        add(charismaLabel);
        add(charismaLabelValue);
        add(alignmentLabel);
        add(alignmentLabelValue);
        add(genderLabel);
        add(genderLabelValue);
        add(raceLabel);
        add(raceLabelValue);
        add(roleLabel);
        add(roleLabelValue);
        add(goldLabel);
        add(goldLabelValue);
        add(healthPointsLabel);
        add(healthPointsLabelValue);
        add(currentHealthPointsLabel);
        add(currentHealthPointsLabelValue);
        add(powerLabel);
        add(powerLabelValue);
        add(armourClassLabel);
        add(armourClassLabelValue);
        add(experiencePointsLabel);
        add(experiencePointsLabelValue);
        add(rankLabel);
        add(rankLabelValue);
        add(dungeonLabel);
        add(dungeonLabelValue);
        add(dungeonLevelLabel);
        add(dungeonLevelLabelValue);
        add(timeLabel);
        add(timeLabelValue);
        add(levelLabel);
        add(levelLabelValue);
        add(confusionLabel);
        add(confusionLabelValue);
        add(hungerLabel);
        add(hungerLabelValue);
        add(sickLabel);
        add(sickLabelValue);
        add(blindLabel);
        add(blindLabelValue);
        add(stunnedLabel);
        add(stunnedLabelValue);
        add(hallucinationLabel);
        add(hallucinationLabelValue);
        add(slimedLabel);
        add(slimedLabelValue);
        add(encumbranceLabel);
        add(encumbranceLabelValue);
        add(monsterLabel);
        add(monsterLabelValue);
        add(HDLabel);
        add(HDLabelValue);


    }

    public JLabel getAlignmentLabelValue() {
        return alignmentLabelValue;
    }

    public void setAlignmentLabelValue(JLabel alignmentLabelValue) {
        this.alignmentLabelValue = alignmentLabelValue;
    }

    public JLabel getGenderLabelValue() {
        return genderLabelValue;
    }

    public void setGenderLabelValue(JLabel genderLabelValue) {
        this.genderLabelValue = genderLabelValue;
    }

    public JLabel getRaceLabelValue() {
        return raceLabelValue;
    }

    public void setRaceLabelValue(JLabel raceLabelValue) {
        this.raceLabelValue = raceLabelValue;
    }

    public JLabel getRoleLabelValue() {
        return roleLabelValue;
    }

    public void setRoleLabelValue(JLabel roleLabelValue) {
        this.roleLabelValue = roleLabelValue;
    }

    public JLabel getStrengthLabelValue() {
        return strengthLabelValue;
    }

    public void setStrengthLabelValue(JLabel strengthLabelValue) {
        this.strengthLabelValue = strengthLabelValue;
    }

    public JLabel getDexterityLabelValue() {
        return dexterityLabelValue;
    }

    public void setDexterityLabelValue(JLabel dexterityLabelValue) {
        this.dexterityLabelValue = dexterityLabelValue;
    }

    public JLabel getIntelligenceLabelValue() {
        return intelligenceLabelValue;
    }

    public void setIntelligenceLabelValue(JLabel intelligenceLabelValue) {
        this.intelligenceLabelValue = intelligenceLabelValue;
    }

    public JLabel getWisdomLabelValue() {
        return wisdomLabelValue;
    }

    public void setWisdomLabelValue(JLabel wisdomLabelValue) {
        this.wisdomLabelValue = wisdomLabelValue;
    }

    public JLabel getConditionLabelValue() {
        return conditionLabelValue;
    }

    public void setConditionLabelValue(JLabel conditionLabelValue) {
        this.conditionLabelValue = conditionLabelValue;
    }

    public JLabel getCharismaLabelValue() {
        return charismaLabelValue;
    }

    public void setCharismaLabelValue(JLabel charismaLabelValue) {
        this.charismaLabelValue = charismaLabelValue;
    }

    public JLabel getGoldLabelValue() {
        return goldLabelValue;
    }

    public void setGoldLabelValue(JLabel goldLabelValue) {
        this.goldLabelValue = goldLabelValue;
    }

    public JLabel getHealthPointsLabelValue() {
        return healthPointsLabelValue;
    }

    public void setHealthPointsLabelValue(JLabel healthPointsLabelValue) {
        this.healthPointsLabelValue = healthPointsLabelValue;
    }

    public JLabel getCurrentHealthPointsLabelValue() {
        return currentHealthPointsLabelValue;
    }

    public void setCurrentHealthPointsLabelValue(JLabel currentHealthPointsLabelValue) {
        this.currentHealthPointsLabelValue = currentHealthPointsLabelValue;
    }

    public JLabel getPowerLabelValue() {
        return powerLabelValue;
    }

    public void setPowerLabelValue(JLabel powerLabelValue) {
        this.powerLabelValue = powerLabelValue;
    }

    public JLabel getCurrentPowerLabelValue() {
        return currentPowerLabelValue;
    }

    public void setCurrentPowerLabelValue(JLabel currentPowerLabelValue) {
        this.currentPowerLabelValue = currentPowerLabelValue;
    }

    public JLabel getArmourClassLabelValue() {
        return armourClassLabelValue;
    }

    public void setArmourClassLabelValue(JLabel armourClassLabelValue) {
        this.armourClassLabelValue = armourClassLabelValue;
    }

    public JLabel getExperiencePointsLabelValue() {
        return experiencePointsLabelValue;
    }

    public void setExperiencePointsLabelValue(JLabel experiencePointsLabelValue) {
        this.experiencePointsLabelValue = experiencePointsLabelValue;
    }

    public JLabel getRankLabelValue() {
        return rankLabelValue;
    }

    public void setRankLabelValue(JLabel rankLabelValue) {
        this.rankLabelValue = rankLabelValue;
    }

    public JLabel getNameLabelValue() {
        return nameLabelValue;
    }

    public void setNameLabelValue(JLabel nameLabelValue) {
        this.nameLabelValue = nameLabelValue;
    }

    public JLabel getDungeonLabelValue() {
        return dungeonLabelValue;
    }

    public void setDungeonLabelValue(JLabel dungeonLabelValue) {
        this.dungeonLabelValue = dungeonLabelValue;
    }

    public JLabel getDungeonLevelLabelValue() {
        return dungeonLevelLabelValue;
    }

    public void setDungeonLevelLabelValue(JLabel dungeonLevelLabelValue) {
        this.dungeonLevelLabelValue = dungeonLevelLabelValue;
    }

    public JLabel getTimeLabelValue() {
        return timeLabelValue;
    }

    public void setTimeLabelValue(JLabel timeLabelValue) {
        this.timeLabelValue = timeLabelValue;
    }

    public JLabel getLevelLabelValue() {
        return levelLabelValue;
    }

    public void setLevelLabelValue(JLabel levelLabelValue) {
        this.levelLabelValue = levelLabelValue;
    }

    public JLabel getConfusionLabelValue() {
        return confusionLabelValue;
    }

    public void setConfusionLabelValue(JLabel confusionLabelValue) {
        this.confusionLabelValue = confusionLabelValue;
    }

    public JLabel getHungerLabelValue() {
        return hungerLabelValue;
    }

    public void setHungerLabelValue(JLabel hungerLabelValue) {
        this.hungerLabelValue = hungerLabelValue;
    }

    public JLabel getSickLabelValue() {
        return sickLabelValue;
    }

    public void setSickLabelValue(JLabel sickLabelValue) {
        this.sickLabelValue = sickLabelValue;
    }

    public JLabel getBlindLabelValue() {
        return blindLabelValue;
    }

    public void setBlindLabelValue(JLabel blindLabelValue) {
        this.blindLabelValue = blindLabelValue;
    }

    public JLabel getStunnedLabelValue() {
        return stunnedLabelValue;
    }

    public void setStunnedLabelValue(JLabel stunnedLabelValue) {
        this.stunnedLabelValue = stunnedLabelValue;
    }

    public JLabel getHallucinationLabelValue() {
        return hallucinationLabelValue;
    }

    public void setHallucinationLabelValue(JLabel hallucinationLabelValue) {
        this.hallucinationLabelValue = hallucinationLabelValue;
    }

    public JLabel getSlimedLabelValue() {
        return slimedLabelValue;
    }

    public void setSlimedLabelValue(JLabel slimedLabelValue) {
        this.slimedLabelValue = slimedLabelValue;
    }

    public JLabel getEncumbranceLabelValue() {
        return encumbranceLabelValue;
    }

    public void setEncumbranceLabelValue(JLabel encumbranceLabelValue) {
        this.encumbranceLabelValue = encumbranceLabelValue;
    }

    public JLabel getMonsterLabelValue() {
        return monsterLabelValue;
    }

    public void setMonsterLabelValue(JLabel monsterLabelValue) {
        this.monsterLabelValue = monsterLabelValue;
    }

    public JLabel getHDLabelValue() {
        return HDLabelValue;
    }

    public void setHDLabelValue(JLabel HDLabelValue) {
        this.HDLabelValue = HDLabelValue;
    }


    public void updatePane(PlayerCharacter pc){
        alignmentLabelValue.setText(pc.getAlignment());
        genderLabelValue.setText(pc.getGender());
        raceLabelValue.setText(pc.getRace());
        roleLabelValue.setText(pc.getRole());
        strengthLabelValue.setText(pc.getStrength().toString());
        dexterityLabelValue.setText(pc.getDexterity().toString());
        intelligenceLabelValue.setText(pc.getIntelligence().toString());
        wisdomLabelValue.setText(pc.getWisdom().toString());
        conditionLabelValue.setText(pc.getCondition().toString());
        charismaLabelValue.setText(pc.getCharisma().toString());
        goldLabelValue.setText(pc.getGold().toString());
        healthPointsLabelValue.setText(pc.getHealthPoints().toString());
        currentHealthPointsLabelValue.setText(pc.getCurrentHealthPoints().toString());
        powerLabelValue.setText(pc.getPower().toString());
        currentPowerLabelValue.setText(pc.getCurrentPower().toString());
        armourClassLabelValue.setText(pc.getArmourClass().toString());
        experiencePointsLabelValue.setText(pc.getExperiencePoints().toString());
        rankLabelValue.setText(pc.getRank());
        nameLabelValue.setText(pc.getName());
        dungeonLabelValue.setText(pc.getDungeon());
        dungeonLevelLabelValue.setText(pc.getDungeonLevel().toString());
        timeLabelValue.setText(pc.getTime().toString());
        levelLabelValue.setText(pc.getLevel().toString());
        confusionLabelValue.setText(pc.getConfusion().toString());
        hungerLabelValue.setText(pc.getHunger().toString());
        sickLabelValue.setText(pc.getSick().toString());
        blindLabelValue.setText(pc.getBlind().toString());
        stunnedLabelValue.setText(pc.getStunned().toString());
        hallucinationLabelValue.setText(pc.getHallucination().toString());
        slimedLabelValue.setText(pc.getSlimed().toString());
        encumbranceLabelValue.setText(pc.getEncumbrance().toString());
        monsterLabelValue.setText(pc.getMonster().toString());
        HDLabelValue.setText(pc.getHD().toString());

    };

}

