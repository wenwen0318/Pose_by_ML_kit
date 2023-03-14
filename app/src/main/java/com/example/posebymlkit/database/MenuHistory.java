package com.example.posebymlkit.database;

public class MenuHistory {
    String menuName;
    String menuDate;
    String poseDate1;   String poseDate2;
    String poseDate3;   String poseDate4;
    String poseDate5;   String poseDate6;
    String poseDate7;   String poseDate8;
    String poseDate9;   String poseDate10;
    String poseDate11;  String poseDate12;
    String poseDate13;  String poseDate14;
    String poseDate15;  String poseDate16;
    String poseDate17;  String poseDate18;
    String poseDate19;  String poseDate20;

    public MenuHistory(){}
    public MenuHistory(
            String menuName,
            String menuDate,
            String poseDate1,   String poseDate2,
            String poseDate3,   String poseDate4,
            String poseDate5,   String poseDate6,
            String poseDate7,   String poseDate8,
            String poseDate9,   String poseDate10,
            String poseDate11,  String poseDate12,
            String poseDate13,  String poseDate14,
            String poseDate15,  String poseDate16,
            String poseDate17,  String poseDate18,
            String poseDate19,  String poseDate20
    ){
        this.menuName = menuName;
        this.menuDate = menuDate;
        this.poseDate1 = poseDate1; this.poseDate2 = poseDate2;
        this.poseDate3 = poseDate3; this.poseDate4 = poseDate4;
        this.poseDate5 = poseDate5; this.poseDate6 = poseDate6;
        this.poseDate7 = poseDate7; this.poseDate8 = poseDate8;
        this.poseDate9 = poseDate9; this.poseDate10 = poseDate10;
        this.poseDate11 = poseDate11; this.poseDate12 = poseDate12;
        this.poseDate13 = poseDate13; this.poseDate14 = poseDate14;
        this.poseDate15 = poseDate15; this.poseDate16 = poseDate16;
        this.poseDate17 = poseDate17; this.poseDate18 = poseDate18;
        this.poseDate19 = poseDate19; this.poseDate20 = poseDate20;

    }

    public String get(int i){
        String item;
        switch (i){
            case 1:
                item = this.menuName;
                break;
            case 2:
                item = this.menuDate;
                break;
            case 3:
                item = this.poseDate1;
                break;
            case 4:
                item = this.poseDate2;
                break;
            case 5:
                item = this.poseDate3;
                break;
            case 6:
                item = this.poseDate4;
                break;
            case 7:
                item = this.poseDate5;
                break;
            case 8:
                item = this.poseDate6;
                break;
            case 9:
                item = this.poseDate7;
                break;
            case 10:
                item = this.poseDate8;
                break;
            case 11:
                item = this.poseDate9;
                break;
            case 12:
                item = this.poseDate10;
                break;
            case 13:
                item = this.poseDate11;
                break;
            case 14:
                item = this.poseDate12;
                break;
            case 15:
                item = this.poseDate13;
                break;
            case 16:
                item = this.poseDate14;
                break;
            case 17:
                item = this.poseDate15;
                break;
            case 18:
                item = this.poseDate16;
                break;
            case 19:
                item = this.poseDate17;
                break;
            case 20:
                item = this.poseDate18;
                break;
            case 21:
                item = this.poseDate19;
                break;
            case 22:
                item = this.poseDate20;
                break;
            default:
                item = "null";
        }
        return item;
    }

    public void set(int index, String data){
        switch (index){
            case 1:
                setMenuName(data);
                break;
            case 2:
                setMenuDate(data);
                break;
            case 3:
                setPoseDate1(data);
                break;
            case 4:
                setPoseDate2(data);
                break;
            case 5:
                setPoseDate3(data);
                break;
            case 6:
                setPoseDate4(data);
                break;
            case 7:
                setPoseDate5(data);
                break;
            case 8:
                setPoseDate6(data);
                break;
            case 9:
                setPoseDate7(data);
                break;
            case 10:
                setPoseDate8(data);
                break;
            case 11:
                setPoseDate9(data);
                break;
            case 12:
                setPoseDate10(data);
                break;
            case 13:
                setPoseDate11(data);
                break;
            case 14:
                setPoseDate12(data);
                break;
            case 15:
                setPoseDate13(data);
                break;
            case 16:
                setPoseDate14(data);
                break;
            case 17:
                setPoseDate15(data);
                break;
            case 18:
                setPoseDate16(data);
                break;
            case 19:
                setPoseDate17(data);
                break;
            case 20:
                setPoseDate18(data);
                break;
            case 21:
                setPoseDate19(data);
                break;
            case 22:
                setPoseDate20(data);
                break;
            default:

        }

    }

    public String getMenuName(){ return this.menuName;}
    public void setMenuName(String menuName){ this.menuName = menuName;}

    public String getMenuDate(){ return this.menuDate;}
    public void setMenuDate(String menuDate){ this.menuDate = menuDate;}

    public String getPoseDate1(){ return this.poseDate1;}
    public void setPoseDate1(String poseDate1){ this.poseDate1 = poseDate1;}

    public String getPoseDate2(){ return this.poseDate2;}
    public void setPoseDate2(String poseDate2){ this.poseDate2 = poseDate2;}

    public String getPoseDate3(){ return this.poseDate3;}
    public void setPoseDate3(String poseDate3){ this.poseDate3 = poseDate3;}

    public String getPoseDate4(){ return this.poseDate4;}
    public void setPoseDate4(String poseDate4){ this.poseDate4 = poseDate4;}

    public String getPoseDate5(){ return this.poseDate5;}
    public void setPoseDate5(String poseDate5){ this.poseDate5 = poseDate5;}

    public String getPoseDate6(){ return this.poseDate6;}
    public void setPoseDate6(String poseDate6){ this.poseDate6 = poseDate6;}

    public String getPoseDate7(){ return this.poseDate7;}
    public void setPoseDate7(String poseDate7){ this.poseDate7 = poseDate7;}

    public String getPoseDate8(){ return this.poseDate8;}
    public void setPoseDate8(String poseDate8){ this.poseDate8 = poseDate8;}

    public String getPoseDate9(){ return this.poseDate9;}
    public void setPoseDate9(String poseDate9){ this.poseDate9 = poseDate9;}

    public String getPoseDate10(){ return this.poseDate10;}
    public void setPoseDate10(String poseDate10){ this.poseDate10 = poseDate10;}

    public String getPoseDate11(){ return this.poseDate11;}
    public void setPoseDate11(String poseDate11){ this.poseDate11 = poseDate11;}

    public String getPoseDate12(){ return this.poseDate12;}
    public void setPoseDate12(String poseDate12){ this.poseDate12 = poseDate12;}

    public String getPoseDate13(){ return this.poseDate13;}
    public void setPoseDate13(String poseDate13){ this.poseDate13 = poseDate13;}

    public String getPoseDate14(){ return this.poseDate14;}
    public void setPoseDate14(String poseDate14){ this.poseDate14 = poseDate14;}

    public String getPoseDate15(){ return this.poseDate15;}
    public void setPoseDate15(String poseDate15){ this.poseDate15 = poseDate15;}

    public String getPoseDate16(){ return this.poseDate16;}
    public void setPoseDate16(String poseDate16){ this.poseDate16 = poseDate16;}

    public String getPoseDate17(){ return this.poseDate17;}
    public void setPoseDate17(String poseDate17){ this.poseDate17 = poseDate17;}

    public String getPoseDate18(){ return this.poseDate18;}
    public void setPoseDate18(String poseDate18){ this.poseDate18 = poseDate18;}

    public String getPoseDate19(){ return this.poseDate19;}
    public void setPoseDate19(String poseDate19){ this.poseDate19 = poseDate19;}

    public String getPoseDate20(){ return this.poseDate20;}
    public void setPoseDate20(String poseDate20){ this.poseDate20 = poseDate20;}
}
