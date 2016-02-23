package com.maxkrass.appreciate.objects;


import java.util.ArrayList;

/**
 * Max made this for APPreciate on 18.12.2015 for APPreciate.
 */
public class MatchRecord extends Record {


    int autoPoints;
    int totalPoints;
    int matchNumber;
    String autoComment;
    String teleComment;
    boolean pickPort;
    boolean pickChevel;
    boolean pickMoat;
    boolean pickRamp;
    boolean pickSally;
    boolean pickRock;
    boolean pickRough;
    boolean startWithBall;
    boolean autoSpy;

    boolean reachedDefense;
    String defenseReach;
    int lowGoalAuto;
    int highGoalAuto;
    boolean secreatPassage;
    boolean netural;
    boolean courtYard;
    boolean steal;
    boolean block;
    boolean push;
    boolean canPickUp;
    boolean fast;
    boolean penalty;
    boolean breach;
    boolean capture;
    boolean scaleLeft;
    boolean scaleMiddle;
    boolean scaleRight;

    boolean fast1;
    boolean fast2;
    boolean fast3;
    boolean fast4;
    boolean fast5;
    ArrayList<String> shots;

    public boolean isFast1()
    {
        return fast1;
    }

    public void setFast1(boolean fast1)
    {
        this.fast1 = fast1;

    }

    public boolean isFast2() {
        return fast2;
    }

    public void setFast2(boolean fast2) {
        this.fast2 = fast2;
    }

    public boolean isFast3() {
        return fast3;
    }

    public void setFast3(boolean fast3) {
        this.fast3 = fast3;
    }

    public boolean isFast4() {
        return fast4;
    }

    public void setFast4(boolean fast4) {
        this.fast4 = fast4;
    }

    public boolean isFast5() {
        return fast5;
    }

    public void setFast5(boolean fast5) {
        this.fast5 = fast5;
    }




    int defenseSpinner;

    public int getSpinner2() {
        return spinner2;
    }

    public void setSpinner2(int spinner2) {
        this.spinner2 = spinner2;
    }

    public int getSpinner3() {
        return spinner3;
    }

    public void setSpinner3(int spinner3) {
        this.spinner3 = spinner3;
    }

    public int getSpinner4() {
        return spinner4;
    }

    public void setSpinner4(int spinner4) {
        this.spinner4 = spinner4;
    }

    public int getSpinner5() {
        return spinner5;
    }

    public void setSpinner5(int spinner5) {
        this.spinner5 = spinner5;
    }

    int spinner2;
    int spinner3;
    int spinner4;
    int spinner5;

    public void setDefenseSpinner(int defenseSpinner){
        this.defenseSpinner=defenseSpinner;
    }
    public int getDefenseSpinner(){ return defenseSpinner;}





    public boolean getPickPort() {
        return pickPort;
    }

    public void setPickPort(boolean pickPort) {
        this.pickPort = pickPort;
    }

    public boolean getPickChevel() {
        return pickChevel;
    }

    public void setPickChevel(boolean pickChevel) {
        this.pickChevel = pickChevel;
    }

    public boolean getPickMoat() {
        return pickMoat;
    }

    public void setPickMoat(boolean pickMoat) {
        this.pickMoat = pickMoat;
    }

    public boolean getPickRamp() {
        return pickRamp;
    }

    public void setPickRamp(boolean pickRamp) {
        this.pickRamp = pickRamp;
    }

    public boolean getPickSally() {
        return pickSally;
    }

    public void setPickSally(boolean pickSally) {
        this.pickSally = pickSally;
    }

    public boolean getPickRock() {
        return pickRock;
    }

    public void setPickRock(boolean pickRock) {
        this.pickRock = pickRock;
    }

    public boolean getPickRough() {
        return pickRough;
    }

    public void setPickRough(boolean pickRough) {
        this.pickRough = pickRough;
    }

    public boolean getStartWithBall() {
        return startWithBall;
    }

    public void setStartWithBall(boolean startWithBall) {
        this.startWithBall = startWithBall;
    }

    public boolean getAutoSpy() {
        return autoSpy;
    }

    public void setAutoSpy(boolean autoSpy) {
        this.autoSpy = autoSpy;
    }

    public boolean getReachedDefense() {
        return reachedDefense;
    }

    public void setReachedDefense(boolean reachedDefense) {
        this.reachedDefense = reachedDefense;
    }

    public String getDefenseReach() {
        return defenseReach;
    }

    public void setDefenseReach(String defenseReach) {
        this.defenseReach = defenseReach;
    }

    public int getLowGoalAuto() {
        return lowGoalAuto;
    }

    public void setLowGoalAuto(int lowGoalAuto) {
        this.lowGoalAuto = lowGoalAuto;
    }

    public int getHighGoalAuto() {
        return highGoalAuto;
    }

    public void setHighGoalAuto(int highGoalAuto) {
        this.highGoalAuto = highGoalAuto;
    }

    public boolean getSecreatPassage() {
        return secreatPassage;
    }

    public void setSecreatPassage(boolean secreatPassage) {
        this.secreatPassage = secreatPassage;
    }

    public boolean getNetural() {
        return netural;
    }

    public void setNetural(boolean netural) {
        this.netural = netural;
    }

    public boolean getCourtYard() {
        return courtYard;
    }

    public void setCourtYard(boolean courtYard) {
        this.courtYard = courtYard;
    }

    public boolean getSteal() {
        return steal;
    }

    public void setSteal(boolean steal) {
        this.steal = steal;
    }

    public boolean getBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public boolean getPush() {
        return push;
    }

    public void setPush(boolean push) {
        this.push = push;
    }

    public boolean getCanPickUp() {
        return canPickUp;
    }

    public void setCanPickUp(boolean canPickUp) {
        this.canPickUp = canPickUp;
    }

    public boolean getFast() {
        return fast;
    }

    public void setFast(boolean fast) {
        this.fast = fast;
    }

    public boolean getPenalty() {
        return penalty;
    }

    public void setPenalty(boolean penalty) {
        this.penalty = penalty;
    }

    public boolean getBreach() {
        return breach;
    }

    public void setBreach(boolean breach) {
        this.breach = breach;
    }

    public boolean getCapture() {
        return capture;
    }

    public void setCapture(boolean capture) {
        this.capture = capture;
    }

    public boolean getScaleLeft() {
        return scaleLeft;
    }

    public void setScaleLeft(boolean scaleLeft) {
        this.scaleLeft = scaleLeft;
    }

    public boolean getScaleMiddle(){return scaleMiddle;}

    public void setScaleMiddle(boolean scaleMiddle){this.scaleMiddle = scaleMiddle;}

    public boolean getScaleRight(){return scaleRight;}

    public void setScaleRight(boolean scaleRight){this.scaleRight = scaleRight;}




    public int getMatchNumber() {
        return matchNumber;

    }

    public void setMatchNumber(int matchNumber) {
        this.matchNumber = matchNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public int getAutoPoints() {
        return autoPoints;
    }

    public void setAutoPoints(int autoPoints) {
        this.autoPoints = autoPoints;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getAutoComment() {
        return autoComment;
    }

    public void setAutoComment(String autoComment) {
        this.autoComment = autoComment;
    }

    public String getTeleComment() {
        return teleComment;
    }

    public void setTeleComment(String teleComment) {
        this.teleComment = teleComment;
    }

    public void setShots(ArrayList<String> shots)
    {
        this.shots = shots;
    }
    public ArrayList<String> getShots()
    {
        return shots;
    }





    public MatchRecord() {}
}
