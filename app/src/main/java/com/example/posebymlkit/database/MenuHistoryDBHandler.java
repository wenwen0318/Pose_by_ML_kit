package com.example.posebymlkit.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class MenuHistoryDBHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "menuHistory.db";
    private static final String TABLE_MENU_HISTORY = "menuHistory";
    private static final String KEY_MENU_NAME = "menuName";
    private static final String KEY_MENU_DATE = "menuDate";
    private static final String KEY_POSE_DATE_1 = "poseDate1";
    private static final String KEY_POSE_DATE_2 = "poseDate2";
    private static final String KEY_POSE_DATE_3 = "poseDate3";
    private static final String KEY_POSE_DATE_4 = "poseDate4";
    private static final String KEY_POSE_DATE_5 = "poseDate5";
    private static final String KEY_POSE_DATE_6 = "poseDate6";
    private static final String KEY_POSE_DATE_7 = "poseDate7";
    private static final String KEY_POSE_DATE_8 = "poseDate8";
    private static final String KEY_POSE_DATE_9 = "poseDate9";
    private static final String KEY_POSE_DATE_10 = "poseDate10";
    private static final String KEY_POSE_DATE_11 = "poseDate11";
    private static final String KEY_POSE_DATE_12 = "poseDate12";
    private static final String KEY_POSE_DATE_13 = "poseDate13";
    private static final String KEY_POSE_DATE_14 = "poseDate14";
    private static final String KEY_POSE_DATE_15 = "poseDate15";
    private static final String KEY_POSE_DATE_16 = "poseDate16";
    private static final String KEY_POSE_DATE_17 = "poseDate17";
    private static final String KEY_POSE_DATE_18 = "poseDate18";
    private static final String KEY_POSE_DATE_19 = "poseDate19";
    private static final String KEY_POSE_DATE_20 = "poseDate20";

    public MenuHistoryDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_HISTORICAL_RECORD_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_MENU_HISTORY + "("
                + KEY_MENU_NAME + " TEXT,"
                + KEY_MENU_DATE + " TEXT PRIMARY KEY,"
                + KEY_POSE_DATE_1 + " INTEGER,"
                + KEY_POSE_DATE_2 + " INTEGER,"
                + KEY_POSE_DATE_3 + " TEXT,"
                + KEY_POSE_DATE_4 + " TEXT,"
                + KEY_POSE_DATE_5 + " TEXT,"
                + KEY_POSE_DATE_6 + " TEXT,"
                + KEY_POSE_DATE_7 + " TEXT,"
                + KEY_POSE_DATE_8 + " TEXT,"
                + KEY_POSE_DATE_9 + " TEXT,"
                + KEY_POSE_DATE_10 + " TEXT,"
                + KEY_POSE_DATE_11 + " TEXT,"
                + KEY_POSE_DATE_12 + " TEXT,"
                + KEY_POSE_DATE_13 + " TEXT,"
                + KEY_POSE_DATE_14 + " TEXT,"
                + KEY_POSE_DATE_15 + " TEXT,"
                + KEY_POSE_DATE_16 + " TEXT,"
                + KEY_POSE_DATE_17 + " TEXT,"
                + KEY_POSE_DATE_18 + " TEXT,"
                + KEY_POSE_DATE_19 + " TEXT,"
                + KEY_POSE_DATE_20 + " TEXT"
                + ")";
        db.execSQL(CREATE_HISTORICAL_RECORD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU_HISTORY);
        onCreate(db);
    }

    public void addMenuHistory(MenuHistory menuHistory) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MENU_NAME, menuHistory.getMenuName());
        values.put(KEY_MENU_DATE, menuHistory.getMenuDate());
        values.put(KEY_POSE_DATE_1, menuHistory.getPoseDate1());
        values.put(KEY_POSE_DATE_2, menuHistory.getPoseDate2());
        values.put(KEY_POSE_DATE_3, menuHistory.getPoseDate3());
        values.put(KEY_POSE_DATE_4, menuHistory.getPoseDate4());
        values.put(KEY_POSE_DATE_5, menuHistory.getPoseDate5());
        values.put(KEY_POSE_DATE_6, menuHistory.getPoseDate6());
        values.put(KEY_POSE_DATE_7, menuHistory.getPoseDate7());
        values.put(KEY_POSE_DATE_8, menuHistory.getPoseDate8());
        values.put(KEY_POSE_DATE_9, menuHistory.getPoseDate9());
        values.put(KEY_POSE_DATE_10, menuHistory.getPoseDate10());
        values.put(KEY_POSE_DATE_11, menuHistory.getPoseDate11());
        values.put(KEY_POSE_DATE_12, menuHistory.getPoseDate12());
        values.put(KEY_POSE_DATE_13, menuHistory.getPoseDate13());
        values.put(KEY_POSE_DATE_14, menuHistory.getPoseDate14());
        values.put(KEY_POSE_DATE_15, menuHistory.getPoseDate15());
        values.put(KEY_POSE_DATE_16, menuHistory.getPoseDate16());
        values.put(KEY_POSE_DATE_17, menuHistory.getPoseDate17());
        values.put(KEY_POSE_DATE_18, menuHistory.getPoseDate18());
        values.put(KEY_POSE_DATE_19, menuHistory.getPoseDate19());
        values.put(KEY_POSE_DATE_20, menuHistory.getPoseDate20());

        // Inserting Row
        db.insert(TABLE_MENU_HISTORY, null, values);
        db.close();
    }

    public List<MenuHistory> getAllMenuHistory() {
        List<MenuHistory> menuHistories = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MENU_HISTORY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MenuHistory menuHistory = new MenuHistory();
                menuHistory.setMenuName(cursor.getString(0));
                menuHistory.setMenuDate(cursor.getString(1));
                menuHistory.setPoseDate1(cursor.getString(2));
                menuHistory.setPoseDate2(cursor.getString(3));
                menuHistory.setPoseDate3(cursor.getString(4));
                menuHistory.setPoseDate4(cursor.getString(5));
                menuHistory.setPoseDate5(cursor.getString(6));
                menuHistory.setPoseDate6(cursor.getString(7));
                menuHistory.setPoseDate7(cursor.getString(8));
                menuHistory.setPoseDate8(cursor.getString(9));
                menuHistory.setPoseDate9(cursor.getString(10));
                menuHistory.setPoseDate10(cursor.getString(11));
                menuHistory.setPoseDate11(cursor.getString(12));
                menuHistory.setPoseDate12(cursor.getString(13));
                menuHistory.setPoseDate13(cursor.getString(14));
                menuHistory.setPoseDate14(cursor.getString(15));
                menuHistory.setPoseDate15(cursor.getString(16));
                menuHistory.setPoseDate16(cursor.getString(17));
                menuHistory.setPoseDate17(cursor.getString(18));
                menuHistory.setPoseDate18(cursor.getString(19));
                menuHistory.setPoseDate19(cursor.getString(20));
                menuHistory.setPoseDate20(cursor.getString(21));
                menuHistories.add(menuHistory);

            } while (cursor.moveToNext());
        }
        return menuHistories;
    }

//    public List<MenuHistory> getMenuHistoryByPoseName(String poseName,int limit) {
//        List<MenuHistory> menuHistory = new ArrayList<>();
//        // Select All Query
//        String selectQuery =
//                " SELECT  * FROM " + TABLE_MENU_HISTORY +
//                        " WHERE " + KEY_POSE_NAME + " = '" + poseName +
//                        "' ORDER BY "+ KEY_DATE +
//                        " DESC LIMIT " + limit;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                MenuHistory historicalRecord = new MenuHistory();
//                historicalRecord.setPoseName(cursor.getString(0));
//                historicalRecord.setDate(cursor.getString(1));
//                historicalRecord.setLevel(Integer.parseInt(cursor.getString(2)));
//                historicalRecord.setAllComplete(Float.parseFloat(cursor.getString(3)));
//                historicalRecord.setRHip(cursor.getString(4));
//                historicalRecord.setLHip(cursor.getString(5));
//                historicalRecord.setRKnee(cursor.getString(6));
//                historicalRecord.setLKnee(cursor.getString(7));
//                historicalRecord.setRElbow(cursor.getString(8));
//                historicalRecord.setLElbow(cursor.getString(9));
//                historicalRecord.setRArmpit(cursor.getString(10));
//                historicalRecord.setLArmpit(cursor.getString(11));
//                historicalRecord.setRShoulder(cursor.getString(12));
//                historicalRecord.setLShoulder(cursor.getString(13));
//                historicalRecord.setRKneeToe(cursor.getString(14));
//                historicalRecord.setLKneeToe(cursor.getString(15));
//                historicalRecord.setRThighHorizontal(cursor.getString(16));
//                historicalRecord.setLThighHorizontal(cursor.getString(17));
//                historicalRecord.setRCrotch(cursor.getString(18));
//                historicalRecord.setLCrotch(cursor.getString(19));
//                historicalRecord.setRShoulderGround(cursor.getString(20));
//                historicalRecord.setLShoulderGround(cursor.getString(21));
//                historicalRecord.setRElbowRaise(cursor.getString(22));
//                historicalRecord.setLElbowRaise(cursor.getString(23));
//                historicalRecord.setRHeelOnGround(cursor.getString(24));
//                historicalRecord.setLHeelOnGround(cursor.getString(25));
//                historicalRecord.setBodyVertical(cursor.getString(26));
//                // Adding contact to list
//                historicalRecords.add(historicalRecord);
//            } while (cursor.moveToNext());
//        }
//
//        // return contact list
//        return historicalRecords;
//    }





//    public void deleteHistoricalRecord(HistoricalRecord historicalRecord) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_HISTORICAL_RECORD, KEY_ID + " = ?",
//                new String[] { String.valueOf(historicalRecord.getId()) });
//        db.close();
//    }


}
