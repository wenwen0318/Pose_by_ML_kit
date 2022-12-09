package com.example.posebymlkit.posedetector;

import com.example.posebymlkit.database.DatabaseHandler;
import com.google.mlkit.vision.pose.Pose;
import com.google.mlkit.vision.pose.PoseLandmark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.atan2;

import android.content.Context;
import android.util.Log;

/** Draw the detected pose in preview. */
public class PoseCalculate{

    private Context context;
    private final Pose pose;
    private final String cardView;
    private final int userLevel;
    static int level;
    static Boolean getPose;
    static int[] status = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //各角度狀態 0:正確 1:<90 2:>90 3:!=180
    static double rightHipAngle;
    static double leftHipAngle;
    static double rightKneeAngle;
    static double leftKneeAngle;
    static double rightElbowAngle;
    static double leftElbowAngle;
    static double rightArmpitAngle;
    static double leftArmpitAngle;
    static double rightShoulderAngle;
    static double leftShoulderAngle;
    static double rightChestAngle;
    static double leftChestAngle;
    static double rightKneeToeAngle;
    static double leftKneeToeAngle;
    static boolean bodyVertical;

    static double[] angleArray = new double[13];
    static ArrayList<String> poseStandard;

    PoseCalculate(
            Context context,
            Pose pose,
            String cardView,
            int userLevel) {
        this.context = context;
        this.pose = pose;
        this.cardView = cardView;
        this.userLevel = userLevel;
        level = userLevel;

        //取關節點，如果有關節點就取得位置計算角度 存至angleList;
        angle();

        DatabaseHandler db = new DatabaseHandler(context);
        poseStandard = db.getPoseStandard(cardView).getPoseStandard();

        if (getPose){
            check();
        }
    }

    public void angle(){
        List<PoseLandmark> landmarks = pose.getAllPoseLandmarks();
        Arrays.fill(status,0);
        if (landmarks.isEmpty()) {
            getPose = false;
            return;
        }
        else { getPose = true; }
        PoseLandmark nose = pose.getPoseLandmark(PoseLandmark.NOSE);
        PoseLandmark leftEyeInner = pose.getPoseLandmark(PoseLandmark.LEFT_EYE_INNER);
        PoseLandmark leftEye = pose.getPoseLandmark(PoseLandmark.LEFT_EYE);
        PoseLandmark leftEyeOuter = pose.getPoseLandmark(PoseLandmark.LEFT_EYE_OUTER);
        PoseLandmark rightEyeInner = pose.getPoseLandmark(PoseLandmark.RIGHT_EYE_INNER);
        PoseLandmark rightEye = pose.getPoseLandmark(PoseLandmark.RIGHT_EYE);
        PoseLandmark rightEyeOuter = pose.getPoseLandmark(PoseLandmark.RIGHT_EYE_OUTER);
        PoseLandmark leftEar = pose.getPoseLandmark(PoseLandmark.LEFT_EAR);
        PoseLandmark rightEar = pose.getPoseLandmark(PoseLandmark.RIGHT_EAR);
        PoseLandmark leftMouth = pose.getPoseLandmark(PoseLandmark.LEFT_MOUTH);
        PoseLandmark rightMouth = pose.getPoseLandmark(PoseLandmark.RIGHT_MOUTH);

        PoseLandmark leftShoulder = pose.getPoseLandmark(PoseLandmark.LEFT_SHOULDER);
        PoseLandmark rightShoulder = pose.getPoseLandmark(PoseLandmark.RIGHT_SHOULDER);
        PoseLandmark leftElbow = pose.getPoseLandmark(PoseLandmark.LEFT_ELBOW);
        PoseLandmark rightElbow = pose.getPoseLandmark(PoseLandmark.RIGHT_ELBOW);
        PoseLandmark leftWrist = pose.getPoseLandmark(PoseLandmark.LEFT_WRIST);
        PoseLandmark rightWrist = pose.getPoseLandmark(PoseLandmark.RIGHT_WRIST);
        PoseLandmark leftHip = pose.getPoseLandmark(PoseLandmark.LEFT_HIP);
        PoseLandmark rightHip = pose.getPoseLandmark(PoseLandmark.RIGHT_HIP);
        PoseLandmark leftKnee = pose.getPoseLandmark(PoseLandmark.LEFT_KNEE);
        PoseLandmark rightKnee = pose.getPoseLandmark(PoseLandmark.RIGHT_KNEE);
        PoseLandmark leftAnkle = pose.getPoseLandmark(PoseLandmark.LEFT_ANKLE);
        PoseLandmark rightAnkle = pose.getPoseLandmark(PoseLandmark.RIGHT_ANKLE);

        PoseLandmark leftPinky = pose.getPoseLandmark(PoseLandmark.LEFT_PINKY);
        PoseLandmark rightPinky = pose.getPoseLandmark(PoseLandmark.RIGHT_PINKY);
        PoseLandmark leftIndex = pose.getPoseLandmark(PoseLandmark.LEFT_INDEX);
        PoseLandmark rightIndex = pose.getPoseLandmark(PoseLandmark.RIGHT_INDEX);
        PoseLandmark leftThumb = pose.getPoseLandmark(PoseLandmark.LEFT_THUMB);
        PoseLandmark rightThumb = pose.getPoseLandmark(PoseLandmark.RIGHT_THUMB);
        PoseLandmark leftHeel = pose.getPoseLandmark(PoseLandmark.LEFT_HEEL);
        PoseLandmark rightHeel = pose.getPoseLandmark(PoseLandmark.RIGHT_HEEL);
        PoseLandmark leftFootIndex = pose.getPoseLandmark(PoseLandmark.LEFT_FOOT_INDEX);
        PoseLandmark rightFootIndex = pose.getPoseLandmark(PoseLandmark.RIGHT_FOOT_INDEX);
        // output[0] ~ output[9]

        angleArray[0] = getAngle(rightShoulder,rightHip,rightKnee); //rightHip
        angleArray[1] = getAngle(leftShoulder,leftHip,leftKnee);    //leftHip
        angleArray[2] = getAngle(rightHip,rightKnee,rightAnkle); //rightKnee
        angleArray[3] = getAngle(leftHip,leftKnee, leftAnkle);    //leftKnee
        angleArray[4] = getAngle(rightShoulder,rightElbow,rightWrist); //rightHip
        angleArray[5] = getAngle(leftShoulder,leftElbow,leftWrist);    //leftHip
        angleArray[6] = getAngle(rightElbow,rightShoulder,rightHip); //rightKnee
        angleArray[7] = getAngle(leftElbow,leftShoulder,leftHip);   //leftKnee
        angleArray[8] = getAngle(rightElbow,rightShoulder,leftShoulder); //rightHip
        angleArray[9] = getAngle(leftElbow,leftShoulder,rightShoulder);    //leftHip
        angleArray[10] = 0; //rightKnee
        angleArray[11] = getAngle(rightKnee, rightFootIndex, rightHeel);    //leftKnee
        angleArray[12] = getAngle(leftKnee, leftFootIndex, leftHeel);    //leftKnee


        rightHipAngle = getAngle(rightShoulder,rightHip,rightKnee);
        leftHipAngle = getAngle(leftShoulder,leftHip,leftKnee);
        rightKneeAngle = getAngle(rightHip,rightKnee,rightAnkle);
        leftKneeAngle = getAngle(leftHip,leftKnee, leftAnkle);
        rightElbowAngle = getAngle(rightShoulder,rightElbow,rightWrist);
        leftElbowAngle = getAngle(leftShoulder,leftElbow,leftWrist);
        rightArmpitAngle = getAngle(rightElbow,rightShoulder,rightHip);
        leftArmpitAngle = getAngle(leftElbow,leftShoulder,leftHip);
        rightShoulderAngle = getAngle(rightElbow,rightShoulder,leftShoulder);
        leftShoulderAngle = getAngle(leftElbow,leftShoulder,rightShoulder);
        // output[10]
        bodyVertical = bodyVertical(leftShoulder,rightShoulder,rightHip,leftHip);
        // output[11] ~ [12]
        rightKneeToeAngle = getAngle(rightKnee, rightFootIndex, rightHeel);
        leftKneeToeAngle = getAngle(leftKnee, leftFootIndex, leftHeel);
        // others
        rightChestAngle = getAngle(leftShoulder,rightShoulder,rightHip);
        leftChestAngle = getAngle(rightShoulder,leftShoulder,leftHip);
    }

    static double getAngle(PoseLandmark firstPoint, PoseLandmark midPoint, PoseLandmark lastPoint) {
        double result =
                Math.toDegrees(
                        atan2(lastPoint.getPosition().y - midPoint.getPosition().y,
                                lastPoint.getPosition().x - midPoint.getPosition().x)
                                - atan2(firstPoint.getPosition().y - midPoint.getPosition().y,
                                firstPoint.getPosition().x - midPoint.getPosition().x));
        result = Math.abs(result); // Angle should never be negative
        if (result > 180) {
            result = (360.0 - result); // Always get the acute representation of the angle
        }
        return result;
    }

    // 0 垂直, 1 則否
    static boolean bodyVertical(PoseLandmark firstPoint, PoseLandmark secPoint, PoseLandmark thiPoint, PoseLandmark lastPoint){
        double upMiddleX = (firstPoint.getPosition().x + secPoint.getPosition().x)/2;
        double upMiddleY = (firstPoint.getPosition().y + secPoint.getPosition().y)/2;
        double downMiddleX = (thiPoint.getPosition().x + lastPoint.getPosition().x)/2;
        double downMiddleY = (thiPoint.getPosition().y + lastPoint.getPosition().y)/2;
        double upResult =
                Math.toDegrees(
                        atan2(firstPoint.getPosition().y - upMiddleY,
                                firstPoint.getPosition().x - upMiddleX)
                                - atan2(downMiddleY -  upMiddleY,
                                downMiddleX - upMiddleX));
        upResult = Math.abs(upResult);
        if (upResult > 180) {
            upResult = (360.0 - upResult);
        }
        double downResult =
                Math.toDegrees(
                        atan2(thiPoint.getPosition().y - downMiddleY,
                                thiPoint.getPosition().x - downMiddleX)
                                - atan2(upMiddleY -  downMiddleY,
                                upMiddleX - downMiddleX));
        downResult = Math.abs(downResult);
        if (downResult > 180) {
            downResult = (360.0 - downResult);
        }

        if((upResult < 90+(5*level)||upResult > 90-(5*level))&&(downResult < 90+(5*level)||downResult > 90-(5*level))){
            return true;
        }
        else{
            return false;
        }
    }

    static void check(){
        for (int i=0;i<poseStandard.size();i++){
            String standardAngle = poseStandard.get(i);
            Log.d("calc.check(): ", i + ":" + poseStandard.get(i) + "&" + angleArray[i]);
            if (standardAngle != null){
                int angle = Integer.parseInt(standardAngle);
                if (angle-5*level > angleArray[i]){
                    Log.d("status:", i + "=1");
                    status[i] = 1;
                }
                else if (angle+5*level < angleArray[i]){
                    Log.d("status:", i + "=2");
                    status[i] = 2;
                }
                else {
                    Log.d("status:", i + "=0");
                    status[i] = 0;
                }
            }
        }
    }

    public int[] getAngleStatus(){
        return status;
    }
}