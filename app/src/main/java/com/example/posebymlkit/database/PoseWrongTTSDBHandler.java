package com.example.posebymlkit.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.posebymlkit.R;

import java.util.ArrayList;
import java.util.List;

public class PoseWrongTTSDBHandler extends SQLiteOpenHelper{

    private Context mContext;

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
        mContext = context;
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

        String right = mContext.getString(R.string.right);      //右
        String left  = mContext.getString(R.string.left);       //左
        String arm = mContext.getString(R.string.arm);          //手臂
        String hip = mContext.getString(R.string.hip);          //臀部
        String knee = mContext.getString(R.string.knee);        //膝
        String elbow = mContext.getString(R.string.elbow);      //手肘
        String armpit = mContext.getString(R.string.armpit);    //腋下
        String shoulder = mContext.getString(R.string.shoulder);//肩
        String tiptop = mContext.getString(R.string.tiptop);    //腳尖
        String thigh = mContext.getString(R.string.thigh);      //大腿
        String crotch = mContext.getString(R.string.crotch);
        String leg = mContext.getString(R.string.leg);          //腿
        String both = mContext.getString(R.string.both);
        String body = mContext.getString(R.string.body);        //身體
        String parallel_floor = mContext.getString(R.string.parallel_to_the_floor);              //與地板平行
        String perpendicular_floor = mContext.getString(R.string.perpendicular_to_the_floor);    //與地板垂直
        String not_exceed_the_toes = mContext.getString(R.string.not_exceed_the_toes);                //不要超過腳尖
        String keep_flat = mContext.getString(R.string.keep_flat);      //保持平坦
        String straight = mContext.getString(R.string.straight);        //伸直
        String and = mContext.getString(R.string.and);
        String in_line_with_body = mContext.getString(R.string.in_line_with_body);              //與身體呈直線
        String perpendicular_body = mContext.getString(R.string.perpendicular_to_the_body);     //與身體垂直
        String degree_90 = mContext.getString(R.string.to_90_degree);//成90度角
        String heels_ground = mContext.getString(R.string.heels_on_the_ground);
        String raise_arm = mContext.getString(R.string.raise_arms);
        String forward_arm = mContext.getString(R.string.extend_arms_forward);

        this.updatePoseWrongTTS(new PoseWrongTTS("Warrior2",
                null,null,
                null,null,
                null,null,
                left + knee + straight,left + knee + straight,
                right + arm + straight,right + arm + straight,
                left + arm + straight,left + arm + straight,
                null, null,
                null, null,
                right + shoulder + keep_flat, right + shoulder + keep_flat,
                left + shoulder + keep_flat, left + shoulder + keep_flat,
                right + knee + not_exceed_the_toes, right + knee + not_exceed_the_toes,
                null, null,
                right + thigh + parallel_floor, right + thigh + parallel_floor,
                null, null,
                null, null,
                "左腳張開往下坐", "左腳張太開",
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                body + perpendicular_floor
        ));

        this.addPoseWrongTTS(new PoseWrongTTS("Plank",
                null,null,
                hip + in_line_with_body,hip + in_line_with_body,
                null,null,
                knee + in_line_with_body,knee + in_line_with_body,
                null,null,
                arm + straight, arm + straight,
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
                arm + perpendicular_floor, arm + perpendicular_floor,
                null, null,
                null, null,
                null, null,
                null, null,
                null
        ));
        this.addPoseWrongTTS(new PoseWrongTTS("Goddess",
                null,null,
                null,null,
                right + leg + degree_90,right + leg + degree_90,
                left + leg + degree_90,left + leg + degree_90,
                right + arm + degree_90,right + arm + degree_90,
                left + arm + degree_90, left + arm + degree_90,
                null, null,
                null, null,
                right + arm + parallel_floor, right + arm + parallel_floor,
                left + arm + parallel_floor, left + arm + parallel_floor,
                right+ knee + not_exceed_the_toes, right+ knee + not_exceed_the_toes,
                left+ knee + not_exceed_the_toes, left+ knee + not_exceed_the_toes,
                right + thigh + parallel_floor, right + thigh + parallel_floor,
                left + thigh + parallel_floor, left + thigh + parallel_floor,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                body + perpendicular_floor
        ));
        this.addPoseWrongTTS(new PoseWrongTTS("Chair",
                null,null,
                null,null,
                thigh + degree_90,thigh + degree_90,
                null,null,
                arm + straight,arm + straight,
                null, null,
                leg + perpendicular_body, leg + perpendicular_body,
                null, null,
                null, null,
                null, null,
                knee + not_exceed_the_toes, knee + not_exceed_the_toes,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                raise_arm, raise_arm,
                null, null,
                null, null,
                null, null,
                null
        ));
        this.addPoseWrongTTS(new PoseWrongTTS("DownDog",
                null,null,
                null,null,
                thigh + straight,thigh + straight,
                null,null,
                arm + straight,arm + straight,
                null, null,
                forward_arm, forward_arm,
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
                heels_ground, heels_ground,
                null, null,
                null
        ));
        this.addPoseWrongTTS(new PoseWrongTTS("Four_Limbed_Staff",
                body + parallel_floor,body + parallel_floor,
                null,null,
                knee + straight,knee + straight,
                null,null,
                arm + degree_90,arm + degree_90,
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
                thigh + straight,thigh + straight,
                null,null,
                arm + straight,arm + straight,
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
                thigh + perpendicular_floor,thigh + perpendicular_floor,
                null,null,
                thigh + straight,thigh + straight,
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
                right + leg + straight,right + leg + straight,
                left + leg + straight,left + leg + straight,
                right + arm + straight,right + arm + straight,
                left + arm + straight, left + arm + straight,
                null, null,
                null, null,
                right + arm + parallel_floor,right + arm + parallel_floor ,
                left + arm + parallel_floor, left + arm + parallel_floor,
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
                body + perpendicular_floor
        ));
        this.addPoseWrongTTS(new PoseWrongTTS("Tree",
                null,null,
                left + arm + straight,left + arm + straight,
                null,null,
                left + knee + straight, left + knee + straight,
                right + elbow + straight,right + elbow + straight,
                left + elbow + straight, left + elbow + straight,
                right + arm + straight, right + arm + straight,
                left + arm + straight, left + arm + straight,
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
                body + perpendicular_floor
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

        if (!poseWrongTTSExist(poseWrongTTS.getPoseName())){
            addPoseWrongTTS(poseWrongTTS);
        }
        // updating row
        return db.update(TABLE_POSE_WRONG_TTS, values, KEY_POSE_NAME + " = ?",
                new String[] { String.valueOf(poseWrongTTS.getPoseName()) });
    }

    public List<String> getAllPoseWrongTTS() {
        List<String> PoseWrongTTSs = new ArrayList<>();

        String selectQuery = "SELECT " + KEY_POSE_NAME + " FROM " + TABLE_POSE_WRONG_TTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                PoseWrongTTSs.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        return PoseWrongTTSs;
    }

    public boolean poseWrongTTSExist(String poseName) {
        List<String> PoseWrongTTSs = getAllPoseWrongTTS();
        return PoseWrongTTSs.contains(poseName);
    }

}
