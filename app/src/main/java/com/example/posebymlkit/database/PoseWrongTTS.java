package com.example.posebymlkit.database;

import java.util.ArrayList;

public class PoseWrongTTS {
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
    String RKneeToe;
    String LKneeToe;
    String RThighHorizontal;
    String LThighHorizontal;
    String RCrotch;
    String LCrotch;
    String RShoulderGround;
    String LShoulderGround;
    String RElbowRaise;
    String LElbowRaise;
    String RHeelOnGround;
    String LHeelOnGround;
    String bodyVertical;
    String ankleLongThenShoulder;
    public PoseWrongTTS(){}
    public PoseWrongTTS(
            String poseName,
            String RHip,
            String LHip,
            String RKnee,
            String LKnee,
            String RElbow,
            String LElbow,
            String RArmpit,
            String LArmpit,
            String RShoulder,
            String LShoulder,
            String RKneeToe,
            String LKneeToe,
            String RThighHorizontal,
            String LThighHorizontal,
            String RCrotch,
            String LCrotch,
            String RShoulderGround,
            String LShoulderGround,
            String RElbowRaise,
            String LElbowRaise,
            String RHeelOnGround,
            String LHeelOnGround,
            String bodyVertical,
            String ankleLongThenShoulder){
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
        this.RKneeToe = RKneeToe;
        this.LKneeToe = LKneeToe;
        this.RThighHorizontal = RThighHorizontal;
        this.LThighHorizontal = LThighHorizontal;
        this.RCrotch = RCrotch;
        this.LCrotch = LCrotch;
        this.RShoulderGround = RShoulderGround;
        this.LShoulderGround = LShoulderGround;
        this.RElbowRaise = RElbowRaise;
        this.LElbowRaise = LElbowRaise;
        this.RHeelOnGround = RHeelOnGround;
        this.LHeelOnGround = LHeelOnGround;
        this.bodyVertical = bodyVertical;
        this.ankleLongThenShoulder = ankleLongThenShoulder;
    }

    // code to get all contacts in a list view
    public ArrayList<String> getWrongTTS(){
        ArrayList<String> wrongTTS = new ArrayList<>();
        wrongTTS.add(this.RHip);
        wrongTTS.add(this.LHip);
        wrongTTS.add(this.RKnee);
        wrongTTS.add(this.LKnee);
        wrongTTS.add(this.RElbow);
        wrongTTS.add(this.LElbow);
        wrongTTS.add(this.RArmpit);
        wrongTTS.add(this.LArmpit);
        wrongTTS.add(this.RShoulder);
        wrongTTS.add(this.LShoulder);
        wrongTTS.add(this.RKneeToe);
        wrongTTS.add(this.LKneeToe);
        wrongTTS.add(this.RThighHorizontal);
        wrongTTS.add(this.LThighHorizontal);
        wrongTTS.add(this.RCrotch);
        wrongTTS.add(this.LCrotch);
        wrongTTS.add(this.RShoulderGround);
        wrongTTS.add(this.LShoulderGround);
        wrongTTS.add(this.RElbowRaise);
        wrongTTS.add(this.LElbowRaise);
        wrongTTS.add(this.RHeelOnGround);
        wrongTTS.add(this.LHeelOnGround);
        wrongTTS.add(this.bodyVertical);
        wrongTTS.add(this.ankleLongThenShoulder);
        return wrongTTS;
    }

    public String getPoseName(){ return this.poseName;}
    public void setPoseName(String poseName){ this.poseName = poseName;}

    public String getRHip1(){ return this.RHip;}
    public void setRHip1(String RHip1){ this.RHip = RHip1;}

    public String getLHip(){ return this.LHip;}
    public void setLHip(String LHip){ this.LHip = LHip;}

    public String getRKnee(){ return this.RKnee;}
    public void setRKnee(String RKnee){ this.RKnee = RKnee;}

    public String getLKnee(){ return this.LKnee;}
    public void setLKnee(String LKnee){ this.LKnee = LKnee;}

    public String getRElbow(){ return this.RElbow;}
    public void setRElbow(String RElbow){ this.RElbow = RElbow;}

    public String getLElbow(){ return this.LElbow;}
    public void setLElbow(String LElbow){ this.LElbow = LElbow;}

    public String getRArmpit(){ return this.RArmpit;}
    public void setRArmpit(String RArmpit){ this.RArmpit = RArmpit;}

    public String getLArmpit(){ return this.LArmpit;}
    public void setLArmpit(String LArmpit){ this.LArmpit = LArmpit;}

    public String getRShoulder(){ return this.RShoulder;}
    public void setRShoulder(String RShoulder){ this.RShoulder = RShoulder;}

    public String getLShoulder(){ return this.LShoulder;}
    public void setLShoulder(String LShoulder){ this.LShoulder = LShoulder;}

    public String getRKneeToe(){ return this.RKneeToe;}
    public void setRKneeToe(String RKneeToe){ this.RKneeToe = RKneeToe;}

    public String getLKneeToe(){ return this.LKneeToe;}
    public void setLKneeToe(String LKneeToe){ this.LKneeToe = LKneeToe;}

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

    public String getAnkleLongThenShoulder(){ return this.ankleLongThenShoulder;}
    public void setAnkleLongThenShoulder(String ankleLongThenShoulder){ this.ankleLongThenShoulder = ankleLongThenShoulder;}
}
