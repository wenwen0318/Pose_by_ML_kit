package com.example.posebymlkit.database;

public class HistoricalRecord {
    String poseName;
    String date;
    int level;
    int time;
    float allComplete;
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

    public HistoricalRecord(){}
    public HistoricalRecord(
            String poseName,
            String date,
            int level,
            int time,
            float allComplete,
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
            String bodyVertical
    ){
        this.poseName = poseName;
        this.date = date;
        this.level = level;
        this.time = time;
        this.allComplete = allComplete;
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
    }

    public String get(int i){
        String item;
        switch (i){
            case 1:
                item = this.poseName;
                break;
            case 2:
                item = this.date;
                break;
            case 3:
                item = Integer.toString(this.level);
                break;
            case 4:
                item = Integer.toString(this.time);
                break;
            case 5:
                item = Float.toString(this.allComplete);
                break;
            case 6:
                item = this.RHip;
                break;
            case 7:
                item = this.LHip;
                break;
            case 8:
                item = this.RKnee;
                break;
            case 9:
                item = this.LKnee;
                break;
            case 10:
                item = this.RElbow;
                break;
            case 11:
                item = this.LElbow;
                break;
            case 12:
                item = this.RArmpit;
                break;
            case 13:
                item = this.LArmpit;
                break;
            case 14:
                item = this.RShoulder;
                break;
            case 15:
                item = this.LShoulder;
                break;
            case 16:
                item = this.RKneeToe;
                break;
            case 17:
                item = this.LKneeToe;
                break;
            case 18:
                item = this.RThighHorizontal;
                break;
            case 19:
                item = this.LThighHorizontal;
                break;
            case 20:
                item = this.RCrotch;
                break;
            case 21:
                item = this.LCrotch;
                break;
            case 22:
                item = this.RShoulderGround;
                break;
            case 23:
                item = this.LShoulderGround;
                break;
            case 24:
                item = this.RElbowRaise;
                break;
            case 25:
                item = this.LElbowRaise;
                break;
            case 26:
                item = this.RHeelOnGround;
                break;
            case 27:
                item = this.LHeelOnGround;
                break;
            case 28:
                item = this.bodyVertical;
                break;
            default:
                item = "null";
        }
        return item;
    }

    public String getPoseName(){ return this.poseName;}
    public void setPoseName(String poseName){ this.poseName = poseName;}

    public String getDate(){ return this.date;}
    public void setDate(String date){ this.date = date;}

    public int getLevel(){ return this.level;}
    public void setLevel(int level){ this.level = level;}

    public int getTime(){ return this.time;}
    public void setTime(int time){ this.time = time;}

    public float getAllComplete(){ return this.allComplete;}
    public void setAllComplete(float allComplete){ this.allComplete = allComplete;}

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
}
