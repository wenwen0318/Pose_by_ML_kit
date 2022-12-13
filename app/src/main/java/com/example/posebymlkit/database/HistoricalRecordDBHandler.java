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
    private static final String KEY_BODY_VERTICAL = "bodyVertical";
    private static final String KEY_RIGHT_KNEE_TOE = "RKneeToe";
    private static final String KEY_LEFT_KNEE_TOE = "LKneeToe";
    private static final String KEY_THIGH_HORIZONTAL = "thighHorizontal";
    private static final String KEY_CROTCH = "crotch";
    private static final String KEY_SHOULDER_GROUND = "shoulderGround";

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
                + KEY_BODY_VERTICAL + " TEXT,"
                + KEY_RIGHT_KNEE_TOE + " TEXT,"
                + KEY_LEFT_KNEE_TOE + " TEXT,"
                + KEY_THIGH_HORIZONTAL + " TEXT,"
                + KEY_CROTCH + " TEXT,"
                + KEY_SHOULDER_GROUND + " TEXT"
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
        values.put(KEY_BODY_VERTICAL, historicalRecord.getBodyVertical());
        values.put(KEY_RIGHT_KNEE_TOE, historicalRecord.getRKneeToe());
        values.put(KEY_LEFT_KNEE_TOE, historicalRecord.getLKneeToe());
        values.put(KEY_THIGH_HORIZONTAL, historicalRecord.getThighHorizontal());
        values.put(KEY_CROTCH, historicalRecord.getCrotch());
        values.put(KEY_SHOULDER_GROUND, historicalRecord.getShoulderGround());

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
                historicalRecord.setAllComplete(Integer.parseInt(cursor.getString(3)));
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
                historicalRecord.setBodyVertical(cursor.getString(14));
                historicalRecord.setRKneeToe(cursor.getString(15));
                historicalRecord.setLKneeToe(cursor.getString(16));
                historicalRecord.setThighHorizontal(cursor.getString(17));
                historicalRecord.setCrotch(cursor.getString(18));
                historicalRecord.setShoulderGround(cursor.getString(19));
                // Adding contact to list
                historicalRecords.add(historicalRecord);
            } while (cursor.moveToNext());
        }

        // return contact list
        return historicalRecords;
    }

    public List<HistoricalRecord> getHistoricalRecordByPoseName(String poseName) {
        List<HistoricalRecord> historicalRecords = new ArrayList<>();
        // Select All Query
        String selectQuery =
                " SELECT  * FROM " + TABLE_HISTORICAL_RECORD +
                " WHERE " + KEY_POSE_NAME + " = '" + poseName +
                "' ORDER BY "+ KEY_DATE +
                " DESC LIMIT 8";

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
                historicalRecord.setBodyVertical(cursor.getString(14));
                historicalRecord.setRKneeToe(cursor.getString(15));
                historicalRecord.setLKneeToe(cursor.getString(16));
                historicalRecord.setThighHorizontal(cursor.getString(17));
                historicalRecord.setCrotch(cursor.getString(18));
                historicalRecord.setShoulderGround(cursor.getString(19));
                // Adding contact to list
                historicalRecords.add(historicalRecord);
            } while (cursor.moveToNext());
        }

        // return contact list
        return historicalRecords;
    }

    public HistoricalRecord getNewestHistoricalRecord() {
        HistoricalRecord historicalRecord = new HistoricalRecord();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_HISTORICAL_RECORD + " ORDER BY " + KEY_DATE + " DESC LIMIT 1";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        historicalRecord.setPoseName(cursor.getString(0));
        historicalRecord.setDate(cursor.getString(1));
        historicalRecord.setLevel(Integer.parseInt(cursor.getString(2)));
        historicalRecord.setAllComplete(Integer.parseInt(cursor.getString(3)));
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
        historicalRecord.setBodyVertical(cursor.getString(14));
        historicalRecord.setRKneeToe(cursor.getString(15));
        historicalRecord.setLKneeToe(cursor.getString(16));
        historicalRecord.setThighHorizontal(cursor.getString(17));
        historicalRecord.setCrotch(cursor.getString(18));
        historicalRecord.setShoulderGround(cursor.getString(19));

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
