package com.example.posebymlkit.posedetector;

import com.google.mlkit.vision.pose.Pose;
import com.google.mlkit.vision.pose.PoseLandmark;
import java.util.List;

import static java.lang.Math.atan2;

/** Draw the detected pose in preview. */
public class PoseCalculate{

    private final Pose pose;
    private final String poseName;
    private final int userLevel;
    static int level;
    static Boolean getPose;
    static int[] output = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
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
    static boolean bodyVertical;
    static boolean aaa;

    PoseCalculate(
            Pose pose,
            String poseName,
            int userLevel) {
        this.pose = pose;
        this.poseName = poseName;
        this.userLevel = userLevel;
        level = userLevel;
        angle();
        if (getPose){
            switch (poseName){
                case "Warrior2":
                    warrior2();
                    break;
                default:
                    break;
            }
        }

    }


    public void angle(){
        List<PoseLandmark> landmarks = pose.getAllPoseLandmarks();
        if (landmarks.isEmpty()) {
            getPose = false;
            return;
        }
        else {getPose = true;}
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
        rightHipAngle = getAngle(rightShoulder,rightHip,rightKnee);
        leftHipAngle = getAngle(leftShoulder,leftHip,leftKnee);
        rightKneeAngle = getAngle(rightHip,rightKnee,rightAnkle);
        leftKneeAngle = getAngle(leftHip,leftKnee, leftAnkle);
        rightElbowAngle = getAngle(rightShoulder,rightElbow,rightWrist);
        leftElbowAngle = getAngle(leftShoulder,leftElbow,leftWrist);
        rightArmpitAngle = getAngle(rightEar,rightShoulder,rightHip);
        leftArmpitAngle = getAngle(leftElbow,leftShoulder,leftHip);
        rightShoulderAngle = getAngle(rightElbow,rightShoulder,leftShoulder);
        leftShoulderAngle = getAngle(leftElbow,leftShoulder,rightShoulder);
        // output[10]
        bodyVertical = bodyVertical(leftShoulder,rightShoulder,rightHip,leftHip);
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

        System.out.println(upResult+" "+downResult);

        if((upResult < 90+(5*level)||upResult > 90-(5*level))&&(downResult < 90+(5*level)||downResult > 90-(5*level))){
            return true;
        }
        else{
            return false;
        }
    }
    static void warrior2(){
        // standard 1 : rightKneeAngle==90°
        if(rightKneeAngle > (90+5*level)){
            output[2] = 2;
        }
        else if(rightKneeAngle < (90-5*level)){
            output[2] = 1;
        }
        else{
            output[2] = 0;
        }
        // standard 2 : leftKneeAngle==180°
        if(leftKneeAngle < (180-5*level)){
            output[3] = 3;
        }
        else{
            output[3] = 0;
        }
        // standard 3 : rightShoulderAngle==180°
        if(rightShoulderAngle < (180-5*level)){
            output[8] = 3;
        }
        else{
            output[8] = 0;
        }
        // standard 4 : leftShoulderAngle==180°
        if(leftShoulderAngle < (180-5*level)){
            output[9] = 3;
        }
        else{
            output[9] = 0;
        }
        // standard 5 : whether body vertical is
        if(bodyVertical){
            output[10] = 0;
        }
        else {
            output[10] = 1;
        }

        output();
    }

    static void output(){
        for(int i=0;i<11;i++){
            if(output[i]==1){
                switch(i){
                    case 0 : System.out.println("rightHipAngle is small => 右身太下去");break;
                    case 1 : System.out.println("leftHipAngle is small => 左身太下去");break;
                    case 2 : System.out.println("rightKneeAngle is small => 右膝蹲太低");break;
                    case 3 : System.out.println("leftKneeAngle is small => 左膝蹲太低");break;
                    case 4 : System.out.println("rightElbowAngle is small => ");break;
                    case 5 : System.out.println("leftElbowAngle is small => ");break;
                    case 6 : System.out.println("rightArmpitAngle is small => 右腋下太收");break;
                    case 7 : System.out.println("leftArmpitAngle is small => 左腋下太收");break;
                    case 8 : System.out.println("rightShoulderAngle is small => ");break;
                    case 9 : System.out.println("leftShoulderAngle is small => ");break;
                    case 10 : System.out.println("Body isn't vertical => 身體保持垂直");break;
                }
            }
            if(output[i]==2){
                switch(i){
                    case 0 : System.out.println("rightHipAngle is big => 右身不夠下去");break;
                    case 1 : System.out.println("leftHipAngle is big => 左身不夠下去");break;
                    case 2 : System.out.println("rightKneeAngle is big => 右膝不夠低");break;
                    case 3 : System.out.println("leftKneeAngle is big => 左膝不夠低");break;
                    case 4 : System.out.println("rightElbowAngle is big => ");break;
                    case 5 : System.out.println("leftElbowAngle is big => ");break;
                    case 6 : System.out.println("rightArmpitAngle is big => 右腋下太開");break;
                    case 7 : System.out.println("leftArmpitAngle is big => 左腋下太開");break;
                    case 8 : System.out.println("rightShoulderAngle is big => ");break;
                    case 9 : System.out.println("leftShoulderAngle is big => ");break;
                }
            }
            if(output[i]==3){
                switch(i){
                    case 0 : System.out.println("rightHipAngle isn't horizontal => ");break;
                    case 1 : System.out.println("leftHipAngle isn't horizontal => ");break;
                    case 2 : System.out.println("rightKneeAngle isn't horizontal => 右膝伸直");break;
                    case 3 : System.out.println("leftKneeAngle isn't horizontal => 左膝伸直");break;
                    case 4 :
                        System.out.println("rightElbowAngle isn't horizontal => 右臂伸直");break;
                    case 5 :
                        System.out.println("leftElbowAngle isn't horizontal => 左臂伸直");break;
                    case 6 :
                        System.out.println("rightArmpitAngle isn't horizontal => 右手不夠高");break;
                    case 7 :
                        System.out.println("leftArmpitAngle isn't horizontal => 左手不夠高");break;
                    case 8 :
                        System.out.println("rightShoulderAngle isn't horizontal => 右肩伸直");break;
                    case 9 :
                        System.out.println("lefttShoulderAngle isn't horizontal => 左肩伸直");break;
                }
            }
        }
    }


}