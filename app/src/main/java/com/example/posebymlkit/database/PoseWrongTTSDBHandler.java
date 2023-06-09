package com.example.posebymlkit.database;

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
    private static final String KEY_ANKLE_LONG_THEN_SHOULDER = "ankleLongThenShoulder";

    public PoseWrongTTSDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_POSE_WRONG_TTS + "("
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
                + KEY_ANKLE_LONG_THEN_SHOULDER + " TEXT"
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
                null,
                null,
                null,
                left + knee + straight,
                right + arm + straight,
                left + arm + straight,
                null,
                null,
                right + shoulder + keep_flat,
                left + shoulder + keep_flat,
                right + knee + not_exceed_the_toes,
                null,
                right + thigh + parallel_floor,
                null,
                null,
                "左腳張開往下坐",
                null,
                null,
                null,
                null,
                null,
                null,
                body + perpendicular_floor,
                null
        ));

        this.addPoseWrongTTS(new PoseWrongTTS("Plank",
                null,
                hip + in_line_with_body,
                null,
                knee + in_line_with_body,
                null,
                arm + straight,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                arm + perpendicular_floor,
                null,
                null,
                null,
                null,
                null,null
        ));
        this.addPoseWrongTTS(new PoseWrongTTS("Goddess",
                null,
                null,
                right + leg + degree_90,
                left + leg + degree_90,
                right + arm + degree_90,
                left + arm + degree_90,
                null,
                null,
                right + arm + parallel_floor,
                left + arm + parallel_floor,
                right+ knee + not_exceed_the_toes,
                left+ knee + not_exceed_the_toes,
                right + thigh + parallel_floor,
                left + thigh + parallel_floor,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                body + perpendicular_floor,"腳與肩同寬"
        ));
        this.addPoseWrongTTS(new PoseWrongTTS("Chair",
                null,
                null,
                thigh + degree_90,
                null,
                arm + straight,
                null,
                leg + perpendicular_body,
                null,
                null,
                null,
                knee + not_exceed_the_toes,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                raise_arm,
                null,
                null,
                null,
                null,null
        ));
        this.addPoseWrongTTS(new PoseWrongTTS("DownDog",
                null,
                null,
                thigh + straight,
                null,
                arm + straight,
                null,
                forward_arm,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                heels_ground,
                null,
                null,null
        ));
        this.addPoseWrongTTS(new PoseWrongTTS("Four_Limbed_Staff",
                body + parallel_floor,
                null,
                knee + straight,
                null,
                arm + degree_90,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,null
        ));
        this.addPoseWrongTTS(new PoseWrongTTS("Boat",
                null,
                null,
                thigh + straight,
                null,
                arm + straight,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,null
        ));
        this.addPoseWrongTTS(new PoseWrongTTS("Rejuvenation",
                thigh + perpendicular_floor,
                null,
                thigh + straight,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,null
        ));
        this.addPoseWrongTTS(new PoseWrongTTS("Star",
                null,
                null,
                right + leg + straight,
                left + leg + straight,
                right + arm + straight,
                left + arm + straight,
                null,
                null,
                right + arm + parallel_floor,
                left + arm + parallel_floor,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                body + perpendicular_floor,"腳與肩同寬"
        ));
        this.addPoseWrongTTS(new PoseWrongTTS("Tree",
                null,
                left + arm + straight,
                null,
                left + knee + straight,
                right + elbow + straight,
                left + elbow + straight,
                right + arm + straight,
                left + arm + straight,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                body + perpendicular_floor,null
        ));
    }

    // code to add the new contact
    public void addPoseWrongTTS(PoseWrongTTS poseWrongTTS) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_POSE_NAME, poseWrongTTS.getPoseName()); // Contact Name
        values.put(KEY_RIGHT_HIP, poseWrongTTS.getRHip1());
        values.put(KEY_LEFT_HIP, poseWrongTTS.getLHip());
        values.put(KEY_RIGHT_KNEE, poseWrongTTS.getRKnee());
        values.put(KEY_LEFT_KNEE, poseWrongTTS.getLKnee());
        values.put(KEY_RIGHT_ELBOW, poseWrongTTS.getRElbow());
        values.put(KEY_LEFT_ELBOW, poseWrongTTS.getLElbow());
        values.put(KEY_RIGHT_ARMPIT, poseWrongTTS.getRArmpit());
        values.put(KEY_LEFT_ARMPIT, poseWrongTTS.getLArmpit());
        values.put(KEY_RIGHT_SHOULDER, poseWrongTTS.getRShoulder());
        values.put(KEY_LEFT_SHOULDER, poseWrongTTS.getLShoulder());
        values.put(KEY_RIGHT_KNEE_TOE, poseWrongTTS.getRKneeToe());
        values.put(KEY_LEFT_KNEE_TOE, poseWrongTTS.getLKneeToe());
        values.put(KEY_RIGHT_THIGH_HORIZONTAL, poseWrongTTS.getRThighHorizontal());
        values.put(KEY_LEFT_THIGH_HORIZONTAL, poseWrongTTS.getLThighHorizontal());
        values.put(KEY_RIGHT_CROTCH, poseWrongTTS.getRCrotch());
        values.put(KEY_LEFT_CROTCH, poseWrongTTS.getLCrotch());
        values.put(KEY_RIGHT_SHOULDER_GROUND, poseWrongTTS.getRShoulderGround());
        values.put(KEY_LEFT_SHOULDER_GROUND, poseWrongTTS.getLShoulderGround());
        values.put(KEY_RIGHT_ELBOW_RAISE, poseWrongTTS.getRElbowRaise());
        values.put(KEY_LEFT_ELBOW_RAISE, poseWrongTTS.getLElbowRaise());
        values.put(KEY_RIGHT_HEEL_ON_GROUND, poseWrongTTS.getRHeelOnGround());
        values.put(KEY_LEFT_HEEL_ON_GROUND, poseWrongTTS.getLHeelOnGround());
        values.put(KEY_BODY_VERTICAL, poseWrongTTS.getBodyVertical());
        values.put(KEY_ANKLE_LONG_THEN_SHOULDER, poseWrongTTS.getAnkleLongThenShoulder());

        // Inserting Row
        db.insert(TABLE_POSE_WRONG_TTS, null, values);
        db.close();
    }

    // code to get the single contact
    public PoseWrongTTS getPoseWrongTTS(String poseName) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_POSE_WRONG_TTS, new String[] {
                        KEY_POSE_NAME,
                        KEY_RIGHT_HIP,
                        KEY_LEFT_HIP,
                        KEY_RIGHT_KNEE,
                        KEY_LEFT_KNEE,
                        KEY_RIGHT_ELBOW,
                        KEY_LEFT_ELBOW,
                        KEY_RIGHT_ARMPIT,
                        KEY_LEFT_ARMPIT,
                        KEY_RIGHT_SHOULDER,
                        KEY_LEFT_SHOULDER,
                        KEY_RIGHT_KNEE_TOE,
                        KEY_LEFT_KNEE_TOE,
                        KEY_RIGHT_THIGH_HORIZONTAL,
                        KEY_LEFT_THIGH_HORIZONTAL,
                        KEY_RIGHT_CROTCH,
                        KEY_LEFT_CROTCH,
                        KEY_RIGHT_SHOULDER_GROUND,
                        KEY_LEFT_SHOULDER_GROUND,
                        KEY_RIGHT_ELBOW_RAISE,
                        KEY_LEFT_ELBOW_RAISE,
                        KEY_RIGHT_HEEL_ON_GROUND,
                        KEY_LEFT_HEEL_ON_GROUND,
                        KEY_BODY_VERTICAL,
                        KEY_ANKLE_LONG_THEN_SHOULDER
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
                cursor.getString(24)
        );
        // return contact
        return poseWrongTTS;
    }

    // code to update the single contact
    public int updatePoseWrongTTS(PoseWrongTTS poseWrongTTS) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_POSE_NAME, poseWrongTTS.getPoseName()); // Contact Name
        values.put(KEY_RIGHT_HIP, poseWrongTTS.getRHip1());
        values.put(KEY_LEFT_HIP, poseWrongTTS.getLHip());
        values.put(KEY_RIGHT_KNEE, poseWrongTTS.getRKnee());
        values.put(KEY_LEFT_KNEE, poseWrongTTS.getLKnee());
        values.put(KEY_RIGHT_ELBOW, poseWrongTTS.getRElbow());
        values.put(KEY_LEFT_ELBOW, poseWrongTTS.getLElbow());
        values.put(KEY_RIGHT_ARMPIT, poseWrongTTS.getRArmpit());
        values.put(KEY_LEFT_ARMPIT, poseWrongTTS.getLArmpit());
        values.put(KEY_RIGHT_SHOULDER, poseWrongTTS.getRShoulder());
        values.put(KEY_LEFT_SHOULDER, poseWrongTTS.getLShoulder());
        values.put(KEY_RIGHT_KNEE_TOE, poseWrongTTS.getRKneeToe());
        values.put(KEY_LEFT_KNEE_TOE, poseWrongTTS.getLKneeToe());
        values.put(KEY_RIGHT_THIGH_HORIZONTAL, poseWrongTTS.getRThighHorizontal());
        values.put(KEY_LEFT_THIGH_HORIZONTAL, poseWrongTTS.getLThighHorizontal());
        values.put(KEY_RIGHT_CROTCH, poseWrongTTS.getRCrotch());
        values.put(KEY_LEFT_CROTCH, poseWrongTTS.getLCrotch());
        values.put(KEY_RIGHT_SHOULDER_GROUND, poseWrongTTS.getRShoulderGround());
        values.put(KEY_LEFT_SHOULDER_GROUND, poseWrongTTS.getLShoulderGround());
        values.put(KEY_RIGHT_ELBOW_RAISE, poseWrongTTS.getRElbowRaise());
        values.put(KEY_LEFT_ELBOW_RAISE, poseWrongTTS.getLElbowRaise());
        values.put(KEY_RIGHT_HEEL_ON_GROUND, poseWrongTTS.getRHeelOnGround());
        values.put(KEY_LEFT_HEEL_ON_GROUND, poseWrongTTS.getLHeelOnGround());
        values.put(KEY_BODY_VERTICAL, poseWrongTTS.getBodyVertical());
        values.put(KEY_ANKLE_LONG_THEN_SHOULDER, poseWrongTTS.getAnkleLongThenShoulder());

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
