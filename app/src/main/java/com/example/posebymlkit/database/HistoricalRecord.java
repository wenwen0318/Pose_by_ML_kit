package com.example.posebymlkit.database;

public class HistoricalRecord {
    String poseName;
    String date;
    int level;
    float allComplete;
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
    String thighHorizontal;
    String crotch;
    String shoulderGround;

    public HistoricalRecord(){}
    public HistoricalRecord(
            String poseName,
            String date,
            int level,
            float allComplete,
            String RHip, String LHip,
            String RKnee, String LKnee,
            String RElbow, String LElbow,
            String RArmpit, String LArmpit,
            String RShoulder, String LShoulder,
            String bodyVertical,
            String RKneeToe, String LKneeToe,
            String thighHorizontal,
            String crotch,
            String shoulderGround
    ){
        this.poseName = poseName;
        this.date = date;
        this.level = level;
        this.allComplete = allComplete;
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
        this.thighHorizontal = thighHorizontal;
        this.crotch = crotch;
        this.shoulderGround = shoulderGround;
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
                item = Float.toString(this.allComplete);
                break;
            case 5:
                item = this.RHip;
                break;
            case 6:
                item = this.LHip;
                break;
            case 7:
                item = this.RElbow;
                break;
            case 8:
                item = this.LElbow;
                break;
            case 9:
                item = this.RArmpit;
                break;
            case 10:
                item = this.LArmpit;
                break;
            case 11:
                item = this.RShoulder;
                break;
            case 12:
                item = this.LShoulder;
                break;
            case 13:
                item = this.bodyVertical;
                break;
            case 14:
                item = this.RKneeToe;
                break;
            case 15:
                item = this.LKneeToe;
                break;
            case 16:
                item = this.thighHorizontal;
                break;
            case 17:
                item = this.crotch;
                break;
            case 18:
                item = this.shoulderGround;
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

    public String getBodyVertical(){ return this.bodyVertical;}
    public void setBodyVertical(String bodyVertical){ this.bodyVertical = bodyVertical;}

    public String getRKneeToe(){ return this.RKneeToe;}
    public void setRKneeToe(String rKneeToe){ this.RKneeToe = rKneeToe;}

    public String getLKneeToe(){ return this.LKneeToe;}
    public void setLKneeToe(String lKneeToe){ this.LKneeToe = lKneeToe;}

    public String getThighHorizontal(){ return this.thighHorizontal;}
    public void setThighHorizontal(String thighHorizontal){ this.thighHorizontal = thighHorizontal;}

    public String getCrotch(){ return this.crotch;}
    public void setCrotch(String crotch){ this.crotch = crotch;}

    public String getShoulderGround(){ return this.shoulderGround;}
    public void setShoulderGround(String shoulderGround){ this.shoulderGround = shoulderGround;}
}
