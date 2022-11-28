package com.example.posebymlkit.posedetector;

import com.example.posebymlkit.R;
import com.google.mlkit.vision.pose.Pose;
import com.google.mlkit.vision.pose.PoseLandmark;
import java.util.List;

import static java.lang.Math.atan2;

import android.os.Bundle;

/** Draw the detected pose in preview. */
public class PoseCalculate{

    private final Pose pose;
    private final String cardView;
    private final int userLevel;
    static int level;
    static Boolean getPose;
    static int[] output = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
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

    PoseCalculate(
            Pose pose,
            String cardView,
            int userLevel) {
        this.pose = pose;
        this.cardView = cardView;
        this.userLevel = userLevel;
        level = userLevel;
        angle();
        if (getPose){
            switch (cardView){
                case "Warrior2":
                    warrior2();
                    break;
                case "Plank":
                    plank();
                    break;
                case "Goddess":
                    goddess();
                    break;
                case "Chair":
                    chair();
                    break;
                case "DownDog":
                    downdog();
                    break;
                case "Four-limbed_Staff":
                    four_limbed_staff();
                    break;
                case "Boat":
                    boat();
                    break;
                case "Rejuvenation":
                    rejuvenation();
                    break;
                case "Star":
                    star();
                    break;
                case "Tree":
                    tree();
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
    }
    static void plank(){
        // standard 1 : rightElbowAngle==180°
        if(rightKneeAngle < (180-5*level)){
            output[4] = 3;
        }
        else{
            output[4] = 0;
        }
        // standard 2 : leftElbowAngle==180°
        if(leftKneeAngle < (180-5*level)){
            output[5] = 3;
        }
        else{
            output[5] = 0;
        }
        // standard 3 : rightKneeAngle==180° and leftKneeAngle==180°
        if((rightKneeAngle < (180-5*level))&&(leftKneeAngle < (180-5*level))){
            output[2] = 3;
            output[3] = 3;
        }
        else if(rightKneeAngle < (180-5*level)){
            output[2] = 3;
            output[3] = 0;
        }
        else if(leftKneeAngle < (180-5*level)){
            output[2] = 0;
            output[3] = 3;
        }
        else{
            output[2] = 0;
            output[3] = 0;
        }
        // standard 4 : whether body vertical is
        if(bodyVertical){
            output[10] = 0;
        }
        else {
            output[10] = 1;
        }
    }
    static void goddess(){
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
        // standard 2 : leftKneeAngle==90°
        if(leftKneeAngle > (90+5*level)){
            output[3] = 2;
        }
        else if(leftKneeAngle < (90-5*level)){
            output[3] = 1;
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
        // standard 5 : rightElbowAngle==90°
        if(rightElbowAngle > (90+5*level)){
            output[4] = 2;
        }
        else if(rightElbowAngle < (90-5*level)){
            output[4] = 1;
        }
        else{
            output[4] = 0;
        }
        // standard 6 : leftElbowAngle==90°
        if(leftElbowAngle > (90+5*level)){
            output[5] = 2;
        }
        else if(leftElbowAngle < (90-5*level)){
            output[5] = 1;
        }
        else{
            output[5] = 0;
        }
        // standard 7 : whether body vertical is
        if(bodyVertical){
            output[10] = 0;
        }
        else {
            output[10] = 1;
        }
    }
    static void chair(){
        // standard 1 : rightElbowAngle==90° and leftElbowAngle==90°
        if((rightElbowAngle > (90+5*level))&&(leftElbowAngle > (90+5*level))){
            output[4] = 2;
            output[5] = 2;
        }
        else if((rightElbowAngle < (90-5*level))&&(leftElbowAngle < (90-5*level))){
            output[4] = 1;
            output[5] = 1;
        }
        else if((rightElbowAngle > (90+5*level))&&(leftElbowAngle < (90-5*level))){
            output[4] = 2;
            output[5] = 1;
        }
        else if((rightElbowAngle < (90-5*level))&&(leftElbowAngle > (90+5*level))){
            output[4] = 1;
            output[5] = 2;
        }
        else if(rightElbowAngle > (90+5*level)){
            output[4] = 2;
            output[5] = 0;
        }
        else if(rightElbowAngle < (90-5*level)){
            output[4] = 1;
            output[5] = 0;
        }
        else if(leftElbowAngle > (90+5*level)){
            output[4] = 0;
            output[5] = 2;
        }
        else if(leftElbowAngle < (90-5*level)){
            output[4] = 0;
            output[5] = 1;
        }
        else{
            output[4] = 0;
            output[5] = 0;
        }
        // standard 2 : rightArmpitAngle==90° and leftArmpitAngle==90°
        if((rightArmpitAngle > (90+5*level))&&(leftArmpitAngle > (90+5*level))){
            output[6] = 2;
            output[7] = 2;
        }
        else if((rightArmpitAngle < (90-5*level))&&(leftArmpitAngle < (90-5*level))){
            output[6] = 1;
            output[7] = 1;
        }
        else if((rightArmpitAngle > (90+5*level))&&(leftArmpitAngle < (90-5*level))){
            output[6] = 2;
            output[7] = 1;
        }
        else if((rightArmpitAngle < (90-5*level))&&(leftArmpitAngle > (90+5*level))){
            output[6] = 1;
            output[7] = 2;
        }
        else if(rightArmpitAngle > (90+5*level)){
            output[6] = 2;
            output[7] = 0;
        }
        else if(rightArmpitAngle < (90-5*level)){
            output[6] = 1;
            output[7] = 0;
        }
        else if(leftArmpitAngle > (90+5*level)){
            output[6] = 0;
            output[7] = 2;
        }
        else if(leftArmpitAngle < (90-5*level)){
            output[6] = 0;
            output[7] = 1;
        }
        else{
            output[6] = 0;
            output[7] = 0;
        }
        // standard 3 : rightKneeToeAngle==90° and leftKneeToeAngle==90°
        if((rightKneeToeAngle > (90+5*level))&&(leftKneeToeAngle > (90+5*level))){
            output[11] = 2;
            output[12] = 2;
        }
        else if((rightKneeToeAngle < (90-5*level))&&(leftKneeToeAngle < (90-5*level))){
            output[11] = 1;
            output[12] = 1;
        }
        else if((rightKneeToeAngle > (90+5*level))&&(leftKneeToeAngle < (90-5*level))){
            output[11] = 2;
            output[12] = 1;
        }
        else if((rightKneeToeAngle < (90-5*level))&&(leftKneeToeAngle > (90+5*level))){
            output[11] = 1;
            output[12] = 2;
        }
        else if(rightKneeToeAngle > (90+5*level)){
            output[11] = 2;
            output[12] = 0;
        }
        else if(rightKneeToeAngle < (90-5*level)){
            output[11] = 1;
            output[12] = 0;
        }
        else if(leftKneeToeAngle > (90+5*level)){
            output[11] = 0;
            output[12] = 2;
        }
        else if(leftKneeToeAngle < (90-5*level)){
            output[11] = 0;
            output[12] = 1;
        }
        else{
            output[11] = 0;
            output[12] = 0;
        }
    }
    static void downdog(){
        // standard 1 : rightElbowAngle==180° and leftElbowAngle==180°
        if((rightElbowAngle < (180-5*level))&&(leftElbowAngle < (180-5*level))){
            output[4] = 3;
            output[5] = 3;
        }
        else if(rightElbowAngle < (180-5*level)){
            output[4] = 3;
            output[5] = 0;
        }
        else if(leftElbowAngle < (180-5*level)){
            output[4] = 0;
            output[5] = 3;
        }
        else{
            output[4] = 0;
            output[5] = 0;
        }
        // standard 2 : rightKneeAngle==180° and leftKneeAngle==180°
        if((rightKneeAngle < (180-5*level))&&(leftKneeAngle < (180-5*level))){
            output[2] = 3;
            output[3] = 3;
        }
        else if(rightKneeAngle < (180-5*level)){
            output[2] = 3;
            output[3] = 0;
        }
        else if(leftKneeAngle < (180-5*level)){
            output[2] = 0;
            output[3] = 3;
        }
        else{
            output[2] = 0;
            output[3] = 0;
        }
        // standard 3 :
        // standard 4 :

    }
    static void four_limbed_staff(){
        // standard 1 : rightKneeAngle==180° and leftKneeAngle==180°
        if((rightKneeAngle < (180-5*level))&&(leftKneeAngle < (180-5*level))){
            output[2] = 3;
            output[3] = 3;
        }
        else if(rightKneeAngle < (180-5*level)){
            output[2] = 3;
            output[3] = 0;
        }
        else if(leftKneeAngle < (180-5*level)){
            output[2] = 0;
            output[3] = 3;
        }
        else{
            output[2] = 0;
            output[3] = 0;
        }
        // standard 2 : rightElbowAngle==90° and leftElbowAngle==90°
        if((rightElbowAngle > (90+5*level))&&(leftElbowAngle > (90+5*level))){
            output[4] = 2;
            output[5] = 2;
        }
        else if((rightElbowAngle < (90-5*level))&&(leftElbowAngle < (90-5*level))){
            output[4] = 1;
            output[5] = 1;
        }
        else if((rightElbowAngle > (90+5*level))&&(leftElbowAngle < (90-5*level))){
            output[4] = 2;
            output[5] = 1;
        }
        else if((rightElbowAngle < (90-5*level))&&(leftElbowAngle > (90+5*level))){
            output[4] = 1;
            output[5] = 2;
        }
        else if(rightElbowAngle > (90+5*level)){
            output[4] = 2;
            output[5] = 0;
        }
        else if(rightElbowAngle < (90-5*level)){
            output[4] = 1;
            output[5] = 0;
        }
        else if(leftElbowAngle > (90+5*level)){
            output[4] = 0;
            output[5] = 2;
        }
        else if(leftElbowAngle < (90-5*level)){
            output[4] = 0;
            output[5] = 1;
        }
        else{
            output[4] = 0;
            output[5] = 0;
        }
    }
    static void boat(){
        // standard 1 : rightKneeAngle==180° and leftKneeAngle==180°
        if((rightKneeAngle < (180-5*level))&&(leftKneeAngle < (180-5*level))){
            output[2] = 3;
            output[3] = 3;
        }
        else if(rightKneeAngle < (180-5*level)){
            output[2] = 3;
            output[3] = 0;
        }
        else if(leftKneeAngle < (180-5*level)){
            output[2] = 0;
            output[3] = 3;
        }
        else{
            output[2] = 0;
            output[3] = 0;
        }
        // standard 2 : rightElbowAngle==180° and leftElbowAngle==180°
        if((rightElbowAngle < (180-5*level))&&(leftElbowAngle < (180-5*level))){
            output[4] = 3;
            output[5] = 3;
        }
        else if(rightElbowAngle < (180-5*level)){
            output[4] = 3;
            output[5] = 0;
        }
        else if(leftElbowAngle < (180-5*level)){
            output[4] = 0;
            output[5] = 3;
        }
        else{
            output[4] = 0;
            output[5] = 0;
        }
        // standard 3 :
        // standard 4 :
    }
    static void rejuvenation(){
        // standard 1 : rightKneeAngle==180° and leftKneeAngle==180°
        if((rightKneeAngle < (180-5*level))&&(leftKneeAngle < (180-5*level))){
            output[2] = 3;
            output[3] = 3;
        }
        else if(rightKneeAngle < (180-5*level)){
            output[2] = 3;
            output[3] = 0;
        }
        else if(leftKneeAngle < (180-5*level)){
            output[2] = 0;
            output[3] = 3;
        }
        else{
            output[2] = 0;
            output[3] = 0;
        }
        // standard 2 : rightHipAngle==90° and leftHipAngle==90°
        if((rightHipAngle > (90+5*level))&&(leftHipAngle > (90+5*level))){
            output[0] = 2;
            output[1] = 2;
        }
        else if((rightHipAngle < (90-5*level))&&(leftHipAngle < (90-5*level))){
            output[0] = 1;
            output[1] = 1;
        }
        else if((rightHipAngle > (90+5*level))&&(leftHipAngle < (90-5*level))){
            output[0] = 2;
            output[1] = 1;
        }
        else if((rightHipAngle < (90-5*level))&&(leftHipAngle > (90+5*level))){
            output[0] = 1;
            output[1] = 2;
        }
        else if(rightHipAngle > (90+5*level)){
            output[0] = 2;
            output[1] = 0;
        }
        else if(rightHipAngle < (90-5*level)){
            output[0] = 1;
            output[1] = 0;
        }
        else if(leftHipAngle > (90+5*level)){
            output[0] = 0;
            output[1] = 2;
        }
        else if(leftHipAngle < (90-5*level)){
            output[0] = 0;
            output[1] = 1;
        }
        else{
            output[0] = 0;
            output[1] = 0;
        }
    }
    static void star(){
        // standard 1 : rightElbowAngle==180°
        if(rightElbowAngle < (180-5*level)){
            output[4] = 3;
        }
        else{
            output[4] = 0;
        }
        // standard 2 : leftElbowAngle==180°
        if(leftElbowAngle < (180-5*level)){
            output[5] = 3;
        }
        else{
            output[5] = 0;
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
        // standard 5 : rightKneeAngle==180°
        if(rightKneeAngle < (180-5*level)){
            output[2] = 3;
        }
        else{
            output[2] = 0;
        }
        // standard 6 : leftKneeAngle==180°
        if(leftKneeAngle > (180-5*level)){
            output[3] = 3;
        }
        else{
            output[3] = 0;
        }

        // standard 9 : whether body vertical is
        if(bodyVertical){
            output[10] = 0;
        }
        else {
            output[10] = 1;
        }
    }
    static void tree(){
        // standard 1 : rightKneeAngle==180°
        if(rightKneeAngle > (180-5*level)){
            output[2] = 3;
        }
        else{
            output[2] = 0;
        }
        // standard 2 : whether body vertical is
        if(bodyVertical){
            output[10] = 0;
        }
        else {
            output[10] = 1;
        }
    }

    public int[] wrong(){
        return output;
    }

}