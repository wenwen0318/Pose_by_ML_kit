package com.example.posebymlkit.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class HistoricalRecordDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "historicalRecord.db";
    private static final String TABLE_HISTORICAL_RECORD = "historicalRecord";
    private static final String KEY_POSE_NAME = "poseName";
    private static final String KEY_DATE = "date";
    private static final String KEY_LEVEL = "level";
    private static final String KEY_ALL_COMPLETE = "allComplete";
    private static final String KEY_RIGHT_HIP = "RHip";
    private static final String KEY_LEFT_HIP = "LHip";
    private static final String KEY_RIGHT_KNEE = "RKnee";
    private static final String KEY_LEFT_KNEE = "LKnee";
    private static final String KEY_RIGHT_ELBOW = "RElbow";
    private static final String KEY_LEFT_ELBOW = "LElbow";
    private static final String KEY_RIGHT_ARMPIT = "RArmpit";
    private static final String KEY_LEFT_ARMPIT = "LArmpit";
    private static final String KEY_RIGHT_SHOULDER = "RShoulder";
    private static final String KEY_LEFT_SHOULDER = "LShoulder";
    private static final String KEY_RIGHT_KNEE_TOE = "RKneeToe";
    private static final String KEY_LEFT_KNEE_TOE = "LKneeToe";
    private static final String KEY_RIGHT_THIGH_HORIZONTAL = "RThighHorizontal";
    private static final String KEY_LEFT_THIGH_HORIZONTAL = "LThighHorizontal";
    private static final String KEY_RIGHT_CROTCH = "RCrotch";
    private static final String KEY_LEFT_CROTCH = "LCrotch";
    private static final String KEY_RIGHT_SHOULDER_GROUND = "RShoulderGround";
    private static final String KEY_LEFT_SHOULDER_GROUND = "LShoulderGround";
    private static final String KEY_RIGHT_ELBOW_RAISE = "RElbowRaise";
    private static final String KEY_LEFT_ELBOW_RAISE = "LElbowRaise";
    private static final String KEY_RIGHT_HEEL_ON_GROUND = "RHeelOnGround";
    private static final String KEY_LEFT_HEEL_ON_GROUND = "LHeelOnGround";
    private static final String KEY_BODY_VERTICAL = "bodyVertical";

    public HistoricalRecordDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_HISTORICAL_RECORD_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_HISTORICAL_RECORD + "("
                + KEY_POSE_NAME + " TEXT,"
                + KEY_DATE + " TEXT PRIMARY KEY,"
                + KEY_LEVEL + " INTEGER,"
                + KEY_ALL_COMPLETE + " INTEGER,"
                + KEY_RIGHT_HIP + " TEXT,"
                + KEY_LEFT_HIP + " TEXT,"
                + KEY_RIGHT_KNEE + " TEXT,"
                + KEY_LEFT_KNEE + " TEXT,"
                + KEY_RIGHT_ELBOW + " TEXT,"
                + KEY_LEFT_ELBOW + " TEXT,"
                + KEY_RIGHT_ARMPIT + " TEXT,"
                + KEY_LEFT_ARMPIT + " TEXT,"
                + KEY_RIGHT_SHOULDER + " TEXT,"
                + KEY_LEFT_SHOULDER + " TEXT,"
                + KEY_RIGHT_KNEE_TOE + " TEXT,"
                + KEY_LEFT_KNEE_TOE + " TEXT,"
                + KEY_RIGHT_THIGH_HORIZONTAL + " TEXT,"
                + KEY_LEFT_THIGH_HORIZONTAL + " TEXT,"
                + KEY_RIGHT_CROTCH + " TEXT,"
                + KEY_LEFT_CROTCH + " TEXT,"
                + KEY_RIGHT_SHOULDER_GROUND + " TEXT,"
                + KEY_LEFT_SHOULDER_GROUND + " TEXT,"
                + KEY_RIGHT_ELBOW_RAISE + " TEXT,"
                + KEY_LEFT_ELBOW_RAISE + " TEXT,"
                + KEY_RIGHT_HEEL_ON_GROUND + " TEXT,"
                + KEY_LEFT_HEEL_ON_GROUND + " TEXT,"
                + KEY_BODY_VERTICAL + " TEXT"
                + ")";
        db.execSQL(CREATE_HISTORICAL_RECORD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORICAL_RECORD);
        onCreate(db);
    }

    public void addHistoricalRecord(HistoricalRecord historicalRecord) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_POSE_NAME, historicalRecord.getPoseName());
        values.put(KEY_DATE, historicalRecord.getDate());
        values.put(KEY_LEVEL, historicalRecord.getLevel());
        values.put(KEY_ALL_COMPLETE, historicalRecord.getAllComplete());
        values.put(KEY_RIGHT_HIP, historicalRecord.getRHip());
        values.put(KEY_LEFT_HIP, historicalRecord.getLHip());
        values.put(KEY_RIGHT_KNEE, historicalRecord.getRKnee());
        values.put(KEY_LEFT_KNEE, historicalRecord.getLKnee());
        values.put(KEY_RIGHT_ELBOW, historicalRecord.getRElbow());
        values.put(KEY_LEFT_ELBOW, historicalRecord.getLElbow());
        values.put(KEY_RIGHT_ARMPIT, historicalRecord.getRArmpit());
        values.put(KEY_LEFT_ARMPIT, historicalRecord.getLArmpit());
        values.put(KEY_RIGHT_SHOULDER, historicalRecord.getRShoulder());
        values.put(KEY_LEFT_SHOULDER, historicalRecord.getLShoulder());
        values.put(KEY_RIGHT_KNEE_TOE, historicalRecord.getRKneeToe());
        values.put(KEY_LEFT_KNEE_TOE, historicalRecord.getLKneeToe());
        values.put(KEY_RIGHT_THIGH_HORIZONTAL, historicalRecord.getRThighHorizontal());
        values.put(KEY_LEFT_THIGH_HORIZONTAL, historicalRecord.getLThighHorizontal());
        values.put(KEY_RIGHT_CROTCH, historicalRecord.getRCrotch());
        values.put(KEY_LEFT_CROTCH, historicalRecord.getLCrotch());
        values.put(KEY_RIGHT_SHOULDER_GROUND, historicalRecord.getRShoulderGround());
        values.put(KEY_LEFT_SHOULDER_GROUND, historicalRecord.getLShoulderGround());
        values.put(KEY_RIGHT_ELBOW_RAISE, historicalRecord.getRElbowRaise());
        values.put(KEY_LEFT_ELBOW_RAISE, historicalRecord.getLElbowRaise());
        values.put(KEY_RIGHT_HEEL_ON_GROUND, historicalRecord.getRHeelOnGround());
        values.put(KEY_LEFT_HEEL_ON_GROUND, historicalRecord.getLHeelOnGround());
        values.put(KEY_BODY_VERTICAL, historicalRecord.getBodyVertical());

        // Inserting Row
        db.insert(TABLE_HISTORICAL_RECORD, null, values);
        db.close();
    }

    public List<HistoricalRecord> getAllHistoricalRecord() {
        List<HistoricalRecord> historicalRecords = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_HISTORICAL_RECORD;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                HistoricalRecord historicalRecord = new HistoricalRecord();
                historicalRecord.setPoseName(cursor.getString(0));
                historicalRecord.setDate(cursor.getString(1));
                historicalRecord.setLevel(Integer.parseInt(cursor.getString(2)));
                historicalRecord.setAllComplete(Float.parseFloat(cursor.getString(3)));
                historicalRecord.setRHip(cursor.getString(4));
                historicalRecord.setLHip(cursor.getString(5));
                historicalRecord.setRKnee(cursor.getString(6));
                historicalRecord.setLKnee(cursor.getString(7));
                historicalRecord.setRElbow(cursor.getString(8));
                historicalRecord.setLElbow(cursor.getString(9));
                historicalRecord.setRArmpit(cursor.getString(10));
                historicalRecord.setLArmpit(cursor.getString(11));
                historicalRecord.setRShoulder(cursor.getString(12));
                historicalRecord.setLShoulder(cursor.getString(13));
                historicalRecord.setRKneeToe(cursor.getString(14));
                historicalRecord.setLKneeToe(cursor.getString(15));
                historicalRecord.setRThighHorizontal(cursor.getString(16));
                historicalRecord.setLThighHorizontal(cursor.getString(17));
                historicalRecord.setRCrotch(cursor.getString(18));
                historicalRecord.setLCrotch(cursor.getString(19));
                historicalRecord.setRShoulderGround(cursor.getString(20));
                historicalRecord.setLShoulderGround(cursor.getString(21));
                historicalRecord.setRElbowRaise(cursor.getString(22));
                historicalRecord.setLElbowRaise(cursor.getString(23));
                historicalRecord.setRHeelOnGround(cursor.getString(24));
                historicalRecord.setLHeelOnGround(cursor.getString(25));
                historicalRecord.setBodyVertical(cursor.getString(26));
                // Adding contact to list
                historicalRecords.add(historicalRecord);
            } while (cursor.moveToNext());
        }

        // return contact list
        return historicalRecords;
    }

    public List<HistoricalRecord> getHistoricalRecordByPoseName(String poseName,int limit) {
        List<HistoricalRecord> historicalRecords = new ArrayList<>();
        // Select All Query
        String selectQuery =
                " SELECT  * FROM " + TABLE_HISTORICAL_RECORD +
                " WHERE " + KEY_POSE_NAME + " = '" + poseName +
                "' ORDER BY "+ KEY_DATE +
                " DESC LIMIT " + limit;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                HistoricalRecord historicalRecord = new HistoricalRecord();
                historicalRecord.setPoseName(cursor.getString(0));
                historicalRecord.setDate(cursor.getString(1));
                historicalRecord.setLevel(Integer.parseInt(cursor.getString(2)));
                historicalRecord.setAllComplete(Float.parseFloat(cursor.getString(3)));
                historicalRecord.setRHip(cursor.getString(4));
                historicalRecord.setLHip(cursor.getString(5));
                historicalRecord.setRKnee(cursor.getString(6));
                historicalRecord.setLKnee(cursor.getString(7));
                historicalRecord.setRElbow(cursor.getString(8));
                historicalRecord.setLElbow(cursor.getString(9));
                historicalRecord.setRArmpit(cursor.getString(10));
                historicalRecord.setLArmpit(cursor.getString(11));
                historicalRecord.setRShoulder(cursor.getString(12));
                historicalRecord.setLShoulder(cursor.getString(13));
                historicalRecord.setRKneeToe(cursor.getString(14));
                historicalRecord.setLKneeToe(cursor.getString(15));
                historicalRecord.setRThighHorizontal(cursor.getString(16));
                historicalRecord.setLThighHorizontal(cursor.getString(17));
                historicalRecord.setRCrotch(cursor.getString(18));
                historicalRecord.setLCrotch(cursor.getString(19));
                historicalRecord.setRShoulderGround(cursor.getString(20));
                historicalRecord.setLShoulderGround(cursor.getString(21));
                historicalRecord.setRElbowRaise(cursor.getString(22));
                historicalRecord.setLElbowRaise(cursor.getString(23));
                historicalRecord.setRHeelOnGround(cursor.getString(24));
                historicalRecord.setLHeelOnGround(cursor.getString(25));
                historicalRecord.setBodyVertical(cursor.getString(26));
                // Adding contact to list
                historicalRecords.add(historicalRecord);
            } while (cursor.moveToNext());
        }

        // return contact list
        return historicalRecords;
    }



    public HistoricalRecord getHistoricalRecordByDate(String date) {

        String selectQuery = "SELECT * FROM " + TABLE_HISTORICAL_RECORD + " WHERE " + KEY_DATE + " = '" + date + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        HistoricalRecord historicalRecord = new HistoricalRecord();
        if (cursor.moveToFirst()) {
            do {
                historicalRecord.setPoseName(cursor.getString(0));
                historicalRecord.setDate(cursor.getString(1));
                historicalRecord.setLevel(Integer.parseInt(cursor.getString(2)));
                historicalRecord.setAllComplete(Float.parseFloat(cursor.getString(3)));
                historicalRecord.setRHip(cursor.getString(4));
                historicalRecord.setLHip(cursor.getString(5));
                historicalRecord.setRKnee(cursor.getString(6));
                historicalRecord.setLKnee(cursor.getString(7));
                historicalRecord.setRElbow(cursor.getString(8));
                historicalRecord.setLElbow(cursor.getString(9));
                historicalRecord.setRArmpit(cursor.getString(10));
                historicalRecord.setLArmpit(cursor.getString(11));
                historicalRecord.setRShoulder(cursor.getString(12));
                historicalRecord.setLShoulder(cursor.getString(13));
                historicalRecord.setRKneeToe(cursor.getString(14));
                historicalRecord.setLKneeToe(cursor.getString(15));
                historicalRecord.setRThighHorizontal(cursor.getString(16));
                historicalRecord.setLThighHorizontal(cursor.getString(17));
                historicalRecord.setRCrotch(cursor.getString(18));
                historicalRecord.setLCrotch(cursor.getString(19));
                historicalRecord.setRShoulderGround(cursor.getString(20));
                historicalRecord.setLShoulderGround(cursor.getString(21));
                historicalRecord.setRElbowRaise(cursor.getString(22));
                historicalRecord.setLElbowRaise(cursor.getString(23));
                historicalRecord.setRHeelOnGround(cursor.getString(24));
                historicalRecord.setLHeelOnGround(cursor.getString(25));
                historicalRecord.setBodyVertical(cursor.getString(26));
                // Adding contact to list
            } while (cursor.moveToNext());
        }
        return historicalRecord;
    }

//    public void deleteHistoricalRecord(HistoricalRecord historicalRecord) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_HISTORICAL_RECORD, KEY_ID + " = ?",
//                new String[] { String.valueOf(historicalRecord.getId()) });
//        db.close();
//    }

    public int getHistoricalRecordCount() {
        String countQuery = "SELECT  * FROM " + TABLE_HISTORICAL_RECORD;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}
