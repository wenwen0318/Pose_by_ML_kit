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

    private static Context context;
    private final Pose pose;
    private static double[] gravity = {0,0,0};
    private final String cardView;
    private final int userLevel;
    static int level;
    static Boolean getPose;
    //各角度狀態 0:正確 1:小於 2:大於
    static int[] status = {0, 0, 0, 0, 0,   0, 0, 0, 0, 0,
                            0, 0, 0, 0, 0,  0, 0, 0, 0, 0,
                            0, 0, 0};
    static double[] angleArray = new double[23];
    static ArrayList<String> poseStandard;

    PoseCalculate(
            Context context,
            Pose pose,
            double[] gravity,
            String cardView,
            int userLevel) {
        this.context = context;
        this.pose = pose;
        this.gravity = gravity;
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
        PoseLandmark leftPinky = pose.getPoseLandmark(PoseLandmark.LEFT_PINKY);
        PoseLandmark rightPinky = pose.getPoseLandmark(PoseLandmark.RIGHT_PINKY);
        PoseLandmark leftIndex = pose.getPoseLandmark(PoseLandmark.LEFT_INDEX);
        PoseLandmark rightIndex = pose.getPoseLandmark(PoseLandmark.RIGHT_INDEX);
        PoseLandmark leftThumb = pose.getPoseLandmark(PoseLandmark.LEFT_THUMB);
        PoseLandmark rightThumb = pose.getPoseLandmark(PoseLandmark.RIGHT_THUMB);

        double rShoulderX = pose.getPoseLandmark(PoseLandmark.RIGHT_SHOULDER).getPosition().x;
        double rShoulderY = pose.getPoseLandmark(PoseLandmark.RIGHT_SHOULDER).getPosition().y;
        double lShoulderX = pose.getPoseLandmark(PoseLandmark.LEFT_SHOULDER).getPosition().x;
        double lShoulderY = pose.getPoseLandmark(PoseLandmark.LEFT_SHOULDER).getPosition().y;
        double rElbowX = pose.getPoseLandmark(PoseLandmark.RIGHT_ELBOW).getPosition().x;
        double rElbowY = pose.getPoseLandmark(PoseLandmark.RIGHT_ELBOW).getPosition().y;
        double lElbowX = pose.getPoseLandmark(PoseLandmark.LEFT_ELBOW).getPosition().x;
        double lElbowY = pose.getPoseLandmark(PoseLandmark.LEFT_ELBOW).getPosition().y;
        double rWristX = pose.getPoseLandmark(PoseLandmark.RIGHT_WRIST).getPosition().x;
        double rWristY = pose.getPoseLandmark(PoseLandmark.RIGHT_WRIST).getPosition().y;
        double lWristX = pose.getPoseLandmark(PoseLandmark.LEFT_WRIST).getPosition().x;
        double lWristY = pose.getPoseLandmark(PoseLandmark.LEFT_WRIST).getPosition().y;
        double rHipX = pose.getPoseLandmark(PoseLandmark.RIGHT_HIP).getPosition().x;
        double rHipY = pose.getPoseLandmark(PoseLandmark.RIGHT_HIP).getPosition().y;
        double lHipX = pose.getPoseLandmark(PoseLandmark.LEFT_HIP).getPosition().x;
        double lHipY = pose.getPoseLandmark(PoseLandmark.LEFT_HIP).getPosition().y;
        double rKneeX = pose.getPoseLandmark(PoseLandmark.RIGHT_KNEE).getPosition().x;
        double rKneeY = pose.getPoseLandmark(PoseLandmark.RIGHT_KNEE).getPosition().y;
        double lKneeX = pose.getPoseLandmark(PoseLandmark.LEFT_KNEE).getPosition().x;
        double lKneeY = pose.getPoseLandmark(PoseLandmark.LEFT_KNEE).getPosition().y;
        double rAnkleX = pose.getPoseLandmark(PoseLandmark.RIGHT_ANKLE).getPosition().x;
        double rAnkleY = pose.getPoseLandmark(PoseLandmark.RIGHT_ANKLE).getPosition().y;
        double lAnkleX = pose.getPoseLandmark(PoseLandmark.LEFT_ANKLE).getPosition().x;
        double lAnkleY = pose.getPoseLandmark(PoseLandmark.LEFT_ANKLE).getPosition().y;
        double rHeelX = pose.getPoseLandmark(PoseLandmark.RIGHT_HEEL).getPosition().x;
        double rHeelY = pose.getPoseLandmark(PoseLandmark.RIGHT_HEEL).getPosition().y;
        double lHeelX = pose.getPoseLandmark(PoseLandmark.LEFT_HEEL).getPosition().x;
        double lHeelY = pose.getPoseLandmark(PoseLandmark.LEFT_HEEL).getPosition().y;
        double rFootIndexX = pose.getPoseLandmark(PoseLandmark.RIGHT_FOOT_INDEX).getPosition().x;
        double rFootIndexY = pose.getPoseLandmark(PoseLandmark.RIGHT_FOOT_INDEX).getPosition().y;
        double lFootIndexX = pose.getPoseLandmark(PoseLandmark.LEFT_FOOT_INDEX).getPosition().x;
        double lFootIndexY = pose.getPoseLandmark(PoseLandmark.LEFT_FOOT_INDEX).getPosition().y;
        double rPinkyX = pose.getPoseLandmark(PoseLandmark.RIGHT_PINKY).getPosition().x;
        double rPinkyY = pose.getPoseLandmark(PoseLandmark.RIGHT_PINKY).getPosition().y;
        double lPinkyX = pose.getPoseLandmark(PoseLandmark.LEFT_PINKY).getPosition().x;
        double lPinkyY = pose.getPoseLandmark(PoseLandmark.LEFT_PINKY).getPosition().y;

        angleArray[0] = getAngle(rShoulderX, rShoulderY, rHipX, rHipY, rKneeX, rKneeY); //rightHip
        angleArray[1] = getAngle(lShoulderX, lShoulderY, lHipX, lHipY, lKneeX, lKneeY); //leftHip
        angleArray[2] = getAngle(rHipX, rHipY, rKneeX, rKneeY, rAnkleX, rAnkleY); //rightKnee
        angleArray[3] = getAngle(lHipX, lHipY, lKneeX, lKneeY, lAnkleX, lAnkleY); //leftKnee
        angleArray[4] = getAngle(rShoulderX, rShoulderY, rElbowX, rElbowY, rWristX, rWristY); //rightElbow
        angleArray[5] = getAngle(lShoulderX, lShoulderY, lElbowX, lElbowY, lWristX, lWristY); //leftElbow
        angleArray[6] = getAngle(rElbowX, rElbowY, rShoulderX, rShoulderY, rHipX, rHipY); //rightArmpit
        angleArray[7] = getAngle(lElbowX, lElbowY, lShoulderX, lShoulderY, lHipX, lHipY); //leftArmpit
        angleArray[8] = getAngle(rElbowX, rElbowY, rShoulderX, rElbowY, lShoulderX, lShoulderY); //rightShoulder
        angleArray[9] = getAngle(lElbowX, lElbowY, lShoulderX, lShoulderY, rShoulderX, rShoulderY); //leftShoulder
        angleArray[10] = isKneeOverToe(getAngle(rKneeX, rKneeY, rFootIndexX, rFootIndexY, rHeelX, rHeelY)); //rightKneeOverToe
        angleArray[11] = isKneeOverToe(getAngle(lKneeX, lKneeY, lFootIndexX, lFootIndexY, lHeelX, lHeelY));  //leftKneeOverToe
        // rThighHorizontal
        angleArray[12] = getAngleGround( rKneeX - rHipX , rKneeY - rHipY);
        // lThighHorizontal
        angleArray[13] = getAngleGround( lKneeX - lHipX , lKneeY - lHipY);
        angleArray[14] = getAngle(rHipX, lFootIndexY, rHipX, rHipY, rKneeX, rKneeY); // rightCrotch
        angleArray[15] = getAngle(lHipX, rFootIndexY, lHipX, lHipY, lKneeX, lKneeY); // leftCrotch
        // rightShoulderGround (shoulderGroundFootIndex)
        angleArray[16] = getAngle(rShoulderX, rShoulderY, rWristX, rFootIndexY, rFootIndexX, rFootIndexY);
        // leftShoulderGround (shoulderGroundFootIndex)
        angleArray[17] = getAngle(lShoulderX, lShoulderY, lWristX, lFootIndexY, lFootIndexX, lFootIndexY);
        // rElbowRaise
        angleArray[18] = getAngle(rElbowX, rElbowY, rShoulderX, rShoulderY, rShoulderX, rFootIndexY);
        // lElbowRaise
        angleArray[19] = getAngle(lElbowX, lElbowY, lShoulderX, lShoulderY, lShoulderX, lFootIndexY);
        angleArray[20] = heelOnGroundTransform(Math.abs(rFootIndexY - rHeelY)); // rHeelOnGround
        angleArray[21] = heelOnGroundTransform(Math.abs(lFootIndexY - lHeelY)); // lHeelOnGround
//        angleArray[22] = getAngle(rElbowX, rElbowY, rWristX, rPinkyY, rPinkyX, rPinkyY) + angleArray[4]; // rArmHorizontal
//        angleArray[23] = getAngle(lElbowX, lElbowY, lWristX, lPinkyY, lPinkyX, lPinkyY) + angleArray[5]; // lArmHorizontal
        // bodyVertical (shoulderGroundHorizontal, hipGroundHorizontal)
        angleArray[22] = getAngleGround((rShoulderX + lShoulderX)/2 - (rHipX + lHipX)/2,(rShoulderY + lShoulderY)/2 -(rHipY + lHipY)/2);
        if (cardView.equals("DownDog")){
            if (angleArray[0] > 80) angleArray[0] = 80;
        }
        System.out.println("rShoulder:" + rShoulderX + "," + rShoulderY);
        System.out.println("lShoulder:" + lShoulderX + "," + lShoulderY);
        System.out.println("rHip:" + rHipX + "," + rHipY);
        System.out.println("lHip:" + lHipX + "," + lHipY);
    }

    static double getAngle(double firstPointX, double firstPointY, double midPointX, double midPointY, double lastPointX, double lastPointY) {
        double result =
                Math.toDegrees(
                        atan2(lastPointY - midPointY, lastPointX - midPointX) -
                                atan2(firstPointY - midPointY, firstPointX - midPointX)
                );
        result = Math.abs(result); // Angle should never be negative
        if (result > 180) {
            result = (360.0 - result); // Always get the acute representation of the angle
        }
        return result;
    }

    public boolean isGetSkeleton(){
        if(getPose){
            return getPose;
        }
        else{
            return getPose;
        }
    }
    static double isKneeOverToe(double angle){
        if (angle <= 90){
            return 90;
        }
        else return 180;
    }

    static double heelOnGroundTransform(double coordinate){
        // if on ground transform angle to 90
        System.out.println("coordinate : "+coordinate);
        if (coordinate <= 7){
            return 90;
        }
        else return 180;
    }

    static double getAngleGround(double vecX, double vecY){
        //標準化
        double vecLen = Math.sqrt(vecX * vecX + vecY * vecY);
        vecX = vecX / vecLen;
        vecY = vecY / vecLen;
        double graLen = Math.sqrt(gravity[0] * gravity[0] + gravity[1] * gravity[1]);
        gravity[0] = gravity[0] / graLen;
        gravity[1] = gravity[1] / graLen;

        //求重力與手機y軸夾角
        double angle = getAngle(0,1,0,0,gravity[0],gravity[1]);
        angle = Math.toRadians(angle * 2);
        //vec. rotate
        double vecXr;
        double vecYr;
        if (gravity[0] > 0){
            vecXr = vecX * Math.cos(angle) + vecY * Math.sin(angle);
            vecYr = vecY * Math.cos(angle) - vecX * Math.sin(angle);
        }
        else {
            vecXr = vecX * Math.cos(angle) - vecY * Math.sin(angle);
            vecYr = vecX * Math.sin(angle) + vecY * Math.cos(angle);
        }

        //gra & vec angle
        angle = getAngle(vecXr,vecYr,0,0,gravity[0],gravity[1]);

        if (angle >= 75 && angle <= 105){
            return 90;
        }
        else if(angle < 15 || angle > 165){
            return 180;
        }
        else
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

    public double[] getAngle() {return angleArray;}

}