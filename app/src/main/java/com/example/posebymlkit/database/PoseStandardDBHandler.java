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
    private static final String KEY_ANKLE_LONG_THAN_SHOULDER = "ankleLongThanShoulder";
    //put new KEY here

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
                + KEY_BODY_VERTICAL + " TEXT,"
                + KEY_ANKLE_LONG_THAN_SHOULDER + " TEXT"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POSE_STANDARD);

        onCreate(db);
    }

    public void poseStandardInit(){
        if (!poseStandardExist("Warrior2")){
            this.addPoseStandard(new PoseStandard("Warrior2",
            null,null,
            null,"170",
            "170","170",
            null,null,
            "90","90",
            "90", null,
            "90", null,
            null, "55",
            null, null,
            null, null,
            null, null,
            "180", null));
        }
        if (!poseStandardExist("Plank")){
            this.addPoseStandard(new PoseStandard("Plank",
            null,"170",
            null,"170",
            null,"170",
            null,null,
            null,null,
            null, null,
            null, null,
            null, null,
            null, "90",
            null, null,
            null, null,
            null,null));
        }
        if (!poseStandardExist("Goddess")){
            this.addPoseStandard(new PoseStandard("Goddess",
            null,null,
            "90","90",
            "90","90",
            null,null,
            "90","90",
            "90", "90",
            "90", "90",
            null, null,
            null, null,
            null, null,
            null, null,
            "180","180"));}
        if (!poseStandardExist("Chair")){
            this.addPoseStandard(new PoseStandard("Chair",
            null,null,
            "80",null,
            "170",null,
            "80",null,
            null,null,
            "90", null,
            null, null,
            null, null,
            null, null,
            "120", null,
            null, null,
            null,null));}
        if (!poseStandardExist("DownDog")){
            this.addPoseStandard(new PoseStandard("DownDog",
            "80",null,
            "170",null,
            "170",null,
            "170",null,
            null,null,
            null, null,
            null, null,
            null, null,
            null, null,
            null, null,
            "90", null,
            null,null));}
        if (!poseStandardExist("Four_Limbed_Staff")){
            this.addPoseStandard(new PoseStandard("Four_Limbed_Staff",
            "170",null,
            "170",null,
            "95",null,
            null,null,
            null,null,
            null, null,
            null, null,
            null, null,
            null, null,
            null, null,
            null, null,
            null,null));}
        if (!poseStandardExist("Boat")){
            this.addPoseStandard(new PoseStandard("Boat",
            null,null,
            "170",null,
            "170",null,
            null,null,
            null,null,
            null, null,
            null, null,
            null, null,
            null, null,
            null, null,
            null, null,
            null,null));}
        if (!poseStandardExist("Rejuvenation")){
            this.addPoseStandard(new PoseStandard("Rejuvenation",
            "90",null,
            "170",null,
            null,null,
            null,null,
            null,null,
            null, null,
            null, null,
            null, null,
            null, null,
            null, null,
            null, null,
            null,null));}
        if (!poseStandardExist("Star")){
            this.addPoseStandard(new PoseStandard("Star",
            null,null,
            "170","170",
            "170","170",
            null,null,
            "90","90",
            null, null,
            null, null,
            null, null,
            null, null,
            null, null,
            null, null,
            "180","180"));}
        if (!poseStandardExist("Tree")){
            this.addPoseStandard(new PoseStandard("Tree",
            null,"170",
            null,"170",
            "170","170",
            "170","170",
            null,null,
            null, null,
            null, null,
            null, null,
            null, null,
            null, null,
            null, null,
            "180",null));}
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
        values.put(KEY_RIGHT_KNEE_TOE, poseStandard.getRKneeToe());
        values.put(KEY_LEFT_KNEE_TOE, poseStandard.getLKneeToe());
        values.put(KEY_RIGHT_THIGH_HORIZONTAL, poseStandard.getRThighHorizontal());
        values.put(KEY_LEFT_THIGH_HORIZONTAL, poseStandard.getLThighHorizontal());
        values.put(KEY_RIGHT_CROTCH, poseStandard.getRCrotch());
        values.put(KEY_LEFT_CROTCH, poseStandard.getLCrotch());
        values.put(KEY_RIGHT_SHOULDER_GROUND, poseStandard.getRShoulderGround());
        values.put(KEY_LEFT_SHOULDER_GROUND, poseStandard.getLShoulderGround());
        values.put(KEY_RIGHT_ELBOW_RAISE, poseStandard.getRElbowRaise());
        values.put(KEY_LEFT_ELBOW_RAISE, poseStandard.getLElbowRaise());
        values.put(KEY_RIGHT_HEEL_ON_GROUND, poseStandard.getRHeelOnGround());
        values.put(KEY_LEFT_HEEL_ON_GROUND, poseStandard.getLHeelOnGround());
        values.put(KEY_BODY_VERTICAL, poseStandard.getBodyVertical());
        values.put(KEY_ANKLE_LONG_THAN_SHOULDER, poseStandard.getAnkleLongThanShoulder());
        // put new value

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
                        KEY_RIGHT_KNEE_TOE, KEY_LEFT_KNEE_TOE,
                        KEY_RIGHT_THIGH_HORIZONTAL, KEY_LEFT_THIGH_HORIZONTAL,
                        KEY_RIGHT_CROTCH, KEY_LEFT_CROTCH,
                        KEY_RIGHT_SHOULDER_GROUND, KEY_LEFT_SHOULDER_GROUND,
                        KEY_RIGHT_ELBOW_RAISE, KEY_LEFT_ELBOW_RAISE,
                        KEY_RIGHT_HEEL_ON_GROUND, KEY_LEFT_HEEL_ON_GROUND,
                        KEY_BODY_VERTICAL,KEY_ANKLE_LONG_THAN_SHOULDER
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
                    cursor.getString(15),
                    cursor.getString(16),
                    cursor.getString(17),
                    cursor.getString(18),
                    cursor.getString(19),
                    cursor.getString(20),
                    cursor.getString(21),
                    cursor.getString(22),
                    cursor.getString(23),
                    cursor.getString(24)
        );
        // return contact
        return poseStandard;
    }

    public List<String> getAllPoseName() {
        List<String> poseNames = new ArrayList<>();

        String selectQuery = "SELECT " + KEY_POSE_NAME + " FROM " + TABLE_POSE_STANDARD;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                poseNames.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        return poseNames;
    }

    public boolean poseStandardExist(String poseName) {
        List<String> poseNames = getAllPoseName();
        return poseNames.contains(poseName);
    }

    // code to update the single contact
    public int updatePoseStandard(PoseStandard poseStandard) {
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
        values.put(KEY_RIGHT_KNEE_TOE, poseStandard.getRKneeToe());
        values.put(KEY_LEFT_KNEE_TOE, poseStandard.getLKneeToe());
        values.put(KEY_RIGHT_THIGH_HORIZONTAL, poseStandard.getRThighHorizontal());
        values.put(KEY_LEFT_THIGH_HORIZONTAL, poseStandard.getLThighHorizontal());
        values.put(KEY_RIGHT_CROTCH, poseStandard.getRCrotch());
        values.put(KEY_LEFT_CROTCH, poseStandard.getLCrotch());
        values.put(KEY_RIGHT_SHOULDER_GROUND, poseStandard.getRShoulderGround());
        values.put(KEY_LEFT_SHOULDER_GROUND, poseStandard.getLShoulderGround());
        values.put(KEY_RIGHT_ELBOW_RAISE, poseStandard.getRElbowRaise());
        values.put(KEY_LEFT_ELBOW_RAISE, poseStandard.getLElbowRaise());
        values.put(KEY_RIGHT_HEEL_ON_GROUND, poseStandard.getRHeelOnGround());
        values.put(KEY_LEFT_HEEL_ON_GROUND, poseStandard.getLHeelOnGround());
        values.put(KEY_BODY_VERTICAL, poseStandard.getBodyVertical());
        values.put(KEY_ANKLE_LONG_THAN_SHOULDER, poseStandard.getAnkleLongThanShoulder());

        // updating row
        return db.update(TABLE_POSE_STANDARD, values, KEY_POSE_NAME + " = ?",
                new String[] { String.valueOf(poseStandard.getPoseName()) });
    }

}