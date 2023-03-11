package com.example.posebymlkit.database;

import java.util.ArrayList;

public class TrainMenu {
    String menuName;
    String pose1;  int time1;
    String pose2;  int time2;
    String pose3;  int time3;
    String pose4;  int time4;
    String pose5;  int time5;
    String pose6;  int time6;
    String pose7;  int time7;
    String pose8;  int time8;
    String pose9;  int time9;
    String pose10; int time10;
    String pose11; int time11;
    String pose12; int time12;
    String pose13; int time13;
    String pose14; int time14;
    String pose15; int time15;
    String pose16; int time16;
    String pose17; int time17;
    String pose18; int time18;
    String pose19; int time19;
    String pose20; int time20;

    public TrainMenu(){}
    public TrainMenu(
            String poseName,
            String pose1, int time1,
            String pose2, int time2,
            String pose3, int time3,
            String pose4, int time4,
            String pose5, int time5,
            String pose6, int time6,
            String pose7, int time7,
            String pose8, int time8,
            String pose9, int time9,
            String pose10, int time10,
            String pose11, int time11,
            String pose12, int time12,
            String pose13, int time13,
            String pose14, int time14,
            String pose15, int time15,
            String pose16, int time16,
            String pose17, int time17,
            String pose18, int time18,
            String pose19, int time19,
            String pose20, int time20
    ){
        this.menuName = poseName;
        this.pose1 = pose1; this.time1 = time1;
        this.pose2 = pose2; this.time2 = time2;
        this.pose3 = pose3; this.time3 = time3;
        this.pose4 = pose4; this.time4 = time4;
        this.pose5 = pose5; this.time5 = time5;
        this.pose6 = pose6; this.time6 = time6;
        this.pose7 = pose7; this.time7 = time7;
        this.pose8 = pose8; this.time8 = time8;
        this.pose9 = pose9; this.time9 = time9;
        this.pose10 = pose10; this.time10 = time10;
        this.pose11 = pose11; this.time11 = time11;
        this.pose12 = pose12; this.time12 = time12;
        this.pose13 = pose13; this.time13 = time13;
        this.pose14 = pose14; this.time14 = time14;
        this.pose15 = pose15; this.time15 = time15;
        this.pose16 = pose16; this.time16 = time16;
        this.pose17 = pose17; this.time17 = time17;
        this.pose18 = pose18; this.time18 = time18;
        this.pose19 = pose19; this.time19 = time19;
        this.pose20 = pose20; this.time20 = time20;
    }

    public ArrayList<String> getAllPose(){
        ArrayList<String> poses = new ArrayList<String>();
        for (int i = 1;i <= 20; i++) {
            if (getPose(i) != null) {
                poses.add(getPose(i));
            } else break;
        }
        return poses;
    }

    public ArrayList<Integer> getAllTime(){
        ArrayList<Integer> times = new ArrayList<Integer>();
        for (int i = 1;i <= 20; i++) {
            if (getTime(i) != 0) {
                times.add(getTime(i));
            } else break;
        }
        return times;
    }

    public String getPose(int ind){
        String pose = "";
        switch (ind){
            case 1:
                pose = getPose1();
                break;
            case 2:
                pose = getPose2();
                break;
            case 3:
                pose = getPose3();
                break;
            case 4:
                pose = getPose4();
                break;
            case 5:
                pose = getPose5();
                break;
            case 6:
                pose = getPose6();
                break;
            case 7:
                pose = getPose7();
                break;
            case 8:
                pose = getPose8();
                break;
            case 9:
                pose = getPose9();
                break;
            case 10:
                pose = getPose10();
                break;
            case 11:
                pose = getPose11();
                break;
            case 12:
                pose = getPose12();
                break;
            case 13:
                pose = getPose13();
                break;
            case 14:
                pose = getPose14();
                break;
            case 15:
                pose = getPose15();
                break;
            case 16:
                pose = getPose16();
                break;
            case 17:
                pose = getPose17();
                break;
            case 18:
                pose = getPose18();
                break;
            case 19:
                pose = getPose19();
                break;
            case 20:
                pose = getPose20();
                break;
            default:
                pose = null;
        }
        return pose;
    }
    public int getTime(int ind){
        int time = 0;
        switch (ind){
            case 1:
                time = getTime1();
                break;
            case 2:
                time = getTime2();
                break;
            case 3:
                time = getTime3();
                break;
            case 4:
                time = getTime4();
                break;
            case 5:
                time = getTime5();
                break;
            case 6:
                time = getTime6();
                break;
            case 7:
                time = getTime7();
                break;
            case 8:
                time = getTime8();
                break;
            case 9:
                time = getTime9();
                break;
            case 10:
                time = getTime10();
                break;
            case 11:
                time = getTime11();
                break;
            case 12:
                time = getTime12();
                break;
            case 13:
                time = getTime13();
                break;
            case 14:
                time = getTime14();
                break;
            case 15:
                time = getTime15();
                break;
            case 16:
                time = getTime16();
                break;
            case 17:
                time = getTime17();
                break;
            case 18:
                time = getTime18();
                break;
            case 19:
                time = getTime19();
                break;
            case 20:
                time = getTime20();
                break;
            default:
        }
        return time;
    }
    public void setPose(int ind,String pose){
        switch (ind){
            case 1:
                setPose1(pose);
                break;
            case 2:
                setPose2(pose);
                break;
            case 3:
                setPose3(pose);
                break;
            case 4:
                setPose4(pose);
                break;
            case 5:
                setPose5(pose);
                break;
            case 6:
                setPose6(pose);
                break;
            case 7:
                setPose7(pose);
                break;
            case 8:
                setPose8(pose);
                break;
            case 9:
                setPose9(pose);
                break;
            case 10:
                setPose10(pose);
                break;
            case 11:
                setPose11(pose);
                break;
            case 12:
                setPose12(pose);
                break;
            case 13:
                setPose13(pose);
                break;
            case 14:
                setPose14(pose);
                break;
            case 15:
                setPose15(pose);
                break;
            case 16:
                setPose16(pose);
                break;
            case 17:
                setPose17(pose);
                break;
            case 18:
                setPose18(pose);
                break;
            case 19:
                setPose19(pose);
                break;
            case 20:
                setPose20(pose);
                break;
        }
    }
    public void setTime(int ind,int time){
        switch (ind){
            case 1:
                setTime1(time);
                break;
            case 2:
                setTime2(time);
                break;
            case 3:
                setTime3(time);
                break;
            case 4:
                setTime4(time);
                break;
            case 5:
                setTime5(time);
                break;
            case 6:
                setTime6(time);
                break;
            case 7:
                setTime7(time);
                break;
            case 8:
                setTime8(time);
                break;
            case 9:
                setTime9(time);
                break;
            case 10:
                setTime10(time);
                break;
            case 11:
                setTime11(time);
                break;
            case 12:
                setTime12(time);
                break;
            case 13:
                setTime13(time);
                break;
            case 14:
                setTime14(time);
                break;
            case 15:
                setTime15(time);
                break;
            case 16:
                setTime16(time);
                break;
            case 17:
                setTime17(time);
                break;
            case 18:
                setTime18(time);
                break;
            case 19:
                setTime19(time);
                break;
            case 20:
                setTime20(time);
                break;
        }
    }

    public void remove(int ind){
        for (int i = ind + 1; i <= 20; i++){
            setPose(i,getPose(i+1));
            setTime(i,getTime(i+1));
        }
    }

    public void show(){
        System.out.println(getMenuName());
        for (int i = 1; i <= 20; i++){
            System.out.print(i + " " + getPose(i) + " " + getTime(i) + "|");
        }
        System.out.println();
    }

    public String getMenuName(){ return this.menuName;}
    public void setMenuName(String menuName){ this.menuName = menuName;}

    public String getPose1(){ return this.pose1;}
    public void setPose1(String pose1){ this.pose1 = pose1;}
    public int getTime1(){ return this.time1;}
    public void setTime1(int time1){ this.time1 = time1;}

    public String getPose2(){ return this.pose2;}
    public void setPose2(String pose2){ this.pose2 = pose2;}
    public int getTime2(){ return this.time2;}
    public void setTime2(int time2){ this.time2 = time2;}

    public String getPose3(){ return this.pose3;}
    public void setPose3(String pose3){ this.pose3 = pose3;}
    public int getTime3(){ return this.time3;}
    public void setTime3(int time3){ this.time3 = time3;}

    public String getPose4(){ return this.pose4;}
    public void setPose4(String pose4){ this.pose4 = pose4;}
    public int getTime4(){ return this.time4;}
    public void setTime4(int time4){ this.time4 = time4;}

    public String getPose5(){ return this.pose5;}
    public void setPose5(String pose5){ this.pose5 = pose5;}
    public int getTime5(){ return this.time5;}
    public void setTime5(int time5){ this.time5 = time5;}

    public String getPose6(){ return this.pose6;}
    public void setPose6(String pose6){ this.pose6 = pose6;}
    public int getTime6(){ return this.time6;}
    public void setTime6(int time6){ this.time6 = time6;}

    public String getPose7(){ return this.pose7;}
    public void setPose7(String pose7){ this.pose7 = pose7;}
    public int getTime7(){ return this.time7;}
    public void setTime7(int time7){ this.time7 = time7;}

    public String getPose8(){ return this.pose8;}
    public void setPose8(String pose8){ this.pose8 = pose8;}
    public int getTime8(){ return this.time8;}
    public void setTime8(int time8){ this.time8 = time8;}

    public String getPose9(){ return this.pose9;}
    public void setPose9(String pose9){ this.pose9 = pose9;}
    public int getTime9(){ return this.time9;}
    public void setTime9(int time9){ this.time9 = time9;}

    public String getPose10(){ return this.pose10;}
    public void setPose10(String pose10){ this.pose10 = pose10;}
    public int getTime10(){ return this.time10;}
    public void setTime10(int time10){ this.time10 = time10;}

    public String getPose11(){ return this.pose11;}
    public void setPose11(String pose11){ this.pose11 = pose11;}
    public int getTime11(){ return this.time11;}
    public void setTime11(int time11){ this.time11 = time11;}

    public String getPose12(){ return this.pose12;}
    public void setPose12(String pose12){ this.pose12 = pose12;}
    public int getTime12(){ return this.time12;}
    public void setTime12(int time12){ this.time12 = time12;}

    public String getPose13(){ return this.pose13;}
    public void setPose13(String pose13){ this.pose13 = pose13;}
    public int getTime13(){ return this.time13;}
    public void setTime13(int time13){ this.time13 = time13;}

    public String getPose14(){ return this.pose14;}
    public void setPose14(String pose14){ this.pose14 = pose14;}
    public int getTime14(){ return this.time14;}
    public void setTime14(int time14){ this.time14 = time14;}

    public String getPose15(){ return this.pose15;}
    public void setPose15(String pose15){ this.pose15 = pose15;}
    public int getTime15(){ return this.time15;}
    public void setTime15(int time15){ this.time15 = time15;}

    public String getPose16(){ return this.pose16;}
    public void setPose16(String pose16){ this.pose16 = pose16;}
    public int getTime16(){ return this.time16;}
    public void setTime16(int time16){ this.time16 = time16;}

    public String getPose17(){ return this.pose17;}
    public void setPose17(String pose17){ this.pose17 = pose17;}
    public int getTime17(){ return this.time17;}
    public void setTime17(int time17){ this.time17 = time17;}

    public String getPose18(){ return this.pose18;}
    public void setPose18(String pose18){ this.pose18 = pose18;}
    public int getTime18(){ return this.time18;}
    public void setTime18(int time18){ this.time18 = time18;}

    public String getPose19(){ return this.pose19;}
    public void setPose19(String pose19){ this.pose19 = pose19;}
    public int getTime19(){ return this.time19;}
    public void setTime19(int time19){ this.time19 = time19;}

    public String getPose20(){ return this.pose20;}
    public void setPose20(String pose20){ this.pose20 = pose20;}
    public int getTime20(){ return this.time20;}
    public void setTime20(int time20){ this.time20 = time20;}

}
