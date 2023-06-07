package com.example.posebymlkit.database;

import java.util.ArrayList;

public class PoseStandard {
    String poseName;
    String RHip, LHip;
    String RKnee, LKnee;
    String RElbow, LElbow;
    String RArmpit, LArmpit;
    String RShoulder, LShoulder;
    String RKneeToe, LKneeToe;
    String RThighHorizontal, LThighHorizontal;
    String RCrotch, LCrotch;
    String RShoulderGround, LShoulderGround;
    String RElbowRaise, LElbowRaise;
    String RHeelOnGround, LHeelOnGround;
    String bodyVertical;
    String ankleLongThanShoulder;
    //key new standard here
    public PoseStandard(){}
    public PoseStandard(
            String poseName,
            String RHip, String LHip,
            String RKnee, String LKnee,
            String RElbow, String LElbow,
            String RArmpit, String LArmpit,
            String RShoulder, String LShoulder,
            String RKneeToe, String LKneeToe,
            String RThighHorizontal, String LThighHorizontal,
            String RCrotch, String LCrotch,
            String RShoulderGround, String LShoulderGround,
            String RElbowRaise, String LElbowRaise,
            String RHeelOnGround, String LHeelOnGround,
            String bodyVertical,
            String ankleLongThanShoulder){//key new standard here
        this.poseName = poseName;
        this.RHip = RHip;   this.LHip = LHip;
        this.RKnee = RKnee; this.LKnee = LKnee;
        this.RElbow = RElbow;   this.LElbow = LElbow;
        this.RArmpit = RArmpit; this.LArmpit = LArmpit;
        this.RShoulder = RShoulder; this.LShoulder = LShoulder;
        this.RKneeToe = RKneeToe;   this.LKneeToe = LKneeToe;
        this.RThighHorizontal = RThighHorizontal;   this.LThighHorizontal = LThighHorizontal;
        this.RCrotch = RCrotch; this.LCrotch = LCrotch;
        this.RShoulderGround = RShoulderGround; this.LShoulderGround = LShoulderGround;
        this.RElbowRaise = RElbowRaise; this.LElbowRaise = LElbowRaise;
        this.RHeelOnGround = RHeelOnGround; this.LHeelOnGround = LHeelOnGround;
        this.bodyVertical = bodyVertical;
        this.ankleLongThanShoulder = ankleLongThanShoulder;
        //key new standard here
    }

    public ArrayList<String> getPoseStandard(){
        ArrayList<String> standard = new ArrayList<>();
        standard.add(this.RHip);
        standard.add(this.LHip);
        standard.add(this.RKnee);
        standard.add(this.LKnee);
        standard.add(this.RElbow);
        standard.add(this.LElbow);
        standard.add(this.RArmpit);
        standard.add(this.LArmpit);
        standard.add(this.RShoulder);
        standard.add(this.LShoulder);
        standard.add(this.RKneeToe);
        standard.add(this.LKneeToe);
        standard.add(this.RThighHorizontal);
        standard.add(this.LThighHorizontal);
        standard.add(this.RCrotch);
        standard.add(this.LCrotch);
        standard.add(this.RShoulderGround);
        standard.add(this.LShoulderGround);
        standard.add(this.RElbowRaise);
        standard.add(this.LElbowRaise);
        standard.add(this.RHeelOnGround);
        standard.add(this.LHeelOnGround);
        standard.add(this.bodyVertical);
        standard.add(this.ankleLongThanShoulder);
        //key new standard here
        return standard;
    }

    public String getPoseName(){ return this.poseName;}
    public void setPoseName(String poseName){ this.poseName = poseName;}

    public String getRHip(){ return this.RHip;}
    public void setRHip(String rHip){ this.RHip = rHip;}

    public String getLHip(){ return this.LHip;}
    public void setLHip(String lHip){ this.LHip = lHip;}

    public String getRKnee(){ return this.RKnee;}
    public void setRKnee(String rKnee){ this.RKnee = rKnee;}

    public String getLKnee(){ return this.LKnee;}
    public void setLKnee(String lKnee){ this.LKnee = lKnee;}

    public String getRElbow(){ return this.RElbow;}
    public void setRElbow(String rElbow){ this.RElbow = rElbow;}

    public String getLElbow(){ return this.LElbow;}
    public void setLElbow(String lElbow){ this.LElbow = lElbow;}

    public String getRArmpit(){ return this.RArmpit;}
    public void setRArmpit(String rArmpit){ this.RArmpit = rArmpit;}

    public String getLArmpit(){ return this.LArmpit;}
    public void setLArmpit(String lArmpit){ this.LArmpit = lArmpit;}

    public String getRShoulder(){ return this.RShoulder;}
    public void setRShoulder(String rShoulder){ this.RShoulder = rShoulder;}

    public String getLShoulder(){ return this.LShoulder;}
    public void setLShoulder(String lShoulder){ this.LShoulder = lShoulder;}

    public String getRKneeToe(){ return this.RKneeToe;}
    public void setRKneeToe(String rKneeToe){ this.RKneeToe = rKneeToe;}

    public String getLKneeToe(){ return this.LKneeToe;}
    public void setLKneeToe(String lKneeToe){ this.LKneeToe = lKneeToe;}

    public String getRThighHorizontal(){ return this.RThighHorizontal;}
    public void setRThighHorizontal(String RThighHorizontal){ this.RThighHorizontal = RThighHorizontal;}

    public String getLThighHorizontal(){ return this.LThighHorizontal;}
    public void setLThighHorizontal(String LThighHorizontal){ this.LThighHorizontal = LThighHorizontal;}

    public String getRCrotch(){ return this.RCrotch;}
    public void setRCrotch(String RCrotch){ this.RCrotch = RCrotch;}

    public String getLCrotch(){ return this.LCrotch;}
    public void setLCrotch(String LCrotch){ this.LCrotch = LCrotch;}

    public String getRShoulderGround(){ return this.RShoulderGround;}
    public void setRShoulderGround(String RShoulderGround){ this.RShoulderGround = RShoulderGround;}

    public String getLShoulderGround(){ return this.LShoulderGround;}
    public void setLShoulderGround(String LShoulderGround){ this.LShoulderGround = LShoulderGround;}

    public String getRElbowRaise(){ return this.RElbowRaise;}
    public void setRElbowRaise(String RElbowRaise){ this.RElbowRaise = RElbowRaise;}

    public String getLElbowRaise(){ return this.LElbowRaise;}
    public void setLElbowRaise(String LElbowRaise){ this.LElbowRaise = LElbowRaise;}

    public String getRHeelOnGround(){ return this.RHeelOnGround;}
    public void setRHeelOnGround(String RHeelOnGround){ this.RHeelOnGround = RHeelOnGround;}

    public String getLHeelOnGround(){ return this.LHeelOnGround;}
    public void setLHeelOnGround(String LHeelOnGround){ this.LHeelOnGround = LHeelOnGround;}

    public String getBodyVertical(){ return this.bodyVertical;}
    public void setBodyVertical(String bodyVertical){ this.bodyVertical = bodyVertical;}

    public String getAnkleLongThanShoulder(){ return this.ankleLongThanShoulder;}
    public void setAnkleLongThanShoulder(String ankleLongThanShoulder){ this.ankleLongThanShoulder = ankleLongThanShoulder;}
    //key new standard here
}