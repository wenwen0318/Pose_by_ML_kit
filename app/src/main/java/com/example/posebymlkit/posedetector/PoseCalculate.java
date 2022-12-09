package com.example.posebymlkit.posedetector;

import com.example.posebymlkit.database.PoseStandardDBHandler;
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
    static int[] status = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //各角度狀態 0:正確 1:小於 2:大於
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
    static double bodyVertical;
    static PoseLandmark leftShoulder;
    static PoseLandmark rightShoulder;
    static PoseLandmark leftElbow;
    static PoseLandmark rightElbow;
    static PoseLandmark leftWrist;
    static PoseLandmark rightWrist;
    static PoseLandmark leftHip;
    static PoseLandmark rightHip;
    static PoseLandmark leftKnee;
    static PoseLandmark rightKnee;
    static PoseLandmark leftAnkle;
    static PoseLandmark rightAnkle;
    static PoseLandmark leftHeel;
    static PoseLandmark rightHeel;
    static PoseLandmark leftFootIndex;
    static PoseLandmark rightFootIndex;

    static double[] angleArray = new double[15];
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

        PoseStandardDBHandler db = new PoseStandardDBHandler(context);
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

        leftShoulder = pose.getPoseLandmark(PoseLandmark.LEFT_SHOULDER);
        rightShoulder = pose.getPoseLandmark(PoseLandmark.RIGHT_SHOULDER);
        leftElbow = pose.getPoseLandmark(PoseLandmark.LEFT_ELBOW);
        rightElbow = pose.getPoseLandmark(PoseLandmark.RIGHT_ELBOW);
        leftWrist = pose.getPoseLandmark(PoseLandmark.LEFT_WRIST);
        rightWrist = pose.getPoseLandmark(PoseLandmark.RIGHT_WRIST);
        leftHip = pose.getPoseLandmark(PoseLandmark.LEFT_HIP);
        rightHip = pose.getPoseLandmark(PoseLandmark.RIGHT_HIP);
        leftKnee = pose.getPoseLandmark(PoseLandmark.LEFT_KNEE);
        rightKnee = pose.getPoseLandmark(PoseLandmark.RIGHT_KNEE);
        leftAnkle = pose.getPoseLandmark(PoseLandmark.LEFT_ANKLE);
        rightAnkle = pose.getPoseLandmark(PoseLandmark.RIGHT_ANKLE);

        PoseLandmark leftPinky = pose.getPoseLandmark(PoseLandmark.LEFT_PINKY);
        PoseLandmark rightPinky = pose.getPoseLandmark(PoseLandmark.RIGHT_PINKY);
        PoseLandmark leftIndex = pose.getPoseLandmark(PoseLandmark.LEFT_INDEX);
        PoseLandmark rightIndex = pose.getPoseLandmark(PoseLandmark.RIGHT_INDEX);
        PoseLandmark leftThumb = pose.getPoseLandmark(PoseLandmark.LEFT_THUMB);
        PoseLandmark rightThumb = pose.getPoseLandmark(PoseLandmark.RIGHT_THUMB);
        leftHeel = pose.getPoseLandmark(PoseLandmark.LEFT_HEEL);
        rightHeel = pose.getPoseLandmark(PoseLandmark.RIGHT_HEEL);
        leftFootIndex = pose.getPoseLandmark(PoseLandmark.LEFT_FOOT_INDEX);
        rightFootIndex = pose.getPoseLandmark(PoseLandmark.RIGHT_FOOT_INDEX);

        // output[0] ~ output[9]

        angleArray[0] = getAngle(rightShoulder,rightHip,rightKnee); //rightHip
        angleArray[1] = getAngle(leftShoulder,leftHip,leftKnee);    //leftHip
        angleArray[2] = getAngle(rightHip,rightKnee,rightAnkle);    //rightKnee
        angleArray[3] = getAngle(leftHip,leftKnee, leftAnkle);      //leftKnee
        angleArray[4] = getAngle(rightShoulder,rightElbow,rightWrist);  //rightArmpit
        angleArray[5] = getAngle(leftShoulder,leftElbow,leftWrist);     //leftArmpit
        angleArray[6] = getAngle(rightElbow,rightShoulder,rightHip);    //rightKnee
        angleArray[7] = getAngle(leftElbow,leftShoulder,leftHip);       //leftKnee
        angleArray[8] = getAngle(rightElbow,rightShoulder,leftShoulder);    //rightHip
        angleArray[9] = getAngle(leftElbow,leftShoulder,rightShoulder);     //leftHip
        angleArray[10] = 0; //rightKnee
        angleArray[11] = isKneeOverToe("right");   //rightKneeOverToe
        angleArray[12] = isKneeOverToe("left");    //leftKneeOverToe
        angleArray[13] = 0;
        angleArray[14] = 0;


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
        bodyVertical = bodyVertical();
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
    // 0 ist genau, 1 ist falsch
    static double bodyVertical(){
        // 鎖骨
        double clavicleMidX = (rightShoulder.getPosition().x + leftShoulder.getPosition().x)/2;
        double clavicleMidY = (rightShoulder.getPosition().y + leftShoulder.getPosition().y)/2;
        double clavicleToGroundX = clavicleMidX;
        double rightClavicleToGroundY = rightFootIndex.getPosition().y;
        double leftClavicleToGroundY = rightFootIndex.getPosition().y;
        double rightClavicleToGroundAngle =
                Math.toDegrees(
                        atan2(clavicleMidY - rightClavicleToGroundY,
                                clavicleMidX - clavicleToGroundX)
                                - atan2(rightFootIndex.getPosition().y - rightClavicleToGroundY,
                                rightFootIndex.getPosition().x - clavicleToGroundX));
        rightClavicleToGroundAngle = Math.abs(rightClavicleToGroundAngle);
        if (rightClavicleToGroundAngle > 180) {
            rightClavicleToGroundAngle = (360.0 - rightClavicleToGroundAngle);
        }
        double leftClavicleToGroundAngle =
                Math.toDegrees(
                        atan2(clavicleMidY - leftClavicleToGroundY,
                                clavicleMidX - clavicleToGroundX)
                                - atan2(leftFootIndex.getPosition().y - leftClavicleToGroundY ,
                                leftFootIndex.getPosition().x - clavicleToGroundX));
        leftClavicleToGroundAngle = Math.abs(leftClavicleToGroundAngle);
        if (leftClavicleToGroundAngle > 180) {
            leftClavicleToGroundAngle = (360.0 - leftClavicleToGroundAngle);
        }
        double clavicleToGroundAngle = rightClavicleToGroundAngle + leftClavicleToGroundAngle;

        System.out.println("clavicleToGroundAngle : "+clavicleToGroundAngle);
        return clavicleToGroundAngle;
    }
    static double isKneeOverToe(String direction){
        double angle;
        if (direction.equals("right")){
            angle = getAngle(rightKnee, rightFootIndex, rightHeel);
        }
        else angle = getAngle(leftKnee, leftFootIndex, leftHeel);
        if (angle <= 90){
            return 90;
        }
        else return 180;
    }
    static double getSpecialAngle(String angle){
        if(angle.equals("rightHipKneeGroundAngle")) {
            double rightKneeToGroundX = rightKnee.getPosition().x;
            double rightKneeToGroundY = rightFootIndex.getPosition().y;
            double rightHipKneeGroundAngle =
                    Math.toDegrees(
                            atan2(rightHip.getPosition().y - rightKnee.getPosition().y,
                                    rightHip.getPosition().x - rightKnee.getPosition().x)
                                    - atan2(rightKneeToGroundY - rightKnee.getPosition().y,
                                    rightKneeToGroundX - rightKnee.getPosition().x)
                    );
            rightHipKneeGroundAngle = Math.abs(rightHipKneeGroundAngle);
            if (rightHipKneeGroundAngle > 180) {
                rightHipKneeGroundAngle = (360.0 - rightHipKneeGroundAngle);
            }
            return rightHipKneeGroundAngle;
        }
        else if(angle.equals("rightKneeGroundAngle")){
            double rightKneeToGroundX = rightKnee.getPosition().x;
            double rightKneeToGroundY = rightFootIndex.getPosition().y;
            double rightKneeGroundAngle =
                    Math.toDegrees(
                            atan2(rightKnee.getPosition().y - rightKneeToGroundY,
                                    rightKnee.getPosition().x - rightKneeToGroundX)
                                    - atan2(leftFootIndex.getPosition().y - rightKneeToGroundY,
                                    leftFootIndex.getPosition().x - rightKneeToGroundX)
                    );
            rightKneeGroundAngle = Math.abs(rightKneeGroundAngle);
            if (rightKneeGroundAngle > 180) {
                rightKneeGroundAngle = (360.0 - rightKneeGroundAngle);
            }
            return rightKneeGroundAngle;
        }
        else if(angle.equals("leftCrotchAngle")){
            double leftHipToGroundX = leftHip.getPosition().x;
            double leftHipToGroundY = rightFootIndex.getPosition().y;
            double leftCrotchAngle =
                    Math.toDegrees(
                            atan2(leftHipToGroundY - leftHip.getPosition().y,
                                    leftHipToGroundX - leftHip.getPosition().x)
                                    - atan2(leftKnee.getPosition().y - leftHip.getPosition().y,
                                    leftKnee.getPosition().x - leftHip.getPosition().x)
                    );
            leftCrotchAngle = Math.abs(leftCrotchAngle);
            if (leftCrotchAngle > 180) {
                leftCrotchAngle = (360.0 - leftCrotchAngle);
            }
            return leftCrotchAngle;
        }
        return 0;
    }

    static void check() {
        for (int i = 0; i < poseStandard.size(); i++) {
            String standardAngle = poseStandard.get(i);
            Log.d("calc.check(): ", i + ":" + poseStandard.get(i) + "&" + angleArray[i]);
            if (standardAngle != null) {
                int angle = Integer.parseInt(standardAngle);
                if (angle - 5 * level > angleArray[i]) {
                    Log.d("status:", i + "=1");
                    status[i] = 1;
                } else if (angle + 5 * level < angleArray[i]) {
                    Log.d("status:", i + "=2");
                    status[i] = 2;
                } else {
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