package com.example.posebymlkit.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TrainMenuDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "trainMenu.db";
    private static final String TABLE_TRAIN_MENU = "trainMenu";
    private static final String KEY_MENU_NAME = "menuName";
    private static final String KEY_POSE1 = "pose1";
    private static final String KEY_POSE2 = "pose2";
    private static final String KEY_POSE3 = "pose3";
    private static final String KEY_POSE4 = "pose4";
    private static final String KEY_POSE5 = "pose5";
    private static final String KEY_POSE6 = "pose6";
    private static final String KEY_POSE7 = "pose7";
    private static final String KEY_POSE8 = "pose8";
    private static final String KEY_POSE9 = "pose9";
    private static final String KEY_POSE10 = "pose10";
    private static final String KEY_POSE11 = "pose11";
    private static final String KEY_POSE12 = "pose12";
    private static final String KEY_POSE13 = "pose13";
    private static final String KEY_POSE14 = "pose14";
    private static final String KEY_POSE15 = "pose15";
    private static final String KEY_POSE16 = "pose16";
    private static final String KEY_POSE17 = "pose17";
    private static final String KEY_POSE18 = "pose18";
    private static final String KEY_POSE19 = "pose19";
    private static final String KEY_POSE20 = "pose20";
    private static final String KEY_TIME1 = "time1";
    private static final String KEY_TIME2 = "time2";
    private static final String KEY_TIME3 = "time3";
    private static final String KEY_TIME4 = "time4";
    private static final String KEY_TIME5 = "time5";
    private static final String KEY_TIME6 = "time6";
    private static final String KEY_TIME7 = "time7";
    private static final String KEY_TIME8 = "time8";
    private static final String KEY_TIME9 = "time9";
    private static final String KEY_TIME10 = "time10";
    private static final String KEY_TIME11 = "time11";
    private static final String KEY_TIME12 = "time12";
    private static final String KEY_TIME13 = "time13";
    private static final String KEY_TIME14 = "time14";
    private static final String KEY_TIME15 = "time15";
    private static final String KEY_TIME16 = "time16";
    private static final String KEY_TIME17 = "time17";
    private static final String KEY_TIME18 = "time18";
    private static final String KEY_TIME19 = "time19";
    private static final String KEY_TIME20 = "time20";

    public TrainMenuDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_TRAIN_MENU + "("
                + KEY_MENU_NAME + " TEXT PRIMARY KEY,"
                + KEY_POSE1 + " TEXT," + KEY_TIME1 + " INTEGER,"
                + KEY_POSE2 + " TEXT," + KEY_TIME2 + " INTEGER,"
                + KEY_POSE3 + " TEXT," + KEY_TIME3 + " INTEGER,"
                + KEY_POSE4 + " TEXT," + KEY_TIME4 + " INTEGER,"
                + KEY_POSE5 + " TEXT," + KEY_TIME5 + " INTEGER,"
                + KEY_POSE6 + " TEXT," + KEY_TIME6 + " INTEGER,"
                + KEY_POSE7 + " TEXT," + KEY_TIME7 + " INTEGER,"
                + KEY_POSE8 + " TEXT," + KEY_TIME8 + " INTEGER,"
                + KEY_POSE9 + " TEXT," + KEY_TIME9 + " INTEGER,"
                + KEY_POSE10 + " TEXT," + KEY_TIME10 + " INTEGER,"
                + KEY_POSE11 + " TEXT," + KEY_TIME11 + " INTEGER,"
                + KEY_POSE12 + " TEXT," + KEY_TIME12 + " INTEGER,"
                + KEY_POSE13 + " TEXT," + KEY_TIME13 + " INTEGER,"
                + KEY_POSE14 + " TEXT," + KEY_TIME14 + " INTEGER,"
                + KEY_POSE15 + " TEXT," + KEY_TIME15 + " INTEGER,"
                + KEY_POSE16 + " TEXT," + KEY_TIME16 + " INTEGER,"
                + KEY_POSE17 + " TEXT," + KEY_TIME17 + " INTEGER,"
                + KEY_POSE18 + " TEXT," + KEY_TIME18 + " INTEGER,"
                + KEY_POSE19 + " TEXT," + KEY_TIME19 + " INTEGER,"
                + KEY_POSE20 + " TEXT," + KEY_TIME20 + " INTEGER "
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRAIN_MENU);
        onCreate(db);
    }

    public void trainMenuInit(){
        this.addTrainMenu(new TrainMenu
                (
                        "推薦清單",
                        "Boat",30,
                        "Star",30,
                        "Tree",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30
                )
        );
        this.addTrainMenu(new TrainMenu
                (
                        "推薦清單2",
                        "Boat",30,
                        "Star",30,
                        "Tree",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30,
                        "Boat",30
                )
        );
    }

    // code to add the new contact
    public void addTrainMenu(TrainMenu trainMenu) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MENU_NAME, trainMenu.getMenuName()); // Contact Name
        values.put(KEY_POSE1, trainMenu.getPose1() ); values.put(KEY_TIME1, trainMenu.getTime1() );
        values.put(KEY_POSE2, trainMenu.getPose2() ); values.put(KEY_TIME2, trainMenu.getTime2() );
        values.put(KEY_POSE3, trainMenu.getPose3() ); values.put(KEY_TIME3, trainMenu.getTime3() );
        values.put(KEY_POSE4, trainMenu.getPose4() ); values.put(KEY_TIME4, trainMenu.getTime4() );
        values.put(KEY_POSE5, trainMenu.getPose5() ); values.put(KEY_TIME5, trainMenu.getTime5() );
        values.put(KEY_POSE6, trainMenu.getPose6() ); values.put(KEY_TIME6, trainMenu.getTime6() );
        values.put(KEY_POSE7, trainMenu.getPose7() ); values.put(KEY_TIME7, trainMenu.getTime7() );
        values.put(KEY_POSE8, trainMenu.getPose8() ); values.put(KEY_TIME8, trainMenu.getTime8() );
        values.put(KEY_POSE9, trainMenu.getPose9() ); values.put(KEY_TIME9, trainMenu.getTime9() );
        values.put(KEY_POSE10, trainMenu.getPose10() ); values.put(KEY_TIME10, trainMenu.getTime10() );
        values.put(KEY_POSE11, trainMenu.getPose11() ); values.put(KEY_TIME11, trainMenu.getTime11() );
        values.put(KEY_POSE12, trainMenu.getPose12() ); values.put(KEY_TIME12, trainMenu.getTime12() );
        values.put(KEY_POSE13, trainMenu.getPose13() ); values.put(KEY_TIME13, trainMenu.getTime13() );
        values.put(KEY_POSE14, trainMenu.getPose14() ); values.put(KEY_TIME14, trainMenu.getTime14() );
        values.put(KEY_POSE15, trainMenu.getPose15() ); values.put(KEY_TIME15, trainMenu.getTime15() );
        values.put(KEY_POSE16, trainMenu.getPose16() ); values.put(KEY_TIME16, trainMenu.getTime16() );
        values.put(KEY_POSE17, trainMenu.getPose17() ); values.put(KEY_TIME17, trainMenu.getTime17() );
        values.put(KEY_POSE18, trainMenu.getPose18() ); values.put(KEY_TIME18, trainMenu.getTime18() );
        values.put(KEY_POSE19, trainMenu.getPose19() ); values.put(KEY_TIME19, trainMenu.getTime19() );
        values.put(KEY_POSE20, trainMenu.getPose20() ); values.put(KEY_TIME20, trainMenu.getTime20() );

        // Inserting Row
        db.insert(TABLE_TRAIN_MENU, null, values);
        db.close();
    }

    // code to get the single contact
    public TrainMenu getMenu(String menuName) {
        String selectQuery = "SELECT * FROM " + TABLE_TRAIN_MENU + " WHERE " + KEY_MENU_NAME + " = '" + menuName + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        TrainMenu trainMenu = new TrainMenu();
        if (cursor.moveToFirst()) {
            do {
                trainMenu.setMenuName(cursor.getString(0));
                trainMenu.setPose1(cursor.getString(1)); trainMenu.setTime1(Integer.parseInt(cursor.getString(2)));
                trainMenu.setPose2(cursor.getString(3)); trainMenu.setTime2(Integer.parseInt(cursor.getString(4)));
                trainMenu.setPose3(cursor.getString(5)); trainMenu.setTime3(Integer.parseInt(cursor.getString(6)));
                trainMenu.setPose4(cursor.getString(7)); trainMenu.setTime4(Integer.parseInt(cursor.getString(8)));
                trainMenu.setPose5(cursor.getString(9)); trainMenu.setTime5(Integer.parseInt(cursor.getString(10)));
                trainMenu.setPose6(cursor.getString(11)); trainMenu.setTime6(Integer.parseInt(cursor.getString(12)));
                trainMenu.setPose7(cursor.getString(13)); trainMenu.setTime7(Integer.parseInt(cursor.getString(14)));
                trainMenu.setPose8(cursor.getString(15)); trainMenu.setTime8(Integer.parseInt(cursor.getString(16)));
                trainMenu.setPose9(cursor.getString(17)); trainMenu.setTime9(Integer.parseInt(cursor.getString(18)));
                trainMenu.setPose10(cursor.getString(19)); trainMenu.setTime10(Integer.parseInt(cursor.getString(20)));
                trainMenu.setPose11(cursor.getString(21)); trainMenu.setTime11(Integer.parseInt(cursor.getString(22)));
                trainMenu.setPose12(cursor.getString(23)); trainMenu.setTime12(Integer.parseInt(cursor.getString(24)));
                trainMenu.setPose13(cursor.getString(25)); trainMenu.setTime13(Integer.parseInt(cursor.getString(26)));
                trainMenu.setPose14(cursor.getString(27)); trainMenu.setTime14(Integer.parseInt(cursor.getString(28)));
                trainMenu.setPose15(cursor.getString(29)); trainMenu.setTime15(Integer.parseInt(cursor.getString(30)));
                trainMenu.setPose16(cursor.getString(31)); trainMenu.setTime16(Integer.parseInt(cursor.getString(32)));
                trainMenu.setPose17(cursor.getString(33)); trainMenu.setTime17(Integer.parseInt(cursor.getString(34)));
                trainMenu.setPose18(cursor.getString(35)); trainMenu.setTime18(Integer.parseInt(cursor.getString(36)));
                trainMenu.setPose19(cursor.getString(37)); trainMenu.setTime19(Integer.parseInt(cursor.getString(38)));
                trainMenu.setPose20(cursor.getString(39)); trainMenu.setTime20(Integer.parseInt(cursor.getString(40)));

                // Adding contact to list
            } while (cursor.moveToNext());
        }
        return trainMenu;
    }

    // code to get all contacts in a list view
    public List<TrainMenu> getAllTrainMenu() {
        List<TrainMenu> trainMenus = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_TRAIN_MENU;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                TrainMenu trainMenu = new TrainMenu();
                trainMenu.setMenuName(cursor.getString(0));
                trainMenu.setPose1(cursor.getString(1));trainMenu.setTime1(cursor.getInt(2));
                trainMenu.setPose2(cursor.getString(3));trainMenu.setTime2(cursor.getInt(4));
                trainMenu.setPose3(cursor.getString(5));trainMenu.setTime3(cursor.getInt(6));
                trainMenu.setPose4(cursor.getString(7));trainMenu.setTime4(cursor.getInt(8));
                trainMenu.setPose5(cursor.getString(9));trainMenu.setTime5(cursor.getInt(10));
                trainMenu.setPose6(cursor.getString(11));trainMenu.setTime6(cursor.getInt(12));
                trainMenu.setPose7(cursor.getString(13));trainMenu.setTime7(cursor.getInt(14));
                trainMenu.setPose8(cursor.getString(15));trainMenu.setTime8(cursor.getInt(16));
                trainMenu.setPose9(cursor.getString(17));trainMenu.setTime9(cursor.getInt(18));
                trainMenu.setPose10(cursor.getString(19));trainMenu.setTime10(cursor.getInt(20));
                trainMenu.setPose11(cursor.getString(21));trainMenu.setTime11(cursor.getInt(22));
                trainMenu.setPose12(cursor.getString(23));trainMenu.setTime12(cursor.getInt(24));
                trainMenu.setPose13(cursor.getString(25));trainMenu.setTime13(cursor.getInt(26));
                trainMenu.setPose14(cursor.getString(27));trainMenu.setTime14(cursor.getInt(28));
                trainMenu.setPose15(cursor.getString(29));trainMenu.setTime15(cursor.getInt(30));
                trainMenu.setPose16(cursor.getString(31));trainMenu.setTime16(cursor.getInt(32));
                trainMenu.setPose17(cursor.getString(33));trainMenu.setTime17(cursor.getInt(34));
                trainMenu.setPose18(cursor.getString(35));trainMenu.setTime18(cursor.getInt(36));
                trainMenu.setPose19(cursor.getString(37));trainMenu.setTime19(cursor.getInt(38));
                trainMenu.setPose20(cursor.getString(39));trainMenu.setTime20(cursor.getInt(40));
                trainMenus.add(trainMenu);
            } while (cursor.moveToNext());
        }
        return trainMenus;
    }

    // code to update the single contact
    public int updateTrainMenu(TrainMenu trainMenu) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MENU_NAME, trainMenu.getMenuName()); // Contact Name
        values.put(KEY_POSE1, trainMenu.getPose1() ); values.put(KEY_TIME1, trainMenu.getTime1() );
        values.put(KEY_POSE2, trainMenu.getPose2() ); values.put(KEY_TIME2, trainMenu.getTime2() );
        values.put(KEY_POSE3, trainMenu.getPose3() ); values.put(KEY_TIME3, trainMenu.getTime3() );
        values.put(KEY_POSE4, trainMenu.getPose4() ); values.put(KEY_TIME4, trainMenu.getTime4() );
        values.put(KEY_POSE5, trainMenu.getPose5() ); values.put(KEY_TIME5, trainMenu.getTime5() );
        values.put(KEY_POSE6, trainMenu.getPose6() ); values.put(KEY_TIME6, trainMenu.getTime6() );
        values.put(KEY_POSE7, trainMenu.getPose7() ); values.put(KEY_TIME7, trainMenu.getTime7() );
        values.put(KEY_POSE8, trainMenu.getPose8() ); values.put(KEY_TIME8, trainMenu.getTime8() );
        values.put(KEY_POSE9, trainMenu.getPose9() ); values.put(KEY_TIME9, trainMenu.getTime9() );
        values.put(KEY_POSE10, trainMenu.getPose10() ); values.put(KEY_TIME10, trainMenu.getTime10() );
        values.put(KEY_POSE11, trainMenu.getPose11() ); values.put(KEY_TIME11, trainMenu.getTime11() );
        values.put(KEY_POSE12, trainMenu.getPose12() ); values.put(KEY_TIME12, trainMenu.getTime12() );
        values.put(KEY_POSE13, trainMenu.getPose13() ); values.put(KEY_TIME13, trainMenu.getTime13() );
        values.put(KEY_POSE14, trainMenu.getPose14() ); values.put(KEY_TIME14, trainMenu.getTime14() );
        values.put(KEY_POSE15, trainMenu.getPose15() ); values.put(KEY_TIME15, trainMenu.getTime15() );
        values.put(KEY_POSE16, trainMenu.getPose16() ); values.put(KEY_TIME16, trainMenu.getTime16() );
        values.put(KEY_POSE17, trainMenu.getPose17() ); values.put(KEY_TIME17, trainMenu.getTime17() );
        values.put(KEY_POSE18, trainMenu.getPose18() ); values.put(KEY_TIME18, trainMenu.getTime18() );
        values.put(KEY_POSE19, trainMenu.getPose19() ); values.put(KEY_TIME19, trainMenu.getTime19() );
        values.put(KEY_POSE20, trainMenu.getPose20() ); values.put(KEY_TIME20, trainMenu.getTime20() );

        // updating row
        return db.update(TABLE_TRAIN_MENU, values, KEY_MENU_NAME + " = ?",
                new String[] { String.valueOf(trainMenu.getMenuName()) });
    }

    // Deleting single contact
    public void deleteTrainMenu(TrainMenu trainMenu) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRAIN_MENU, KEY_MENU_NAME + " = ?",
                new String[] { String.valueOf(trainMenu.getMenuName()) });
        db.close();
    }

    // Getting contacts Count
    public int getTrainMenuCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TRAIN_MENU;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}