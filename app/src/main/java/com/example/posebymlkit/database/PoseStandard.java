package com.example.posebymlkit.database;

import java.util.ArrayList;

public class PoseStandard {
    String poseName;
    String RHip;
    String LHip;
    String RKnee;
    String LKnee;
    String RElbow;
    String LElbow;
    String RArmpit;
    String LArmpit;
    String RShoulder;
    String LShoulder;
    String bodyVertical;
    String RKneeToe;
    String LKneeToe;
    String RThighHorizontal;
    String crotch;
    String shoulderGround;
    String LThighHorizontal;
    String RElbowRaise;
    public PoseStandard(){}
    public PoseStandard(
            String poseName,
            String RHip, String LHip,
            String RKnee, String LKnee,
            String RElbow, String LElbow,
            String RArmpit, String LArmpit,
            String RShoulder, String LShoulder,
            String bodyVertical,
            String RKneeToe, String LKneeToe,
            String RThighHorizontal,
            String crotch,
            String shoulderGround,
            String LThighHorizontal,
            String RElbowRaise){
        this.poseName = poseName;
        this.RHip = RHip;
        this.LHip = LHip;
        this.RKnee = RKnee;
        this.LKnee = LKnee;
        this.RElbow = RElbow;
        this.LElbow = LElbow;
        this.RArmpit = RArmpit;
        this.LArmpit = LArmpit;
        this.RShoulder = RShoulder;
        this.LShoulder = LShoulder;
        this.bodyVertical = bodyVertical;
        this.RKneeToe = RKneeToe;
        this.LKneeToe = LKneeToe;
        this.RThighHorizontal = RThighHorizontal;
        this.crotch = crotch;
        this.shoulderGround = shoulderGround;
        this.LThighHorizontal = LThighHorizontal;
        this.RElbowRaise = RElbowRaise;
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
        standard.add(this.bodyVertical);
        standard.add(this.RKneeToe);
        standard.add(this.LKneeToe);
        standard.add(this.RThighHorizontal);
        standard.add(this.crotch);
        standard.add(this.shoulderGround);
        standard.add(this.LThighHorizontal);
        standard.add((this.RElbowRaise));
        return standard;
    }

    public String getPoseName(){ return this.poseName;}
    public void setPoseName(String poseName){ this.poseName = poseName;}

    public String getRHip(){ return this.RHip;}
    public void setRHip(String rHip){ this.RHip = rHip;}

    String getLHip(){ return this.LHip;}
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

    public String getBodyVertical(){ return this.bodyVertical;}
    public void setBodyVertical(String bodyVertical){ this.bodyVertical = bodyVertical;}

    public String getRKneeToe(){ return this.RKneeToe;}
    public void setRKneeToe(String rKneeToe){ this.RKneeToe = rKneeToe;}

    public String getLKneeToe(){ return this.LKneeToe;}
    public void setLKneeToe(String lKneeToe){ this.LKneeToe = lKneeToe;}

    public String getRThighHorizontal(){ return this.RThighHorizontal;}
    public void setRThighHorizontal(String RThighHorizontal){ this.RThighHorizontal = RThighHorizontal;}

    public String getCrotch(){ return this.crotch;}
    public void setCrotch(String crotch){ this.crotch = crotch;}

    public String getShoulderGround(){ return this.shoulderGround;}
    public void setShoulderGround(String shoulderGround){ this.shoulderGround = shoulderGround;}

    public String getLThighHorizontal(){ return this.LThighHorizontal;}
    public void setLThighHorizontal(String LThighHorizontal){ this.LThighHorizontal = LThighHorizontal;}

    public String getRElbowRaise(){ return RElbowRaise;}
    public void setRElbowRaise(String RElbowRaise){ this.RElbowRaise = RElbowRaise;}
}