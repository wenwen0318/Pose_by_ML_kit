package com.example.posebymlkit.posedetector;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;

import com.example.posebymlkit.database.PoseStandardDBHandler;
import com.example.posebymlkit.posedetector.classification.PoseClassifierProcessor;
import com.google.android.gms.tasks.Task;
import com.google.android.odml.image.MlImage;
import com.google.mlkit.vision.common.InputImage;
import com.example.posebymlkit.GraphicOverlay;
import com.example.posebymlkit.VisionProcessorBase;
import com.google.mlkit.vision.pose.Pose;
import com.google.mlkit.vision.pose.PoseDetection;
import com.google.mlkit.vision.pose.PoseDetector;
import com.google.mlkit.vision.pose.PoseDetectorOptionsBase;

import java.util.ArrayList;

/** A processor to run pose detector. */
public class PoseDetectorProcessor
        extends VisionProcessorBase<PoseDetectorProcessor.GetPose> {
    private static final String TAG = "PoseDetectorProcessor";

    private final PoseDetector detector;

    private final boolean showInFrameLikelihood;
    private final boolean visualizeZ;
    private final boolean rescaleZForVisualization;
    private final boolean runClassification;
    private final boolean isStreamMode;
    private final Context context;
    private final String cardView;
    private final int userLevel;
//    private final Executor classificationExecutor;

    private PoseClassifierProcessor poseClassifierProcessor;

    int[] angleStatus;
    float frameNum = 0;
    int[][] wrongFre =  {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0},
                            {0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0},
                            {0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0},
                            {0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0},
                            {0, 0, 0}, {0, 0, 0}, {0, 0}};
    float[] wrongSum = {0, 0, 0, 0, 0,  0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0,  0, 0, 0, 0, 0,
                        0, 0, 0};
    String classificationResult ;
    Boolean isCorrectPose;
    static ArrayList<String> poseStandard;

    /** Internal class to hold Pose and classification results. */

    protected static class GetPose {
        private final Pose pose;
        private final String classificationResult;

        public GetPose(Pose pose, String classificationResult) {
            this.pose = pose;
            this.classificationResult = classificationResult;
        }

        public Pose getPose() {
            return pose;
        }

        public String getClassificationResult(){
            return classificationResult;
        }
    }

    public PoseDetectorProcessor(
            Context context,
            PoseDetectorOptionsBase options,
            boolean showInFrameLikelihood,
            boolean visualizeZ,
            boolean rescaleZForVisualization,
            boolean runClassification,
            boolean isStreamMode,
            String cardView,
            int userLevel) {
        super(context);
        this.showInFrameLikelihood = showInFrameLikelihood;
        this.visualizeZ = visualizeZ;
        this.rescaleZForVisualization = rescaleZForVisualization;
        detector = PoseDetection.getClient(options);
        this.runClassification = runClassification;
        this.isStreamMode = isStreamMode;
        this.context = context;
        this.cardView = cardView;
        this.userLevel = userLevel;
//        classificationExecutor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void stop() {
        super.stop();
        detector.close();
    }

    protected Task<GetPose> detectInImage(InputImage image) {
        return detector
                .process(image)
                .continueWith(
                        task -> {
                            Pose pose = task.getResult();
                            String classificationResult = "";
                            if (runClassification) {
                                if (poseClassifierProcessor == null) {
                                    poseClassifierProcessor = new PoseClassifierProcessor(context, isStreamMode);
                                }
                                classificationResult = poseClassifierProcessor.getPoseResult(pose);
                            }
                            return new GetPose(pose, classificationResult);
                        });
    }

    protected Task<GetPose> detectInImage(MlImage image) {
        return detector
                .process(image)
                .continueWith(
//                        classificationExecutor,
                        task -> {
                            Pose pose = task.getResult();
                            String classificationResult = "";
                            if (runClassification) {
                                System.out.println("hasInRunClassification");
                                if (poseClassifierProcessor == null) {
                                    poseClassifierProcessor = new PoseClassifierProcessor(context, isStreamMode);
                                }
                                classificationResult = poseClassifierProcessor.getPoseResult(pose);
                            }
                            return new GetPose(pose, classificationResult);
                        });
    }

    @Override
    protected void onSuccess(
            @NonNull GetPose getPose,
            @NonNull GraphicOverlay graphicOverlay) {
        frameNum += 1;
        graphicOverlay.add(
                new PoseGraphic(
                        graphicOverlay,
                        getPose.getPose(),
                        showInFrameLikelihood,
                        visualizeZ,
                        rescaleZForVisualization));
        PoseCalculate Calculate = new PoseCalculate(
                context,
                getPose.getPose(),
                cardView,
                userLevel);
        angleStatus = Calculate.getAngleStatus();
        calWrongSum();
        calWrongTem();
    }

    @Override
    protected void onFailure(@NonNull Exception e) {
        Log.e(TAG, "Pose detection failed!", e);
    }

    @Override
    protected boolean isMlImageEnabled(Context context) {
        return true;
    }

    public void calWrongSum(){
        for(int i=0;i<angleStatus.length;i++){
            if(angleStatus[i] != 0){
                wrongSum[i] += 1;
            }
        }
    }

    public void calWrongTem(){
        for(int i=0;i<wrongFre.length;i++){
            for(int j=1;j<wrongFre[i].length;j++){
                if(angleStatus[i] == j){
                    wrongFre[i][j] += 1;
                }
            }
        }
    }

    public void clearWrongTem(){
        for(int i=0;i<wrongFre.length;i++){
            for(int j=0;j<wrongFre[i].length;j++){
                wrongFre[i][j] = 0;
            }
        }
    }

    public int[] getWrongForTTS(){
        int max = wrongFre[0][0];
        // wrongAngle wrongStatus;
        int[] wrongInf = new int[2];
        for(int i=0;i<wrongFre.length;i++){
            for(int j=0;j<wrongFre[i].length;j++){
                if(wrongFre[i][j] >= max){
                    max = wrongFre[i][j];
                    wrongInf[0] = i;// wrongAngle
                    wrongInf[1] = j;// wrongStatus
                }
            }
        }
        // when completeness == 100%
        if(wrongFre[wrongInf[0]][wrongInf[1]] == 0){
            wrongInf[0] = 0;// wrongAngle
            wrongInf[1] = 0;// wrongStatus
        }
        return wrongInf;
    }

    public float getOverallCompleteness(){
        float allWrong = 0;
        float standardNum = 0;
        switch (cardView){
            case "Warrior2": standardNum = 9;break;
            case "Plank": standardNum = 4;break;
            case "Goddess": standardNum = 11;break;
            case "Chair": standardNum = 5;break;
            case "DownDog": standardNum = 5;break;
            case "Four_Limbed_Staff": standardNum = 3;break;
            case "Boat": standardNum = 4;break;
            case "Rejuvenation": standardNum = 2;break;
            case "Star": standardNum = 7;break;
            case "Tree": standardNum = 7;break;
        }
        for(float num : wrongSum){
            allWrong += num;
        }
        System.out.println("allWrong :　"+allWrong);
        System.out.println("myFrame : "+frameNum);
        float unCompleteness = allWrong/(frameNum*standardNum);
        float completeness = 100 - unCompleteness*100;
        completeness = (float)(Math.round(completeness*100.0)/100.0);
        return completeness;
    }

    public String[] getJointsCompleteness(){
        float[] unCompleteness = new float[23];
        float[] completeness = new float[23];
        String[] jointCompleteness = new String[23];
        PoseStandardDBHandler db = new PoseStandardDBHandler(context);
        poseStandard = db.getPoseStandard(cardView).getPoseStandard();
        for(int i=0;i<wrongSum.length;i++){
            unCompleteness[i] = (wrongSum[i]/frameNum);
            completeness[i] = 100 - unCompleteness[i]*100;
            completeness[i] = (float)(Math.round(completeness[i]*100.0)/100.0);
            if(poseStandard.get(i) == null || poseStandard.get(i).length() == 0){
                jointCompleteness[i] = null;
            }
            else{
                jointCompleteness[i] = Float.toString(completeness[i]);
                jointCompleteness[i] = jointCompleteness[i]+"%";
            }
        }
        return jointCompleteness;
    }
}