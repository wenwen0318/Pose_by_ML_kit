package com.example.posebymlkit.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class PoseStandardDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "poseStandard.db";
    private static final String TABLE_POSE_STANDARD = "poseStandard";
    private static final String KEY_POSE_NAME = "poseName";
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

    public PoseStandardDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_POSE_STANDARD + "("
                + KEY_POSE_NAME + " TEXT PRIMARY KEY,"
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
                + KEY_CROTCH + " TEXT"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POSE_STANDARD);

        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
    public void addPoseStandard(PoseStandard poseStandard) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_POSE_NAME, poseStandard.getPoseName()); // Contact Name
        values.put(KEY_RIGHT_HIP, poseStandard.getRHip());
        values.put(KEY_LEFT_HIP, poseStandard.getLHip());
        values.put(KEY_RIGHT_KNEE, poseStandard.getRKnee());
        values.put(KEY_LEFT_KNEE, poseStandard.getLKnee());
        values.put(KEY_RIGHT_ELBOW, poseStandard.getRElbow());
        values.put(KEY_LEFT_ELBOW, poseStandard.getLElbow());
        values.put(KEY_RIGHT_ARMPIT, poseStandard.getRArmpit());
        values.put(KEY_LEFT_ARMPIT, poseStandard.getLArmpit());
        values.put(KEY_RIGHT_SHOULDER, poseStandard.getRShoulder());
        values.put(KEY_LEFT_SHOULDER, poseStandard.getLShoulder());
        values.put(KEY_BODY_VERTICAL, poseStandard.getBodyVertical());
        values.put(KEY_RIGHT_KNEE_TOE, poseStandard.getRKneeToe());
        values.put(KEY_LEFT_KNEE_TOE, poseStandard.getLKneeToe());
        values.put(KEY_THIGH_HORIZONTAL, poseStandard.getThighHorizontal());
        values.put(KEY_CROTCH, poseStandard.getCrotch());

        // Inserting Row
        db.insert(TABLE_POSE_STANDARD, null, values);
        db.close();
    }

    // code to get the single contact
    public PoseStandard getPoseStandard(String poseName) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_POSE_STANDARD, new String[] {
                        KEY_POSE_NAME,
                        KEY_RIGHT_HIP, KEY_LEFT_HIP,
                        KEY_RIGHT_KNEE, KEY_LEFT_KNEE,
                        KEY_RIGHT_ELBOW, KEY_LEFT_ELBOW,
                        KEY_RIGHT_ARMPIT, KEY_LEFT_ARMPIT,
                        KEY_RIGHT_SHOULDER, KEY_LEFT_SHOULDER,
                        KEY_BODY_VERTICAL,
                        KEY_RIGHT_KNEE_TOE, KEY_LEFT_KNEE_TOE,
                        KEY_THIGH_HORIZONTAL,
                        KEY_CROTCH
                }, KEY_POSE_NAME + "=?",
                new String[] { String.valueOf(poseName) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
            PoseStandard poseStandard = new PoseStandard(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9),
                    cursor.getString(10),
                    cursor.getString(11),
                    cursor.getString(12),
                    cursor.getString(13),
                    cursor.getString(14),
                    cursor.getString(15)
        );
        // return contact
        return poseStandard;
    }

    // code to get all contacts in a list view
    public List<PoseStandard> getAllPoseStandard() {
        List<PoseStandard> poseStandardList = new ArrayList<PoseStandard>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_POSE_STANDARD;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PoseStandard poseStandard = new PoseStandard();
                poseStandard.setPoseName(cursor.getString(0));
                poseStandard.setRHip(cursor.getString(1));
                poseStandard.setLHip(cursor.getString(2));
                poseStandard.setRKnee(cursor.getString(3));
                poseStandard.setLKnee(cursor.getString(4));
                poseStandard.setRElbow(cursor.getString(5));
                poseStandard.setLElbow(cursor.getString(6));
                poseStandard.setRArmpit(cursor.getString(7));
                poseStandard.setLArmpit(cursor.getString(8));
                poseStandard.setRShoulder(cursor.getString(9));
                poseStandard.setLShoulder(cursor.getString(10));
                poseStandard.setBodyVertical(cursor.getString(11));
                poseStandard.setRKneeToe(cursor.getString(12));
                poseStandard.setLKneeToe(cursor.getString(13));
                poseStandard.setThighHorizontal(cursor.getString(14));
                poseStandard.setCrotch(cursor.getString(15));
                // Adding contact to list
                poseStandardList.add(poseStandard);
            } while (cursor.moveToNext());
        }

        // return contact list
        return poseStandardList;
    }

    // code to update the single contact
    public int updatePoseStandard(PoseStandard poseStandard) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_POSE_NAME, poseStandard.getPoseName());
        values.put(KEY_RIGHT_HIP, poseStandard.getRHip());
        values.put(KEY_LEFT_HIP, poseStandard.getLHip());
        values.put(KEY_RIGHT_KNEE, poseStandard.getRKnee());
        values.put(KEY_LEFT_KNEE, poseStandard.getLKnee());
        values.put(KEY_RIGHT_ELBOW, poseStandard.getRElbow());
        values.put(KEY_LEFT_ELBOW, poseStandard.getLElbow());
        values.put(KEY_RIGHT_ARMPIT, poseStandard.getRArmpit());
        values.put(KEY_LEFT_ARMPIT, poseStandard.getLArmpit());
        values.put(KEY_RIGHT_SHOULDER, poseStandard.getRShoulder());
        values.put(KEY_LEFT_SHOULDER, poseStandard.getLShoulder());
        values.put(KEY_BODY_VERTICAL, poseStandard.getBodyVertical());
        values.put(KEY_RIGHT_KNEE_TOE, poseStandard.getRKneeToe());
        values.put(KEY_LEFT_KNEE_TOE, poseStandard.getLKneeToe());
        values.put(KEY_THIGH_HORIZONTAL, poseStandard.getThighHorizontal());
        values.put(KEY_CROTCH, poseStandard.getCrotch());

        // updating row
        return db.update(TABLE_POSE_STANDARD, values, KEY_POSE_NAME + " = ?",
                new String[] { String.valueOf(poseStandard.getPoseName()) });
    }

    // Deleting single contact
    public void deletePoseStandard(PoseStandard poseStandard) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_POSE_STANDARD, KEY_POSE_NAME + " = ?",
                new String[] { String.valueOf(poseStandard.getPoseName()) });
        db.close();
    }

    // Getting contacts Count
    public int getPoseStandardCount() {
        String countQuery = "SELECT  * FROM " + TABLE_POSE_STANDARD;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}