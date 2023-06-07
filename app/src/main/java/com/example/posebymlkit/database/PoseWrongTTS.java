package com.example.posebymlkit.database;

import java.util.ArrayList;

public class PoseWrongTTS {
    String poseName;
    String RHip1, RHip2;
    String LHip1, LHip2;
    String RKnee1, RKnee2;
    String LKnee1, LKnee2;
    String RElbow1, RElbow2;
    String LElbow1, LElbow2;
    String RArmpit1, RArmpit2;
    String LArmpit1, LArmpit2;
    String RShoulder1, RShoulder2;
    String LShoulder1, LShoulder2;
    String RKneeToe1, RKneeToe2;
    String LKneeToe1, LKneeToe2;
    String RThighHorizontal1, RThighHorizontal2;
    String LThighHorizontal1, LThighHorizontal2;
    String RCrotch1, RCrotch2;
    String LCrotch1, LCrotch2;
    String RShoulderGround1, RShoulderGround2;
    String LShoulderGround1, LShoulderGround2;
    String RElbowRaise1, RElbowRaise2;
    String LElbowRaise1, LElbowRaise2;
    String RHeelOnGround1, RHeelOnGround2;
    String LHeelOnGround1, LHeelOnGround2;
    String bodyVertical;
    String ankleLongThenShoulder;
    public PoseWrongTTS(){}
    public PoseWrongTTS(
            String poseName,
            String RHip1, String RHip2,
            String LHip1, String LHip2,
            String RKnee1, String RKnee2,
            String LKnee1, String LKnee2,
            String RElbow1, String RElbow2,
            String LElbow1, String LElbow2,
            String RArmpit1, String RArmpit2,
            String LArmpit1, String LArmpit2,
            String RShoulder1, String RShoulder2,
            String LShoulder1, String LShoulder2,
            String RKneeToe1, String RKneeToe2,
            String LKneeToe1, String LKneeToe2,
            String RThighHorizontal1, String RThighHorizontal2,
            String LThighHorizontal1, String LThighHorizontal2,
            String RCrotch1, String RCrotch2,
            String LCrotch1, String LCrotch2,
            String RShoulderGround1, String RShoulderGround2,
            String LShoulderGround1, String LShoulderGround2,
            String RElbowRaise1, String RElbowRaise2,
            String LElbowRaise1, String LElbowRaise2,
            String RHeelOnGround1, String RHeelOnGround2,
            String LHeelOnGround1, String LHeelOnGround2,
            String bodyVertical,
            String ankleLongThenShoulder){
        this.poseName = poseName;
        this.RHip1 = RHip1; this.RHip2 = RHip2;
        this.LHip1 = LHip1; this.LHip2 = LHip2;
        this.RKnee1 = RKnee1;   this.RKnee2 = RKnee2;
        this.LKnee1 = LKnee1;   this.LKnee2 = LKnee2;
        this.RElbow1 = RElbow1; this.RElbow2 = RElbow2;
        this.LElbow1 = LElbow1; this.LElbow2 = LElbow2;
        this.RArmpit1 = RArmpit1;   this.RArmpit2 = RArmpit2;
        this.LArmpit1 = LArmpit1;   this.LArmpit2 = LArmpit2;
        this.RShoulder1 = RShoulder1;   this.RShoulder2 = RShoulder2;
        this.LShoulder1 = LShoulder1;   this.LShoulder2 = LShoulder2;
        this.RKneeToe1 = RKneeToe1;   this.RKneeToe2 = RKneeToe2;
        this.LKneeToe1 = LKneeToe1;   this.LKneeToe2 = LKneeToe2;
        this.RThighHorizontal1 = RThighHorizontal1; this.RThighHorizontal2 = RThighHorizontal2;
        this.LThighHorizontal1 = LThighHorizontal1; this.LThighHorizontal2 = LThighHorizontal2;
        this.RCrotch1 = RCrotch1;   this.RCrotch2 = RCrotch2;
        this.LCrotch1 = LCrotch1;   this.LCrotch2 = LCrotch2;
        this.RShoulderGround1 = RShoulderGround1;   this.RShoulderGround2 = RShoulderGround2;
        this.LShoulderGround1 = LShoulderGround1;   this.LShoulderGround2 = LShoulderGround2;
        this.RElbowRaise1 = RElbowRaise1;   this.RElbowRaise2 = RElbowRaise2;
        this.LElbowRaise1 = LElbowRaise1;   this.LElbowRaise2 = LElbowRaise2;
        this.RHeelOnGround1 = RHeelOnGround1;   this.RHeelOnGround2 = RHeelOnGround2;
        this.LHeelOnGround1 = LHeelOnGround1;   this.LHeelOnGround2 = LHeelOnGround2;
        this.bodyVertical = bodyVertical;
        this.ankleLongThenShoulder = ankleLongThenShoulder;
    }

    // code to get all contacts in a list view
    public ArrayList<String> getWrongTTS(){
        ArrayList<String> wrongTTS = new ArrayList<>();
        wrongTTS.add(this.RHip1);   wrongTTS.add(this.RHip2);
        wrongTTS.add(this.LHip1);   wrongTTS.add(this.LHip2);
        wrongTTS.add(this.RKnee1);  wrongTTS.add(this.RKnee2);
        wrongTTS.add(this.LKnee1);  wrongTTS.add(this.LKnee2);
        wrongTTS.add(this.RElbow1); wrongTTS.add(this.RElbow2);
        wrongTTS.add(this.LElbow1); wrongTTS.add(this.LElbow2);
        wrongTTS.add(this.RArmpit1);    wrongTTS.add(this.RArmpit2);
        wrongTTS.add(this.LArmpit1);    wrongTTS.add(this.LArmpit2);
        wrongTTS.add(this.RShoulder1);  wrongTTS.add(this.RShoulder2);
        wrongTTS.add(this.LShoulder1);  wrongTTS.add(this.LShoulder2);
        wrongTTS.add(this.RKneeToe1);   wrongTTS.add(this.RKneeToe2);
        wrongTTS.add(this.LKneeToe1);   wrongTTS.add(this.LKneeToe2);
        wrongTTS.add(this.RThighHorizontal1);   wrongTTS.add(this.RThighHorizontal2);
        wrongTTS.add(this.LThighHorizontal1);   wrongTTS.add(this.LThighHorizontal2);
        wrongTTS.add(this.RCrotch1);    wrongTTS.add(this.RCrotch2);
        wrongTTS.add(this.LCrotch1);    wrongTTS.add(this.LCrotch2);
        wrongTTS.add(this.RShoulderGround1);    wrongTTS.add(this.RShoulderGround2);
        wrongTTS.add(this.LShoulderGround1);    wrongTTS.add(this.LShoulderGround2);
        wrongTTS.add(this.RElbowRaise1);    wrongTTS.add(this.RElbowRaise2);
        wrongTTS.add(this.LElbowRaise1);    wrongTTS.add(this.LElbowRaise2);
        wrongTTS.add(this.RHeelOnGround1);  wrongTTS.add(this.RHeelOnGround2);
        wrongTTS.add(this.LHeelOnGround1);  wrongTTS.add(this.LHeelOnGround2);
        wrongTTS.add(this.bodyVertical);    wrongTTS.add(this.ankleLongThenShoulder);
        return wrongTTS;
    }

    public String getPoseName(){ return this.poseName;}
    public void setPoseName(String poseName){ this.poseName = poseName;}

    public String getRHip1(){ return this.RHip1;}
    public void setRHip1(String RHip1){ this.RHip1 = RHip1;}

    public String getRHip2(){ return this.RHip2;}
    public void setRHip2(String RHip2){ this.RHip2 = RHip2;}

    public String getLHip1(){ return this.LHip1;}
    public void setLHip1(String LHip1){ this.LHip1 = LHip1;}

    public String getLHip2(){ return this.LHip2;}
    public void setLHip2(String LHip2){ this.LHip2 = LHip2;}

    public String getRKnee1(){ return this.RKnee1;}
    public void setRKnee1(String RKnee1){ this.RKnee1 = RKnee1;}

    public String getRKnee2(){ return this.RKnee2;}
    public void setRKnee2(String RKnee2){ this.RKnee2 = RKnee2;}

    public String getLKnee1(){ return this.LKnee1;}
    public void setLKnee1(String LKnee1){ this.LKnee1 = LKnee1;}

    public String getLKnee2(){ return this.LKnee2;}
    public void setLKnee2(String LKnee2){ this.LKnee2 = LKnee2;}

    public String getRElbow1(){ return this.RElbow1;}
    public void setRElbow1(String RElbow1){ this.RElbow1 = RElbow1;}

    public String getRElbow2(){ return this.RElbow2;}
    public void setRElbow2(String RElbow2){ this.RElbow2 = RElbow2;}

    public String getLElbow1(){ return this.LElbow1;}
    public void setLElbow1(String LElbow1){ this.LElbow1 = LElbow1;}

    public String getLElbow2(){ return this.LElbow2;}
    public void setLElbow2(String LElbow2){ this.LElbow2 = LElbow2;}

    public String getRArmpit1(){ return this.RArmpit1;}
    public void setRArmpit1(String RArmpit1){ this.RArmpit1 = RArmpit1;}

    public String getRArmpit2(){ return this.RArmpit2;}
    public void setRArmpit2(String RArmpit2){ this.RArmpit2 = RArmpit2;}

    public String getLArmpit1(){ return this.LArmpit1;}
    public void setLArmpit1(String LArmpit1){ this.LArmpit1 = LArmpit1;}

    public String getLArmpit2(){ return this.LArmpit2;}
    public void setLArmpit2(String LArmpit2){ this.LArmpit2 = LArmpit2;}

    public String getRShoulder1(){ return this.RShoulder1;}
    public void setRShoulder1(String RShoulder1){ this.RShoulder1 = RShoulder1;}

    public String getRShoulder2(){ return this.RShoulder2;}
    public void setRShoulder2(String RShoulder2){ this.RShoulder2 = RShoulder2;}

    public String getLShoulder1(){ return this.LShoulder1;}
    public void setLShoulder1(String LShoulder1){ this.LShoulder1 = LShoulder1;}

    public String getLShoulder2(){ return this.LShoulder2;}
    public void setLShoulder2(String LShoulder2){ this.LShoulder2 = LShoulder2;}

    public String getRKneeToe1(){ return this.RKneeToe1;}
    public void setRKneeToe1(String RKneeToe1){ this.RKneeToe1 = RKneeToe1;}

    public String getRKneeToe2(){ return this.RKneeToe2;}
    public void setRKneeToe2(String RKneeToe2){ this.RKneeToe2 = RKneeToe2;}

    public String getLKneeToe1(){ return this.LKneeToe1;}
    public void setLKneeToe1(String LKneeToe1){ this.LKneeToe1 = LKneeToe1;}

    public String getLKneeToe2(){ return this.LKneeToe2;}
    public void setLKneeToe2(String LKneeToe2){ this.LKneeToe2 = LKneeToe2;}

    public String getRThighHorizontal1(){ return this.RThighHorizontal1;}
    public void setRThighHorizontal1(String RThighHorizontal1){ this.RThighHorizontal1 = RThighHorizontal1;}

    public String getRThighHorizontal2(){ return this.RThighHorizontal2;}
    public void setRThighHorizontal2(String RThighHorizontal2){ this.RThighHorizontal2 = RThighHorizontal2;}

    public String getLThighHorizontal1(){ return this.LThighHorizontal1;}
    public void setLThighHorizontal1(String LThighHorizontal1){ this.LThighHorizontal1 = LThighHorizontal1;}

    public String getLThighHorizontal2(){ return this.LThighHorizontal2;}
    public void setLThighHorizontal2(String LThighHorizontal2){ this.LThighHorizontal2 = LThighHorizontal2;}

    public String getRCrotch1(){ return this.RCrotch1;}
    public void setRCrotch1(String RCrotch1){ this.RCrotch1 = RCrotch1;}

    public String getRCrotch2(){ return this.RCrotch2;}
    public void setRCrotch2(String RCrotch2){ this.RCrotch2 = RCrotch2;}

    public String getLCrotch1(){ return this.LCrotch1;}
    public void setLCrotch1(String LCrotch1){ this.LCrotch1 = LCrotch1;}

    public String getLCrotch2(){ return this.LCrotch2;}
    public void setLCrotch2(String LCrotch2){ this.LCrotch2 = LCrotch2;}

    public String getRShoulderGround1(){ return this.RShoulderGround1;}
    public void setRShoulderGround1(String RShoulderGround1){ this.RShoulderGround1 = RShoulderGround1;}

    public String getRShoulderGround2(){ return this.RShoulderGround2;}
    public void setRShoulderGround2(String RShoulderGround2){ this.RShoulderGround2 = RShoulderGround2;}

    public String getLShoulderGround1(){ return this.LShoulderGround1;}
    public void setLShoulderGround1(String LShoulderGround1){ this.LShoulderGround1 = LShoulderGround1;}

    public String getLShoulderGround2(){ return this.LShoulderGround2;}
    public void setLShoulderGround2(String LShoulderGround2){ this.LShoulderGround2 = LShoulderGround2;}

    public String getRElbowRaise1(){ return this.RElbowRaise1;}
    public void setRElbowRaise1(String RElbowRaise1){ this.RElbowRaise1 = RElbowRaise1;}

    public String getRElbowRaise2(){ return this.RElbowRaise2;}
    public void setRElbowRaise2(String RElbowRaise2){ this.RElbowRaise2 = RElbowRaise2;}

    public String getLElbowRaise1(){ return this.LElbowRaise1;}
    public void setLElbowRaise1(String LElbowRaise1){ this.LElbowRaise1 = LElbowRaise1;}

    public String getLElbowRaise2(){ return this.LElbowRaise2;}
    public void setLElbowRaise2(String LElbowRaise2){ this.LElbowRaise2 = LElbowRaise2;}

    public String getRHeelOnGround1(){ return this.RHeelOnGround1;}
    public void setRHeelOnGround1(String RHeelOnGround1){ this.RHeelOnGround1 = RHeelOnGround1;}

    public String getRHeelOnGround2(){ return this.RHeelOnGround2;}
    public void setRHeelOnGround2(String RHeelOnGround2){ this.RHeelOnGround2 = RHeelOnGround2;}

    public String getLHeelOnGround1(){ return this.LHeelOnGround1;}
    public void setLHeelOnGround1(String LHeelOnGround1){ this.LHeelOnGround1 = LHeelOnGround1;}

    public String getLHeelOnGround2(){ return this.LHeelOnGround2;}
    public void setLHeelOnGround2(String LHeelOnGround2){ this.LHeelOnGround2 = LHeelOnGround2;}

    public String getBodyVertical(){ return this.bodyVertical;}
    public void setBodyVertical(String bodyVertical){ this.bodyVertical = bodyVertical;}

    public String getAnkleLongThenShoulder(){ return this.ankleLongThenShoulder;}
    public void setAnkleLongThenShoulder(String ankleLongThenShoulder){ this.ankleLongThenShoulder = ankleLongThenShoulder;}
}
