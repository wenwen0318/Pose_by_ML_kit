package com.example.posebymlkit.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class PoseWrongTTSDBHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "poseWrongTTS.db";
    private static final String TABLE_POSE_WRONG_TTS = "poseWrongTTS";
    private static final String KEY_POSE_NAME = "poseName";
    private static final String KEY_RIGHT_HIP_1 = "RHip1";
    private static final String KEY_RIGHT_HIP_2 = "RHip2";
    private static final String KEY_LEFT_HIP_1 = "LHip1";
    private static final String KEY_LEFT_HIP_2 = "LHip2";
    private static final String KEY_RIGHT_KNEE_1 = "RKnee1";
    private static final String KEY_RIGHT_KNEE_2 = "RKnee2";
    private static final String KEY_LEFT_KNEE_1 = "LKnee1";
    private static final String KEY_LEFT_KNEE_2 = "LKnee2";
    private static final String KEY_RIGHT_ELBOW_1 = "RElbow1";
    private static final String KEY_RIGHT_ELBOW_2 = "RElbow2";
    private static final String KEY_LEFT_ELBOW_1 = "LElbow1";
    private static final String KEY_LEFT_ELBOW_2 = "LElbow2";
    private static final String KEY_RIGHT_ARMPIT_1 = "RArmpit1";
    private static final String KEY_RIGHT_ARMPIT_2 = "RArmpit2";
    private static final String KEY_LEFT_ARMPIT_1 = "LArmpit1";
    private static final String KEY_LEFT_ARMPIT_2 = "LArmpit2";
    private static final String KEY_RIGHT_SHOULDER_1 = "RShoulder1";
    private static final String KEY_RIGHT_SHOULDER_2 = "RShoulder2";
    private static final String KEY_LEFT_SHOULDER_1 = "LShoulder1";
    private static final String KEY_LEFT_SHOULDER_2 = "LShoulder2";
    private static final String KEY_RIGHT_KNEE_TOE_1 = "RKneeToe1";
    private static final String KEY_RIGHT_KNEE_TOE_2 = "RKneeToe2";
    private static final String KEY_LEFT_KNEE_TOE_1 = "LKneeToe1";
    private static final String KEY_LEFT_KNEE_TOE_2 = "LKneeToe2";
    private static final String KEY_RIGHT_THIGH_HORIZONTAL_1 = "RThighHorizontal1";
    private static final String KEY_RIGHT_THIGH_HORIZONTAL_2 = "RThighHorizontal2";
    private static final String KEY_LEFT_THIGH_HORIZONTAL_1 = "LThighHorizontal1";
    private static final String KEY_LEFT_THIGH_HORIZONTAL_2 = "LThighHorizontal2";
    private static final String KEY_RIGHT_CROTCH_1 = "RCrotch1";
    private static final String KEY_RIGHT_CROTCH_2 = "RCrotch2";
    private static final String KEY_LEFT_CROTCH_1 = "LCrotch1";
    private static final String KEY_LEFT_CROTCH_2 = "LCrotch2";
    private static final String KEY_RIGHT_SHOULDER_GROUND_1 = "RShoulderGround1";
    private static final String KEY_RIGHT_SHOULDER_GROUND_2 = "RShoulderGround2";
    private static final String KEY_LEFT_SHOULDER_GROUND_1 = "LShoulderGround1";
    private static final String KEY_LEFT_SHOULDER_GROUND_2 = "LShoulderGround2";
    private static final String KEY_RIGHT_ELBOW_RAISE_1 = "RElbowRaise1";
    private static final String KEY_RIGHT_ELBOW_RAISE_2 = "RElbowRaise2";
    private static final String KEY_LEFT_ELBOW_RAISE_1 = "LElbowRaise1";
    private static final String KEY_LEFT_ELBOW_RAISE_2 = "LElbowRaise2";
    private static final String KEY_RIGHT_HEEL_ON_GROUND_1 = "RHeelOnGround1";
    private static final String KEY_RIGHT_HEEL_ON_GROUND_2 = "RHeelOnGround2";
    private static final String KEY_LEFT_HEEL_ON_GROUND_1 = "LHeelOnGround1";
    private static final String KEY_LEFT_HEEL_ON_GROUND_2 = "LHeelOnGround2";
    private static final String KEY_BODY_VERTICAL = "bodyVertical";

    public PoseWrongTTSDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_POSE_WRONG_TTS + "("
                + KEY_POSE_NAME + " TEXT PRIMARY KEY,"
                + KEY_RIGHT_HIP_1 + " TEXT,"
                + KEY_RIGHT_HIP_2 + " TEXT,"
                + KEY_LEFT_HIP_1 + " TEXT,"
                + KEY_LEFT_HIP_2 + " TEXT,"
                + KEY_RIGHT_KNEE_1 + " TEXT,"
                + KEY_RIGHT_KNEE_2 + " TEXT,"
                + KEY_LEFT_KNEE_1 + " TEXT,"
                + KEY_LEFT_KNEE_2 + " TEXT,"
                + KEY_RIGHT_ELBOW_1 + " TEXT,"
                + KEY_RIGHT_ELBOW_2 + " TEXT,"
                + KEY_LEFT_ELBOW_1 + " TEXT,"
                + KEY_LEFT_ELBOW_2 + " TEXT,"
                + KEY_RIGHT_ARMPIT_1 + " TEXT,"
                + KEY_RIGHT_ARMPIT_2 + " TEXT,"
                + KEY_LEFT_ARMPIT_1 + " TEXT,"
                + KEY_LEFT_ARMPIT_2 + " TEXT,"
                + KEY_RIGHT_SHOULDER_1 + " TEXT,"
                + KEY_RIGHT_SHOULDER_2 + " TEXT,"
                + KEY_LEFT_SHOULDER_1 + " TEXT,"
                + KEY_LEFT_SHOULDER_2 + " TEXT,"
                + KEY_RIGHT_KNEE_TOE_1 + " TEXT,"
                + KEY_RIGHT_KNEE_TOE_2 + " TEXT,"
                + KEY_LEFT_KNEE_TOE_1 + " TEXT,"
                + KEY_LEFT_KNEE_TOE_2 + " TEXT,"
                + KEY_RIGHT_THIGH_HORIZONTAL_1 + " TEXT,"
                + KEY_RIGHT_THIGH_HORIZONTAL_2 + " TEXT,"
                + KEY_LEFT_THIGH_HORIZONTAL_1 + " TEXT,"
                + KEY_LEFT_THIGH_HORIZONTAL_2 + " TEXT,"
                + KEY_RIGHT_CROTCH_1 + " TEXT,"
                + KEY_RIGHT_CROTCH_2 + " TEXT,"
                + KEY_LEFT_CROTCH_1 + " TEXT,"
                + KEY_LEFT_CROTCH_2 + " TEXT,"
                + KEY_RIGHT_SHOULDER_GROUND_1 + " TEXT,"
                + KEY_RIGHT_SHOULDER_GROUND_2 + " TEXT,"
                + KEY_LEFT_SHOULDER_GROUND_1 + " TEXT,"
                + KEY_LEFT_SHOULDER_GROUND_2 + " TEXT,"
                + KEY_RIGHT_ELBOW_RAISE_1 + " TEXT,"
                + KEY_RIGHT_ELBOW_RAISE_2 + " TEXT,"
                + KEY_LEFT_ELBOW_RAISE_1 + " TEXT,"
                + KEY_LEFT_ELBOW_RAISE_2 + " TEXT,"
                + KEY_RIGHT_HEEL_ON_GROUND_1 + " TEXT,"
                + KEY_RIGHT_HEEL_ON_GROUND_2 + " TEXT,"
                + KEY_LEFT_HEEL_ON_GROUND_1 + " TEXT,"
                + KEY_LEFT_HEEL_ON_GROUND_2 + " TEXT,"
                + KEY_BODY_VERTICAL + " TEXT"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POSE_WRONG_TTS);

        // Create tables again
        onCreate(db);
    }

    public void poseWrongTTSInit(){
        this.addPoseWrongTTS(new PoseWrongTTS("Warrior2",
                null,null,
                null,null,
                null,null,
                "左膝伸直","左膝伸直",
                "右手伸直","右手伸直",
                "左手伸直", "左手伸直",
                null, null,
                null, null,
                "右肩保持平坦", "右肩保持平坦",
                "左肩保持平坦", "左肩保持平坦",
                "右膝勿超過腳尖", "右膝勿超過腳尖",
                null, null,
                "右大腿與地板平行", "右大腿與地板平行",
                null, null,
                null, null,
                "左腳張開往下坐", "左腳張太開",
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                "身體保持垂直"
                ));
        this.addPoseWrongTTS(new PoseWrongTTS("Plank",
                null,null,
                "臀部與身體呈直線","臀部與身體呈直線",
                null,null,
                "膝蓋與身體呈直線","膝蓋與身體呈直線",
                null,null,
                "手臂伸直", "手臂伸直",
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                "手臂伸太出去", "手臂太進去",
                null, null,
                null, null,
                null, null,
                null, null,
                null
        ));
        this.addPoseWrongTTS(new PoseWrongTTS("Goddess",
                null,null,
                null,null,
                "右膝蹲太下去","右膝蹲不夠下去",
                "左膝蹲太下去","左膝蹲不夠下去",
                "右手肘張不夠開","右手肘張太開",
                "左手肘張不夠開", "左手肘張太開",
                null, null,
                null, null,
                "右手與地板平行", "右手與地板平行",
                "左手與地板平行", "左手與地板平行",
                "右膝勿超過腳尖", "右膝勿超過腳尖",
                "左膝勿超過腳尖", "左膝勿超過腳尖",
                "右大腿與地板平行", "右大腿與地板平行",
                "左大腿與地板平行", "右大腿與地板平行",
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                "身體保持垂直"
        ));
        this.addPoseWrongTTS(new PoseWrongTTS("Chair",
                null,null,
                null,null,
                "膝蓋蹲太低","膝蓋蹲不夠低",
                null,null,
                "手肘伸直","手肘伸直",
                null, null,
                "身體與大腿垂直", "身體與大腿垂直",
                null, null,
                null, null,
                null, null,
                "膝蓋不超過腳尖", "膝蓋不超過腳尖",
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                "手臂舉高點", "手臂舉高點",
                null, null,
                null, null,
                null, null,
                null
        ));
        this.addPoseWrongTTS(new PoseWrongTTS("DownDog",
                null,null,
                null,null,
                "膝蓋挺直","膝蓋挺直",
                null,null,
                "手肘伸直","手肘伸直",
                null, null,
                "手臂往前伸", "手臂往前伸",
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                "腳跟卓第", "腳跟卓第",
                null, null,
                null
        ));
        this.addPoseWrongTTS(new PoseWrongTTS("Four_Limbed_Staff",
                "臀部與地板平行","臀部與地板平行",
                null,null,
                "膝蓋挺直","膝蓋挺直",
                null,null,
                "手臂與手肘夾90度","右臂與手肘夾90度",
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null
        ));
        this.addPoseWrongTTS(new PoseWrongTTS("Boat",
                null,null,
                null,null,
                "膝蓋挺直","膝蓋挺直",
                null,null,
                "手肘伸直","手肘伸直",
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null
        ));
        this.addPoseWrongTTS(new PoseWrongTTS("Rejuvenation",
                "雙腳與地板垂直","雙腳與地板垂直",
                null,null,
                "膝蓋挺直","膝蓋挺直",
                null,null,
                null,null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null
        ));
        this.addPoseWrongTTS(new PoseWrongTTS("Star",
                null,null,
                null,null,
                "右膝挺直","右膝挺直",
                "左膝挺直","左膝挺直",
                "右手伸直","右手伸直",
                "左手伸直", "左手伸直",
                null, null,
                null, null,
                "右手與地板平行", "右手與地板平行",
                "左手與地板平行", "左手與地板平行",
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                "身體與地板垂直"
        ));
        this.addPoseWrongTTS(new PoseWrongTTS("Tree",
                null,null,
                "左臀保持一直線","左臀保持一直線",
                null,null,
                "左膝挺直","左膝挺直",
                "右手肘伸直","右手肘伸直",
                "左手肘伸直", "左手肘伸直",
                "右手伸直", "右手伸直",
                "左手伸直", "左手伸直",
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                "身體保持垂直"
        ));
    }

    // code to add the new contact
    public void addPoseWrongTTS(PoseWrongTTS poseWrongTTS) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_POSE_NAME, poseWrongTTS.getPoseName()); // Contact Name
        values.put(KEY_RIGHT_HIP_1, poseWrongTTS.getRHip1());
        values.put(KEY_RIGHT_HIP_2, poseWrongTTS.getRHip2());
        values.put(KEY_LEFT_HIP_1, poseWrongTTS.getLHip1());
        values.put(KEY_LEFT_HIP_2, poseWrongTTS.getLHip2());
        values.put(KEY_RIGHT_KNEE_1, poseWrongTTS.getRKnee1());
        values.put(KEY_RIGHT_KNEE_2, poseWrongTTS.getRKnee2());
        values.put(KEY_LEFT_KNEE_1, poseWrongTTS.getLKnee1());
        values.put(KEY_LEFT_KNEE_2, poseWrongTTS.getLKnee2());
        values.put(KEY_RIGHT_ELBOW_1, poseWrongTTS.getRElbow1());
        values.put(KEY_RIGHT_ELBOW_2, poseWrongTTS.getRElbow2());
        values.put(KEY_LEFT_ELBOW_1, poseWrongTTS.getLElbow1());
        values.put(KEY_LEFT_ELBOW_2, poseWrongTTS.getLElbow2());
        values.put(KEY_RIGHT_ARMPIT_1, poseWrongTTS.getRArmpit1());
        values.put(KEY_RIGHT_ARMPIT_2, poseWrongTTS.getRArmpit2());
        values.put(KEY_LEFT_ARMPIT_1, poseWrongTTS.getLArmpit1());
        values.put(KEY_LEFT_ARMPIT_2, poseWrongTTS.getLArmpit2());
        values.put(KEY_RIGHT_SHOULDER_1, poseWrongTTS.getRShoulder1());
        values.put(KEY_RIGHT_SHOULDER_2, poseWrongTTS.getRShoulder2());
        values.put(KEY_LEFT_SHOULDER_1, poseWrongTTS.getLShoulder1());
        values.put(KEY_LEFT_SHOULDER_2, poseWrongTTS.getLShoulder2());
        values.put(KEY_RIGHT_KNEE_TOE_1, poseWrongTTS.getRKneeToe1());
        values.put(KEY_RIGHT_KNEE_TOE_2, poseWrongTTS.getRKneeToe2());
        values.put(KEY_LEFT_KNEE_TOE_1, poseWrongTTS.getLKneeToe1());
        values.put(KEY_LEFT_KNEE_TOE_2, poseWrongTTS.getLKneeToe2());
        values.put(KEY_RIGHT_THIGH_HORIZONTAL_1, poseWrongTTS.getRThighHorizontal1());
        values.put(KEY_RIGHT_THIGH_HORIZONTAL_2, poseWrongTTS.getRThighHorizontal2());
        values.put(KEY_LEFT_THIGH_HORIZONTAL_1, poseWrongTTS.getLThighHorizontal1());
        values.put(KEY_LEFT_THIGH_HORIZONTAL_2, poseWrongTTS.getLThighHorizontal2());
        values.put(KEY_RIGHT_CROTCH_1, poseWrongTTS.getRCrotch1());
        values.put(KEY_RIGHT_CROTCH_2, poseWrongTTS.getRCrotch2());
        values.put(KEY_LEFT_CROTCH_1, poseWrongTTS.getLCrotch1());
        values.put(KEY_LEFT_CROTCH_2, poseWrongTTS.getLCrotch2());
        values.put(KEY_RIGHT_SHOULDER_GROUND_1, poseWrongTTS.getRShoulderGround1());
        values.put(KEY_RIGHT_SHOULDER_GROUND_2, poseWrongTTS.getRShoulderGround2());
        values.put(KEY_LEFT_SHOULDER_GROUND_1, poseWrongTTS.getLShoulderGround1());
        values.put(KEY_LEFT_SHOULDER_GROUND_2, poseWrongTTS.getLShoulderGround2());
        values.put(KEY_RIGHT_ELBOW_RAISE_1, poseWrongTTS.getRElbowRaise1());
        values.put(KEY_RIGHT_ELBOW_RAISE_2, poseWrongTTS.getRElbowRaise2());
        values.put(KEY_LEFT_ELBOW_RAISE_1, poseWrongTTS.getLElbowRaise1());
        values.put(KEY_LEFT_ELBOW_RAISE_2, poseWrongTTS.getLElbowRaise2());
        values.put(KEY_RIGHT_HEEL_ON_GROUND_1, poseWrongTTS.getRHeelOnGround1());
        values.put(KEY_RIGHT_HEEL_ON_GROUND_2, poseWrongTTS.getRHeelOnGround2());
        values.put(KEY_LEFT_HEEL_ON_GROUND_1, poseWrongTTS.getLHeelOnGround1());
        values.put(KEY_LEFT_HEEL_ON_GROUND_2, poseWrongTTS.getLHeelOnGround2());
        values.put(KEY_BODY_VERTICAL, poseWrongTTS.getBodyVertical());

        // Inserting Row
        db.insert(TABLE_POSE_WRONG_TTS, null, values);
        db.close();
    }

    // code to get the single contact
    public PoseWrongTTS getPoseWrongTTS(String poseName) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_POSE_WRONG_TTS, new String[] {
                        KEY_POSE_NAME,
                        KEY_RIGHT_HIP_1, KEY_RIGHT_HIP_2,
                        KEY_LEFT_HIP_1, KEY_LEFT_HIP_2,
                        KEY_RIGHT_KNEE_1, KEY_RIGHT_KNEE_2,
                        KEY_LEFT_KNEE_1, KEY_LEFT_KNEE_2,
                        KEY_RIGHT_ELBOW_1, KEY_RIGHT_ELBOW_2,
                        KEY_LEFT_ELBOW_1, KEY_LEFT_ELBOW_2,
                        KEY_RIGHT_ARMPIT_1, KEY_RIGHT_ARMPIT_2,
                        KEY_LEFT_ARMPIT_1, KEY_LEFT_ARMPIT_2,
                        KEY_RIGHT_SHOULDER_1, KEY_RIGHT_SHOULDER_2,
                        KEY_LEFT_SHOULDER_1, KEY_LEFT_SHOULDER_2,
                        KEY_RIGHT_KNEE_TOE_1, KEY_RIGHT_KNEE_TOE_2,
                        KEY_LEFT_KNEE_TOE_1, KEY_LEFT_KNEE_TOE_2,
                        KEY_RIGHT_THIGH_HORIZONTAL_1, KEY_RIGHT_THIGH_HORIZONTAL_2,
                        KEY_LEFT_THIGH_HORIZONTAL_1, KEY_LEFT_THIGH_HORIZONTAL_2,
                        KEY_RIGHT_CROTCH_1, KEY_RIGHT_CROTCH_2,
                        KEY_LEFT_CROTCH_1, KEY_LEFT_CROTCH_2,
                        KEY_RIGHT_SHOULDER_GROUND_1, KEY_RIGHT_SHOULDER_GROUND_2,
                        KEY_LEFT_SHOULDER_GROUND_1, KEY_LEFT_SHOULDER_GROUND_2,
                        KEY_RIGHT_ELBOW_RAISE_1, KEY_RIGHT_ELBOW_RAISE_2,
                        KEY_LEFT_ELBOW_RAISE_1, KEY_LEFT_ELBOW_RAISE_2,
                        KEY_RIGHT_HEEL_ON_GROUND_1, KEY_RIGHT_HEEL_ON_GROUND_2,
                        KEY_LEFT_HEEL_ON_GROUND_1, KEY_LEFT_HEEL_ON_GROUND_2,
                        KEY_BODY_VERTICAL
                }, KEY_POSE_NAME + "=?",
                new String[] { String.valueOf(poseName) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        PoseWrongTTS poseWrongTTS = new PoseWrongTTS(
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
                cursor.getString(24),
                cursor.getString(25),
                cursor.getString(26),
                cursor.getString(27),
                cursor.getString(28),
                cursor.getString(29),
                cursor.getString(30),
                cursor.getString(31),
                cursor.getString(32),
                cursor.getString(33),
                cursor.getString(34),
                cursor.getString(35),
                cursor.getString(36),
                cursor.getString(37),
                cursor.getString(38),
                cursor.getString(39),
                cursor.getString(40),
                cursor.getString(41),
                cursor.getString(42),
                cursor.getString(43),
                cursor.getString(44),
                cursor.getString(45)
        );
        // return contact
        return poseWrongTTS;
    }

    public List<PoseWrongTTS> getAllPoseWrongTTS() {
        List<PoseWrongTTS> poseWrongTTSList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_POSE_WRONG_TTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PoseWrongTTS poseWrongTTS = new PoseWrongTTS();
                poseWrongTTS.setPoseName(cursor.getString(0));
                poseWrongTTS.setRHip1(cursor.getString(1));
                poseWrongTTS.setRHip2(cursor.getString(2));
                poseWrongTTS.setLHip1(cursor.getString(3));
                poseWrongTTS.setLHip2(cursor.getString(4));
                poseWrongTTS.setRKnee1(cursor.getString(5));
                poseWrongTTS.setRKnee2(cursor.getString(6));
                poseWrongTTS.setLKnee1(cursor.getString(7));
                poseWrongTTS.setLKnee2(cursor.getString(8));
                poseWrongTTS.setRElbow1(cursor.getString(9));
                poseWrongTTS.setRElbow2(cursor.getString(10));
                poseWrongTTS.setLElbow1(cursor.getString(11));
                poseWrongTTS.setLElbow2(cursor.getString(12));
                poseWrongTTS.setRArmpit1(cursor.getString(13));
                poseWrongTTS.setRArmpit2(cursor.getString(14));
                poseWrongTTS.setLArmpit1(cursor.getString(15));
                poseWrongTTS.setLArmpit2(cursor.getString(16));
                poseWrongTTS.setRShoulder1(cursor.getString(17));
                poseWrongTTS.setRShoulder2(cursor.getString(18));
                poseWrongTTS.setLShoulder1(cursor.getString(19));
                poseWrongTTS.setLShoulder2(cursor.getString(20));
                poseWrongTTS.setRKneeToe1(cursor.getString(21));
                poseWrongTTS.setRKneeToe2(cursor.getString(22));
                poseWrongTTS.setLKneeToe1(cursor.getString(23));
                poseWrongTTS.setLKneeToe2(cursor.getString(24));
                poseWrongTTS.setRThighHorizontal1(cursor.getString(25));
                poseWrongTTS.setRThighHorizontal2(cursor.getString(26));
                poseWrongTTS.setLThighHorizontal1(cursor.getString(27));
                poseWrongTTS.setLThighHorizontal2(cursor.getString(28));
                poseWrongTTS.setRCrotch1(cursor.getString(29));
                poseWrongTTS.setRCrotch2(cursor.getString(30));
                poseWrongTTS.setLCrotch1(cursor.getString(31));
                poseWrongTTS.setLCrotch2(cursor.getString(32));
                poseWrongTTS.setRShoulderGround1(cursor.getString(33));
                poseWrongTTS.setRShoulderGround2(cursor.getString(34));
                poseWrongTTS.setLShoulderGround1(cursor.getString(35));
                poseWrongTTS.setLShoulderGround2(cursor.getString(36));
                poseWrongTTS.setRElbowRaise1(cursor.getString(37));
                poseWrongTTS.setRElbowRaise2(cursor.getString(38));
                poseWrongTTS.setLElbowRaise1(cursor.getString(39));
                poseWrongTTS.setLElbowRaise2(cursor.getString(40));
                poseWrongTTS.setRHeelOnGround1(cursor.getString(41));
                poseWrongTTS.setRHeelOnGround2(cursor.getString(42));
                poseWrongTTS.setLHeelOnGround1(cursor.getString(43));
                poseWrongTTS.setLHeelOnGround2(cursor.getString(44));
                poseWrongTTS.setBodyVertical(cursor.getString(45));
                // Adding contact to list
                poseWrongTTSList.add(poseWrongTTS);
            } while (cursor.moveToNext());
        }

        // return contact list
        return poseWrongTTSList;
    }

    // code to update the single contact
    public int updatePoseWrongTTS(PoseWrongTTS poseWrongTTS) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_POSE_NAME, poseWrongTTS.getPoseName()); // Contact Name
        values.put(KEY_RIGHT_HIP_1, poseWrongTTS.getRHip1());
        values.put(KEY_RIGHT_HIP_2, poseWrongTTS.getRHip2());
        values.put(KEY_LEFT_HIP_1, poseWrongTTS.getLHip1());
        values.put(KEY_LEFT_HIP_2, poseWrongTTS.getLHip2());
        values.put(KEY_RIGHT_KNEE_1, poseWrongTTS.getRKnee1());
        values.put(KEY_RIGHT_KNEE_2, poseWrongTTS.getRKnee2());
        values.put(KEY_LEFT_KNEE_1, poseWrongTTS.getLKnee1());
        values.put(KEY_LEFT_KNEE_2, poseWrongTTS.getLKnee2());
        values.put(KEY_RIGHT_ELBOW_1, poseWrongTTS.getRElbow1());
        values.put(KEY_RIGHT_ELBOW_2, poseWrongTTS.getRElbow2());
        values.put(KEY_LEFT_ELBOW_1, poseWrongTTS.getLElbow1());
        values.put(KEY_LEFT_ELBOW_2, poseWrongTTS.getLElbow2());
        values.put(KEY_RIGHT_ARMPIT_1, poseWrongTTS.getRArmpit1());
        values.put(KEY_RIGHT_ARMPIT_2, poseWrongTTS.getRArmpit2());
        values.put(KEY_LEFT_ARMPIT_1, poseWrongTTS.getLArmpit1());
        values.put(KEY_LEFT_ARMPIT_2, poseWrongTTS.getLArmpit2());
        values.put(KEY_RIGHT_SHOULDER_1, poseWrongTTS.getRShoulder1());
        values.put(KEY_RIGHT_SHOULDER_2, poseWrongTTS.getRShoulder2());
        values.put(KEY_LEFT_SHOULDER_1, poseWrongTTS.getLShoulder1());
        values.put(KEY_LEFT_SHOULDER_2, poseWrongTTS.getLShoulder2());
        values.put(KEY_RIGHT_KNEE_TOE_1, poseWrongTTS.getRKneeToe1());
        values.put(KEY_RIGHT_KNEE_TOE_2, poseWrongTTS.getRKneeToe2());
        values.put(KEY_LEFT_KNEE_TOE_1, poseWrongTTS.getLKneeToe1());
        values.put(KEY_LEFT_KNEE_TOE_2, poseWrongTTS.getLKneeToe2());
        values.put(KEY_RIGHT_THIGH_HORIZONTAL_1, poseWrongTTS.getRThighHorizontal1());
        values.put(KEY_RIGHT_THIGH_HORIZONTAL_2, poseWrongTTS.getRThighHorizontal2());
        values.put(KEY_LEFT_THIGH_HORIZONTAL_1, poseWrongTTS.getLThighHorizontal1());
        values.put(KEY_LEFT_THIGH_HORIZONTAL_2, poseWrongTTS.getLThighHorizontal2());
        values.put(KEY_RIGHT_CROTCH_1, poseWrongTTS.getRCrotch1());
        values.put(KEY_RIGHT_CROTCH_2, poseWrongTTS.getRCrotch2());
        values.put(KEY_LEFT_CROTCH_1, poseWrongTTS.getLCrotch1());
        values.put(KEY_LEFT_CROTCH_2, poseWrongTTS.getLCrotch2());
        values.put(KEY_RIGHT_SHOULDER_GROUND_1, poseWrongTTS.getRShoulderGround1());
        values.put(KEY_RIGHT_SHOULDER_GROUND_2, poseWrongTTS.getRShoulderGround2());
        values.put(KEY_LEFT_SHOULDER_GROUND_1, poseWrongTTS.getLShoulderGround1());
        values.put(KEY_LEFT_SHOULDER_GROUND_2, poseWrongTTS.getLShoulderGround2());
        values.put(KEY_RIGHT_ELBOW_RAISE_1, poseWrongTTS.getRElbowRaise1());
        values.put(KEY_RIGHT_ELBOW_RAISE_2, poseWrongTTS.getRElbowRaise2());
        values.put(KEY_LEFT_ELBOW_RAISE_1, poseWrongTTS.getLElbowRaise1());
        values.put(KEY_LEFT_ELBOW_RAISE_2, poseWrongTTS.getLElbowRaise2());
        values.put(KEY_RIGHT_HEEL_ON_GROUND_1, poseWrongTTS.getRHeelOnGround1());
        values.put(KEY_RIGHT_HEEL_ON_GROUND_2, poseWrongTTS.getRHeelOnGround2());
        values.put(KEY_LEFT_HEEL_ON_GROUND_1, poseWrongTTS.getLHeelOnGround1());
        values.put(KEY_LEFT_HEEL_ON_GROUND_2, poseWrongTTS.getLHeelOnGround2());
        values.put(KEY_BODY_VERTICAL, poseWrongTTS.getBodyVertical());

        // updating row
        return db.update(TABLE_POSE_WRONG_TTS, values, KEY_POSE_NAME + " = ?",
                new String[] { String.valueOf(poseWrongTTS.getPoseName()) });
    }

    // Deleting single contact
    public void deletePoseWrongTTS(PoseWrongTTS poseWrongTTS) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_POSE_WRONG_TTS, KEY_POSE_NAME + " = ?",
                new String[] { String.valueOf(poseWrongTTS.getPoseName()) });
        db.close();
    }

    // Getting contacts Count
    public int getPoseWrongTTSCount() {
        String countQuery = "SELECT  * FROM " + TABLE_POSE_WRONG_TTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}
